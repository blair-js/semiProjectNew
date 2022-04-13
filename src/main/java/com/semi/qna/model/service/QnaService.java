package com.semi.qna.model.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.dto.Attachment;
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

	public int insertQna(Qna q, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		int noticeWriter = Integer.parseInt(q.getQnaWriter());
		
		int result1 = new QnaDao().insertQna(conn, q);
		
		//첨부파일 없는 경우
		int result2 = 1;
		if(!fileList.isEmpty()) {
			//for(int i = 0; i < fileList.size(); i++) {
				result2 = new QnaDao().insertAttachment(conn, noticeWriter, fileList);
			//}
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}

}
