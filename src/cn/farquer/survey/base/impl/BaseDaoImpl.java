package cn.farquer.survey.base.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;

import cn.farquer.survey.base.interfaces.BaseDao;
import cn.farquer.survey.utils.ValidateUtils;

/**
 * 所有DAO的父类，实现了接口中的方法，封装了最基本的DAO操作数据库代码
 * 
 * @author farquer
 * 
 *         2016年1月27日下午4:39:42
 */

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory; // 自动装配注入SessionFactory

	private Class<T> entityType; // 本类泛型

	// 通过反射获取子类泛型的class
	public BaseDaoImpl() {
		entityType = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// 共有的获取Session的方法
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveEntity(T t) {
		getSession().save(t);
	}

	@Override
	public void deleteEntity(T t) {
		getSession().delete(t);
	}

	@Override
	public void updateEntity(T t) {
		getSession().update(t);
	}

	@Override
	public T getEntityById(Integer entityId) {
		return (T) getSession().get(entityType, entityId);
	}

	@Override
	public List<T> getEntityList() {
		return getSession().createCriteria(entityType).list();
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public void doBatchWork(final String sql, final List<Object[]> params) {
		this.getSession().doWork(new Work() {
			/**
			 * 匿名内部类，创建work对象重写execute方法
			 */
			@Override
			public void execute(Connection connection) throws SQLException {

				// 从connection中获取prepareStatement
				PreparedStatement ps = connection.prepareStatement(sql);

				// 判断参数是否可用
				if (ValidateUtils.collectionValidate(params)) {

					for (int i = 0; i < params.size(); i++) {

						Object[] arr = params.get(i);

						if (!ValidateUtils.arrayValidate(arr))
							continue;

						for (int j = 0; j < arr.length; j++) {
							// 给每一条SQL语句设置占位符参数
							ps.setObject(j + 1, arr[j]);
						}
						// 积攒SQL语句
						ps.addBatch();
					}
					// 批量执行SQL语句
					ps.executeBatch();
				}
				ps.close();
			}
		});
	}

}
