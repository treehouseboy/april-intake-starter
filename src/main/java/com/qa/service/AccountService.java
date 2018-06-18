package com.qa.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.qa.domain.Account;

public class AccountService {

	private Map<Long, Account> accountMap;

	private long count = 0;

	public AccountService() {
		accountMap = new HashMap<Long, Account>();
	}

	public void addAccountFromMap(Account userAccount) {
		userAccount.setID(count);
		accountMap.put(userAccount.getID(), userAccount);
		count++;
	}

	public void removeAccountFromMap(Long i) {
		boolean countExists = accountMap.containsKey(i);
		if (countExists) {
			accountMap.remove(i);
		}
	}

	public Map<Long, Account> getAccountMap() {
		return accountMap;
	}

	public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
	}

	public Boolean checkBlockedAccount() {
		Boolean accountBlock = false;
		for (Entry<Long, Account> entry : accountMap.entrySet()) {
			if(entry.getValue().getAccountNumber().equals("9999")){
				accountBlock = true;
			}
		}
		return accountBlock;
	}

}
