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
		// 게시물 추가 메소드, 일단 글만 추가 이미지는 내일 고민 필요할 듯
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

}
