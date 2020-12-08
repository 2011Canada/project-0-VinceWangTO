package com.revature.repositories;

import java.util.List;

import com.revature.models.MenuOption;

public interface MenuDAO {

	public List<MenuOption> findMain();

	public List<MenuOption> findCustomer();

}
