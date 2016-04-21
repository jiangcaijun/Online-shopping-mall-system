package com.eshop.bean;

import java.util.Date;

/**
 * 商品评论信息
 * @author uname
 *
 */
public class Comment {
	String id;
	int uid;
	int itemId;
	String content;
	Date time;
	Double rank;
	String username;
	String url;
	String curl;
	
	public Comment() {
	}
	
	public Comment(String id, int uid, int itemId, String content, Date time, Double rank) {
		this.id = id;
		this.uid = uid;
		this.itemId = itemId;
		this.content = content;
		this.time = time;
		this.rank = rank;
	}
	
	public Comment(String id, int uid, int itemId, String content, Date time, Double rank, String username, String url,
			String curl) {
		this.id = id;
		this.uid = uid;
		this.itemId = itemId;
		this.content = content;
		this.time = time;
		this.rank = rank;
		this.username = username;
		this.url = url;
		this.curl = curl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Double getRank() {
		return rank;
	}
	public void setRank(Double rank) {
		this.rank = rank;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	
}

