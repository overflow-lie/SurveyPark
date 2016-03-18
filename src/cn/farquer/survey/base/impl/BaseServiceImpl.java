package cn.farquer.survey.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.farquer.survey.base.interfaces.BaseDao;
import cn.farquer.survey.base.interfaces.BaseService;

/**
 * 最基本的业务逻辑层的父类，封装了直接调用基础DAO的代码
 * 
 * @author farquer
 * 
 *         2016年1月27日下午6:33:24
 */

@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}
	
	@Override
	public T getEntityById(Integer entityId) {
		return baseDao.getEntityById(entityId);
	}

	@Override
	public List<T> getEntityList() {
		return baseDao.getEntityList();
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

}
