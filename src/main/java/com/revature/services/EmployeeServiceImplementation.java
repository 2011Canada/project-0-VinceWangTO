package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.repositories.AccountDAO;

public class EmployeeServiceImplementation implements EmployeeService {

	AccountDAO accountDAO;

	public EmployeeServiceImplementation(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public boolean manageNewAccount(Account account, String setStatue) {
		// TODO Auto-generated method stub
		account.setStatus(setStatue);
		accountDAO.manageNewAccount(account);
		return true;
	}

	@Override
	public List<Account> viewCustomerAccount(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> viewTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

}
