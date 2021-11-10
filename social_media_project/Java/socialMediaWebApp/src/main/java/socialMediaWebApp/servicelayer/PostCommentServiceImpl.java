package socialMediaWebApp.servicelayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialMediaWebApp.models.PostComment;
import socialMediaWebApp.repositorylayer.PostCommentDao;

@Service("postCommentServiceImpl")
public class PostCommentServiceImpl implements PostCommentService
{
	/*
	 * ALL METHODS IN THIS SERIVCE CLASS ARE NOT IN USE AND ARE NOT FINALIZED AS THEY ARE MOCK UPS FOR BASE LEVEL FOR NEXT IMPLEMENATION
	 * 
	 * THEY ALL JUST CALL THE ASSOCIATED CLASS INSIDE THE DAO LAYER AT THIS TIME
	 * 
	 * CHECK THE DAO LAYER FOR MORE INFORMATION ON WHAT THEY RETURN AND WHAT THE DAO LAYER DOES WITH THE INPUTS
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	/**
	 * 
	 */
	private PostCommentDao postCommentDao;
	
	/**
	 * 
	 */
	@Override
	public List<PostComment> getAllComments() {
		// TODO Auto-generated method stub
		return postCommentDao.getAllComments();
	}
	
	/**
	 * 
	 */
	@Override
	public List<PostComment> getAllUserComments(int userId) {
		// TODO Auto-generated method stub
		return postCommentDao.getAllUserComments(userId);
	}
	
	/**
	 * 
	 */
	@Override
	public List<PostComment> getMyComments(int userId) {
		// TODO Auto-generated method stub
		return postCommentDao.getMyComments(userId);
	}
	
	/**
	 * 
	 */
	@Override
	public void updateCommentToPost(PostComment comment) {
		// TODO Auto-generated method stub
		postCommentDao.updateCommentToPost(comment);
	}
	
	/**
	 * 
	 */
	@Override
	public void createCommentToPost(PostComment comment) {
		// TODO Auto-generated method stub
		postCommentDao.createCommentToPost(comment);
	}
	
	/**
	 * 
	 */
	@Override
	public void deleteCommentFromPost(PostComment comment) {
		// TODO Auto-generated method stub
		postCommentDao.deleteCommentFromPost(comment);
		
	}

	//CONSTRUCTOR
	@Autowired
	public PostCommentServiceImpl(PostCommentDao postCommentDao) {
		super();
		this.postCommentDao = postCommentDao;
	}
	
	//GETTER AND SETTER
	public PostCommentDao getPostCommentDao() {
		return postCommentDao;
	}
	
	public void setPostCommentDao(PostCommentDao postCommentDao) {
		this.postCommentDao = postCommentDao;
	}

	
	
}
