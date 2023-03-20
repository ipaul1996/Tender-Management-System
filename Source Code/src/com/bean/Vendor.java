package com.bean;

public class Vendor {
	
	private int vendorid;
	private String email;
	private String password;
	private String name;
	private String contactno;
	
	public Vendor() {
		
	}

	public Vendor(int vendorid, String email, String password, String name, String contactno) {
		super();
		this.vendorid = vendorid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.contactno = contactno;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
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

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	@Override
	public String toString() {
		return "Vendor [vendorid=" + vendorid + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", contactno=" + contactno + "]";
	}
	
	

}
