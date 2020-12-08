package com.revature.menus;

import com.revature.models.Displayable;
import com.revature.repositories.MenuDAO;

public class MainMenu extends Menu implements Displayable {

	public MainMenu(MenuDAO menu) {

		super(menu, menu.findMain());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String display() {
		String display = "##########  HOME PAGE  ##########\n";
		display += super.display();
		return display;
	}

}
