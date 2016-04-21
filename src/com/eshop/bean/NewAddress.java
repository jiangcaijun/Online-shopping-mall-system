package com.eshop.bean;

/**
 * 新增用户地址信息
 * @author uname
 *
 */
public class NewAddress {
	int id;
	int uid;
	String tel;
	String city;
	String address;
	int  postcode;
	String gainName;
	int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGainName() {
		return gainName;
	}

	public void setGainName(String gainName) {
		this.gainName = gainName;
	}

	public NewAddress() {
	}
	
	public NewAddress(int id, int uid, String tel, String city, String address, int postcode,String gainName,int status) {
		this.id = id;
		this.uid = uid;
		this.tel = tel;
		this.city = city;
		this.address = address;
		this.postcode = postcode;
		this.gainName = gainName;
		this.status = status;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
	
}
