package socialMediaWebApp.repositorylayer;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import socialMediaWebApp.models.PostComment;

@Transactional
@Repository("postCommentDaoImpl")
public class PostCommentDaoImpl implements PostCommentDao
{
	/*
	 * THIS CLASS HAS NOT BEEN IMLEMENTED INTO THE PROGRAM YET
	 * 
	 * THIS METHODS ARE JUST MOCK METHODS FOR THE FUTURE METHODS
	 * 
	 * 
	 * 
	 */
	private SessionFactory sesFact;
	
	/**
	 * 
	 */
	@Override
	public List<PostComment> getAllComments() {
		// TODO Auto-generated method stub
		
		List<PostComment> postComments = sesFact.getCurrentSession().createQuery("from PostComment", PostComment.class).list();
		
		
		return postComments;
	}
	
	/**
	 * 
	 */
	@Override
	public List<PostComment> getAllUserComments(int userId) {
		// TODO Auto-generated method stub
		
		List<PostComment> userComments = sesFact.getCurrentSession().createQuery("from PostComment where commentor=:id", PostComment.class)
				.setParameter("id", userId)
				.list();
		
		
		return userComments; 
	}
	
	/**
	 * 
	 */
	@Override
	public List<PostComment> getMyComments(int userId) {
		// TODO Auto-generated method stub
		
		List<PostComment> myComments = sesFact.getCurrentSession().createQuery("from PostComment where commentor=:id", PostComment.class)
				.setParameter("id", userId)
				.list();
		
		System.out.println(myComments);
		
		return myComments;
	}
	
	/**
	 * 
	 */
	@Override
	public void updateCommentToPost(PostComment comment) {
		sesFact.getCurrentSession().update(comment);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void createCommentToPost(PostComment comment) {
		sesFact.getCurrentSession().save(comment);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void deleteCommentFromPost(PostComment comment) {
		sesFact.getCurrentSession().delete(comment);
		
	}
	
	//CONSTRUCTOR
	@Autowired
	public PostCommentDaoImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	//GETTERS AND SETTERS
	public SessionFactory getSesFact() {
		return sesFact;
	}
	
	public void setSesFact(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}
	
	
}
