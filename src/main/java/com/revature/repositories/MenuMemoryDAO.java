package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.MenuOption;

public class MenuMemoryDAO implements MenuDAO {

	static List<MenuOption> mainOptions = new ArrayList<MenuOption>();

	static List<MenuOption> customerOptions = new ArrayList<MenuOption>();

	static {

		mainOptions.add(new MenuOption("login"));
		mainOptions.add(new MenuOption("register"));

		customerOptions.add(new MenuOption("creat new"));
		customerOptions.add(new MenuOption("view"));
		customerOptions.add(new MenuOption("deposite"));

	}

	@Override
	public List<MenuOption> findMain() {
		// TODO Auto-generated method stub
		return mainOptions;
	}

	@Override
	public List<MenuOption> findCustomer() {
		// TODO Auto-generated method stub
		return customerOptions;
	}

}
