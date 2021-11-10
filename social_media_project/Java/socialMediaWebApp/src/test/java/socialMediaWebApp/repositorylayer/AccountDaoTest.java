package socialMediaWebApp.repositorylayer;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
import socialMediaWebApp.models.Account;

@Transactional
public class AccountDaoTest 
{
	
	public static ApplicationContext appContext = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
	AccountDao accountDaoImpl = 
			appContext.getBean("accountDaoImpl", AccountDao.class);
	
	@Test
	@DisplayName("getMyAccountByUserNameAndPasswordTest")
    public void getMyAccountByUserNameAndPasswordTest()
    {
    	
		String username = "test2";
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";
		
    	Account expectedAccount = new Account(2, username, password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);

    	Account actualAccount = 
    			accountDaoImpl.getMyAccount(username ,password);
    	actualAccount.setMyComments(null);
    	actualAccount.setLikedPosts(null);
    	actualAccount.setMyPosts(null);
    	
    	
    	
    	
    	assertEquals(expectedAccount, actualAccount);
    }
    
	@Test
	@DisplayName("getAccountByIdTest")
	public void getAccountByIdTest()
	{
		int userId = 2;
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

		
		Account expectedAccount = new Account(2, "test2", password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
		
    	Account actualAccount = 
    			accountDaoImpl.getAccountById(userId);
    	actualAccount.setMyComments(null);
    	actualAccount.setMyPosts(null);
    	actualAccount.setLikedPosts(null);
		
    	assertEquals(expectedAccount, actualAccount);
	}
	
	
	@Test
	@DisplayName("getUserAccountByUsernameTest")
	public void getUserAccountByUsernameTest()
	{
		
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

		Account expectedAccount = new Account(2, "test2", password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
		
    	Account actualAccount = 
    			accountDaoImpl.getUserAccountByUsername("test2");
    	actualAccount.setMyComments(null);
    	actualAccount.setMyPosts(null);
    	actualAccount.setLikedPosts(null);
		
    	assertEquals(expectedAccount, actualAccount);
	}
	
	
	@Test
	@DisplayName("getMyAccountByEmailAndUsernameTest")
	public void getMyAccountByEmailAndUsernameTest()
	{
		
		String password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

		Account expectedAccount = new Account(2, "test2", password, null, 
    			"test2@gmail.com", null, "oldname", "newlname", null, null);
		
    	Account actualAccount = 
    			accountDaoImpl.getMyAccountByEmailAndUsername("test2", "test2@gmail.com");
    	actualAccount.setMyComments(null);
    	actualAccount.setMyPosts(null);
    	actualAccount.setLikedPosts(null);
		
    	assertEquals(expectedAccount, actualAccount);
	}
    
}
