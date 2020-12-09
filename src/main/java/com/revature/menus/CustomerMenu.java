package com.revature.menus;

import com.revature.repositories.MenuDAO;

public class CustomerMenu extends Menu {

	public CustomerMenu(MenuDAO menu) {
		super(menu, menu.findCustomer());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String display() {
		String display = "\n##########  Customer  PAGE  ##########\n";
		display += super.display();
		return display;
	}

}
