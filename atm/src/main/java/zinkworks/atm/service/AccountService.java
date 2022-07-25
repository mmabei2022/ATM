package zinkworks.atm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import zinkworks.atm.model.Account;
import zinkworks.atm.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
    private AccountRepository repository;
	
	
	public Account findByAccountNumber(int id) {
        return repository.findByAccountNumber(id);
    }
	
//	
//	public void getAll() {
//		// TODO Auto-generated method stub
//		repository.createAccounts();
//
//	}

	
	public List<Account> listAccount() {
		return repository.listAccount();

	}
	
	public Account saveAccount(Account account) {
        return repository.save(account);
    }
	
	public String deleteAccount(int id) {
        repository.delete(id);
        return "account removed !! " + id;
    }
	public Map<String, Object> balance(int accountNumber, int PIN) {
		return repository.balance(accountNumber, PIN);
	}
	
	public Map<String, Object> withdrawal(int accountNumber, int PIN, int amount) {
		return repository.withdrawal(accountNumber, PIN, amount);
	}
}
