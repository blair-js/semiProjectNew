package com.semi.notice.model.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.dto.Attachment;
import com.semi.common.dto.PageInfo;
import com.semi.notice.model.dao.NoticeDao;
import com.semi.notice.model.dto.Notice;

public class NoticeService {

	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().getListCount(conn);
		
		close(conn);
		return listCount;
	}

	public ArrayList<Notice> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(conn, pi);
		
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

	public ArrayList<Attachment> selectAttachment(int nno) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> atList = new NoticeDao().selectAttachment(conn, nno);
		
		close(conn);
		return atList;
	}

	public int insertNotice(Notice n, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		//게시글 등록
		int result1 = new NoticeDao().insertNotice(conn, n);
		
		//첨부파일이 없을 경우
		int result2 = 1;
		if(!fileList.isEmpty()) { //첨부파일이 비어있지 않다면 등록
			result2 = new NoticeDao().insertAttachment(conn, fileList);
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}

}
