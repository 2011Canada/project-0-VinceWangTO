package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Account;
import com.revature.repositories.AccountDAO;

public class EmployeeServiceImplementationTest {

	private EmployeeServiceImplementation es;
	private AccountDAO acd;

	@BeforeEach
	public void setupES() {
		this.acd = mock(AccountDAO.class);
		es = new EmployeeServiceImplementation(this.acd);
	}

	@Test
	public void testGetPendingAccounts() {
		List<Account> testVals = new ArrayList<Account>();

		Account ac1 = new Account(1, 10.5, 1);
		Account ac2 = new Account(2, 15.5, 2);
		Account ac3 = new Account(4, 20.5, 3);
		testVals.add(ac1);
		testVals.add(ac2);
		testVals.add(ac3);

		when(acd.getAllPendingAccounts()).thenReturn(testVals);
		// if someone call getPendingAccounts, should return above values

		List<Account> expectedVals = new ArrayList<Account>(testVals);
		assertEquals(expectedVals, es.getPendingAccounts());
		verify(acd).getAllPendingAccounts();

	}
}
