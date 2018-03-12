package zeroEtc_designPattern.proxy_pattern.apply;

public class User {
	public String userName;
	public String password;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
