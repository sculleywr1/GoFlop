package socialMediaWebApp.aspect;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import socialMediaWebApp.models.Account;

@Aspect
@Component("logginAspectRepositoryLayerAccount")
public class LogginAspectRepositoryLayerAccount
{
	/**
	 * Sets up logger
	 */
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	
	/**
	 * Logs the username and email when they attempt to reset their password after forgetting it
	 * 
	 * @param jp
	 */
	@AfterReturning("execution(* socialMediaWebApp.repositorylayer.AccountDao.getMyAccountByEmailAndUsername(..))")
	public void UserFrogotPassword(JoinPoint jp)
	{
		myLogger.setLevel(Level.INFO);
		String theMethod = jp.getSignature().toShortString();
		
		String credentialsResetPassword = jp.getArgs()[0] + " " + jp.getArgs()[1];
//		System.out.println(credentialsResetPassword);
		
//		System.out.println("------------------------------------" + theMethod);
		myLogger.info("====> @After: calling method: " + theMethod + "\n\t " + credentialsResetPassword);
		
	}
	
	/**
	 * Logging the username when a user successfully logs in
	 * 
	 * 
	 * @param jp
	 */
	@AfterReturning("execution(* socialMediaWebApp.repositorylayer.AccountDao.getMyAccount(String, String))")
	public void UserLoginAttempt(JoinPoint jp)
	{
		String theMethod = jp.getSignature().toShortString();
		
		String credentials = jp.getArgs()[0].toString();
		
		myLogger.info("=====> @After: calling method: " + theMethod + "\n\t " + credentials);
//		System.out.println(credentials);
		
		
	}
	/**
	 * Logs a new account partial information after account is successfully created
	 * 
	 * @param jp
	 */
	@AfterReturning("execution(* socialMediaWebApp.repositorylayer.AccountDao.createNewAccount(..))")
	public void newUserAccountCreation(JoinPoint jp)
	{
		String theMethod = jp.getSignature().toShortString();
		
		String credentials = ((Account)jp.getArgs()[0]).getUserId() + " -> " + ((Account)jp.getArgs()[0]).getUsername() + " -> " + ((Account)jp.getArgs()[0]).getUserEmail();
	
		myLogger.info("=====> @After: calling method: " + theMethod + "\n\t " + credentials);
	
	
	}
	
	
	
}
