package com.eshop.bean;

/**
 * 权限信息
 * @author uname
 *
 */
public class Auth {
	int id;
	int auth;
	String url;

	public Auth() {
	}
	public Auth(int id, int auth, String url) {
		this.id = id;
		this.auth = auth;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
