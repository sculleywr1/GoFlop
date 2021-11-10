package socialMediaWebApp.servicelayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;




@Service("emailService")
public class EmailServiceImpl implements EmailService
{
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * This method creates the look of the email by using javamailsender and who to send the email to
	 * 
	 * It uses the arguments passed in to construct the layout and who receives the email
	 * 
	 * It then use javamailsender to send the email
	 */
	@Override
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
	

	
	
}
