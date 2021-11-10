package socialMediaWebApp.servicelayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import socialMediaWebApp.models.Account;
import socialMediaWebApp.repositorylayer.AccountDao;

@ExtendWith(MockitoExtension.class)
public class AcountServiceImpl
{
	AccountServiceImpl accountServiceImpl;
    
    
    @Mock
    AccountDao accountDao;
    
    
    
    
    @BeforeEach
    public void setUp()
    {
    	accountServiceImpl = new AccountServiceImpl(accountDao);
    }
    
    @Test
    @DisplayName("getUserTest")
    public void getUserTest()
    {
    	String username = "test2";
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";
		
    	Account mockAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
    	Account expectedAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
    	
    	when(accountDao.getMyAccount(username, password)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountDao.getMyAccount(username, password);
    	
    	verify(accountDao, times(1)).getMyAccount(username, password);
    	assertEquals(expectedAccount, actualAccount);
    }
    
    @Test
	@DisplayName("getAccountByIdTest")
	public void getAccountByIdTest()
	{
		int userId = 2;
		String username = "test2";
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

		
		Account mockAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
		
		Account expectedAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
    	
    	when(accountDao.getAccountById(userId)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountDao.getAccountById(userId);
    	
    	verify(accountDao, times(1)).getAccountById(userId);
    	assertEquals(expectedAccount, actualAccount);
	}
	
	
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
    	
    	when(accountDao.getUserAccountByUsername(username)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountDao.getUserAccountByUsername(username);
    	
    	verify(accountDao, times(1)).getUserAccountByUsername(username);
    	assertEquals(expectedAccount, actualAccount);
	}
	
	
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
    	
    	when(accountDao.getMyAccountByEmailAndUsername(username, email)).thenReturn(mockAccount);
    	
    	Account actualAccount = accountDao.getMyAccountByEmailAndUsername(username, email);
    	
    	verify(accountDao, times(1)).getMyAccountByEmailAndUsername(username, email);
    	assertEquals(expectedAccount, actualAccount);
	}
    


}
