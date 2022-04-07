package com.semi.notice.model.dao;

import static com.semi.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.dto.Attachment;
import com.semi.common.dto.PageInfo;
import com.semi.notice.model.dto.Notice;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.properties").getPath();
		System.out.println(fileName);
		
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
	
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		//getListCount = SELECT COUNT(*) FROM NOTICE WHERE STATUS='Y'
		String sql = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) { //list의 총 개수 결과 값은 1개이기 때문에 if 사용
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<Notice> selectList(Connection conn, PageInfo pi) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectList=SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT NOTICE_NO, NOTICE_TITLE, USER_NAME, COUNT, CREATE_DATE FROM NOTICE A JOIN R_USER B ON NOTICE_WRITER=USER_NO WHERE A.STATUS = 'Y' ORDER BY A.NOTICE_NO DESC) A) WHERE RNUM BETWEEN ? AND ?
		String sql = prop.getProperty("selectList");
		
		//where 조건문에는 한 페이지 당 보여지는 게시물(10개)를 보여주기 위해
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; //1
		int endRow = startRow + pi.getBoardLimit() - 1; //10
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			//SELECT NOTICE_NO, NOTICE_TITLE, USER_NAME, COUNT, CREATE_DATE
			while(rset.next()) {
				list.add(new Notice(rset.getInt("NOTICE_NO"),
									rset.getString("NOTICE_TITLE"),
									rset.getString("USER_NAME"),
									rset.getInt("COUNT"),	
									rset.getDate("CREATE_DATE")
									));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

	public int increaseCount(Connection conn, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//increaseCount=UPDATE NOTICE SET COUNT=COUNT+1 WHERE NOTICE_NO=?
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Notice selectNotice(Connection conn, int nno) {
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectNotice=SELECT NOTICE_NO, NOTICE_TITLE, B.USER_NAME, CREATE_DATE, COUNT, NOTICE_CONTENT FROM NOTICE A JOIN R_USER B ON NOTICE_WRITER=USER_NO WHERE A.STATUS='Y' AND NOTICE_NO=?
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) { //하나의 게시글만 조회
				n = new Notice(rset.getInt("NOTICE_NO"),
								rset.getString("NOTICE_TITLE"),
								rset.getString("USER_NAME"),
								rset.getDate("CREATE_DATE"),
								rset.getInt("COUNT"),
								rset.getString("NOTICE_CONTENT")
								);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	public ArrayList<Attachment> selectAttachment(Connection conn, int nno) {
		ArrayList<Attachment> atList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectAttachment=SELECT FILE_NO, ORIGIN_NAME, CHANGE_NAME FROM ATTACHMENT WHERE REF_NO=? AND CATEGORY=3 AND STATUS='Y'
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				
				atList.add(at);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return atList;
	}

	public int insertNotice(Connection conn, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//insertNotice=INSERT INTO NOTICE VALUES (SEQ_NNO.NEXTVAL, ?, DEFAULT, ?, ?, DEFAULT, SYSDATE, DEFAULT)
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(n.getNoticeWriter()));
			pstmt.setString(2, n.getNoticeTitle());
			pstmt.setString(3, n.getNoticeContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
			
		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		Attachment at = new Attachment();
		int result = 0;
		PreparedStatement pstmt = null;
		
		return 0;
	}

}
