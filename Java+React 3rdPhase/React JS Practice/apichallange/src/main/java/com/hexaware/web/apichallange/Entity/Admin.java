package com.hexaware.web.apichallange.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue
	int adminid;
	public Admin(int adminid, String username, String password) {
		super();
		this.adminid = adminid;
		this.username = username;
		this.password = password;
	}
	
	public Admin() {
		
	}
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", username=" + username + ", password=" + password + "]";
	}
	String username;
	String password;
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
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
	
}
