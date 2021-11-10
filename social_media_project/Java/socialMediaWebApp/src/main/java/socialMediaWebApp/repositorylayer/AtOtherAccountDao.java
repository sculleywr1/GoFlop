package socialMediaWebApp.repositorylayer;

import java.util.List;

import socialMediaWebApp.models.LikedPost;

public interface AtOtherAccountDao 
{
	//gets all posts that are inside the liked post
	public List<LikedPost> getAllLikedPosts();
		
	//deletes a liked post from the table
	public void unlikePost(LikedPost likePost);
	
	//adds a liked post to the table
	public void likePost(LikedPost likePost);
}
