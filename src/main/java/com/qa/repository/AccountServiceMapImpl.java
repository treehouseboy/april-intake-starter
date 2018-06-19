package com.qa.repository;

import java.util.HashMap;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountServiceMapImpl implements AccountService{
	
	@Inject
	private JSONUtil util;
	private HashMap<Long, Account> map;
	
	@Override
	public String findAllAccounts() {
		return util.getJSONForObject(map.values());
	}
	
	@Override
	public String findAnAccount(Long id) {
		return util.getJSONForObject(map.get(id));
	}

	@Override
	public String createAnAccount(String account) {
		Account newAccount = util.getObjectForJSON(account, Account.class);
		map.put(newAccount.getID(), newAccount);
		return "{\"message\": \"Account successfully added\"}";
	}

	@Override
	public String updateAnAccount(Long id, String account) {
		Account updatedAccount = util.getObjectForJSON(account, Account.class);
		map.put(id, updatedAccount);
		return account;
	}
	
	@Override
	public String deleteAnAccount(Long id) {
		map.remove(id);
		return "{\"message\": \"Account successfully added\"}";
	}
}
