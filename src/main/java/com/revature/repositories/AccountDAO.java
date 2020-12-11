package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> getAllAccounts();

	public List<Account> getAllPendingAccounts();

	public List<Account> getAccountsByUserId(int userId);

	public List<Account> getAccountsByUsername(String username);

	public boolean addAccount(Account account);

	public boolean updateAccount(Account account);

	public boolean deleteAccount(Account account);

	public boolean manageNewAccount(Account account);

	public boolean acceptMoney(int accountId, double amount);
}
