package zinkworks.atm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import zinkworks.atm.model.Account;
import zinkworks.atm.service.AccountService;

@RestController
public class AccountController {

	
	@Autowired
    private AccountService service;
	
	@GetMapping("/accounts/{id}")
    public Account findByAccountNumber(@PathVariable("id") int id) {
        return service.findByAccountNumber(id);
    }
	
	@GetMapping("/accounts")
	public List<Account> listAccount() {
		return service.listAccount();
	}
	
	@PostMapping("/add")
	public Account add(@RequestBody Account a) {
		return service.saveAccount(a);
	}
	
	@GetMapping("/balance/{accountNumber}/{PIN}")
	@ResponseBody
	public  Map<String, Object> balance(@PathVariable int accountNumber, @PathVariable int PIN) {
		return service.balance(accountNumber, PIN);
	}
	
	@GetMapping("/withdrawal/{accountNumber}/{PIN}/{amount}")
	@ResponseBody
	public  Map<String, Object> withdrawal(@PathVariable int accountNumber, @PathVariable int PIN, @PathVariable int amount) {
		return service.withdrawal(accountNumber, PIN, amount);
	}
	
	@DeleteMapping("{id}")
    public String deleteAccount(Model model, @PathVariable(name = "id") int id) {
        service.deleteAccount(id);
        return "redirect:/";
    }
}
