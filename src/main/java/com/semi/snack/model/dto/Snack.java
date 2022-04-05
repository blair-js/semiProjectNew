package com.semi.snack.model.dto;

public class Snack {

	private int sanckNo;
	private String sanckName;
	private int category;
	private int price;
	private String status;
	
	public Snack() {
	
	}

	public Snack(int sanckNo, String sanckName, int category, int price, String status) {
		super();
		this.sanckNo = sanckNo;
		this.sanckName = sanckName;
		this.category = category;
		this.price = price;
		this.status = status;
	}
	
	
	public Snack(int sanckNo, String sanckName, int price) {
		super();
		this.sanckNo = sanckNo;
		this.sanckName = sanckName;
		this.price = price;
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

	@Override
	public String toString() {
		return "Snack [sanckNo=" + sanckNo + ", sanckName=" + sanckName + ", category=" + category + ", price=" + price
				+ ", status=" + status + "]";
	}
	
	
	
}
