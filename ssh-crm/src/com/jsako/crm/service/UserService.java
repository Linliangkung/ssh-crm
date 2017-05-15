package com.jsako.crm.service;

import com.jsako.crm.domain.User;

public interface UserService {

	User getUserByCodeAndPassword(User user);
	
	void saveUser(User user);
}
