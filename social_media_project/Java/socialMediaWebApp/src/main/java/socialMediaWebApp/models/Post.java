package socialMediaWebApp.models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Users_Post_Table")
public class Post 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	private int postId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_Id", nullable = false)
	private Account poster;
	
	@Column(name="post_date")
	@Temporal(TemporalType.DATE)
	private Date postDate = new Date();
	
	@Column(name="post_contents", nullable = false)
	private String contents;

	@Column(name="post_number_likes")
	private int likes;
	
	@Column(name ="post_pic_url")
	private String postURL;
	
	
	@OneToMany(mappedBy = "postCommentId", fetch=FetchType.LAZY, cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<PostComment> postComments;

	public Post() {
		super();
	}
	
		
	public Post(Account myAccount, String contents, int likes, String postURL) {
		super();
		this.poster = myAccount;
		this.contents = contents;
		this.likes = likes;
		this.postURL = postURL;
	}
	
	public Post(int postId, Account myAccount, String contents, int likes, String postURL) 
	{
		super();
		this.postId = postId;
		this.poster = myAccount;
		this.contents = contents;
		this.likes = likes;
		this.postURL = postURL;
	}

	public Post(Account myAccount, String contents, int likes, String postURL, List<PostComment> postComments) {
		super();
		this.poster = myAccount;
		this.contents = contents;
		this.likes = likes;
		this.postURL = postURL;
		this.postComments = postComments;
	}

	public Post(int postId, Account myAccount, String contents, int likes, String postURL,
			List<PostComment> postComments) {
		super();
		System.out.println("here1231213");
		this.postId = postId;
		this.poster = myAccount;
		this.contents = contents;
		this.likes = likes;
		this.postURL = postURL;
		this.postComments = postComments;
	}





	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public Account getPoster() {
		return poster;
	}


	public void setPoster(Account poster) {
		this.poster = poster;
	}


	public Date getPostDate() {
		return postDate;
	}


	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public String getPostURL() {
		return postURL;
	}


	public void setPostURL(String postURL) {
		this.postURL = postURL;
	}


	public List<PostComment> getPostComments() {
		return postComments;
	}


	public void setPostComments(List<PostComment> postComments) {
		this.postComments = postComments;
	}


	@Override
	public String toString() {
		return "\n\tPost [postId=" + postId + ", poster=" + poster.getUsername() + ", postDate=" + postDate + ", contents=" + contents
				+ ", likes=" + likes + ", postURL=" + postURL + ", postComments=" + postComments + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(contents, likes, postComments, postDate, postId, postURL, poster);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(contents, other.contents) && likes == other.likes
				&& Objects.equals(postComments, other.postComments) && Objects.equals(postDate, other.postDate)
				&& postId == other.postId && Objects.equals(postURL, other.postURL)
				&& Objects.equals(poster, other.poster);
	}
	
	
	
	
	
}
