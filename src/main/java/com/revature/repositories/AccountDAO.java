package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> getAllAccounts();

	public List<Account> getAccountsByUserId(int userId);

	public boolean addAccount(Account account);

	public boolean updateAccount(Account account);

	public boolean deleteAccount(Account account);
}
