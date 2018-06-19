package com.qa.repository;

public interface AccountRepository {
	
	public String findAllAccounts(); 

	public String findAnAccount(Long id); 

	public String createAnAccount(String account); 

	public String updateAnAccount(Long id, String account); 

	public String deleteAnAccount(Long id); 

}
