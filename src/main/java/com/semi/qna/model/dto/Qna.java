package com.semi.qna.model.dto;

import java.sql.Date;

public class Qna {
	
	private int qnaNo;			 	//q&a번호
	private String qnaWriter;		//작성자회원번호
	private int category;			//분류번호
	private String qnaTitle;		//q&a제목
	private String qnaContent;		//q&a내용
	private int count;				//조회수
	private Date createDate;		//작성일
	private String status;			//상태값(Y/N)
	private String qnaPwd;				//비밀글 비밀번호
	private String qnaSecret;		//비밀글 여부
	private String answer;			//답변 여부
	private int rowNo;				//list에서 rownum으로 게시판 번호를 주기 위함
	
	//검색을 위한 field
	private String keyword;			//검색 카테고리
	private String searchKey;		//검색할 내용
	
	public Qna() {
		// TODO Auto-generated constructor stub
	}

	public Qna(int qnaNo, String qnaTitle, String answer, String qnaWriter, int count, Date createDate, String qnaPwd, String qnaSecret, int rowNo) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.answer = answer;
		this.qnaWriter = qnaWriter;	
		this.count = count;
		this.createDate = createDate;
		this.qnaPwd = qnaPwd;
		this.qnaSecret = qnaSecret;
		this.rowNo = rowNo;
	}
	
	public Qna(int qnaNo, String qnaTitle, String qnaWriter, Date createDate, int count, String qnaContent) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaWriter = qnaWriter;
		this.createDate = createDate;
		this.count = count;
		this.qnaContent = qnaContent;		
	}

	public Qna(int qnaNo, String qnaWriter, int category, String qnaTitle, String qnaContent, int count,
			Date createDate, String status, String qnaPwd, String qnaSecret, int rowNo) {
		super();
		this.qnaNo = qnaNo;
		this.qnaWriter = qnaWriter;
		this.category = category;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
		this.qnaPwd = qnaPwd;
		this.qnaSecret = qnaSecret;
		this.rowNo = rowNo;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaWriter() {
		return qnaWriter;
	}

	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
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

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQnaPwd() {
		return qnaPwd;
	}

	public void setQnaPwd(String qnaPwd) {
		this.qnaPwd = qnaPwd;
	}

	public String getQnaSecret() {
		return qnaSecret;
	}

	public void setQnaSecret(String qnaSecret) {
		this.qnaSecret = qnaSecret;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", qnaWriter=" + qnaWriter + ", category=" + category + ", qnaTitle=" + qnaTitle
				+ ", qnaContent=" + qnaContent + ", count=" + count + ", createDate=" + createDate + ", status="
				+ status + ", qnaPwd=" + qnaPwd + ", qnaSecret=" + qnaSecret + ", answer=" + answer + ", rowNo=" + rowNo
				+ ", keyword=" + keyword + ", searchKey=" + searchKey + "]";
	}
	
}
