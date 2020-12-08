package com.revature.services;

import com.revature.launcher.BankLauncher;
import com.revature.models.User;

public class UserServiceImplementation implements UserService {

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User register(String username, String password) {
		// TODO Auto-generated method stub

		BankLauncher.logger.debug("A new user try to register.");
		System.out.println("Try to register!");
		System.out.println(username);
		System.out.println(password);
		return null;
	}

}
