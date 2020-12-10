package com.revature.menus;

import com.revature.repositories.MenuDAO;

public class EmployeeMenu extends Menu {

	public EmployeeMenu(MenuDAO menu) {
		super(menu, menu.findEmployee());
	}

	@Override
	public String display() {
		String display = "\n##########  Employee  PAGE  ##########\n";
		display += super.display();
		return display;
	}
}
