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
import socialMediaWebApp.models.LikedPost;

@Transactional
@Repository("likedPostDaoImpl")
public class LikedPostDaoImpl implements LikedPostDao
{
	private SessionFactory sesFact;
	
	/**
	 * This method will get all the liked post from the data base using
	 * 	HQL query and setparameters to prevent sql injection
	 * 
	 * It will return a list of likepost that have two nested objects account and post
	 */
	@Override
	public List<LikedPost> getAllLikedPosts() 
	{
		List<LikedPost> likedPosts = sesFact.getCurrentSession().createQuery("from LikedPost", LikedPost.class).list();
		
		return likedPosts;
	}
	
	/**
	 * THIS METHOD IS NOT IMPLEMENTED 
	 */
	@Override
	public List<LikedPost> getAllMyLikedPostByUserId(Account myAccount) 
	{
		return null;
	}
	
	/**
	 * THIS METHOD IS NOT IMPLEMENTED
	 */
	@Override
	public boolean haveILiked(int userId, int postId) 
	{		
		return  true; /*(lp.isEmpty())? false : true;*/
	}
	
	/**
	 * This method will contact the database and attempt to remove the likedpost junction from the likedpost table
	 * There are two nested objects within the likedpost object
	 * 	Account
	 * 	Post
	 *
	 *	It returns void
	 */
	@Override
	public void unlikePost(LikedPost likePost) 
	{
		sesFact.getCurrentSession().delete(likePost);		
	}
	
	/**
	 * This method will contact the database and add a new likedpost junction of the two nested objects
	 * 	Account
	 * 	Post
	 * 
	 *  It will persist the likedpost object into the likedpost table making an connection between the account and the post it like in the database
	 */
	@Override
	public void likePost(LikedPost likePost) 
	{
		sesFact.getCurrentSession().save(likePost);
		
	}
		
	
	//CONSTRUCTOR
	@Autowired
	public LikedPostDaoImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	//GETTER AND SETTERS
	public SessionFactory getSesFact() {
		return sesFact;
	}
	
	public void setSesFact(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}	
	
}
