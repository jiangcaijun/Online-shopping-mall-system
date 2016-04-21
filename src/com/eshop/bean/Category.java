package com.eshop.bean;

/**
 * 商品分类信息
 * @author uname
 *
 */
public class Category {
	int id;
	String type;
	
	public Category() {
	}

	public Category(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
