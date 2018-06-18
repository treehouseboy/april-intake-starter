package com.qa.repository;

public interface AccountServiceImpl {
	
	public String findAllAccounts(); 

	public String findAnAccount(Long id); 

	public String createAnAccount(String account); 

	public String updateAnAccount(Long id, String field, String value); 

	public String deleteAnAccount(Long id); 

}
