package socialMediaWebApp.repositorylayer;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import socialMediaWebApp.aspect.LogginAspectRepositoryLayerAccount;
import socialMediaWebApp.models.Account;

@Transactional
@Repository("accountDaoImpl")
public class AccountDaoImpl implements AccountDao
{
	
	
	
	/*
	 * ALL METHODS IN THIS CLASS USE THE SessionFactory to get
	 * the current session and use on the session methods
	 * 
	 *  
	 * 
	 */
	private SessionFactory sesFact;
	
	
	
	
	
	
	
	
	
	/**
	 * This method contacts the database using HQL query language to get and account object in the user account table
	 * 
	 * It uses set parameter to prevent against sql injection
	 * 
	 * The username and password are set in the hql query using set parameters
	 * 
	 * this method will return an account associated with the username and password if it is found in the database
	 * 
	 * if it is not then it will return null
	 * 
	 * 
	 */
	@Override
	public Account getMyAccount(String username, String password) 
	{
		// TODO Auto-generated method stub

		List<Account> myAccount = sesFact.getCurrentSession().createQuery("from Account where username=:username and password=:password", Account.class)
				.setParameter("username", username)
				.setParameter("password", password).list();
		
		
		return (myAccount.isEmpty())? null : myAccount.get(0);
	}
	
	/**
	 * This method will contact the database to get all the accounts in the database and return them as a list of accounts
	 * 
	 *  using hql query
	 */
	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		
		
		List<Account> myAccounts = sesFact.getCurrentSession().createQuery("from Account", Account.class).list();
		
		return myAccounts;
	}
	/**
	 * THIS METHOD IS NOT USED AND IS NOT FULLY IMPLEMENTED 
	 */
	@Override
	public List<Account> getAllAccountsNoPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method will contact a data base an return the account that has the pk of the userId passed in
	 * 
	 * If no account has that primary key userId then it will return null
	 * 
	 * Otherwise it will return the account assocaiated to that primary key
	 */
	@Override
	public Account getAccountById(int userId) {
		// TODO Auto-generated method stub
		return sesFact.getCurrentSession().get(Account.class, userId);
	}
	
	/*
	 * This method will contact the database an return the account associated with the username that is passed in
	 * 
	 *  It used the hql query and setParameters to keep it decoupled from sql langauage and prevent sql injection
	 * 
	 * It will return the account associated with the username if there is one otherwise it will return null
	 */
	public Account getUserAccountByUsername(String username)
	{
		List<Account> myAccount = sesFact.getCurrentSession().createQuery("from Account where username=:username", Account.class)
				.setParameter("username", username)
				.list();
		
		
		return (myAccount.isEmpty())? null : myAccount.get(0);
	}
	
	/**
	 * This method contacts the database to get the account that has the username and the email passed in associated with it
	 * 
	 * It used HQL query language and set parameters to make it decoupled from sql syntax and to prevent sql injection
	 *
	 *It will either return the account associated with the two parameters from the database or it will return null
	 *
	 *if no association is found
	 *
	 */
	@Override
	public Account getMyAccountByEmailAndUsername(String username, String userEmail)
	{
		List<Account> myAccount = sesFact.getCurrentSession().createQuery("from Account where username=:username and userEmail=:userEmail", Account.class)
				.setParameter("username", username)
				.setParameter("userEmail", userEmail).list();
		System.out.println("-----------" + myAccount);
		
		return (myAccount.isEmpty()) ? null: myAccount.get(0);
	}
	

	
	/**
	 * This method will update an account object in the database if it matches the account object that is passed in
	 * 
	 * This is used to update the data about the user if they have change any part of their data if they have requested to do so
	 * 
	 */
	@Override
	public void updateUserInfo(Account myAccount)
	{
		sesFact.getCurrentSession().update(myAccount);
	}
	

	
	/**
	 * This method is used to update the password of the account passed in with the account object associated within the sql database
	 */
	@Override
	public void updatePassword(Account myAccount) {
		// TODO Auto-generated method stub
		sesFact.getCurrentSession().update(myAccount);
		
	}
	
	/**
	 * This method is used to create a new object in the account table in the database which will be based off the Account object newAccoun
	 * That is passed in
	 * 
	 */
	@Override
	public void createNewAccount(Account newAccount) {
		sesFact.getCurrentSession().save(newAccount);
		
	}
		
	/**
	 * THI METHOD IS NOT IMPLMENTED
	 */
	@Override
	public void deleteMyAccount(Account myAccount) {
		// TODO Auto-generated method stub
		
	}
	
	
	///////////Constructor
	public AccountDaoImpl() {
		super();
	}
	
	
	public AccountDaoImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	////////GETTERS AND SETTERS
	public SessionFactory getSesFact() {
		return sesFact;
	}
	
	
	@Autowired
	public void setSesFact(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}
	
}
