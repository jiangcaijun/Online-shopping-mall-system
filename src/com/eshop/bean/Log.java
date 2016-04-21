package com.eshop.bean;

import java.util.Date;

/**
 * 后台日志信息
 * @author uname
 *
 */
public class Log {
	int id;
	int uid;
	Date time;
	String operate;
	String obj;
	
	public Log() {
	}
	public Log(int uid, String operate, String obj) {
		this.uid = uid;
		this.operate = operate;
		this.obj = obj;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}

}
