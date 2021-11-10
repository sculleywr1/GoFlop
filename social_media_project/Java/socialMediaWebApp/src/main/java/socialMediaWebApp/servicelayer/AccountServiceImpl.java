package socialMediaWebApp.servicelayer;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import socialMediaWebApp.models.Account;
import socialMediaWebApp.repositorylayer.AccountDao;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao;

	/**
	 * Returns the account object that is in the db passed on the passed in username and password
	 * 
	 * If there is an association inside the database then it will receive an account or null if no association
	 * 
	 * If there is a return then it will set the password to null so the password is not passed upwards
	 * 
	 * Hashing is used in the first variable model in order to allow for a securely hashed password every time the service layer is called
	 */
	@Override
	public Account getMyAccount(String username, String password) {
		// TODO Auto-generated method stub

		Account myAccount = accountDao.getMyAccount(username, Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());

		if (myAccount == null) {

		} else {
			myAccount.setPassword(null);
		}

		return myAccount;
	}

	/**
	 * This will return all accounts in the system regardless from the call to the dao layer method
	 */
	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub

		List<Account> myAccounts = accountDao.getAllAccounts();
		return myAccounts;
	}

	/**
	 * THIS METHOD IS NOT IMPLEMENTED YET 
	 */
	@Override
	public List<Account> getAllAccountsNoPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method will return an account based on the id that is passed in
	 * 
	 *  If there is an account in the account table in the db it will return that account
	 *  if not then it will return null
	 *  
	 *  It will set the password to null if there is an account object returned
	 */
	@Override
	public Account getAccountById(int userId) {
		// TODO Auto-generated method stub
		Account myAccount = accountDao.getAccountById(userId);

		if (myAccount == null) {

		} else {
			myAccount.setPassword(null);
		}

		return myAccount;
	}
	
	/**
	 * This method will return an account from the dao layer based on the username passed in
	 * 
	 * It will either return a null value or an account object
	 * 
	 * It will set the password to null if myAccount is not null
	 * 
	 */
	public Account getUserAccountByUsername(String username) {
		
		Account myAccount = accountDao.getUserAccountByUsername(username);

		if (myAccount == null) {

		} else {
			myAccount.setPassword(null);
		}

		return myAccount;
	}

	/**
	 * This method will return an account based on the username and userEmail by calling the dao layer
	 * 
	 * It will return either an account object associated to the username and usermail args or return null if no association found
	 * 
	 */
	@Override
	public Account getMyAccountByEmailAndUsername(String username, String userEmail) {
		return accountDao.getMyAccountByEmailAndUsername(username, userEmail);
	}



	/**
	 * This method will update the user password in the db when the user pings the updatepassword in the sessionaccount controller layer
	 * 
	 * It copies the password from the Account object which is passed into the Service layer, and in one line it will hash the password String and replace the password with a hashed password. Then it passes the Account object on to the DAO layer
	 */
	@Override
	public void updatePassword(Account myAccount) {
		// TODO Auto-generated method stub
		
		String password = myAccount.getPassword();
		myAccount.setPassword(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
		accountDao.updatePassword(myAccount);
	}

	/**
	 * This method is used to create an account based on an account object that is passed in from the controlley layer
	 * 
	 * It copies the password from the Account object which is passed into the Service layer, and in one line it will hash the password String and replace the password with a hashed password. Then it passes the Account object on to the DAO layer
	 */
	@Override
	public void createNewAccount(Account newAccount) {
		
		String password = newAccount.getPassword();
		newAccount.setPassword(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
		accountDao.createNewAccount(newAccount);

	}

	/**
	 * This method is used to update the user info
	 * 
	 * it is called from the controller layer when the endpoint associated with updating the user info is called
	 * 
	 * THIS IS NO THE METHOD USED WHEN A USER RESETS THEIR PASSWORD
	 * 
	 * 
	 * It copies the password from the Account object which is passed into the Service layer, and in one line it will hash the password String and replace the password with a hashed password. Then it passes the Account object on to the DAO layer
	 */
	public void updateUserInfo(Account myAccount) {
		
		String password = myAccount.getPassword();
		myAccount.setPassword(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
		
		accountDao.updateUserInfo(myAccount);
	}


	/**
	 * This method is used to delete the user from the db by calling the dao layer method that it is associated with
	 */
	@Override
	public void deleteMyAccount(Account myAccount) {
		// TODO Auto-generated method stub
		accountDao.deleteMyAccount(myAccount);

	}

	// CONSTRUCTOR
	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		super();
		this.accountDao = accountDao;
	}

	// GETTER AND SETTER
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

}
