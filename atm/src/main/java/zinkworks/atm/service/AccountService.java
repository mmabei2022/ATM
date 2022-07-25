package zinkworks.atm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import zinkworks.atm.model.Account;

@Service
public class AccountService {

	
	
	
	private static Account account1 = new Account(123456789, 1234, 800, 200);
	private static Account account2 = new Account(987654321, 4321, 1230, 150);
	private static int availableCash = 1500;
	 
	private static List<Account> accounts = List.of(account1, account2);
	
	
	
	
	public Map<String, Object> balance(int accountNumber, int PIN) {
		if (findByAccountNumber(accountNumber).getPIN() == PIN)
			return Map.of("Balance", findByAccountNumber(accountNumber).getBalance(), "Maximum withdrawal",
					findByAccountNumber(accountNumber).getBalance() + findByAccountNumber(accountNumber).getOverdraft());
		else
			return Map.of("error", "Incorrect PIN code");
	}

	public Map<String, Object> withdrawal(int accountNumber, int PIN, int amount) {
		if (findByAccountNumber(accountNumber).getPIN() == PIN) {
			if (amount <= availableCash) {
				if (amount <= findByAccountNumber(accountNumber).getBalance() + findByAccountNumber(accountNumber).getOverdraft()) {
					if(amount % 5 == 0) {
					return Map.of("Details of Notes", countNotes(amount), "Remaining balance",
							findByAccountNumber(accountNumber).getBalance() - amount);
					}
					return Map.of("Error", "Invalide amount, size only amount ending with 0 or 5");
				} else {
					return Map.of("Error", "Insufficient balance");
				}
			} else {
				return Map.of("Error", "Insufficient cash in this ATM machine");
			}
		} else {

			return Map.of("Error", "Incorrect PIN code");
		}
	}
	
	private Account findByAccountNumber(int accountNumber) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == (accountNumber)) {
				return accounts.get(i);
			}
		}
		return null;
    }

	
	private Map<String, String> countNotes(int amount)
	{
		int[] notes = new int[] {  50, 20, 10, 5 };
		int[] noteCounter = new int[4];
		int[] notesCount = new int[] { 10,  30,  30,  20 };
		Map<String, String> map = new HashMap<String, String>();

		
		for (int i = 0; i < 4; i++) {
			if (amount >= notes[i]) {
				if (amount / notes[i] <= notesCount[i]) {
					noteCounter[i] = amount / notes[i];
					amount = amount % notes[i];
				} else {
					noteCounter[i] = notesCount[i];
					amount = amount % notes[i] + (amount / notes[i] - notesCount[i]) * notes[i];
				}

			}
		}
		
		for (int i = 0; i < 4; i++) {
			if (noteCounter[i] != 0) {
				map.put(String.valueOf(notes[i])+"s", String.valueOf(noteCounter[i]));

			}
		}
		return map;
		
			
	}
}
