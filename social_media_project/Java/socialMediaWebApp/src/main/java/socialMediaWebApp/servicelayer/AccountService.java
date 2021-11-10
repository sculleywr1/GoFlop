package socialMediaWebApp.servicelayer;

import java.util.List;

import socialMediaWebApp.models.Account;


public interface AccountService 
{
	public Account getMyAccount(String username, String password);
	
	public List<Account> getAllAccounts();
	
	public List<Account> getAllAccountsNoPassword();
	
	public Account getAccountById(int userId);
	
	public Account getUserAccountByUsername(String username);
	
	public Account getMyAccountByEmailAndUsername(String username, String userEmail);
	

	
	public void updatePassword(Account myAccount);
	
	public void updateUserInfo(Account myAccount);
	
	//create
	public void createNewAccount(Account newAccount);
	
	
	//delete
	public void deleteMyAccount(Account myAccount);
}
