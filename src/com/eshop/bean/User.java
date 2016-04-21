package com.eshop.bean;


/**
 * 用户信息
 * @author uname
 *
 */
public class User {
	 int id;
	 String username;
	 String password;
	 String tel;
	 String  url;
	 int credit;
	 String email;
	 int auth;
	 int del;//delete为关键字不可用
	
	public User() {
	}

	public User(int id, String username, String password, String tel, String url, int credit, String email, int auth,
			int del) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.url = url;
		this.credit = credit;
		this.email = email;
		this.auth = auth;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
