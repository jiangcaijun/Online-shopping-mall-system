package com.eshop.bean;

public class AuthManage {
	String username;
	int auth;
	String url;
	
	public AuthManage() {
	}
	public AuthManage(String username, int auth, String url) {
		this.username = username;
		this.auth = auth;
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
