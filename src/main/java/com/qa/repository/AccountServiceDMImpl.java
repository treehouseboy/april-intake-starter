package com.qa.repository;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

@Transactional(SUPPORTS)
public class AccountServiceDMImpl {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	private JSONUtil util = new JSONUtil();

	public List<Account> findAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a ORDER BY a.id DESC", Account.class);
		return query.getResultList();
	}

	public Account findAnAccount(Long id) {
		return em.find(Account.class, id);
	}

	@Transactional(REQUIRED)
	public String createAnAccount(String account) {
		Account newAccount = util.getObjectForJSON(account, Account.class);
		em.persist(newAccount);
		return "{\"message\": \"Account successfully added\"}";
	}

	@Transactional(REQUIRED)
	public void updateAnAccount(Long id, String field, String value) {

		Account existingAccount = em.find(Account.class, id);
		switch (field) {
		case "firstName":
			existingAccount.setFirstName(value);
			break;
		case "secondName":
			existingAccount.setSecondName(value);
			break;
		}
	}

	@Transactional(REQUIRED)
	public String deleteAnAccount(Long id) {
		em.remove(em.find(Account.class, id));
		return "{\"message\": \"Account successfully deleted\"}";
	}

}
