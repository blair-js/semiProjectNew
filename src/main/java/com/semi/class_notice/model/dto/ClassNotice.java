package com.semi.class_notice.model.dto;

import java.sql.Date;

public class ClassNotice {
	
	private int classNoticeNo; // 알림장 게시글 번호
	private String noticeWriter; // 작성자 회원 번호 (번호 or 회원아이디)
	private String className; // 알림장 반 이름
	private int category; // 분류 번호 (게시판 별 번호 알림장은 5)
	private String classNoticeTitle; // 게시글 제목
	private String classNoticeContent; // 게시글 내용
	private Date createDate; // 작성일
	private String status; // 상태값 (기본값 'Y' , 'N' 되면 조회 X)
	private int count; // 조회수
	
	private String titleImg; // 게시글 썸네일 이미지 (체인지네임)
	
	public ClassNotice() {
		// TODO Auto-generated constructor stub
	}

	public ClassNotice(int classNoticeNo, String noticeWriter, String className, int category, String classNoticeTitle,
			String classNoticeContent, Date createDate, String status, int count, String titleImg) {
		super();
		this.classNoticeNo = classNoticeNo;
		this.noticeWriter = noticeWriter;
		this.className = className;
		this.category = category;
		this.classNoticeTitle = classNoticeTitle;
		this.classNoticeContent = classNoticeContent;
		this.createDate = createDate;
		this.status = status;
		this.count = count;
	}
	

	public int getClassNoticeNo() {
		return classNoticeNo;
	}

	public void setClassNoticeNo(int classNoticeNo) {
		this.classNoticeNo = classNoticeNo;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
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

	public String getClassNoticeTitle() {
		return classNoticeTitle;
	}

	public void setClassNoticeTitle(String classNoticeTitle) {
		this.classNoticeTitle = classNoticeTitle;
	}

	public String getClassNoticeContent() {
		return classNoticeContent;
	}

	public void setClassNoticeContent(String classNoticeContent) {
		this.classNoticeContent = classNoticeContent;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "ClassNotice [classNoticeNo=" + classNoticeNo + ", noticeWriter=" + noticeWriter + ", className="
				+ className + ", category=" + category + ", classNoticeTitle=" + classNoticeTitle
				+ ", classNoticeContent=" + classNoticeContent + ", createDate=" + createDate + ", status=" + status
				+ ", count=" + count + ", titleImg=" + titleImg + "]";
	}
	
	
	
}
