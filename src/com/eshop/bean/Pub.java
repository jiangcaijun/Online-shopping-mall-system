package com.eshop.bean;

import java.util.Date;

/**
 * 商品发布信息
 * @author uname
 *
 */
public class Pub {
	int id;
	int itemId;
	Date startTime;
	Date endTime;
	
	public Pub() {
	}
	
	public Pub(int id, int itemId, Date startTime, Date endTime) {
		this.id = id;
		this.itemId = itemId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}