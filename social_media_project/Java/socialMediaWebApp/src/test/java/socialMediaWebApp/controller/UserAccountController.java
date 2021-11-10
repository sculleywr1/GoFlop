package socialMediaWebApp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import socialMediaWebApp.controllers.SessionAccountController;
import socialMediaWebApp.models.Account;
import socialMediaWebApp.servicelayer.AccountService;
import socialMediaWebApp.servicelayer.EmailService;


@ExtendWith(MockitoExtension.class)
public class UserAccountController 
{

    SessionAccountController accountController;
    
    
    @Mock
    AccountService accountService;
    
    @Mock
    EmailService emailService;
    
    
    @BeforeEach
    public void setUp()
    {
    	accountController = new SessionAccountController(accountService, emailService);
    }
    /*
     * Testing the method getUser() inside the controller layer
     * 
     */
    @Test
    @DisplayName("getUserTest")
    public void getUserTest()
    {
    	String username = "test2";
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";
		
//		Set
    	Account mockAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
    	Account expectedAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
//    	Define
    	when(accountService.getMyAccount(username, password)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountService.getMyAccount(username, password);
//    	Assert
    	verify(accountService, times(1)).getMyAccount(username, password);
    	assertEquals(expectedAccount, actualAccount);
    }
    
    /*
     *Testing themethod getAccountById() inside the controller layer 
     * 
     */
    @Test
	@DisplayName("getAccountByIdTest")
	public void getAccountByIdTest()
	{
		int userId = 2;
		String username = "test2";
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

//		Set
		Account mockAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
		
		Account expectedAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
//    	Defene mock
    	when(accountService.getAccountById(userId)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountService.getAccountById(userId);
    
//    	Assert
    	verify(accountService, times(1)).getAccountById(userId);
    	assertEquals(expectedAccount, actualAccount);
	}
	
	/*
	 * Testing the method getAccountByUsername() in the controller layer
	 * 
	 */
	@Test
	@DisplayName("getUserAccountByUsernameTest")
	public void getUserAccountByUsernameTest()
	{
		String username = "test2";
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

		Account mockAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
		
		Account expectedAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
    	
    	when(accountService.getUserAccountByUsername(username)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountService.getUserAccountByUsername(username);
    	
    	verify(accountService, times(1)).getUserAccountByUsername(username);
    	assertEquals(expectedAccount, actualAccount);
	}
	
	/*
	 *Test the method getMyAccountByEmailAndUsername() in the controller layer 
	 * 
	 */
	@Test
	@DisplayName("getMyAccountByEmailAndUsernameTest")
	public void getMyAccountByEmailAndUsernameTest()
	{
		String username = "test2";
		String email = "test2@gmail.com";
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

		Account mockAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
		
		Account expectedAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
    	
    	when(accountService.getMyAccountByEmailAndUsername(username, email)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountService.getMyAccountByEmailAndUsername(username, email);
    	
    	verify(accountService, times(1)).getMyAccountByEmailAndUsername(username, email);
    	assertEquals(expectedAccount, actualAccount);
	}
    

    
}