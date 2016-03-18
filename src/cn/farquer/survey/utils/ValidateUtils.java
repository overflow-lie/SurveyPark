package cn.farquer.survey.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.farquer.survey.guest.entity.Bag;

/**
 * 工具类，封装了对数据检查的静态方法
 * 
 * @author farquer
 * 
 *         2016年2月17日上午11:07:34
 */
public class ValidateUtils {

	/**
	 * 检查map对象是否为空，null和size为0都看作是空
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean mapValidate(Map map) {
		return map != null && map.size() > 0;
	}

	/**
	 * 检查集合对象是否为空，null和size为0都看做是空
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean collectionValidate(Collection c) {
		return c != null && c.size() > 0;
	}

	/**
	 * 检查字符串是否有效
	 * 
	 * @param logoPath
	 * @return
	 */
	public static boolean stringValidate(String logoPath) {
		return logoPath != null && logoPath.length() > 0;
	}

	/**
	 * 检查数组是否有效
	 * 
	 * @return
	 */
	public static boolean arrayValidate(Object[] arr) {
		return arr != null && arr.length > 0;
	}

	/**
	 * 检查提交的包裹序列是否有重复
	 * 
	 * 因为Set集合中无法存放重复的数据，所以把bagList中的数据拿出来放入set中然后比较set和list的size
	 * 如果相同则没有重复的，如果不同则有重复的数据
	 * 
	 * @param bagList
	 * @return
	 */
	public static boolean bagListValidate(List<Bag> bagList) {

		Set<Integer> orderSet = new HashSet<Integer>();

		for (Bag bag : bagList) {

			int bagOrder = bag.getBagOrder();

			orderSet.add(bagOrder);
		}

		return orderSet.size() == bagList.size();
	}

}
