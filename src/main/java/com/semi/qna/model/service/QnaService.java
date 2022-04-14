package com.semi.qna.model.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.dto.PageInfo;
import com.semi.qna.model.dao.QnaDao;
import com.semi.qna.model.dto.Qna;

public class QnaService {

	public int getListCount(String searchKey, String keyword) {
		Connection conn = getConnection();
		
		int listCount = 0;
		
		//넘어온 searchKey와 keyword가 servlet에서 지정한 기본 값이면 검색 기능을 사용하지 않음
		if(keyword == "QNA_TITLE" && searchKey == "") {
			listCount = new QnaDao().getListCount(conn);
		}else {
			listCount = new QnaDao().getSearchListCount(conn, searchKey, keyword);
		}
		
		close(conn);
		return listCount;
	}

	public ArrayList<Qna> selectList(PageInfo pi, String keyword, String searchKey) {
		Connection conn = getConnection();
		
		ArrayList<Qna> list = null;
		
		if(keyword == "QNA_TITLE" && searchKey == "") {
			list = new QnaDao().selectList(conn, pi);
		}else {
			list = new QnaDao().searchList(conn, pi, keyword, searchKey);
		}
		
		close(conn);
		return list;
	}

	public int insertQna(Qna q) {
		Connection conn = getConnection();
		
		int result = new QnaDao().insertQna(conn, q);
		
		close(conn);		
		return result;
	}

	public Qna selectQna(int qno) {
		Connection conn = getConnection();
		
		//조회수 증가 메소드
		int result = new QnaDao().increaseCount(conn, qno);
		Qna q = null;
		
		if(result > 0) {
			commit(conn); //조회수 commit
			q = new QnaDao().selectQna(conn, qno);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return q;
	}

	public Qna selectUpdateQna(int qno) {
		Connection conn = getConnection();
		
		Qna q = new QnaDao().selectQna(conn, qno);
		
		close(conn);
		return q;
	}

	public int updateQna(Qna q) {
		Connection conn = getConnection();
		
		int result = new QnaDao().updateQna(conn, q);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteQna(int qno) {
		Connection conn = getConnection();
		
		int result = new QnaDao().deleteQna(conn, qno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Qna selectPwdCheck(int qno, int userNo) {
		Connection conn = getConnection();
		
		Qna q = new QnaDao().selectPwdCheck(conn, qno, userNo);
		
		close(conn);
		
		return q;
	}

}
