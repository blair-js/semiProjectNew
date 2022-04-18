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

	public int getListCount(String className, String keyword, String searchkey) {
		Connection conn = getConnection();
		
		int listCount = 0;
		// 검색어가 있는경우, 없는경우 조회 게시글 수가 다르니 메서드 따로 생성
		if(searchkey.equals("") && searchkey.length() == 0) {
			// 검색어가 없는경우
			listCount = new ClassNoticeDao().getListCount(conn, className);
		}else {
			// 검색어가 있는경우
			listCount = new ClassNoticeDao().getSeListCount(conn, className, keyword, searchkey);
		}
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<ClassNotice> selectList(PageInfo pi, String className, String keyword, String searchkey) {
		Connection conn = getConnection();
		
		ArrayList<ClassNotice> list = null;
		
		// 검색 값이 넘어오지 않으면 기존 조회방식 이용
		if(searchkey == "") {
			list = new ClassNoticeDao().selectList(conn, pi, className);
		}else {
			// 검색 값이 넘어 올 경우 쿼리 다르기때문에 메소드 생성
			list = new ClassNoticeDao().selectseList(conn, pi, className, keyword, searchkey);
		}
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

	public ArrayList<Reply> selectRList(int nno) {
		// 참조게시글 번호로 댓글 목록 뿌려줄 메소드
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new ClassNoticeDao().selectRList(conn, nno);
		
		close(conn);
		
		return list;
	}

	public int updateReply(Reply r) {
		// 댓글 수정 메소드
		
		Connection conn = getConnection();
		
		int result = new ClassNoticeDao().updateReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteReply(int rno) {
		// 댓글 삭제 메소드
		Connection conn = getConnection();
		
		int result = new ClassNoticeDao().deleteReply(conn, rno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteNotice(int nno) {
		// 게시물 삭제할 경우 썸네일, 댓글, 게시글 모두 지워야 함 (썸네일은 무조건 있으므로 댓글만 있는지 없는지 여부 확인)
		Connection conn = getConnection();
		
		// 게시글번호를 통해서 게시글 삭제처리
		int result1 = new ClassNoticeDao().deleteNotice(conn, nno);
		
		// 댓글 있는지 없는지 조회 게시물에 댓글은 여러개가 있을수도 있으니까 리스트로 조회
		ArrayList<Reply> rList = new ClassNoticeDao().selectRList(conn, nno);
		// 댓글은 0개일수도있고 여러개가 있을수도 있기때문에 1로 우선 값 선언
		int result2 = 1;

		// 조회 해서 온 댓글목록이 1개 이상이라면
		if(rList.size() > 0) {
			// 게시물에 등록 되어 있는 모든 댓글 삭제하기 위해 새로운 메소드 필요
			result2 = new ClassNoticeDao().deleteListReply(conn, nno);
		}
		
		// 잘 수행 되어서 결과 값들의 곱이 0보다 크면
		if(result1 * result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}

	public int deleteAttachment(int nno) {
		// 썸네일 이미지 삭제 메소드
		Connection conn = getConnection();
		// 첨부파일이 반드시 있기때문에 바로 result에 삭제한 뒤 수행결과 담아준다
		int result = new ClassNoticeDao().deleteAttachment(conn, nno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
