package socialMediaWebApp.repositorylayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import socialMediaWebApp.models.Account;
import socialMediaWebApp.models.Post;
 

@Transactional
public class PostDaoTest 
{
	
	public static ApplicationContext appContext = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
	PostDao postDaoImpl = 
			appContext.getBean("postDaoImpl", PostDao.class);
	
	@Test
	@DisplayName("getAllPostsByUserIdTest")
	public void getAllPostsByUserIdTest()
	{

		int userId = 2;
		
		List<Post> allPost = postDaoImpl.getAllPostsByUserId(userId);
		

		
		List<Post> posts = postDaoImpl.getAllPostsByUserId(userId);

		
		assertEquals(allPost, posts);
	}
	

	
}
