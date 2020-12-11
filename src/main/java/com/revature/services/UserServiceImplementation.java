package com.revature.services;

import com.revature.launcher.BankLauncher;
import com.revature.models.User;
import com.revature.repositories.UserDAOImpl;

public class UserServiceImplementation implements UserService {

	private UserDAOImpl userd;

	public UserServiceImplementation(UserDAOImpl userd) {
		this.userd = userd;
	}

	@Override
	public User login(String username, String password) {
		User user = this.userd.findUserByName(username);
		if (user.getPassword().equals(password))
			return user;
		return null;
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
