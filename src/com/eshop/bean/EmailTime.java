package com.eshop.bean;

import java.util.Date;

/**
 * 库存不足时发送邮件次数控制信息
 * @author uname
 *
 */
public class EmailTime {
	int id;
	int itemId;
	Date niticeTime;
	
	public EmailTime() {
	}
	
	public EmailTime(int id, int itemId, Date niticeTime) {
		this.id = id;
		this.itemId = itemId;
		this.niticeTime = niticeTime;
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
	public Date getNiticeTime() {
		return niticeTime;
	}
	public void setNiticeTime(Date niticeTime) {
		this.niticeTime = niticeTime;
	}
	
}
