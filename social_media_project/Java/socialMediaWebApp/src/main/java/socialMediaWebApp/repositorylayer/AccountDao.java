package socialMediaWebApp.repositorylayer;

import java.util.List;

import socialMediaWebApp.models.Account;

public interface AccountDao 
{
	//select

	public Account getMyAccount(String username, String password);
	
	public List<Account> getAllAccounts();
	
	public List<Account> getAllAccountsNoPassword();
	
	public Account getAccountById(int userId);
	
	public Account getMyAccountByEmailAndUsername(String username, String userEmail);
	
	public Account getUserAccountByUsername(String username);
		
	public void updatePassword(Account myAccount);
	
	public void updateUserInfo(Account myAccount);
	
	//create
	public void createNewAccount(Account newAccount);
	
	
	//delete
	public void deleteMyAccount(Account myAccount);
}
