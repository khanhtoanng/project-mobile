/**
 * 
 */
package com.toannk.project.serviceImp;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toannk.project.dataset.Account;
import com.toannk.project.dataset.ActorDestiny;
import com.toannk.project.mapper.AccountMapper;
import com.toannk.project.service.AccountService;
import com.toannk.project.utils.ConstranstUtils;

/**
 * @author WIN
 *
 */
@Service
public class AccountServiceImp implements AccountService {

	private final int ACTION_ON_ACCOUNT_ERROR = -3;

	@Autowired
	private AccountMapper mapper;

	public AccountServiceImp(AccountMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int insertActor(Account account) {
		int result = ACTION_ON_ACCOUNT_ERROR;
		try {
			result = mapper.createActor(account);
		} catch (Exception e) {
			if(e.getMessage().contains("PRIMARY")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_PRIMARY_KEY;
			}
			System.out.print("Exception in insert: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int updateActor(Account account, String username) {	
		account.setUpdateAccount(username);
		return mapper.updateActor(account);
	}

	@Override
	public int deleteActor(String username) {
		int result = ACTION_ON_ACCOUNT_ERROR;
		try {
			result = mapper.deleteActor(username);
		} catch (Exception e) {
			if(e.getMessage().contains("REFERENCE")) {
				result = ConstranstUtils.EXCEPTION_SQLSERVER_REFERENCE;
			}
			System.out.print("Exception in delete: " + e.getMessage());
		}
		return result;

	}

	@Override
	public List<Account> getAllActor() {
		return mapper.getAllActor();
	}

	@Override
	public String isLogin(String username, String password) {
		return mapper.isLogin(username, password);
	}

	@Override
	public int isExistAccount(String username) {
		return mapper.isExistAccount(username);
	}

	@Override
	public List<ActorDestiny> getHistoryDestiny(String username) {
		List<ActorDestiny> list = null;
		try {
			list = mapper.getHistoryDestiny(username);
		} catch (Exception e) {
			System.out.print("Exception in getHistoryDestiny: " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<ActorDestiny> getIncomingDestiny(String username) {
		List<ActorDestiny> list = null;
		try {
			list = mapper.getIncomingDestiny(username);
		} catch (Exception e) {
			System.out.print("Exception in getIncomingDestiny: " + e.getMessage());
		}
		return list;
	}

}
