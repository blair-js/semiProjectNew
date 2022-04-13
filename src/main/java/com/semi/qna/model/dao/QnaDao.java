package com.semi.qna.model.dao;

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
import com.semi.notice.model.dao.NoticeDao;
import com.semi.qna.model.dto.Qna;

public class QnaDao {

	private Properties prop = new Properties();
	
	public 	QnaDao() {
		String fileName = NoticeDao.class.getResource("/sql/qna/qna-query.properties").getPath();
		//System.out.println(fileName);
		
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
		
		//getListCount=SELECT COUNT(*) FROM QA WHERE STATUS='Y'
		String sql = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
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

	public int getSearchListCount(Connection conn, String searchKey, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT COUNT(*) FROM QA WHERE STATUS='Y' AND "+keyword+" LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKey+"%");
			
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

	public ArrayList<Qna> selectList(Connection conn, PageInfo pi) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectList=SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT QNA_NO, QNA_TITLE, ANSWER, USER_ID, COUNT, CREATE_DATE, QNA_PWD, QNA_SECRET\
		//FROM QA A JOIN R_USER B ON QNA_WRITER=USER_NO WHERE A.STATUS = 'Y' ORDER BY A.QNA_NO DESC) A) WHERE RNUM BETWEEN ? AND ?;
		String sql = prop.getProperty("selectList");
		
		//where 조건문에는 한 페이지 당 보여지는 게시물(10개)를 보여주기 위해
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; //1
		int endRow = startRow + pi.getBoardLimit() - 1; //10
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			//QNA_NO, QNA_TITLE, ANSWER, USER_ID, COUNT, CREATE_DATE, QNA_PWD, QNA_SECRET, RNUM
			while(rset.next()){
				list.add(new Qna(rset.getInt("QNA_NO"),
								rset.getString("QNA_TITLE"),
								rset.getString("ANSWER"),
								rset.getString("USER_ID"),
								rset.getInt("COUNT"),
								rset.getDate("CREATE_DATE"),
								rset.getInt("QNA_PWD"),
								rset.getString("QNA_SECRET"),
								rset.getInt("RNUM")
								));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public ArrayList<Qna> searchList(Connection conn, PageInfo pi, String keyword, String searchKey) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//where 조건문에는 한 페이지 당 보여지는 게시물(10개)를 보여주기 위해
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; //1
		int endRow = startRow + pi.getBoardLimit() - 1; //10
		
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM ("
						+ "SELECT QNA_NO, QNA_TITLE, ANSWER, USER_ID, COUNT, CREATE_DATE, QNA_PWD, QNA_SECRET "
						+ "FROM QA A JOIN R_USER B ON QNA_WRITER=USER_NO WHERE A.STATUS = 'Y' AND "+keyword+" LIKE ? "
						+ "ORDER BY A.QNA_NO DESC) A) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchKey);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Qna(rset.getInt("QNA_NO"),
						rset.getString("QNA_TITLE"),
						rset.getString("ANSWER"),
						rset.getString("USER_ID"),
						rset.getInt("COUNT"),
						rset.getDate("CREATE_DATE"),
						rset.getInt("QNA_PWD"),
						rset.getString("QNA_SECRET"),
						rset.getInt("RNUM")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertQna(Connection conn, Qna q) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//insertQna=INSERT INTO QA VALUES (SEQ_QNO.NEXTVAL, ?, DEFAULT, ?, ?, DEFAULT, SYSDATE, DEFAULT, ?, ?, DEFAULT)
		String sql = prop.getProperty("insertQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(q.getQnaWriter()));
			pstmt.setString(2, q.getQnaTitle());
			pstmt.setString(3, q.getQnaContent());
			pstmt.setInt(4, q.getQnaPwd());
			pstmt.setString(5, q.getQnaSecret());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAttachment(Connection conn, int noticeWriter, ArrayList<Attachment> fileList) {
		Attachment at = new Attachment();
		int result = 0;
		PreparedStatement pstmt = null;
		
		//insertAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, ?, SEQ_QNO.CURRVAL, 4, ?, ?, ?, SYSDATE, DEFAULT)
		String sql = prop.getProperty("insertAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				at = fileList.get(i);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, noticeWriter);
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getChangeName());
				pstmt.setString(4, at.getFilePath());
				
				result += pstmt.executeUpdate();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
