package com.semi.qna.model.dto;

import java.sql.Date;

public class QnaReply {
	
	private int qnaReplyNo;
	private int refQno;
	private String replyWriter; // 조회시 : 작성자 이름,  댓글작성시 : 회원번호로 쓰일 것!!
	private String qnaReplyContent;
	private Date createDate;
	private String status;
	private int rowNo;
	
	public QnaReply() {
		// TODO Auto-generated constructor stub
	}

	public QnaReply(int qnaReplyNo, int refQno, String replyWriter, String qnaReplyContent, Date createDate,
			String status, int rowNo) {
		super();
		this.qnaReplyNo = qnaReplyNo;
		this.refQno = refQno;
		this.replyWriter = replyWriter;
		this.qnaReplyContent = qnaReplyContent;
		this.createDate = createDate;
		this.status = status;
		this.rowNo = rowNo;
	}

	public int getQnaReplyNo() {
		return qnaReplyNo;
	}

	public void setQnaReplyNo(int qnaReplyNo) {
		this.qnaReplyNo = qnaReplyNo;
	}

	public int getRefQno() {
		return refQno;
	}

	public void setRefQno(int refQno) {
		this.refQno = refQno;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public String getQnaReplyContent() {
		return qnaReplyContent;
	}

	public void setQnaReplyContent(String qnaReplyContent) {
		this.qnaReplyContent = qnaReplyContent;
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

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	@Override
	public String toString() {
		return "QnaReply [qnaReplyNo=" + qnaReplyNo + ", refQno=" + refQno + ", replyWriter=" + replyWriter
				+ ", qnaReplyContent=" + qnaReplyContent + ", createDate=" + createDate + ", status=" + status
				+ ", rowNo=" + rowNo + "]";
	}
	
}
