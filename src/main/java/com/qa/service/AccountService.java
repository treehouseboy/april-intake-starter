package com.qa.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.domain.Account;
import com.qa.repository.AccountRepository;

public class AccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private AccountRepository repo;

	public String getAllAccounts() {
		LOGGER.info("In AccountServiceImpl getAllAccounts ");
		return repo.findAllAccounts();
	}
	
	public String getOneAccount(Long id) {
		return repo.findAnAccount(id);
	}

	public String addAccount(String account) {
		return repo.createAnAccount(account);
	}

	public String updateAccount(Long id, String account) {
		return repo.updateAnAccount(id, account);
	}

	public String deleteAccount(Long id) {
		return repo.deleteAnAccount(id);

	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}

}
