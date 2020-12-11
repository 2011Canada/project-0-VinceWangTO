package com.revature.repositories;

import com.revature.models.User;

public interface UserDAO {

	public User findUserByName(String userName);

	public boolean registerUser(User user);
}
