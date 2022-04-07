package com.semi.user.model.dto;

public class Dog {

	private int dogNo;
	private int userNo;
	private String className;
	private int category; 
	private String dogName;
	private int dogAge;
	private String dogGender;
	private String memo;
	private String wating;
	
	public Dog() {	}
	
	public Dog(int userNo, String className, String dogName, int dogAge, String dogGender,
			String memo, String wating) {
		super();
		this.userNo = userNo;
		this.className = className;
		this.dogName = dogName;
		this.dogAge = dogAge;
		this.dogGender = dogGender;
		this.memo = memo;
		this.wating = wating;
	}

	public Dog(int dogNo, int userNo, String className, int category, String dogName, int dogAge, String dogGender,
			String memo, String wating) {
		super();
		this.dogNo = dogNo;
		this.userNo = userNo;
		this.className = className;
		this.category = category;
		this.dogName = dogName;
		this.dogAge = dogAge;
		this.dogGender = dogGender;
		this.memo = memo;
		this.wating = wating;
	}

	public int getDogNo() {
		return dogNo;
	}

	public void setDogNo(int dogNo) {
		this.dogNo = dogNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public int getDogAge() {
		return dogAge;
	}

	public void setDogAge(int dogAge) {
		this.dogAge = dogAge;
	}

	public String getDogGender() {
		return dogGender;
	}

	public void setDogGender(String dogGender) {
		this.dogGender = dogGender;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getWating() {
		return wating;
	}

	public void setWating(String wating) {
		this.wating = wating;
	}

	@Override
	public String toString() {
		return "Dog [dogNo=" + dogNo + ", userNo=" + userNo + ", className=" + className + ", category=" + category
				+ ", dogName=" + dogName + ", dogAge=" + dogAge + ", dogGender=" + dogGender + ", memo=" + memo
				+ ", wating=" + wating + "]";
	}
	
}
