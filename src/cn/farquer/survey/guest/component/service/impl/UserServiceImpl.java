package cn.farquer.survey.guest.component.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.admin.component.dao.interfaces.ResourceDao;
import cn.farquer.survey.admin.component.dao.interfaces.RoleDao;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.guest.component.dao.interfaces.UserDao;
import cn.farquer.survey.guest.component.service.interfaces.UserService;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.DataProcessUtils;

/**
 * 用户的业务逻辑层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:39:05
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public boolean regist(User user) {

		// 1.检查用户名是否可用
		boolean exists = userDao.checkUserName(user.getUserName());

		if (!exists) {

			// 2.将密码加密后再保存
			String userPwd = user.getUserPwd();
			userPwd = DataProcessUtils.md5(userPwd);
			user.setUserPwd(userPwd);

			// 添加“普通登录用户”角色
			Role role = roleDao.getRoleByName("普通登录用户");
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user.setRoles(roles);

			// 计算权限码
			Integer maxResPos = resourceDao.getMaxResPos();
			String resCode = DataProcessUtils.calculatCodeByRoles(roles,
					maxResPos);
			user.setResCode(resCode);

			// 3.如果用户名可用，则将用户信息保存到数据库中
			userDao.saveEntity(user);

		}

		return exists;
	}

	@Override
	public User login(User t) {

		// 将密码进行加密，比较加密后的密码字符串
		String userPwd = t.getUserPwd();

		userPwd = DataProcessUtils.md5(userPwd);

		t.setUserPwd(userPwd);

		return userDao.login(t);
	}

	@Override
	public boolean vip(int vipDays, User t) {

		// 1.根据days计算金额
		int amount = 0;

		switch (vipDays) {
		case 30:
			amount = vipDays * 10;
			break;
		case 90:
			amount = vipDays * 8;

			break;
		case 180:
			amount = vipDays * 5;

			break;
		case 360:
			amount = vipDays * 3;

			break;

		default:
			break;
		}

		// 2.检查用户当前余额是否足够
		int currentBalance = t.getBalance();
		if (currentBalance < amount) {
			// 3.不够：返回false
			return false;
		} else {
			// 4.够
			// ①减用户余额
			t.setBalance(currentBalance - amount);

			// ②将用户当前的会员截止日期延长days天
			// 计算当前选择的天数对应的毫秒数
			// 为避免整数类型溢出，需要转换为long类型进行计算
			long extendsSeconds = ((long) vipDays) * 24 * 60 * 60 * 1000;

			// 声明一个currentEndTime变量作为追加VIP时间的基准
			long currentEndTime = 0;
			if (t.isPayStatus()) {
				// VIP：在当前截止日期基础上添加
				currentEndTime = t.getEndTime();

			} else {
				// 普通会员：在当前系统时间基础上追加
				currentEndTime = new Date().getTime();
			}
			// 追加
			currentEndTime = currentEndTime + extendsSeconds;

			// 设置
			t.setEndTime(currentEndTime);

			// ③将用户payStatus设置为true
			t.setPayStatus(true);

			// a different object .... 异常，此处需要考虑对象的几种形态，保存操作
			Role dbRole = roleDao.getRoleByName("付费登录用户");
			Set<Role> roles = t.getRoles();
			
			for (Role role : roles) {
				if(role.getRoleName().equals("付费登录用户")){
					roles.remove(role);
				}
			}

			roles.add(dbRole);
			Integer maxResPos = resourceDao.getMaxResPos();
			String resCode = DataProcessUtils.calculatCodeByRoles(roles,
					maxResPos);
			t.setResCode(resCode);

			// 5.更新到数据库中
			updateEntity(t);

			return true;
		}

	}

	@Override
	public void deletePayRole(Integer userId, Integer roleId) {
		userDao.deletePayRole(userId, roleId);
	}

}
