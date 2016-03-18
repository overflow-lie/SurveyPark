package cn.farquer.survey.guest.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.farquer.survey.guest.entity.Question;
import cn.farquer.survey.utils.GlobalNames;
import cn.farquer.survey.utils.HTMLTagUtils;
import cn.farquer.survey.utils.ValidateUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 用于在参与调查页面显示问题详情的自定义标签处理器类
 * <farquer:generateQuestion currentBagIdOGNL="xxx"/>
 * 
 * @author farquer
 *
 * 		2016年2月23日下午7:56:00
 */
public class QuestionTag extends SimpleTagSupport{
	
	private String currentBagIdOGNL;
	
	public void setCurrentBagIdOGNL(String currentBagIdOGNL) {
		this.currentBagIdOGNL = currentBagIdOGNL;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doTag() throws JspException, IOException {
		
		//获取JSP页面上的上下文对象
		PageContext pageContext = (PageContext) getJspContext();
		
		//获取out对象
		JspWriter out = pageContext.getOut();
		
		//声明变量保存向页面上输出的字符串
		StringBuilder finalBuilder = new StringBuilder();
		
		//获取Session对象
		HttpSession session = pageContext.getSession();
		
		//获取allBagMap
		Map<Integer, Map<String,String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) session.getAttribute(GlobalNames.ALL_BAG_MAP);
		
		//获取当前包裹id
		Integer currentBagId = parseBagId(currentBagIdOGNL);
		
		//根据currentBagId获取ParametersMap
		Map<String, String[]> parameters = allBagMap.get(currentBagId);
		
		//获取Question对象
		Question question = getQuestion();
		
		//获取Question的id值
		Integer questionId = question.getQuestionId();
		
		//获取问题类型
		int questionType = question.getQuestionType();
		
		//如果是单选题
		if(questionType == 0) {
			
			String[] optionsArray = question.getOptionsArray();
			
			for (int i = 0; i < optionsArray.length; i++) {
				//准备生成标签所需要的数据
				String idStr = "radio"+questionId+i;
				String name = "question"+questionId;
				String value = ""+i;
				String label = optionsArray[i];
				
				//通过调用方法决定是否将当前选项设置为被选中
				boolean redisplay = checkedRedisplay(parameters, name, value);
				
				String checkedStr = redisplay?"checked='checked'":"";
				
				boolean isBr = question.isBr();
				
				//生成标签
				String radioStr = HTMLTagUtils.generateRadio(idStr, name, value, label, checkedStr, isBr);
				
				//添加到finalBuilder中
				finalBuilder.append(radioStr);
				
			}
			//是否有其他项
			if(question.isHasOther()) {
				
				//获取其他项类型
				int otherType = question.getOtherType();
				
				//和主题型一致
				if (otherType == 0) {
					String idStr = "other"+questionId;
					String name = "question"+questionId;
					String value = "other";
					
					boolean redisplay = checkedRedisplay(parameters, name, value);
					
					String checkedStr = redisplay?"checked='checked'":"";
					
					String otherRadio = HTMLTagUtils.generateRadio(idStr, name, value, "其它", checkedStr, false);
					finalBuilder.append(otherRadio);
				}
				
				//文本框
				if (otherType == 1) {
					
					String name = "question"+questionId+"Other";
					
					//根据是否回显，决定是空字符串还是用户以前填的数据
					String value = checkTextRedisplay(parameters, name);
					
					String otherText = HTMLTagUtils.generateText(name, value, true);
					finalBuilder.append(otherText);
				}
				
			}
			
		}
		
		//如果是多选题
		if(questionType == 1) {
			
			String[] optionsArray = question.getOptionsArray();
			
			for (int i = 0; i < optionsArray.length; i++) {
				//准备生成标签所需要的数据
				String idStr = "checkbox"+questionId+i;
				String name = "question"+questionId;
				String value = ""+i;
				String label = optionsArray[i];
				
				//通过调用方法决定是否将当前选项设置为被选中
				boolean redisplay = checkedRedisplay(parameters, name, value);
				
				String checkedStr = redisplay?"checked='checked'":"";
				
				boolean isBr = question.isBr();
				
				//生成标签
				String checkboxStr = HTMLTagUtils.generateCheckbox(idStr, name, value, label, checkedStr, isBr);
				
				//添加到finalBuilder中
				finalBuilder.append(checkboxStr);
				
			}
			//是否有其他项
			if(question.isHasOther()) {
				
				//获取其他项类型
				int otherType = question.getOtherType();
				
				//和主题型一致
				if (otherType == 0) {
					String idStr = "other"+questionId;
					String name = "question"+questionId;
					String value = "other";
					
					boolean redisplay = checkedRedisplay(parameters, name, value);
					
					String checkedStr = redisplay?"checked='checked'":"";
					
					String otherCheckbox = HTMLTagUtils.generateCheckbox(idStr, name, value, "其它", checkedStr, false);
					finalBuilder.append(otherCheckbox);
				}
				
				//文本框
				if (otherType == 1) {
					
					String name = "question"+questionId+"Other";
					
					//根据是否回显，决定是空字符串还是用户以前填的数据
					String value = checkTextRedisplay(parameters, name);
					
					String otherText = HTMLTagUtils.generateText(name, value, true);
					finalBuilder.append(otherText);
				}
				
			}
			
		}
		//如果是下拉列表选择题
		if(questionType == 2) {
			
			String name = "question"+questionId;
			
			StringBuilder optionBuilder = new StringBuilder();
			
			String[] optionsArray = question.getOptionsArray();
			for (int i = 0; i < optionsArray.length; i++) {
				
				String value = ""+i;
				
				boolean redisplay = checkedRedisplay(parameters, name, value);
				
				String selected = redisplay?"selected='selected'":"";
				String label = optionsArray[i];
				
				String option = HTMLTagUtils.generateOption(value, selected, label);
				
				optionBuilder.append(option);
			}
			
			String selectStr = HTMLTagUtils.generateSelect(name, optionBuilder.toString());
			
			finalBuilder.append(selectStr);
		}
		
		//如果是简答题
		if(questionType == 3) {
			
			String name = "question"+questionId;
			String value = checkTextRedisplay(parameters, name);
			
			String text = HTMLTagUtils.generateText(name, value, false);
			finalBuilder.append(text);
			
		}
		
		//如果是矩阵式问题
		if(questionType == 4 || questionType == 5 || questionType == 6) {
			
			//生成表格的第一行
			//<tr><td>&nbsp;</td><td>列标题</td><td>列标题</td><td>列标题</td><td>列标题</td></tr>
			StringBuilder tdBuilder = new StringBuilder();
			
			//创建第一行的第一个单元格
			String tdStr = HTMLTagUtils.generateTd("&nbsp;");
			tdBuilder.append(tdStr);
			
			//创建第一行的其他单元格
			String[] colTitlesArray = question.getMatrixColTitlesArray();
			for (int i = 0; i < colTitlesArray.length; i++) {
				String colTitles = colTitlesArray[i];
				tdStr = HTMLTagUtils.generateTd(colTitles);
				tdBuilder.append(tdStr);
			}
			
			//第一行所有td
			String allTdStr = tdBuilder.toString();
			String trStr = HTMLTagUtils.generateTr(allTdStr);
			StringBuilder trBuilder = new StringBuilder();
			trBuilder.append(trStr);
			
			//每创建一行，都需要将tdBuilder清空
			tdBuilder.delete(0, tdBuilder.length());
			
			//生成表格的其他行：双重循环
			//第一重循环：控制每一行的生成
			//第二重循环：在每一行中控制每一个td的生成
			//<tr><td>行标题</td><td>CellContent</td><td>CellContent</td><td>CellContent</td></tr>
			//<tr><td>行标题</td><td>CellContent</td><td>CellContent</td><td>CellContent</td></tr>
			//...
			//<tr><td>行标题</td><td>CellContent</td><td>CellContent</td><td>CellContent</td></tr>
			String[] rowTitlesArray = question.getMatrixRowTitlesArray();
			for (int i = 0; i < rowTitlesArray.length; i++) {
				
				String rotTitle = rowTitlesArray[i];
				tdStr = HTMLTagUtils.generateTd(rotTitle);
				tdBuilder.append(tdStr);
				
				for(int j = 0; j < colTitlesArray.length; j++) {
					
					String cellContnet = null;
					
					//如果是矩阵单选题
					if(questionType == 4) {
						
						String idStr = "matrix"+questionId+i+j;
						String label = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						
						//question23_2
						String name = "question"+questionId+"_"+i;
						
						//0_0,0_1,...
						String value = i+"_"+j;
						
						boolean redisplay = checkedRedisplay(parameters, name, value);
						
						String checkedStr = redisplay?"checked='checked'":"";
						
						cellContnet = HTMLTagUtils.generateRadio(idStr, name, value, label, checkedStr, false);
						
					}
					
					//如果是矩阵多选题
					if(questionType == 5) {
						
						String idStr = "matrix"+questionId+i+j;
						String label = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						
						//question23_2
						String name = "question"+questionId;
						
						//0_0,0_1,...
						String value = i+"_"+j;
						
						boolean redisplay = checkedRedisplay(parameters, name, value);
						
						String checkedStr = redisplay?"checked='checked'":"";
						
						cellContnet = HTMLTagUtils.generateCheckbox(idStr, name, value, label, checkedStr, false);
						
					}
					
					//如果是下拉列表选择题
					if(questionType == 6) {
						
						//question23_2
						String name = "question"+questionId;
						
						//0_0_0,0_0_1,...
						String[] optionArray = question.getMatrixOptionsArray();
						StringBuilder optionBuilder = new StringBuilder();
						for (int k = 0; k < optionArray.length; k++) {
							//<option value='xxx'>xxx</option>
							String value = i+"_"+j+"_"+k;
							String label = optionArray[k];
							
							boolean redisplay = checkedRedisplay(parameters, name, value);
							
							String selected = redisplay?"selected='selected'":"";
							
							String optionStr = HTMLTagUtils.generateOption(value, selected, label);
							optionBuilder.append(optionStr);
						}
					
						cellContnet = HTMLTagUtils.generateSelect(name, optionBuilder.toString());
						
					}
					
					tdStr = HTMLTagUtils.generateTd(cellContnet);
					
					tdBuilder.append(tdStr);
					
				}
				
				trStr = HTMLTagUtils.generateTr(tdBuilder.toString());
				trBuilder.append(trStr);
				tdBuilder.delete(0, tdBuilder.length());
				
			}
			
			String allTrStr = trBuilder.toString();
			String table = HTMLTagUtils.generateTable(allTrStr);
			finalBuilder.append(table);
			
		}
		
		
		//通过out对象向页面上输出数据
		out.print(finalBuilder.toString());
	}
	
	public String checkTextRedisplay(Map<String, String[]> parameters,
			String name) {
		
		if(!ValidateUtils.mapValidate(parameters)) return "";
		
		String[] paramValueArray = parameters.get(name);
		
		if(!ValidateUtils.arrayValidate(paramValueArray)) return "";
		
		return paramValueArray[0];
	}

	public Integer parseBagId(String currentBagIdOGNL) {
		
		return (Integer) ActionContext.getContext().getValueStack().findValue(currentBagIdOGNL);
	}
	
	public boolean checkedRedisplay(Map<String, String[]> parameters,String name, String currentValue) {
		
		if(!ValidateUtils.mapValidate(parameters)) return false;
		
		//从parameters中获取paramValue值
		//由于当前问题有可能是多选，所以paramValueArray可能是多个值
		String[] paramValueArray = parameters.get(name);
		
		if(!ValidateUtils.arrayValidate(paramValueArray)) return false;
		
		//使用paramValue和当前HTML标签的value值进行比较
		for (String paramValue : paramValueArray) {
			
			if(paramValue.equals(currentValue)) {
				return true;
			}
			
		}
		
		return false;
	}
	
	public Question getQuestion() {
		
		//1.获取值栈对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//2.通过值栈对象获取栈顶对象
		Object top = valueStack.getRoot().get(0);
		
		return (Question) top;
	}
	
}
