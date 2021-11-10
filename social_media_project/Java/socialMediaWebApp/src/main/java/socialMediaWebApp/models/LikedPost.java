package socialMediaWebApp.models;

import java.util.Objects;

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
@Table(name="user_liked_posts")
public class LikedPost 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int likedId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="liking_account_Id", nullable = false)
	private Account myAccount;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="liked_post", nullable = false)
	private Post likedPost;
	
	
	
	public LikedPost() {
		super();
	}



	public LikedPost(Account myAccount, Post likedPost) {
		super();
		this.myAccount = myAccount;
		this.likedPost = likedPost;
	}



	public LikedPost(int likedId, Account myAccount, Post likedPost) {
		super();
		this.likedId = likedId;
		this.myAccount = myAccount;
		this.likedPost = likedPost;
	}
	

	public int getLikedId() {
		return likedId;
	}



	public void setLikedId(int likedId) {
		this.likedId = likedId;
	}



	public Account getMyAccount() {
		return myAccount;
	}



	public void setMyAccount(Account myAccount) {
		this.myAccount = myAccount;
	}



	public Post getLikedPost() {
		return likedPost;
	}



	public void setLikedPost(Post likedPost) {
		this.likedPost = likedPost;
	}



	@Override
	public String toString() {
		return "\n\t\tLikedPosts [likedId=" + likedId + ", likingAccount=" + myAccount.getUsername() + ", aPost=" + likedPost.getPostId() + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(likedId, likedPost, myAccount);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikedPost other = (LikedPost) obj;
		return likedId == other.likedId && Objects.equals(likedPost, other.likedPost)
				&& Objects.equals(myAccount, other.myAccount);
	}
	
	


	

	
}
