package com.bean;

public class Admin {
	
	private String email;
	private String password;
	private String name;
	
	public Admin() {
		
	}

	public Admin(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", password=" + password + ", name=" + name + "]";
	}
	
	

}
