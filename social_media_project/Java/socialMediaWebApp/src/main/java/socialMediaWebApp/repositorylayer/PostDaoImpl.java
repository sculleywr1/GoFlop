package socialMediaWebApp.repositorylayer;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import socialMediaWebApp.models.Account;
import socialMediaWebApp.models.Post;

@Transactional
@Repository("postDaoImpl")
public class PostDaoImpl implements PostDao
{
	/*
	 * ALL METHODS IN THIS CLASS USE THE SESSIONFACTORY INTERFACE TO GET THE CURRENT SESION THEN
	 * THEN USE A SESSION METHOD OR CREATE A QUERY
	 * 
	 * 
	 */
	private SessionFactory sesFact;
	
	/**
	 * This method is used to get all posts from the database using the hql
	 * 
	 * This method will return a list of post
	 */
	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		
		List<Post> allPosts = sesFact.getCurrentSession().createQuery("from Post", Post.class).list();
				
		return allPosts; 
	}
	
	/**
	 * This method will contact the database to get all the post that are associated with the userId that is passed in
	 * It will return a list of all their post if they have any or it will return null  
	 */
	@Override
	public List<Post> getAllPostsByUserId(int userId) {

			
//		Contact the account table in the database to get the account object associated with that userid
		List<Account> myAccount = sesFact.getCurrentSession().createQuery("from Account where userId=:userId", Account.class)
				.setParameter("userId", userId)
				.list();
//		Check to see if the myaccount list is null... we don't want to attempt to contact the database passing it a null account object
		if(myAccount.isEmpty())
		{
//			we are done return null and end method
			return null;
		}
		else
		{
//			we got an object account back now contact the database and get all the post that have the account referenced in the post table 
			List<Post> userPost = sesFact.getCurrentSession().createQuery("from Post where poster=:id", Post.class)
					.setParameter("id", myAccount.get(0))
					.list();
			System.out.println(userPost);
			return userPost;
		}
		
	}
	
	/**
	 * THIS IS AN OUTDATED METHOD THAT IS NOT IN USE ANYMORE
	 */
	@Override
	public void updatePostLikes(int postId, int likes) 
	{	
	}
	
	/**
	 * This method updates the post object in the database that is the post object we passed
	 * 
	 * It is used when the number of likes on the post has changed so we update the post in the database with the new number of likes
	 */
	@Override
	public void updatePost(Post post) {
		sesFact.getCurrentSession().update(post);
		
	}
	
	/**
	 * This method is used create a new Post that will create a new post object in the database
	 * 
	 * it uses the save method to persist the post object into the database
	 */
	@Override
	public void createNewPost(Post post) {
		sesFact.getCurrentSession().save(post);
		
	}
	
	/**
	 * THIS METHOD IS NOT CURRENTLY IN USE
	 * 
	 *  BUT,
	 *  
	 *  It deletes the post from the database that matches the post object that is past in
	 */
	@Override
	public void deletePost(Post post) {
		sesFact.getCurrentSession().delete(post);
		
	}
	
	//CONSTRUCTOR
	@Autowired
	public PostDaoImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	//GETTER AND SETTER
	public SessionFactory getSesFact() {
		return sesFact;
	}
	
	public void setSesFact(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}	
}
