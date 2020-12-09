package com.revature.menus;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Displayable;

public class JDBCMenu<T> implements Displayable {

	List<T> lines;
	Scanner userIn;

	public JDBCMenu() {
		userIn = new Scanner(System.in);
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		return null;
	}

	public T getUserOption() {
		String input = this.userIn.nextLine();// read in the entire line of text
		// this should be a number, but not necessarily
		try {
			int choice = Integer.parseInt(input) - 1;// for readability

			if (choice < 0 || choice >= lines.size()) {
				System.out.println("Make a valid choice please!\n");
				return null;
			}

			return lines.get(choice);
		} catch (NumberFormatException e) {
			System.out.println("Make A valid choice please!\n");
		}
		return null;

	}

}
