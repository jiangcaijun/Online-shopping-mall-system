package com.eshop.bean;

/**
 * 商品图片信息
 * @author uname
 *
 */
public class ItemPic {
	int id;
	int itemId;
	String url;
	String info;
	
	
	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public ItemPic() {
	}


	public ItemPic(int id, int itemId, String url) {
		this.id = id;
		this.itemId = itemId;
		this.url = url;
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
