package cn.farquer.survey.base.interfaces;

import java.util.List;

/**
 * 所有DAO的父类，封装了基础的方法
 * 
 * @author farquer
 * 
 *         2016年1月27日下午4:39:21
 */
public interface BaseDao<T> {

	/**
	 * 存储更新实体对象
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

	/**
	 * 批量处理SQL语句的方法，需要使用原生的JDBC支持（获取到connection）
	 */
	public void doBatchWork(String sql, List<Object[]> params);
	

}
