package com.semi.snack.model.dto;

public class Snack {
	private int userNo; 
	private int sanckNo; //간식번호
	private String sanckName; // 간식명
	private int category; // 분류번호
	private int price; //가격(필요 뼈다귀)
	private String status; //상태값
	
	private String titleImg; //사진
	
	public Snack() {
	
	}

	public Snack(int userNo, int sanckNo, String sanckName, int category, int price, String status, String titleImg) {
		super();
		this.userNo = userNo;
		this.sanckNo = sanckNo;
		this.sanckName = sanckName;
		this.category = category;
		this.price = price;
		this.status = status;
		this.titleImg = titleImg;
	}
	

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getSanckNo() {
		return sanckNo;
	}

	public void setSanckNo(int sanckNo) {
		this.sanckNo = sanckNo;
	}

	public String getSanckName() {
		return sanckName;
	}

	public void setSanckName(String sanckName) {
		this.sanckName = sanckName;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "Snack [userNo=" + userNo + ", sanckNo=" + sanckNo + ", sanckName=" + sanckName + ", category="
				+ category + ", price=" + price + ", status=" + status + ", titleImg=" + titleImg + "]";
	}
	
	
	
	
}
