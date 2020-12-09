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

		customerOptions.add(new MenuOption("apply new account"));
		customerOptions.add(new MenuOption("view balance"));
		customerOptions.add(new MenuOption("withdrawal"));
		customerOptions.add(new MenuOption("deposite"));
		customerOptions.add(new MenuOption("transfer money"));
		customerOptions.add(new MenuOption("accept money"));
		customerOptions.add(new MenuOption("go back"));

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
