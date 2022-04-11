package com.semi.class_notice.model.dao;

import static com.semi.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.class_notice.model.dto.ClassNotice;
import com.semi.common.dto.Attachment;
import com.semi.common.dto.PageInfo;
import com.semi.common.dto.Reply;

public class ClassNoticeDao {
	
	private Properties prop = new Properties();
	public ClassNoticeDao() {
		String fileName = ClassNoticeDao.class.getResource("/sql/class_notice/class_notice-query.properties").getPath();
		System.out.println("fileName   " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getListCount(Connection conn, String className) {
		// 총 게시글 수 계산할 메소드
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
//		getListCount=SELECT COUNT(*) FROM CLASS_NOTICE WHERE CLASS_NAME= ? AND STATUS = 'Y' 
		
		String sql = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, className);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<ClassNotice> selectList(Connection conn, PageInfo pi, String className) {
		
		ArrayList<ClassNotice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		// 시작게시물 끝게시물 보여주어야 함 일단 수업때 한걸로 진행
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		// 반 별 게시판 목록 다르게 보여주어야 하니까 앞에서 매개변수로 받아주어야함 우선 여기서 담아준다

//			selectList=(SELECT ROWNUM RNUM, A.* FROM \
//					(SELECT CLASS_NOTICE_NO, CLASS_NOTICE_TITLE,CHANGE_NAME, USER_ID, COUNT, CREATE_DATE \
//					FROM CLASS_NOTICE B JOIN R_USER C ON (NOTICE_WRITER=USER_NO) \
//                    LEFT JOIN ATTACHMENT D ON (C.USER_NO = D.USER_NO) \
//					WHERE CLASS_NAME= ? AND B.STATUS='Y' AND B.CLASS_NOTICE_NO = D.REF_NO \
//                    AND D.CATEGORY = B.CATEGORY ORDER BY CLASS_NOTICE_NO DESC) A) \
//					WHERE RNUM BETWEEN ? AND ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, className);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();


			while(rset.next()) {
				ClassNotice c = new ClassNotice();
				c.setClassNoticeNo(rset.getInt("CLASS_NOTICE_NO"));
				c.setClassNoticeTitle(rset.getString("CLASS_NOTICE_TITLE"));
				c.setNoticeWriter(rset.getString("USER_ID"));
				c.setCreateDate(rset.getDate("CREATE_DATE"));
				c.setCount(rset.getInt("COUNT"));
				c.setTitleImg(rset.getString("CHANGE_NAME"));
				
				list.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int insertNotice(Connection conn, ClassNotice n) {
		// DB에 게시물 추가하는 메소드
		
		int result = 0;
		PreparedStatement pstmt = null;
//		insertCNotice=INSERT INTO CLASS_NOTICE VALUES(SEQ_CNNO.NEXTVAL, ?, ?, 5, ?, ?, SYSDATE, DEFAULT, DEFAULT)
//		작성자 번호, 반이름, 글 제목, 글 내용
		String sql = prop.getProperty("insertCNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(n.getNoticeWriter()));
			pstmt.setString(2, n.getClassName());
			pstmt.setString(3, n.getClassNoticeTitle());
			pstmt.setString(4, n.getClassNoticeContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int increaseCount(Connection conn, int nno) {
		// 게시물 조회수 증가 메소드
		PreparedStatement pstmt = null;
		int result = 0;
//		increaseCount=UPDATE CLASS_NOTICE SET COUNT = COUNT+1 WHERE CLASS_NOTICE_NO=? AND STATUS='Y'
		String sql = prop.getProperty("increaseCount");

		try {
			pstmt = conn.prepareStatement(sql);
			// 오라클 DB에 테이블 컬럼명 순서 어떻게 들어가는지 확인하고 입력
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public ClassNotice selectNotice(Connection conn, int nno) {
		// 게시물 상세 조회 메소드
		ClassNotice cn = null;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
//		selectNotice=SELECT CLASS_NOTICE_NO, CLASS_NOTICE_TITLE, CLASS_NOTICE_CONTENT, USER_ID, COUNT, CREATE_DATE FROM CLASS_NOTICE N JOIN "USER" ON (NOTICE_WRITER=USER_NO) WHERE CLASS_NOTICE_NO=? AND N.STATUS='Y'
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			// 한건 if문으로 처리
			
			if(rset.next()) {
				cn = new ClassNotice();
				cn.setClassNoticeNo(rset.getInt("CLASS_NOTICE_NO"));
				cn.setClassNoticeTitle(rset.getString("CLASS_NOTICE_TITLE"));
				cn.setClassNoticeContent(rset.getString("CLASS_NOTICE_CONTENT"));
				cn.setClassName(rset.getString("CLASS_NAME"));
				cn.setNoticeWriter(rset.getString("USER_ID"));
				cn.setCount(rset.getInt("COUNT"));
				cn.setCreateDate(rset.getDate("CREATE_DATE"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return cn;
	}
	public int updateNotice(Connection conn, ClassNotice cn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
//		updateNotice=UPDATE CLASS_NOTICE SET CLASS_NOTICE_TITLE=?, CLASS_NOTICE_CONTENT=? WHERE CLASS_NOTICE_NO=?
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cn.getClassNoticeTitle());
			pstmt.setString(2, cn.getClassNoticeContent());
			pstmt.setInt(3, cn.getClassNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int insertAttachment(Connection conn, Attachment at) {
		// 첨부파일 저장하는 메소드
		int result = 0;
		PreparedStatement pstmt = null;
		
//		insertAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, ?, SEQ_CNNO.NEXTVAL, 5, ?, ?, ?, SYSDATE, DEFAULT)
		String sql = prop.getProperty("insertAttachment");
		
//		ORIGIN_NAME
//		CHANGE_NAME
//		FILE_PATH
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, at.getUserNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public Attachment selectAttachment(Connection conn, int nno) {

		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		selectAttachment=SELECT FILE_NO, ORIGIN_NAME, CHANGE_NAME FROM ATTACHMENT WHERE REF_BNO=? AND CATEGORY= 5 AND STATUS='Y'
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return at;
	}
	public int updateAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
//		updateAttachment=UPDATE ATTACHMENT SET CHANGE_NAME=?, ORIGIN_NAME=?, FILE_PATH=? WHERE FILE_NO=?		
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getChangeName());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int insertReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
//		insertReply=INSERT INTO CLASS_REPLY VALUES(SEQ_CRNO.NEXTVAL, ?, ?, SYSDATE, ?, DEFAULT)
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getRefBoardId());
			pstmt.setInt(2, Integer.parseInt(r.getReplyWriter()));
			pstmt.setString(3, r.getReplyContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public ArrayList<Reply> selectRList(Connection conn, int nno) {
		// 게시글에 달려있는 댓글 조회 하는 메소드
		
		ArrayList<Reply> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		selectRList=SELECT REPLY_NO, REPLY_CONTENT, USER_ID, CREATE_DATE FROM REPLY A JOIN R_USER ON(REF_WRITER = USER_NO) WHERE REF_NO = ? AND A.STATUS='Y' ORDER BY REPLY_NO DESC
		String sql = prop.getProperty("selectRList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 참조 게시글 번호 ? 자리에 넣어준다.
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
//				필요한거 : 작성자, 댓글 내용, 작성일, 댓글번호
				Reply r = new Reply();
				r.setReplyId(rset.getInt("REPLY_NO"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setReplyWriter(rset.getString("USER_ID"));
				r.setCreateDate(rset.getDate("CREATE_DATE"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public int updateReply(Connection conn, Reply r) {
		// DB 댓글 수정
		
		int result = 0;
		PreparedStatement pstmt = null;
//		updateReply=UPDATE CLASS_REPLY SET REPLY_CONTENT = ? WHERE REPLY_NO = ?
		String sql = prop.getProperty("updateReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getReplyId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

}
