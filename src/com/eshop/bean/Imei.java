package com.eshop.bean;

/**
 * 设备管理信息
 * @author uname
 *
 */
public class Imei {
	int id;
	String device;
	String imei;
	
	public Imei() {
	}

	public Imei(int id, String device, String imei) {
		this.id = id;
		this.device = device;
		this.imei = imei;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
}
