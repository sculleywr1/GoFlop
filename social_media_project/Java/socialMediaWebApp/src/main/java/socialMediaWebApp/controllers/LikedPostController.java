package socialMediaWebApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialMediaWebApp.models.LikedPost;
import socialMediaWebApp.models.Post;
import socialMediaWebApp.servicelayer.LikedPostService;
import socialMediaWebApp.servicelayer.PostService;

@RequestMapping("like")
@RestController
@CrossOrigin(origins="http://34.71.84.231:4200", allowCredentials = "true")
public class LikedPostController 
{
	private LikedPostService likedService;
	private PostService postService;
	
	
	/**
	 * This method will get all liked post from the database through a get request when the endpoint is pinged
	 * 
	 * @return
	 */
	@GetMapping("/allLikedPosts")
	public List<LikedPost> getAllLikedPost()
	{
		return likedService.getAllLikedPosts();
	}
	
	/**
	 * This method will get all the liked post by the userid  from the database through a get request using a path variable
	 * 
	 *  THIS METHOD IS NOT IMPLEMENTED
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("/myLikedPost/{userId}")
	public List<LikedPost> getMyLikedPost(@PathVariable("userId") int userId)
	{
		return null;
	}
	
	/**
	 * This method will update the post that is based in through the post request body using the likedpost object
	 * 
	 * It will auto increase the amount of likes on the post by 1 and link the the liked post to user account nested inside the likedpost object
	 * 
	 * @param likedPost
	 * @return
	 */
	@PostMapping("/likePost")
	public ResponseEntity likePost(@RequestBody LikedPost likedPost)
	{
		try
		{
			
			Post p = likedPost.getLikedPost();
			
			int likes = p.getLikes() + 1;
			
			p.setLikes(likes);
			
			likedService.likePost(likedPost);
			
			postService.updatePost(p);
			
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.METHOD_FAILURE);
		}
	}
	
	/**
	 * This method will unlike a post based on the post object that is passed in through the post httprequest 
	 * 
	 * It will automatically decrease the amount of likes on the post object in the db and remove the user who unliked the post from the likedpost table
	 * 
	 * @param likedPost
	 * @return
	 */
	@PostMapping("/unlikePost")
	public ResponseEntity unlikePost(@RequestBody LikedPost likedPost)
	{
		Post p = likedPost.getLikedPost();
		
		int likes = (p.getLikes() >= 1)? p.getLikes()-1 : 0;
		
		p.setLikes(likes);
		
		likedService.likePost(likedPost);
		
		postService.updatePost(p);
		
		return new ResponseEntity(HttpStatus.OK);
	}

	
	
	
	@Autowired
	public LikedPostController(LikedPostService likedService, PostService postService) {
		super();
		this.likedService = likedService;
		this.postService = postService;
	}

	public LikedPostService getLikedService() {
		return likedService;
	}

	public void setLikedService(LikedPostService likedService) {
		this.likedService = likedService;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	
	
	
}
