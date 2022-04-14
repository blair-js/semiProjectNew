package com.semi.schoolbus.model.dto;

public class Schoolbus {
	private int busDallyNo; //버스 일정 번호
	private String busContent; // 버스 일정 내용
	private String status; // 예약 가능 여부
	private int seatCount; // 잔여 좌석 수
	
	public Schoolbus() {
		// TODO Auto-generated constructor stub
	}
	
	public Schoolbus(int busDallyNo, String busContent, String status, int seatCount) {
		super();
		this.busDallyNo = busDallyNo;
		this.busContent = busContent;
		this.status = status;
		this.seatCount = seatCount;
	}
	
	public int getBusDallyNo() {
		return busDallyNo;
	}
	public void setBusDallyNo(int busDallyNo) {
		this.busDallyNo = busDallyNo;
	}
	public String getBusContent() {
		return busContent;
	}
	public void setBusContent(String busContent) {
		this.busContent = busContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	@Override
	public String toString() {
		return "Schoolbus [busDallyNo=" + busDallyNo + ", busContent=" + busContent + ", status=" + status
				+ ", searCount=" + seatCount + "]";
	}
	
	
}
