package socialMediaWebApp.controllers;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import socialMediaWebApp.models.Account;
import socialMediaWebApp.servicelayer.AccountService;
import socialMediaWebApp.servicelayer.EmailService;



@RequestMapping("userAccount")
@RestController
@CrossOrigin(origins="http://34.71.84.231:4200", allowCredentials = "true")
public class SessionAccountController 
{
	private Logger logger = Logger.getLogger(getClass().getName());
	private AccountService accountService;
	private EmailService emailService;
	
	

	/**
	 * This method calls the service layer to check if the user is in the DB
	 * 
	 * It will set the httpsession to the user who logged in if and only if the user is in the db
	 * 
	 * @param mySession
	 * @param myAccount an account object that represents the the user login information from the body of the post
	 * @return a ResponseEntity that contains an account object
	 */
	@PostMapping("/login")
	public ResponseEntity login(HttpSession mySession, @RequestBody Account myAccount)
	{

		
		Account currentUser = accountService.getMyAccount(myAccount.getUsername(), myAccount.getPassword());
		
		
		if(currentUser == null)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
		{
			mySession.setAttribute("currentUser", currentUser);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(currentUser);
		}
	}
	
	/**
	 * This method will return the account that is stored in the current session
	 * 
	 * @param mySession
	 * @return
	 */
	@PostMapping("/getUser")
	public Account getUserAccount(HttpSession mySession)
	{
		return ((Account)mySession.getAttribute("currentUser"));
	}
	
	
	/**
	 * This method will invalidate the current session the endpoint is called
	 * 
	 * @param mySession
	 * @return
	 */
	@PostMapping("/logout")
	public ResponseEntity logout(HttpSession mySession)
	{
		mySession.invalidate();
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**This method will get the users information by contacting the db through the service layer -> day layer -> db
	 * 
	 * It will get the information for the credentials passed in. If they are present in the db together
	 * 
	 * It will send an salted string to the users provided email
	 * 
	 * @param mySession
	 * @param myAccount
	 * @return
	 */
	@PostMapping("/forgotPassword")
	public ResponseEntity forgotPassword(HttpSession mySession, @RequestBody Account myAccount)
	{
		Account currentUser = accountService.getMyAccountByEmailAndUsername(myAccount.getUsername(), myAccount.getUserEmail());
		
		if(currentUser != null)
		{
			mySession.setAttribute("currentUser", currentUser);
			
			System.out.println(mySession);
			
			String passcode = getSaltString();
			
			String email = currentUser.getUserEmail();
			String subject = "Password Reset Request";
			String body = "Hello " 
					+ currentUser.getFirstName()
					+ " "
					+ currentUser.getLastName()
					+ ",\n\tYou have requested to reset your password.\n\n"
					+ "Enter the following code to reset your password: " + passcode;
			try
			{
				emailService.sendEmail(email, subject, body);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(passcode);
			}
			catch(Exception e)
			{
				logger.error(e);
				return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
			}
		}
		else
		{
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * this method takes in an account object which contains the new password and sets the session user to the new password
	 * Then sends the session with the new password to the service layer -> dao layer -> db which will update the password in the db
	 * 
	 * Then it invalidates the session
	 * 
	 * 
	 * @param mySession
	 * @param myAccount
	 * @return
	 */
	@PostMapping("/updatePassword")
	public ResponseEntity updatePassword(HttpSession mySession, @RequestBody Account myAccount)
	{
		
		Account currentUser = ((Account)mySession.getAttribute("currentUser"));
		
		String newPassword = myAccount.getPassword();
		
		currentUser.setPassword(newPassword);
		
		accountService.updatePassword(currentUser);
		
		mySession.invalidate();
		
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * This method takes in an account object from the body of the post
	 * 
	 * It calls the service layer -> dao layer -> db to create a new account in the dab
	 * 
	 * @param newAccount
	 * @return
	 */
	@PostMapping("/createAccount")
	public ResponseEntity createAccount(@RequestBody Account newAccount)
	{
		accountService.createNewAccount(newAccount);
		
		return new ResponseEntity(HttpStatus.CREATED);
		
	}
	
	/**
	 * This method will contact the service layer -> dao layer -> db to get all the users in the db
	 * 
	 * @return
	 */
	@PostMapping("/allUsers")
	public List<Account> getAllUsers()
	{
		
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/getUserById/{userId}")
	public Account getUserById(@PathVariable("userId") int userId)
	{
		return accountService.getAccountById(userId);
	}
	/**
	 * This method will contact the service layer -> dao layer -> db and get the users information
	 * 
	 * based on the username and if no user name is present it returns null
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("/getUserByUsername/{username}")
	public Account getUserByUsername(@PathVariable("username") String username)
	{
		return accountService.getUserAccountByUsername(username);
	}
	
	/**
	 * This method is used for when the user updates their account information
	 * 
	 * It will update the associated account in the db with the account object that was sent into the system
	 * 
	 * @param mySession
	 * @param myAccount
	 * @return
	 */
	@PostMapping("/updateProfile")
	public ResponseEntity updateMyProfile(HttpSession mySession, @RequestBody Account myAccount)
	{
		
		accountService.updateUserInfo(myAccount);
		mySession.setAttribute("currentUser", myAccount);
		return new ResponseEntity(HttpStatus.OK); 
	}
	
	/**
	 * This method is used in creating a salted string to be used as the passcode sent to the users email when resetting their password
	 * @return
	 */
	private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///CONSTRUCTOR
	@Autowired
	public SessionAccountController(AccountService accountService, EmailService emailService) {
		super();
		this.accountService = accountService;
		this.emailService = emailService;
	}
	///GETTER AND SETTER
	public AccountService getAccountService() {
		return accountService;
	}
	
	public EmailService getEmailService() {
		return emailService;
	}

	public void setAccountService(AccountService accountService, EmailService emailService) {
		this.accountService = accountService;
		this.emailService = emailService;
	}
	
}
