package cn.farquer.survey.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 二级缓存中自定义的key生成器
 * 
 * @author farquer
 * 
 *         2016年3月2日下午8:59:38
 */
public class SurveyKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {

		StringBuilder sb = new StringBuilder();
		sb.append(target.getClass().getName()).append(".");

		sb.append(method.getName()).append(".");

		if (params != null) {
			for (Object param : params) {
				sb.append(param).append(".");
			}
		}

		return sb.substring(0, sb.lastIndexOf("."));
	}

}
