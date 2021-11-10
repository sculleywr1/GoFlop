package socialMediaWebApp.config;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig 
{
	private Logger logger = Logger.getLogger(getClass().getName());
	
    @Bean
    public JavaMailSender getJavaMailSender() 
    {	
    	try
	    {
	    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	          
	        mailSender.setUsername("kyawjohnscul@gmail.com");
	        mailSender.setPassword("Hello123!");
	          
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	          
	        return mailSender;
	    }
	    catch(Exception e)
	    {
	    	logger.error(e);
	    	return null;
	    }
    }
}
