package com.semi.notice.model.dto;

import java.sql.Date;

public class Notice {
	
	private int noticeNo;			//공지사항번호
	private String noticeWriter;		//작성자회원번호
	private int category;			//분류번호
	private String noticeTitle;		//공지사항제목
	private String noticeContent;	//공지사항내용
	private int count;				//조회수
	private Date createDate;		//작성일
	private String status;			//상태값(Y/N)
	
	//기본 생성자
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	public Notice(int noticeNo, String noticeTitle, String noticeWriter, int count, Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.count = count;
		this.createDate = createDate;
	}	
	
	public Notice(int noticeNo, String noticeTitle, String noticeWriter, Date createDate, int count, String noticeContent) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.count = count;
		this.createDate = createDate;
		this.noticeContent = noticeContent;
	}	

	public Notice(int noticeNo, String noticeWriter, int category, String noticeTitle, String noticeContent, int count,
			Date createDate, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeWriter = noticeWriter;
		this.category = category;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeWriter=" + noticeWriter + ", category=" + category
				+ ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent + ", count=" + count
				+ ", createDate=" + createDate + ", status=" + status + "]";
	}
	
}
