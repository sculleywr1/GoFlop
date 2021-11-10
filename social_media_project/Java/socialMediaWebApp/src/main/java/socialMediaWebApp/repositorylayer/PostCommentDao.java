package socialMediaWebApp.repositorylayer;

import java.util.List;

import socialMediaWebApp.models.PostComment;

public interface PostCommentDao 
{
	public List<PostComment> getAllComments();
	
	public List<PostComment> getAllUserComments(int userId);
	
	public List<PostComment> getMyComments(int userId);
	
	public void updateCommentToPost(PostComment comment);
	
	public void createCommentToPost(PostComment comment);
	
	public void deleteCommentFromPost(PostComment comment);
}
