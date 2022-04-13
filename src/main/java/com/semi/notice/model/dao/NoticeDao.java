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
	
	public int getSearchListCount(Connection conn, String searchKey, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//String sql = prop.getProperty("getSearchListCount");
		String sql = "SELECT COUNT(*) FROM NOTICE WHERE STATUS='Y' AND "+keyword+" LIKE ?";
		
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

	public ArrayList<Notice> selectList(Connection conn, PageInfo pi) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectList=SELECT * FROM (SELECT ROWNUM RNUM, A.*  FROM (SELECT NOTICE_NO, NOTICE_TITLE, USER_ID, COUNT, CREATE_DATE FROM NOTICE A JOIN R_USER B ON NOTICE_WRITER=USER_NO WHERE A.STATUS = 'Y' ORDER BY A.NOTICE_NO DESC) A) WHERE RNUM BETWEEN ? AND ?
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
									rset.getString("USER_ID"),
									rset.getInt("COUNT"),	
									rset.getDate("CREATE_DATE"),
									rset.getInt("RNUM")
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
	
	public ArrayList<Notice> searchList(Connection conn, String keyword, String searchKey, PageInfo pi) {
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; //1
		int endRow = startRow + pi.getBoardLimit() - 1; //10
		
		//searchList=SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT NOTICE_NO, NOTICE_TITLE, USER_ID, COUNT, CREATE_DATE FROM NOTICE A JOIN R_USER B ON NOTICE_WRITER=USER_NO WHERE A.STATUS = 'Y' AND ? LIKE ? ORDER BY A.NOTICE_NO DESC) A) WHERE RNUM BETWEEN ? AND ?
		//?로 인자를 먼저 올려놓으면 값으로 인식하여 "title" -> 이렇게 들어가기 때문에 앞뒤로 "++"를 넣어주고 그 안에 keyword를 넣어준다.
		//String sql = prop.getProperty("searchList"); -> properties 파일에 jsp에서 받아온 keyword가 들어가지 않아서 sql을 따로 빼서 작성
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM ("
						+ "SELECT NOTICE_NO, NOTICE_TITLE, USER_ID, COUNT, CREATE_DATE "
						+ "FROM NOTICE A JOIN R_USER B ON NOTICE_WRITER=USER_NO WHERE A.STATUS = 'Y' AND "+keyword+" LIKE ? "
						+ "ORDER BY A.NOTICE_NO DESC) A) WHERE RNUM BETWEEN ? AND ?";
				
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, keyword);
			pstmt.setString(1, "%"+searchKey+"%"); //searchKey가 포함된 게시물을 모두 찾기 위하여 앞뒤로 %%를 붙인다.
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Notice(rset.getInt("NOTICE_NO"),
						rset.getString("NOTICE_TITLE"),
						rset.getString("USER_ID"),
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
		
		//selectNotice=SELECT NOTICE_NO, NOTICE_TITLE, B.USER_ID, CREATE_DATE, COUNT, NOTICE_CONTENT FROM NOTICE A JOIN R_USER B ON NOTICE_WRITER=USER_NO WHERE A.STATUS='Y' AND NOTICE_NO=?
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) { //하나의 게시글만 조회
				n = new Notice(rset.getInt("NOTICE_NO"),
								rset.getString("NOTICE_TITLE"),
								rset.getString("USER_ID"),
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
	
	public Attachment selectAttachment(Connection conn, int nno) {
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectAttachment=SELECT FILE_NO, ORIGIN_NAME, CHANGE_NAME FROM ATTACHMENT WHERE REF_NO=? AND CATEGORY=3 AND STATUS='Y'
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
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}

//	public ArrayList<Attachment> selectAttachment(Connection conn, int nno) {
//		ArrayList<Attachment> atList = new ArrayList<Attachment>();
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		//selectAttachment=SELECT FILE_NO, ORIGIN_NAME, CHANGE_NAME FROM ATTACHMENT WHERE REF_NO=? AND CATEGORY=3 AND STATUS='Y'
//		String sql = prop.getProperty("selectAttachment");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, nno);
//			
//			rset = pstmt.executeQuery();
//			
//			while(rset.next()) {
//				Attachment at = new Attachment();
//				at.setFileNo(rset.getInt("FILE_NO"));
//				at.setOriginName(rset.getString("ORIGIN_NAME"));
//				at.setChangeName(rset.getString("CHANGE_NAME"));
//				
//				atList.add(at);
//				System.out.println(atList);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		return atList;
//	}

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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
			
		return result;
	}
	
	public int insertAttachment(Connection conn, int noticeWriter, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//insertAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, ?, SEQ_NNO.CURRVAL, 3, ?, ?, ?, SYSDATE, DEFAULT)
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeWriter);
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


//	public int insertAttachment(Connection conn, int noticeWriter, ArrayList<Attachment> fileList) {
//		Attachment at = new Attachment();
//		int result = 0;
//		PreparedStatement pstmt = null;
//		
//		//insertAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, ?, SEQ_NNO.CURRVAL, 3, ?, ?, ?, SYSDATE, DEFAULT)
//		String sql = prop.getProperty("insertAttachment");
//		
//		try {
//			for(int i = 0; i < fileList.size(); i++) {
//				at = fileList.get(i);
//				
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, noticeWriter);
//				pstmt.setString(2, at.getOriginName());
//				pstmt.setString(3, at.getChangeName());
//				pstmt.setString(4, at.getFilePath());
//				
//				result += pstmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}

	public int updateNotice(Connection conn, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//updateNotice=UPDATE NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=? WHERE NOTICE_NO=?
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateAttachment(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//updateAttachment=UPDATE ATTACHMENT SET CHANGE_NAME=?, ORIGIN_NAME=?, FILE_PATH=? WHERE FILE_NO=?
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
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/*public int updateAttachment(Connection conn, ArrayList<Attachment> atList) {
		Attachment at = new Attachment();
		int result = 0;
		//int result2 = 0;
		PreparedStatement pstmt = null;
		
		//updateAttachment=UPDATE ATTACHMENT SET CHANGE_NAME=?, ORIGIN_NAME=?, FILE_PATH=? WHERE FILE_NO=?
		String sql = prop.getProperty("updateAttachment");
		
		try {			
			for(int i = 0; i < atList.size(); i++) {
				at = atList.get(i);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileNo());
				
				result += pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}*/
	
	//일단 사용 안함
	public int updateDeleteAttachment(Connection conn, String delFile) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//updateDeleteAttachment=DELETE FROM ATTACHMENT WHERE STATUS='Y' AND FILE_NO=?
		String sql = prop.getProperty("updateDeleteAttachment");
		
		try {			
			//for(String delFile : delFiles) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(delFile));
				System.out.println(delFile);
				
				result = pstmt.executeUpdate();
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertUpdateAttachment(Connection conn,  Attachment at, int noticeWriter) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//insertUpdateAttachment=INSERT INTO ATTACHMENT VALUES (SEQ_FNO.NEXTVAL, ?, ?, 3, ?, ?, ?, SYSDATE, DEFAULT)
		String sql = prop.getProperty("insertUpdateAttachment");
		
		try {
			//for(int i = 0; i < atList.size(); i++) {
				//at = atList.get(i);				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, noticeWriter);
				pstmt.setInt(2, at.getRefNo());
				pstmt.setString(3, at.getOriginName());
				pstmt.setString(4, at.getChangeName());
				pstmt.setString(5, at.getFilePath());
				
				result = pstmt.executeUpdate(); 
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteNotice(Connection conn, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//deleteNotice=UPDATE NOTICE SET STATUS='N' WHERE NOTICE_NO=?
		String sql = prop.getProperty("deleteNotice");
		
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

	public int deleteAttachment(Connection conn, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//deleteAttachment=UPDATE ATTACHMENT SET STATUS='N' WHERE REF_NO=?
		String sql = prop.getProperty("deleteAttachment");
		
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

}
