package com.eshop.bean;

import java.util.Date;

/**
 * 积分兑换信息
 * @author uname
 *
 */
public class Exchange {
	int id;
	int itemId;
	int price;
	int num;
	int state;
	int uid;
	Date time;
	String title;
	String info;
	String type;
	String mainPic;
	
	
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Exchange() {
	}
	public Exchange(int id, int itemId, int price, int num, int state, int uid, Date time,String title,String info,String mainPic) {
		this.id = id;
		this.itemId = itemId;
		this.price = price;
		this.num = num;
		this.state = state;
		this.uid = uid;
		this.time = time;
		this.title = title;
		this.info = info;
		this.mainPic = mainPic;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	
	
	
	
}
