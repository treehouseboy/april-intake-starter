package com.qa.repository;

import java.util.HashMap;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountRepositoryMapImpl implements AccountRepository {

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
		if (account != null) {
			Account newAccount = util.getObjectForJSON(account, Account.class);
			map.put(newAccount.getID(), newAccount);
			return "{\"message\": \"Account successfully added\"}";
		} else {
			return "{\"message\": \"Error\"}";
		}
	}

	@Override
	public String updateAnAccount(Long id, String account) {
		if (account != null) {
			Account updatedAccount = util.getObjectForJSON(account, Account.class);
			map.put(id, updatedAccount);
			return account;
		} else {
			return "{\"message\": \"Error\"}";
		}
	}

	@Override
	public String deleteAnAccount(Long id) {
		if (id != null) {
			map.remove(id);
			return "{\"message\": \"Account successfully added\"}";
		} else {
			return "{\"message\": \"Error\"}";
		}
	}
}
