package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class AccountServiceDMImpl implements AccountServiceImpl {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	private JSONUtil util = new JSONUtil();

	public String findAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a ORDER BY a.id DESC", Account.class);
		List<Account> accounts = (List<Account>)query.getResultList();
		return util.getJSONForObject(accounts);
	}

	public String findAnAccount(Long id) {
		return util.getJSONForObject(em.find(Account.class, id));
	}

	@Transactional(REQUIRED)
	public String createAnAccount(String account) {
		Account newAccount = util.getObjectForJSON(account, Account.class);
		em.persist(newAccount);
		return "{\"message\": \"Account successfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateAnAccount(Long id, String field, String value) {

		Account existingAccount = em.find(Account.class, id);
		switch (field) {
		case "firstName":
			existingAccount.setFirstName(value);
			break;
		case "secondName":
			existingAccount.setSecondName(value);
			break;
		}
		return util.getJSONForObject(existingAccount);
		
	}

	@Transactional(REQUIRED)
	public String deleteAnAccount(Long id) {
		em.remove(em.find(Account.class, id));
		return "{\"message\": \"Account successfully deleted\"}";
	}

}
