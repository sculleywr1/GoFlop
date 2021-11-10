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

import socialMediaWebApp.models.Post;
import socialMediaWebApp.servicelayer.PostService;

@RequestMapping("/post")
@RestController
@CrossOrigin(origins="http://34.71.84.231:4200", allowCredentials = "true")
public class PostController 
{
	private PostService postService;
	
	
	/**
	 * This method calls the post service layer - > dao layer -> db
	 * 
	 * It will create a new post in the database
	 * 
	 * @param newPost
	 * @return
	 */
	@PostMapping("/createPost")
	public ResponseEntity createPost(@RequestBody Post newPost)
	{
		postService.createNewPost(newPost);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	/**
	 * This method will contact the post service layer - > dao layer -db
	 * 
	 * will return all post in the db
	 * 
	 * 
	 * @return
	 */
	@PostMapping("/allPosts")
	public List<Post> getAllPosts()
	{
		
		List<Post> allPosts = postService.getAllPosts();
		
		if(allPosts.isEmpty())
		{
			return null;
		}
		{
			return allPosts;
		}
	}
	
	/**
	 * This method will contact the service layer - > dao layer -> db
	 * 
	 * Will update the amount of likes on the post object that is passed in within the db
	 * 
	 * 
	 * @param post
	 */
	@PostMapping("/likePost")
	public void likePost(@RequestBody Post post)
	{
		postService.updatePost(post);
	}
	
	
	
	/**
	 *  This method will contact the service layer - > dao layer -> db
	 *  
	 *  Will get all the post from the database based on the userid in the post table 
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/userPosts/{userId}")
	public List<Post> getUserPost(@PathVariable("userId") int userId)
	{
		List<Post> userPosts = postService.getAllPostsByUserId(userId);
		
		if(userPosts.isEmpty())
		{
			return null;
		}
		else
		{
			return userPosts;
		}
	}
	
	/**
	 *  This method will contact the service layer - > dao layer -> db
	 *  
	 *  This method will delete the post from the database based on the post object that is past in through the post request body
	 * 
	 * @param post
	 * @return
	 */
	@PostMapping("/deletePost")
	public ResponseEntity deleteUserPost(@RequestBody Post post)
	{
		postService.deletePost(post);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 *  This method will contact the service layer - > dao layer -> db
	 * 
	 * This method will update the post in the database based on the post object that is passed in through the post request body
	 * 
	 * @param post
	 * @return
	 */
	@PostMapping("/updatePost")
	public ResponseEntity updateUserPost(@RequestBody Post post)
	{
		postService.updatePost(post);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	
	
	
	
	@Autowired
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	
}
