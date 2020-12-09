package com.revature.services;

import com.revature.launcher.BankLauncher;
import com.revature.models.User;

public class UserServiceImplementation implements UserService {

	@Override
	public User login(String username, String password) {
		BankLauncher.logger.debug("A user try to login.");
		// connect to database, if find use return User, else return null
		User user = new User();
		user.setUserId(123);
		user.setAccountType("CUSTOMER");
		return user;
	}

	@Override
	public User register(String username, String password) {
		// TODO Auto-generated method stub

		BankLauncher.logger.debug("A new user try to register.");
		System.out.println(username);
		System.out.println(password);
		return null;
	}

}
