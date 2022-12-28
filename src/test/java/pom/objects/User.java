package pom.objects;

import io.qameta.allure.Step;

public class User {

	private String username;
	private String password;
	private String email;
	
	public User() {}

	public User(String username, String password) {

		this.username = username;
		this.password = password;
		
	}
	
	public User(String username, String password, String email) {

		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	@Step
	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	@Step
	public String getPassword() {
		return password;
	}

	@Step
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}


}
