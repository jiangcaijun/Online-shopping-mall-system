package com.eshop.bean;

import java.util.Date;

/**
 * 用户登录验证码信息
 * @author uname
 *
 */
public class VerifyCode {
	int id;
	int uid;
	String code;
	Date expireDate;
	
	
	public VerifyCode() {
	}
	public VerifyCode(int id, int uid, String code, Date expireDate) {
		this.id = id;
		this.uid = uid;
		this.code = code;
		this.expireDate = expireDate;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	
}
