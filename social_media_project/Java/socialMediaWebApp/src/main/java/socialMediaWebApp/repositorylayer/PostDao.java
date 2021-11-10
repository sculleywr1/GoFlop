package socialMediaWebApp.repositorylayer;

import java.util.List;

import socialMediaWebApp.models.Post;

public interface PostDao 
{
	public List<Post> getAllPosts();
	
	public List<Post> getAllPostsByUserId(int userId);
	
	public void updatePostLikes(int postId, int likes);
	public void updatePost(Post post);
	
	public void createNewPost(Post post);
	
	
	public void deletePost(Post post);
}
