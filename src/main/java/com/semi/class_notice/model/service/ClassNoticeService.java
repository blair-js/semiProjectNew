package com.semi.class_notice.model.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.class_notice.model.dao.ClassNoticeDao;
import com.semi.class_notice.model.dto.ClassNotice;
import com.semi.common.dto.Attachment;
import com.semi.common.dto.PageInfo;
import com.semi.common.dto.Reply;

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

	public int insertNotice(ClassNotice n, Attachment at) {
		// 게시물 추가 메소드, 썸네일 첨부파일도 추가 해주어야 함
		Connection conn = getConnection();
		
		int result1 = new ClassNoticeDao().insertNotice(conn, n);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new ClassNoticeDao().insertAttachment(conn, at);
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
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
	
	public Attachment selectAttachment(int nno) {
		// 첨부파일 조회 메소드
		Connection conn = getConnection();
		
		Attachment at = new ClassNoticeDao().selectAttachment(conn, nno);
		
		close(conn);
		
		return at;
	}

	public int updateNotice(ClassNotice cn, Attachment at) {
		// 게시글 수정 메소드 첨부파일도 수정 가능
		Connection conn = getConnection();
		
		int result1 = new ClassNoticeDao().updateNotice(conn, cn);
		int result2 = 1;
		if(at != null) {
			if(at.getFileNo() != 0) {
				result2 = new ClassNoticeDao().updateAttachment(conn, at);
			}else {
				result2 = new ClassNoticeDao().insertAttachment(conn, at);
			}
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}

	public int insertReply(Reply r) {
		// 댓글 추가 구현 메소드
		Connection conn = getConnection();
		
		int result = new ClassNoticeDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
