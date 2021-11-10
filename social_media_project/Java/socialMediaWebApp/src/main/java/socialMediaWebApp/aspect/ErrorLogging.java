package socialMediaWebApp.aspect;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component("errorLogging")
public class ErrorLogging 
{
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(Object socialMediaWebApp.repositorylayer.*.*(..))")
	private void forRepositoryLayerObjectReturnType() {}
	
	@Pointcut("execution(java.util.List socialMediaWebApp.repositorylayer.*.*(..))")
	private void forRepositoryLayerListReturnType() {}
	
	@Pointcut("execution(void socialMediaWebApp.repositorylayer.*.*(..))")
	private void forRepositoryLayerVoid() {}
	
	@Pointcut("execution(Object socialMediaWebApp.serviceLayer.*.*(..))")
	private void forServiceLayerObjectReturnType() {}
	
	@Pointcut("execution(java.util.List socialMediaWebApp.serviceLayer.*.*(..))")
	private void forServiceLayerListReturnType() {}
	
	@Pointcut("execution(void socialMediaWebApp.serviceLayer.*.*(..))")
	private void forServiceLayerVoidReturnType() {}
	
	@Pointcut("execution(Object socialMediaWebApp.controllers.*.*(..))")
	private void forControllerLayerReturnTypeObject() {}
	
	@Pointcut("execution(java.util.List socialMediaWebApp.controllers.*.*(..))")
	private void forControllerLayerReturnTypeList() {}
	
	@Pointcut("execution(void socialMediaWebApp.controllers.*.*(..))")
	private void forControllerLayerNoReturn() {}
	
	@Pointcut("execution(org.springframework.http.ResponseEntity socialMediaWebApp.controllers.*.*(..))")
	private void forControllerLayerReturnTypeResponseEntity() {}
	


	/**
	 * Handles the throwables that can occur during a method call inside the repository layer that returns an object type
	 * @param pjp
	 * @return
	 */
	@Around("forRepositoryLayerObjectReturnType()")
	public Object handleThrowsRepositoryLayerForAccountReturnTypeObject(ProceedingJoinPoint pjp)
	{
		try
		{
			Object obj = pjp.proceed();
			
//			System.out.println("Returning the return ");
			return obj;
			
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			return null;
		}
	}
	
	/**
	 * Handles the throwables that can occur during a method call inside the repository layer that returns a List<Object> type
	 * @param pjp
	 * @return
	 */
	@Around("forRepositoryLayerListReturnType()")
	public List<Object> handleThrowsRepositroyLayerForAccountReturnTypeList(ProceedingJoinPoint pjp)
	{
		try
		{
			List<Object> list = (List<Object>) pjp.proceed();
			
			return list;
			
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			return null;
		}
	}
	
	/**
	 * Handles the throwables that can occur during a method call inside the repository layer that is void return type
	 * 
	 * @param pjp
	 */
	@Around("forRepositoryLayerVoid()")
	public void handleThrowsRepositoryLayerForAccountVoidReturnTyoe(ProceedingJoinPoint pjp)
	{
		try
		{
			pjp.proceed();
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			
		}
	}
	
	/**
	 * Handles the throwables that can occur during a method call inside the service layer that is object return type
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("forServiceLayerObjectReturnType()")
	public Object handleThrowsServiceLayersReturnTypeObject(ProceedingJoinPoint pjp)
	{
		try
		{
			Object obj = pjp.proceed();
			
//			System.out.println("Returning the return ");
			return obj;
			
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			return null;
		}
	}
	
	/**
	 *Handles the throwables that can occur during a method call inside the service layer that is list<object> return type
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("forServiceLayerListReturnType()")
	public List<Object> handleThrowsServiceLayersReturnTypeList(ProceedingJoinPoint pjp)
	{
		try
		{
			List<Object> list = (List<Object>) pjp.proceed();
			
//			System.out.println("Returning the return list<Account> ");
			return list;
			
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			return null;
		}
	}
	
	/**
	 * Handles the throwables that can occur during a method call inside the service layer that is void return type
	 * 
	 * @param pjp
	 */
	@Around("forServiceLayerVoidReturnType()")
	public void handleThrowsServiceLayerVoid(ProceedingJoinPoint pjp)
	{
		try
		{
			pjp.proceed();
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			
		}
	}
	
	/**
	 * Handles throwables that can occur during a method call inside the controllers layer that is void return type
	 * 
	 * @param pjp
	 */
	@Around("forServiceLayerVoidReturnType()")
	public void handleThrowscontrollersVoid(ProceedingJoinPoint pjp)
	{
		try
		{
			pjp.proceed();
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			
		}
	}
	
	/**
	 * Handles throwables that can occur during a method call inside the controllers layer that is Object return type
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("forControllerLayerReturnTypeObject()")
	public Object handleThrowsControllersObject(ProceedingJoinPoint pjp)
	{
		try
		{
//			System.out.println("##################################  " + pjp.getSignature().toShortString());
			return pjp.proceed();
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			return null;
			
		}
	}
	
	/**
	 * Handles throwables that can occur during a method call inside the controllers layer that is list<Objct> return type
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("forControllerLayerReturnTypeList()")
	public List<Object> handleThrowsControlelrsList(ProceedingJoinPoint pjp)
	{
		try
		{
//			System.out.println("##################################  " + pjp.getSignature().toShortString());
			return (List<Object>) pjp.proceed();
		}
		catch(Throwable t)
		{
//			t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occured during this method call: " + pjp.getSignature().toShortString());
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			return null;
		}
	}
	
	/**
	 * Handles throwables that can occur during a method call inside the controllers layer that is RepsonseEntity return type
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("forControllerLayerReturnTypeResponseEntity()")
	public ResponseEntity handleThrowsControllersResponeEntity(ProceedingJoinPoint pjp) throws Throwable
	{
		try
		{
//			System.out.println("##################################  " + pjp.getSignature().toShortString());
			return (ResponseEntity) pjp.proceed();
		}
		catch(Throwable t)
		{
			//t.printStackTrace();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  big big error");
//			System.out.println("Failure Occurred during this method call: " + pjp.getSignature().toShortString());
//			System.out.println("Localized message from throwable" + t.toString());
//			System.out.println("Get the track trace from throwable" + t.fillInStackTrace().getCause());
			
			logger.error("Failure Occured during this method call: " + pjp.getSignature().toShortString() + "\n Exception: " + t);
			String args = "";
			for(Object obj : pjp.getArgs())
			{
				args += ": " + obj;
			}
			logger.error("Inputs that caused failer: " + args);
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
