package com.revature.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Displayable;
import com.revature.models.MenuOption;
import com.revature.repositories.MenuDAO;

public class Menu implements Displayable {

	List<MenuOption> lines;
	Scanner userIn;
	MenuDAO menu;

	public Menu() {
		userIn = new Scanner(System.in);
	}

	public Menu(MenuDAO menu, List<MenuOption> options) {
		this.menu = menu;
		this.lines = new ArrayList<MenuOption>(options);
		userIn = new Scanner(System.in);
	}

	@Override
	public String display() {
		String display = "";
		for (int i = 0; i < lines.size(); i++) {

			display += (i + 1) + ": " + lines.get(i).getName() + "\n";
		}
		display += "Please choose an option: ";

		return display;
	}

	public String getUserOption() {
		String input = this.userIn.nextLine();// read in the entire line of text
		// this should be a number, but not necessarily
		try {
			int choice = Integer.parseInt(input) - 1;// for readability
			if (choice < 0 || choice >= lines.size()) {
				System.out.println("Make a valid choice please!\n");
				return "";
			}
			return lines.get(choice).getName();
		} catch (Exception e) {
			System.out.println("Make A valid choice please!\n");
		}
		return "";

	}
}
