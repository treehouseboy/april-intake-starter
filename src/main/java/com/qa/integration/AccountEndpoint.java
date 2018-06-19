package com.qa.integration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.AccountService;

@Path("/account")
public class AccountEndpoint {

	@Inject
	private AccountService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addAccount(String account) {
		return service.addAccount(account);
	}

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		return service.updateAccount(id, account);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);

	}

	public void setService(AccountService service) {
		this.service = service;
	}

}
