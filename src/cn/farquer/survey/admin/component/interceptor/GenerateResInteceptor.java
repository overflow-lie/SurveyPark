package cn.farquer.survey.admin.component.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.farquer.survey.admin.component.service.interfaces.ResourceService;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.ValidateUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 捕获每一个Struts2请求的ActionName，据此创建Resource对象，并保存到数据库
 * @author Creathin
 *
 */
public class GenerateResInteceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionProxy proxy = invocation.getProxy();
		String actionName = proxy.getActionName();
		
		if(!ValidateUtils.stringValidate(actionName)) return invocation.invoke();
		
		String resourceName = DataProcessUtils.translateActionName(actionName);
		
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		ResourceService resourceService = ioc.getBean(ResourceService.class);
		
		boolean exists = resourceService.getResourceByActionName(actionName);
		if(exists) return invocation.invoke();
		
		//计算允许的最大值
		long allowedMaxCode = 1L << 60;
		
		Integer resPos = null;
		
		Long resCode = null;
		
		//一、计算权限位
		//1.读取当前权限位的最大值：maxResPos
		Integer maxResPos = resourceService.getMaxResPos();
		
		//2.读取当前权限位中的最大权限码：maxResCode
		Long maxResCode = resourceService.getCurrentMaxResCode(maxResPos);
		
		//3.判断maxResPos是否为null
		if(maxResPos == null) {
			//4.如果为nullresPos设置为0，resCode设置为1
			resPos = 0;
		}else{
			//5.如果不为null
			if(maxResCode == allowedMaxCode) {
				//①maxResCode已经到最大值，不能再<<1位，此时权限位+1
				maxResPos++;
			}
			//②maxResCode还没有到最大值，还可以继续<<1位，此时权限位保持不变
			resPos = maxResPos;
		}
		
		//二、计算权限码
		//1.判断当前最大权限码是否已经达到允许的最大值
		if(maxResCode == null) {
			resCode = 1L;
		}else if(maxResCode == allowedMaxCode) {
			//①是：权限码设置为1
			resCode = 1L;
		}else{
			//②否：<<1
			resCode = maxResCode << 1;
		}
		
		Resource resource = new Resource(actionName, resourceName, resPos, resCode);
		resourceService.saveEntity(resource);
		
		return invocation.invoke();
	}

}
