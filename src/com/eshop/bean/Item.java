package com.eshop.bean;

/**
 * 商品信息
 * 
 * @author uname
 *
 */
public class Item {

	int id;
	String title;
	String type;
	int price;
	String mainPic;
	String info;
	int state;
	int num;
	int storeNum;
	int hot;
	int sale;
	int sum;//某商品的兑换总数
	
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Item() {
	}
	
	public Item(int id, String title, String type, int price, String mainPic, String info, int state, int num,
			int storeNum, int hot) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.price = price;
		this.mainPic = mainPic;
		this.info = info;
		this.state = state;
		this.num = num;
		this.storeNum = storeNum;
		this.hot = hot;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}
	
}
