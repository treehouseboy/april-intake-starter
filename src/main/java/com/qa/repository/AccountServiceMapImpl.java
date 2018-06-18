package com.qa.repository;

import java.util.HashMap;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountServiceMapImpl implements AccountServiceImpl{
	
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
	public String updateAnAccount(Long id, String field, String value) {
		Account existingAccount = map.get(id);
		switch(field) {
		case "firstName":
		existingAccount.setFirstName(value);
		break;
		case "secondName":
		existingAccount.setSecondName(value);
		break;
		}
		return util.getJSONForObject(existingAccount);
	}
	
	@Override
	public String deleteAnAccount(Long id) {
		map.remove(id);
		return "{\"message\": \"Account successfully added\"}";
	}
}
