/**
 * 
 */
package com.toannk.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toannk.project.dataset.Account;
import com.toannk.project.dataset.ActorDestiny;
import com.toannk.project.service.AccountService;

/**
 * @author WIN
 *
 */
@RequestMapping(value = "/api")
@RestController
public class AccountController {
	
	@Autowired
	private AccountService service;

	
	public AccountController(AccountService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/actor")
	public List<Account> getAllAccount() {
		return service.getAllActor();
	}

	@DeleteMapping(value = "/actor/{username}")
	public int deleteAccount(@PathVariable("username") String username) {
		return service.deleteActor(username);
	}

	@PostMapping(value = "/actor")
	public int insertAccount(@RequestBody Account account) {
		return service.insertActor(account);
	}

	@PutMapping(value = "/actor/{updateAccount}")
	public int updateAccount(@PathVariable("updateAccount") String updateAccount,@RequestBody Account account) {
		return service.updateActor(account, updateAccount);
	}
	
	@GetMapping(value = "/actor/login/{username}")
	public String isLogin(@PathVariable("username") String username, @RequestParam(name = "password") String password) {
		return service.isLogin(username, password);
	}
	
	@GetMapping(value = "/account/exist/{username}")
	public int isExistAccount(@PathVariable("username") String username) {
		return service.isExistAccount(username);
	}
	
	@GetMapping(value = "/actor/history/{username}")
	public List<ActorDestiny> getHistoryDestiny(@PathVariable("username") String username) {
		return service.getHistoryDestiny(username);
	}
	
	@GetMapping(value = "/actor/incoming/{username}")
	public List<ActorDestiny> getIncomingDestiny(@PathVariable("username") String username) {
		return service.getIncomingDestiny(username);
	}
}
