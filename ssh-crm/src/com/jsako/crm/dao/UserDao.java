package com.jsako.crm.dao;

import com.jsako.crm.domain.User;

public interface UserDao extends BaseDao<User>{
	
	User getUserByCode(String code);

	
}
