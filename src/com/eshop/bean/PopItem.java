package com.eshop.bean;

public class PopItem {
    String title;
    int sum;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public PopItem(String title, int sum) {
		this.title = title;
		this.sum = sum;
	}
	public PopItem() {
	}
}
