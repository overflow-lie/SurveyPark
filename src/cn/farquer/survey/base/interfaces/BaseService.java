package cn.farquer.survey.base.interfaces;

import java.util.List;

/**
 * 所有Service的父类接口，声明了一些常用的方法
 * 
 * @author farquer
 * 
 *         2016年1月27日下午6:32:32
 */
public interface BaseService<T> {

	/**
	 * 保存或更新实体对象
	 * 
	 * @param t
	 */
	public void saveOrUpdateEntity(T t);

	/**
	 * 存储实体对象
	 * 
	 * @param t
	 */
	public void saveEntity(T t);

	/**
	 * 删除实体对象
	 * 
	 * @param t
	 */
	public void deleteEntity(T t);

	/**
	 * 修改实体对象
	 * 
	 * @param t
	 */
	public void updateEntity(T t);

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param entityId
	 * @return
	 */
	public T getEntityById(Integer entityId);

	/**
	 * 获取所有实体对象的集合
	 * 
	 * @return
	 */
	public List<T> getEntityList();


}
