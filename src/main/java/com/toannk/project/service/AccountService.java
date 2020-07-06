/**
 * 
 */
package com.toannk.project.service;

import java.util.List;

import com.toannk.project.dataset.Account;
import com.toannk.project.dataset.ActorDestiny;

/**
 * @author WIN
 *
 */
public interface AccountService {
	public List<Account> getAllActor();
	public int insertActor(Account account);
	public int updateActor(Account account,String username);
	public int deleteActor(String username);
	public String isLogin(String username, String password);
	public int isExistAccount(String username);
	public List<ActorDestiny> getHistoryDestiny(String username);
	public List<ActorDestiny> getIncomingDestiny(String username);
}
