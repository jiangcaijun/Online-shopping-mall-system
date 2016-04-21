package com.eshop.bean;

import java.util.Date;

/**
 * 设备信息
 * @author uname
 *
 */
public class Device {
	int id;
	String imei;
	int uid;
	Date time;
	Double onlineTime;
	Double onlineCredit;
	Double current;
	Double currentCredit;
	String device;
	
	public Device() {
	}
	
	public Device(int id, String imei, int uid, Date time, Double onlineTime, Double onlineCredit, Double current,
			Double currentCredit) {
		this.id = id;
		this.imei = imei;
		this.uid = uid;
		this.time = time;
		this.onlineTime = onlineTime;
		this.onlineCredit = onlineCredit;
		this.current = current;
		this.currentCredit = currentCredit;
	}
	
	public Device(int id, String imei, int uid, Date time, Double onlineTime, Double onlineCredit, Double current,
			Double currentCredit, String device) {
		this.id = id;
		this.imei = imei;
		this.uid = uid;
		this.time = time;
		this.onlineTime = onlineTime;
		this.onlineCredit = onlineCredit;
		this.current = current;
		this.currentCredit = currentCredit;
		this.device = device;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
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
	public Double getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Double onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Double getOnlineCredit() {
		return onlineCredit;
	}
	public void setOnlineCredit(Double onlineCredit) {
		this.onlineCredit = onlineCredit;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public Double getCurrentCredit() {
		return currentCredit;
	}
	public void setCurrentCredit(Double currentCredit) {
		this.currentCredit = currentCredit;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
}
	
	
	
