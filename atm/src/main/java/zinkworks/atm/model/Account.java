package zinkworks.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {

	
	 private int accountNumber;
     private int PIN;
     private int balance;
     private int overdraft;
}
