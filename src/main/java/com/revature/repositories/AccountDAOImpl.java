package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;

public class AccountDAOImpl implements AccountDAO {

	static List<Account> pendingAccounts = new ArrayList<Account>();
	static boolean loading = false;
	static int pendingNumber = 0;

	public static int getPendingNumber() {
		return pendingNumber;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllPendingAccounts() {
		// TODO Auto-generated method stub
		if (pendingAccounts.size() == 0 && !loading) {
			pendingAccounts.add(new Account(10000, 20.2, 33));
			pendingAccounts.add(new Account(10040, 1120.2, 33));
			pendingAccounts.add(new Account(10020, 5520.2, 33));
			pendingAccounts.add(new Account(12000, 520.2, 33));
			loading = true;
			pendingNumber = pendingAccounts.size();
		}

		return pendingAccounts;
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Account> accounts = new ArrayList<Account>();

		// public Account(int accountId, double balance, int userId, boolean isActive) {
		accounts.add(new Account(10000, 20.2, 33));
		accounts.add(new Account(10040, 1120.2, 33));
		accounts.add(new Account(10020, 5520.2, 33));
		accounts.add(new Account(12000, 520.2, 33));

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

	@Override
	public boolean manageNewAccount(Account account) {
		// TODO Auto-generated method stub
		pendingAccounts.remove(account);
		pendingNumber--;
		return true;
	}

}
