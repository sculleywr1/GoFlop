package socialMediaWebApp.models;

public class AtOtherAccount 
{
	private int AtOtherAccounts;
	private Account atAccount;
	
	public AtOtherAccount() {
		super();
	}

	public AtOtherAccount(int postId, Account atAccount) {
		super();
		this.AtOtherAccounts = postId;
		this.atAccount = atAccount;
	}

	public int getPostId() {
		return AtOtherAccounts;
	}

	public void setPostId(int postId) {
		this.AtOtherAccounts = postId;
	}

	public Account getAtAccount() {
		return atAccount;
	}

	public void setAtAccount(Account atAccount) {
		this.atAccount = atAccount;
	}

	@Override
	public String toString() {
		return "\n\nAtOtherAccounts [postId=" + AtOtherAccounts + ", atAccount=" + atAccount + "]";
	}
	
	
	
}