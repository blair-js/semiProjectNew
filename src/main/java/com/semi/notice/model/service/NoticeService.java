package com.semi.notice.model.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.dto.Attachment;
import com.semi.common.dto.PageInfo;
import com.semi.notice.model.dao.NoticeDao;
import com.semi.notice.model.dto.Notice;

public class NoticeService {

	public int getListCount(String searchKey, String keyword) {
		Connection conn = getConnection();
		
		int listCount = 0;
		
		//keyword와 searchKey가 servlet에서 설정한 기본값이면 검색 기능 사용하지 않음 
		if(keyword == "NOTICE_TITLE" && searchKey == "") {
			listCount = new NoticeDao().getListCount(conn);
		}else { //검색 기능을 사용하면, 그 검색된 글에 맞는 listCount가 필요해서 따로 받아온다.
			listCount = new NoticeDao().getSearchListCount(conn, searchKey, keyword);
		}
		
		close(conn);
		return listCount;
	}

	public ArrayList<Notice> selectList(PageInfo pi, String keyword, String searchKey) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = null;
		
		//keyword와 searchKey가 servlet에서 설정한 기본값이면 검색 기능 사용하지 않음 
		if(keyword == "NOTICE_TITLE" && searchKey == "") {
			list = new NoticeDao().selectList(conn, pi);
		}else {
			list = new NoticeDao().searchList(conn, keyword, searchKey, pi);
		}
		
		close(conn);
		return list;
	}

	public Notice selectNotice(int nno) {
		Connection conn = getConnection();
		
		//조회수를 올려준다.
		int result = new NoticeDao().increaseCount(conn, nno);
		Notice n = null;
		
		if(result > 0) {
			commit(conn);
			n = new NoticeDao().selectNotice(conn, nno);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return n;
	}

	public Attachment selectAttachment(int nno) {
		Connection conn = getConnection();
		
		//ArrayList<Attachment> atList = new NoticeDao().selectAttachment(conn, nno);
		Attachment at = new NoticeDao().selectAttachment(conn, nno);
		
		close(conn);
		return at;
	}
	
	public int insertNotice(Notice n, Attachment at) {
		Connection conn = getConnection();
		
		int noticeWriter = Integer.parseInt(n.getNoticeWriter());
		
		//게시글 등록
		int result1 = new NoticeDao().insertNotice(conn, n);
		
		//첨부파일이 없을 경우
		int result2 = 1;
		if(at != null) { //첨부파일이 비어있지 않다면 등록
			result2 = new NoticeDao().insertAttachment(conn, noticeWriter, at);
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}

	public Notice selectUpdateNotice(int nno) {
		Connection conn = getConnection();
		Notice n =new NoticeDao().selectNotice(conn, nno);
		
		close(conn);
		return n;
	}

	public int updateNotice(Notice n, Attachment at/*, String delFile*/) {
		Connection conn = getConnection();
		
		//게시글 수정
		int result1 = new NoticeDao().updateNotice(conn, n);
		
		//첨부파일 수정
		int result2 = 1;
		int noticeWriter = Integer.parseInt(n.getNoticeWriter()); //작성자 id
		if(at != null) { //첨부파일이 있는 경우
			if(at.getFileNo() != 0) {
				result2 = new NoticeDao().updateAttachment(conn, at);
			}else {
				result2 = new NoticeDao().insertUpdateAttachment(conn, at, noticeWriter);
			}
		}
		
		close(conn);
		return result1 * result2 /** result3*/;
	}

	public int deleteNotice(int nno) {
		Connection conn = getConnection();
		
		//게시글 지우기
		int result1 = new NoticeDao().deleteNotice(conn, nno);
		
		//첨부파일 지우기
		int result2 = 1; //첨부파일 없는 경우
		//ArrayList<Attachment> atList = new NoticeDao().selectAttachment(conn, nno);
		Attachment at = new NoticeDao().selectAttachment(conn, nno);
		if(at != null) {
			result2 = new NoticeDao().deleteAttachment(conn, nno);
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}

}
