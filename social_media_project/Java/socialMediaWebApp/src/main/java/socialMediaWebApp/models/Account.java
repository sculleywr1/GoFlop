package socialMediaWebApp.models;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="User_Account_Table")
public class Account 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "user_username",unique = true, nullable = false)
	private String username;
	
	@Column(name = "user_password", nullable = false)
	private String password;
	
	//will force this to be the the username if no display name is entered
	@Column(name = "user_display_name")
	private String displayName;
	
	@Column(name= "user_email")
	private String userEmail;
	
	@Column(name = "user_profile_pic_url")
	private String profilePicURL;
	
	@Column(name = "user_first_name")
	private String firstName;
	
	@Column(name = "user_last_name")
	private String lastName;
	
	@Column(name = "user_gender")
	private String gender;
	
	@Column(name = "user_dob")
	private String dOB;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL
			, orphanRemoval = true, mappedBy="postId")
	private List<Post> myPosts;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "likedId", cascade = CascadeType.ALL
			,orphanRemoval = true)
	private List<LikedPost> likedPosts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "postCommentId", fetch=FetchType.LAZY , cascade = CascadeType.ALL
			, orphanRemoval = true)
	private List<PostComment> myComments;
	
	
	//no args constructor
	public Account() {
		super();
	}
	public Account(int userId)
	{
		this.userId = userId;
	}
	public Account(int userId, String username)
	{
		this.userId = userId;
		this.username = username;
	}
	public Account(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
			
	public Account(String username, String password, String displayName, String profilePicURL, String first_name,
			String last_name, String gender, String dOB) {
		super();
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.profilePicURL = profilePicURL;
		this.firstName = first_name;
		this.lastName = last_name;
		this.gender = gender;
		this.dOB = dOB;
	}
	
	public Account(int userId, String username, String password, String displayName, String userEmail, String profilePicURL,
			String firstName, String lastName, String gender, String dOB) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.userEmail = userEmail;
		this.profilePicURL = profilePicURL;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dOB = dOB;
	}
	public Account(int userId, String username, String password, String displayName, String userEmail,
			String profilePicURL, String firstName, String lastName, String gender, String dOB, List<Post> myPosts,
			List<LikedPost> likedPosts, List<PostComment> myComments) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.userEmail = userEmail;
		this.profilePicURL = profilePicURL;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dOB = dOB;
		this.myPosts = myPosts;
		this.likedPosts = likedPosts;
		this.myComments = myComments;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getProfilePicURL() {
		return profilePicURL;
	}
	public void setProfilePicURL(String profilePicURL) {
		this.profilePicURL = profilePicURL;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getdOB() {
		return dOB;
	}
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	public List<Post> getMyPosts() {
		return myPosts;
	}
	public void setMyPosts(List<Post> myPosts) {
		this.myPosts = myPosts;
	}
	public List<LikedPost> getLikedPosts() {
		return likedPosts;
	}
	public void setLikedPosts(List<LikedPost> likedPosts) {
		this.likedPosts = likedPosts;
	}
	public List<PostComment> getMyComments() {
		return myComments;
	}
	public void setMyComments(List<PostComment> myComments) {
		this.myComments = myComments;
	}
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", username=" + username + ", password=" + password + ", displayName="
				+ displayName + ", userEmail=" + userEmail + ", profilePicURL=" + profilePicURL + ", firstName="
				+ firstName + ", lastName=" + lastName + ", gender=" + gender + ", dOB=" + dOB + ", myPosts=" + myPosts
				+ ", likedPosts=" + likedPosts + ", myComments=" + myComments + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(dOB, displayName, firstName, gender, lastName, likedPosts, myComments, myPosts, password,
				profilePicURL, userEmail, userId, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(dOB, other.dOB) && Objects.equals(displayName, other.displayName)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(likedPosts, other.likedPosts)
				&& Objects.equals(myComments, other.myComments) && Objects.equals(myPosts, other.myPosts)
				&& Objects.equals(password, other.password) && Objects.equals(profilePicURL, other.profilePicURL)
				&& Objects.equals(userEmail, other.userEmail) && userId == other.userId
				&& Objects.equals(username, other.username);
	}
	
	
	
}
