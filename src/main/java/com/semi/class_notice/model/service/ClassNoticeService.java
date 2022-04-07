package com.semi.class_notice.model.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.class_notice.model.dao.ClassNoticeDao;
import com.semi.class_notice.model.dto.ClassNotice;
import com.semi.common.dto.PageInfo;

public class ClassNoticeService {

	public int getListCount(String className) {
		Connection conn = getConnection();
		
		int listCount = new ClassNoticeDao().getListCount(conn, className);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<ClassNotice> selectList(PageInfo pi, String className) {
		Connection conn = getConnection();
		
		ArrayList<ClassNotice> list = new ClassNoticeDao().selectList(conn, pi, className);
		
		close(conn);
		
		return list;
	}

	public int insertNotice(ClassNotice n) {
		// 게시물 추가 메소드
		Connection conn = getConnection();
		
		int result = new ClassNoticeDao().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public ClassNotice selectNotice(int nno) {
		Connection conn = getConnection();
		// 조회수 증가도 시켜주어야 하니까 메소드 생성
		int result = new ClassNoticeDao().increaseCount(conn, nno);
		ClassNotice cn = null;
		
		if(result > 0) {
			commit(conn);
			// 정상적으로 조회수가 증가 했을경우 객체 조회
			cn = new ClassNoticeDao().selectNotice(conn, nno);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return cn;
	}

	public ClassNotice selectNewNotice(int nno) {
		// 수정시 사용하는 조회 메소드 조회수 증가되면 안되니까
		Connection conn = getConnection();
		
		ClassNotice cn = new ClassNoticeDao().selectNotice(conn, nno);
		
		close(conn);
		
		return cn;
	}

	public ClassNotice updateNotice(ClassNotice cn, int nno) {
		// 게시글 수정 메소드
		Connection conn = getConnection();
		ClassNotice updateCn = null;
		
		int result = new ClassNoticeDao().updateNotice(conn, cn, nno);
		
		if(result > 0) {
			commit(conn);
			updateCn = new ClassNoticeDao().selectNotice(conn, nno);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateCn;
	}

}
