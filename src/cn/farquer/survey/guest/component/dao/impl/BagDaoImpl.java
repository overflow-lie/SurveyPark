package cn.farquer.survey.guest.component.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.farquer.survey.base.impl.BaseDaoImpl;
import cn.farquer.survey.guest.component.dao.interfaces.BagDao;
import cn.farquer.survey.guest.entity.Bag;

/**
 * 包裹的DAO层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:58:42
 */
@Repository
public class BagDaoImpl extends BaseDaoImpl<Bag> implements BagDao {

	@Override
	public void batchUpdateBagOrder(List<Bag> bagList) {

		// 准备SQL语句更新bagOrder
		String sql = "UPDATE t_bag SET bag_order=? WHERE bag_id=?";

		// 将bagList转换成List<Object[]> params
		List<Object[]> list = new ArrayList<>();

		for (int i = 0; i < bagList.size(); i++) {
			Bag bag = bagList.get(i);

			// 因为SQL中只有两个参数，而且我们只需要bagId和bagOrder，所以创建数组长度为2
			Object[] arr = new Object[2];

			// 需要注意sql语句中参数的位置与插入的数据一一匹配
			arr[0] = bag.getBagOrder();
			arr[1] = bag.getBagId();

			list.add(arr);
		}
		// 3.执行批量更新
		this.doBatchWork(sql, list);
	}

	@Override
	public void moveToThisSurvey(Integer bagId, Integer surveyId) {

		// 本质：将Bag的SurveyId外键修改为目标调查的OID值
		String hql = "update Bag b set b.survey.surveyId=? where b.bagId=?";
		this.getSession().createQuery(hql).setInteger(0, surveyId)
				.setInteger(1, bagId).executeUpdate();

	}

	@Override
	public Bag getFirstBag(Integer surveyId) {

		String hql = "From Bag b left join fetch b.questions Where b.survey.surveyId=? order by b.bagOrder asc";

		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId)
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public Bag getPrevBag(Integer surveyId, Integer bagId) {

		// 要查询的目标包裹：bagOrder<bagId对应的包裹的bagOrder

		String hql = "From Bag b left join fetch b.questions where b.survey.surveyId=? and "
				+ "b.bagOrder<(select innerBag.bagOrder from Bag innerBag where innerBag.bagId=?) order by b.bagOrder desc";

		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId)
				.setInteger(1, bagId).setMaxResults(1).uniqueResult();
	}

	@Override
	public Bag getNextBag(Integer surveyId, Integer bagId) {
		String hql = "From Bag b left join fetch b.questions where b.survey.surveyId=? and "
				+ "b.bagOrder>(select innerBag.bagOrder from Bag innerBag where innerBag.bagId=?) order by b.bagOrder asc";

		return (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId)
				.setInteger(1, bagId).setMaxResults(1).uniqueResult();
	}
}
