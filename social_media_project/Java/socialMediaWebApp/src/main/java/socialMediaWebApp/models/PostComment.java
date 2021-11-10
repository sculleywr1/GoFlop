package socialMediaWebApp.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Post_Comment")
public class PostComment 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_comment_Id")
	private int postCommentId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="post_id")
	private Post currentPost;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Account commenter;
	
	@Column(name = "post_comment_contents")
	private String comment;
	
	
	public PostComment() {
		super();
	}
	
	
	
	public PostComment(Post currentPost, Account myAccount, String comment) {
		super();
		this.currentPost = currentPost;
		this.commenter = myAccount;
		this.comment = comment;
	}



	public PostComment(int postCommentId, Post currentPost, Account commenter, String comment) {
		super();
		this.postCommentId = postCommentId;
		this.currentPost = currentPost;
		this.commenter = commenter;
		this.comment = comment;
	}



	public int getPostCommentId() {
		return postCommentId;
	}



	public void setPostCommentId(int postCommentId) {
		this.postCommentId = postCommentId;
	}



	public Post getCurrentPost() {
		return currentPost;
	}



	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}



	public Account getCommenter() {
		return commenter;
	}



	public void setCommenter(Account commenter) {
		this.commenter = commenter;
	}

	

	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	@Override
	public String toString() {
		return "\n\t\tPostComment [postCommentId=" + postCommentId + ", currentPost=" + currentPost + ", commenter="
				+ commenter + ", comment=" + comment + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(comment, commenter, currentPost, postCommentId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostComment other = (PostComment) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(commenter, other.commenter)
				&& Objects.equals(currentPost, other.currentPost) && postCommentId == other.postCommentId;
	}
	
	
}
