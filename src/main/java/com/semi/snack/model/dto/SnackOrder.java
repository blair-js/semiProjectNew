package com.semi.snack.model.dto;

import java.sql.Date;

public class SnackOrder {

	private int orderNo; //주문번호
	private int snackNo; //간식번호
	private String userId;
	private String snackName;
	private int snackOrderUserId; //간식 주문한 회원
	private Date orderDate; //구입날짜
	
	public SnackOrder() {
	
	}

	public SnackOrder(int orderNo, int snackNo, String userId, String snackName, int snackOrderUserId, Date orderDate) {
		super();
		this.orderNo = orderNo;
		this.snackNo = snackNo;
		this.userId = userId;
		this.snackName = snackName;
		this.snackOrderUserId = snackOrderUserId;
		this.orderDate = orderDate;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getSnackNo() {
		return snackNo;
	}

	public void setSnackNo(int snackNo) {
		this.snackNo = snackNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSnackName() {
		return snackName;
	}

	public void setSnackName(String snackName) {
		this.snackName = snackName;
	}

	public int getSnackOrderUserId() {
		return snackOrderUserId;
	}

	public void setSnackOrderUserId(int snackOrderUserId) {
		this.snackOrderUserId = snackOrderUserId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "SnackOrder [orderNo=" + orderNo + ", snackNo=" + snackNo + ", userId=" + userId + ", snackName="
				+ snackName + ", snackOrderUserId=" + snackOrderUserId + ", orderDate=" + orderDate + "]";
	}

	
}
