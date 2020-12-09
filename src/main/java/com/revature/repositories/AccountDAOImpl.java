package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Account> accounts = new ArrayList<Account>();

		// public Account(int accountId, double balance, int userId, boolean isActive) {
		accounts.add(new Account(10000, 20.2, 33, true));
		accounts.add(new Account(10040, 1120.2, 33, true));
		accounts.add(new Account(10020, 5520.2, 33, true));
		accounts.add(new Account(12000, 520.2, 33, true));

		return accounts;
	}

	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

}
