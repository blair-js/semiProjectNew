package com.semi.snack.model.dto;

public class UserPoint {

	private int userNo; //회원번호
	private int userPoint; //보유 포인트(뼈다귀)
	private int point; //간신 구매 후 포인트 
	
	public UserPoint() {
		
	}

	public UserPoint(int userNo, int userPoint, int point) {
		super();
		this.userNo = userNo;
		this.userPoint = userPoint;
		this.point = point;
	}
	
	public UserPoint(int userNo, int userPoint) {
		super();
		this.userNo = userNo;
		this.userPoint = userPoint;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "UserPoint [userNo=" + userNo + ", userPoint=" + userPoint + ", point=" + point + "]";
	}
	

	
}
