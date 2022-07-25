package zinkworks.atm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import zinkworks.atm.service.AccountService;

@RestController 
public class AccountController {

	
	@Autowired
    private AccountService service;
	

	@GetMapping("/api/balance/{accountNumber}/{PIN}")
	@ResponseBody
	public  Map<String, Object> balance(@PathVariable int accountNumber, @PathVariable int PIN) {
		return service.balance(accountNumber, PIN);
	}
	
	@GetMapping("/api/withdrawal/{accountNumber}/{PIN}/{amount}")
	@ResponseBody
	public  Map<String, Object> withdrawal(@PathVariable int accountNumber, @PathVariable int PIN, @PathVariable int amount) {
		return service.withdrawal(accountNumber, PIN, amount);
	}
	
}
