package socialMediaWebApp.servicelayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialMediaWebApp.models.Account;
import socialMediaWebApp.models.LikedPost;
import socialMediaWebApp.repositorylayer.LikedPostDao;

@Service("likedPostServiceImpl")
public class LikedPostServiceImpl implements LikedPostService
{

	private LikedPostDao likedPostDao;
	
	/**
	 * This method is used to get all posts that have been liked and the association of who like what post
	 * 
	 * It contacts the dao layer and is returned will all posts that have been liked
	 * 
	 * or if not post have been liked then it returns null
	 */
	@Override
	public List<LikedPost> getAllLikedPosts() {
		// TODO Auto-generated method stub
		return likedPostDao.getAllLikedPosts();
	}
	
	/**
	 * This method will get all the posts the user has liked based on the account object that is passed into it by the arguments
	 * 
	 * It contacts the dao layer method that is associated with it
	 * 
	 * It can return all 
	 * 
	 * 
	 * 
	 */
	@Override
	public List<LikedPost> getMyLikedPosts(Account myAccount) {
		// TODO Auto-generated method stub
		return likedPostDao.getAllMyLikedPostByUserId(myAccount);
	}
	
	/**
	 * This method contacts the dao layer to see if the postid and userid are currently linked in the likedposttable in the database
	 * 
	 * It will return true or false based on if the userid and postid have an association
	 */
	@Override
	public boolean haveILiked(int userId, int postId) {
		
		return likedPostDao.haveILiked(userId, postId);
	}
	
	/**
	 * This will call the dao layer and remove the association between the two nested objects with likepost object
	 * 			object 1: Account
	 * 			object 2: post
	 * It will remove that association in the database under the likepost table through the logic in the dao layer
	 */
	@Override
	public void unlikePost(LikedPost likePost) {
		// TODO Auto-generated method stub
		
		likedPostDao.unlikePost(likePost);
		
	}
	
	/**
	 * This will call the dao layer and add an association between the two nested objects within the likepost object
	 * 		object 1: account
	 * 		object 2: post
	 * 
	 * It will contact the dao layer associated method
	 */
	@Override
	public void likePost(LikedPost likePost) {
		// TODO Auto-generated method stub
		
		likedPostDao.likePost(likePost);
		
	}
	

	
	@Autowired
	public LikedPostServiceImpl(LikedPostDao likePostDao) {
		super();
		this.likedPostDao = likePostDao;
	}
	
	//GETTER AND SETTER
	public LikedPostDao getLikePostDao() {
		return likedPostDao;
	}

	public void setLikePostDao(LikedPostDao likePostDao) {
		this.likedPostDao = likePostDao;
	}	
}
