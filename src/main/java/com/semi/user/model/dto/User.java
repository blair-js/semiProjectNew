package com.semi.user.model.dto;

import java.sql.Date;

public class User {
	
	private int userNo;
	private String userId;
	private String email;
	private String userPwd;
	private String userName;
	private String phone;
	private String smsChecked;
	private String emailHash;
	private String emailChecked;
	private String gender;
	private String status;
	private Date enrollDate;
	private String cookieChecked;

	//기본 생성자
	public User() {	}

	//매개변수 생성자 
	public User(int userNo, String userId, String email, String userPwd, String userName, String phone,
			String smsChecked, String emailHash, String emailChecked, String gender, String status, Date enrollDate,
			String cookieChecked) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.email = email;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.smsChecked = smsChecked;
		this.emailHash = emailHash;
		this.emailChecked = emailChecked;
		this.gender = gender;
		this.status = status;
		this.enrollDate = enrollDate;
		this.cookieChecked = cookieChecked;
	}
	
	public User(String userId, String userPwd, String userName, String phone,
			String smsChecked, String gender, String email, String emailHash, String emailChecked) {
		super();
		this.userId = userId;
		this.email = email;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.smsChecked = smsChecked;
		this.emailHash = emailHash;
		this.emailChecked = emailChecked;
		this.gender = gender;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSmsChecked() {
		return smsChecked;
	}

	public void setSmsChecked(String smsChecked) {
		this.smsChecked = smsChecked;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	public String getEmailChecked() {
		return emailChecked;
	}

	public void setEmailChecked(String emailChecked) {
		this.emailChecked = emailChecked;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getCookieChecked() {
		return cookieChecked;
	}

	public void setCookieChecked(String cookieChecked) {
		this.cookieChecked = cookieChecked;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", email=" + email + ", userPwd=" + userPwd
				+ ", userName=" + userName + ", phone=" + phone + ", smsChecked=" + smsChecked + ", emailHash="
				+ emailHash + ", emailChecked=" + emailChecked + ", gender=" + gender + ", status=" + status
				+ ", enrollDate=" + enrollDate + ", cookieChecked=" + cookieChecked + "]";
	}	
	
}
