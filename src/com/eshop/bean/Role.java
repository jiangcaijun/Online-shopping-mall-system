package com.eshop.bean;

/**
 * 员工角色信息
 * @author uname
 *
 */
public class Role {
	int id;
	int uid;
	int auth;
	
	public Role() {
	}
	
	public Role(int id, int uid, int auth) {
		this.id = id;
		this.uid = uid;
		this.auth = auth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}
	
}
