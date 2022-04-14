package com.semi.schoolbus.model.dto;

public class UserReservation {
	private int busNo; // 버스 예약 번호
	private String busDailyNo; // 버스 일정 번호 (버스 일정 내용)
	private int resUserNo; // 예약 회원 번호
	private String resUserId; // 예약 회원 아이디
	private String resUserName; // 예약 회원 이름
	private String resUserPhone; // 예약 회원 전화번호
	
	public UserReservation() {
		// TODO Auto-generated constructor stub
	}

	public UserReservation(int busNo, String busDailyNo, int resUserNo, String resUserId, String resUserName, String resUserPhone) {
		super();
		this.busNo = busNo;
		this.busDailyNo = busDailyNo;
		this.resUserNo = resUserNo;
		this.resUserId = resUserId;
		this.resUserName = resUserName;
		this.resUserPhone = resUserPhone;
	}

	public int getBusNo() {
		return busNo;
	}

	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}

	public String getBusDailyNo() {
		return busDailyNo;
	}

	public void setBusDailyNo(String busDailyNo) {
		this.busDailyNo = busDailyNo;
	}

	public int getResUserNo() {
		return resUserNo;
	}

	public void setResUserNo(int resUserNo) {
		this.resUserNo = resUserNo;
	}

	public String getResUserName() {
		return resUserName;
	}

	public void setResUserName(String resUserName) {
		this.resUserName = resUserName;
	}

	public String getResUserPhone() {
		return resUserPhone;
	}

	public void setResUserPhone(String resUserPhone) {
		this.resUserPhone = resUserPhone;
	}
	
	public String getResUserId() {
		return resUserId;
	}

	public void setResUserId(String resUserId) {
		this.resUserId = resUserId;
	}

	@Override
	public String toString() {
		return "UserReservation [busNo=" + busNo + ", busDailyNo=" + busDailyNo + ", resUserNo=" + resUserNo
				+ ", resUserName=" + resUserName + ", resUserPhone=" + resUserPhone + "]";
	}
	
}
