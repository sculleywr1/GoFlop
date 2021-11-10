package socialMediaWebApp.servicelayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialMediaWebApp.models.Post;
import socialMediaWebApp.repositorylayer.PostDao;

@Service("postServiceImpl")
public class PostServiceImpl implements PostService
{
	
	private PostDao postDao;
	
	/**
	 * This method will return all the posts that our in the database
	 * 
	 * It will either return all post in the database or return none
	 * 
	 * If it does return a list of post it will set the account object nested within the post object passwords to null so it is not passed up
	 */
	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		List<Post> userPosts = postDao.getAllPosts();
		if(userPosts.isEmpty())
		{
			return null;
		}
		else
		{
			for(Post p : userPosts)
			{
				p.getPoster().setPassword(null);
			}
		}
		return userPosts;
	}
	
	/**
	 *This method will return all posts that have a reference to the userid passed in
	 *
	 *It will either return all posts or return none
	 * 
	 * If it does return a list of post it will set the account object nested within the post object passwords to null so it is not passed up
	 * 
	 */
	@Override
	public List<Post> getAllPostsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Post> userPosts = postDao.getAllPostsByUserId(userId);
		if(userPosts.isEmpty())
		{
			return null;
		}
		else
		{
			for(Post p : userPosts)
			{
				p.getPoster().setPassword(null);
			}
		}
		return userPosts;
	}
	
	/**
	 * THIS METHOD IS NOT IMPLEMENTED
	 * IT HAS BEEN DEPRECATED
	 */
	@Override
	public void updatePostLikes(int postId, int likes) {
		postDao.updatePostLikes(postId, likes);	
	}
	
	/**
	 * This method is used to update the post object that is passed in
	 * 
	 *  It contacts the dao layer method associated
	 *  
	 *  It has not yet been implemented in the front end
	 */
	@Override
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		
		postDao.updatePost(post);
	}
	
	/**
	 * This method will create a new post object in the database by calling the associated dao layer
	 * 
	 * 
	 */
	@Override
	public void createNewPost(Post post) {
		// TODO Auto-generated method stub
		
		postDao.createNewPost(post);
	}
	
	/**
	 * This method will delete a post object in the database by calling the associated dao layer method
	 */
	@Override
	public void deletePost(Post post) {
		// TODO Auto-generated method stub
		
		postDao.deletePost(post);
	}

	//CONSTRUCTOR
	@Autowired
	public PostServiceImpl(PostDao postDao) {
		super();
		this.postDao = postDao;
	}
	
	//GETTER AND SETTER
	public PostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}


	
	
	
	

}
