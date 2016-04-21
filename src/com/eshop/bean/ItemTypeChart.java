package com.eshop.bean;

public class ItemTypeChart {
	String type;
	int sum;
	
	public ItemTypeChart() {
	}
	public ItemTypeChart(String type, int sum) {
		this.type = type;
		this.sum = sum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
}
