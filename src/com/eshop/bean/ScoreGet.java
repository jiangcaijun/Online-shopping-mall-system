package com.eshop.bean;

import java.util.Date;

/**
 * 用户积分信息
 * @author uname
 *
 */
public class ScoreGet {
	int id;
	int uid;
	int source;
	Date time;
	int num;
	public ScoreGet() {
	}
	public ScoreGet(int id, int uid, int source, Date time, int num) {
		this.id = id;
		this.uid = uid;
		this.source = source;
		this.time = time;
		this.num = num;
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
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
