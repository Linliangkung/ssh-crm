package com.jsako.crm.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.crm.dao.UserDao;
import com.jsako.crm.domain.User;
import com.jsako.crm.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
	public User getUserByCodeAndPassword(User user) {

		User queryUser = userDao.getUserByCode(user.getUser_code());

		if (queryUser == null) {

			throw new RuntimeException("用户不存在");
		}

		if (!queryUser.getUser_password().equals(user.getUser_password())) {

			throw new RuntimeException("密码不正确");
		}

		return queryUser;
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUser(User user) {
		User existUser = userDao.getUserByCode(user.getUser_code());
		if(existUser!=null){
			throw new RuntimeException("用户已存在");
		}
		userDao.save(user);
	}

}
