package com.revature.menus;

import com.revature.models.Displayable;
import com.revature.repositories.MenuDAO;

public class CustomerMenu extends Menu implements Displayable {

	public CustomerMenu(MenuDAO menu) {
		super(menu, menu.findCustomer());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String display() {
		String display = "##########  Customer  PAGE  ##########\n";
		display += super.display();
		return display;
	}

}
