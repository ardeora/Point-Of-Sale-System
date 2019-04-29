
public class User {

	private String username;
	private String password;
	private int authorized;
	
	public User(String username, String password, int authorized) {
		this.username = username;
		this.password = password;
		this.authorized = authorized;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthorized() {
		return authorized;
	}
	
	public boolean usernameMatch(String str) {
		if (username.compareTo(str) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean passwordMatch(String str) {
		if (password.compareTo(str) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
