package socialMediaWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import socialMediaWebApp.models.PostComment;
import socialMediaWebApp.servicelayer.PostCommentService;

@Controller("comment")
@RestController
public class PostCommentController 
{
	private PostCommentService postCommentService;
	
	/**
	 * This method is currently not in use
	 * 
	 * @param postComment
	 */
	//create comment
	@PostMapping("/comment")
	public void commentOnPost(@RequestBody PostComment postComment)
	{
		postCommentService.createCommentToPost(postComment);
	}
	
	/**
	 * This method is currently not in use
	 * 
	 * @param postComment
	 */
	@PostMapping("/updateComment")
	public void updateCommentOnPost(@RequestBody PostComment postComment)
	{
		postCommentService.updateCommentToPost(postComment);
	}
	
	/**
	 * This method is currently not in use
	 * 
	 * @param postComment
	 */
	@PostMapping("/deleteComment")
	public void deleteCommentOnPost(@RequestBody PostComment postComment)
	{
		postCommentService.deleteCommentFromPost(postComment);
	}
	
	
	
	@Autowired
	public PostCommentController(PostCommentService postCommentService) {
		super();
		this.postCommentService = postCommentService;
	}

	public PostCommentService getPostCommentService() {
		return postCommentService;
	}

	public void setPostCommentService(PostCommentService postCommentService) {
		this.postCommentService = postCommentService;
	}
}
