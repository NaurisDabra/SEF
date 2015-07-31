package sef.module8.activity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Thsi class represents a simple representation of an account encapsulating
 * a name
 *
 * @author John Doe
 *
 */
public class Account {
private String accountName;


	/**
	 * Creates an Account object with the specified name.  If the accout name
	 * given violates the minimum requirements, then an AccountException is thrown
	 *
	 * @param accountName
	 * @throws AccountException
	 */
	public  Account(String accountName) throws AccountException{
		if(accountName.length()<4)
			throw new AccountException("tooShort",accountName);
		else
		if(!accountName.matches("[a-zA-Z0-9]+"))
			throw new AccountException("tooSimple", accountName);
		else
		this.accountName=accountName;


	}


	/**
	 * Returns the account name
	 *
	 * @return the account name
	 */
	public String getName(){
		return accountName;
	}
}
