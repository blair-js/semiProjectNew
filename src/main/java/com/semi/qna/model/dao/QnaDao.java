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

import com.semi.common.dto.PageInfo;
import com.semi.common.dto.Reply;
import com.semi.notice.model.dao.NoticeDao;
import com.semi.qna.model.dto.Qna;
import com.semi.qna.model.dto.QnaReply;

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
		
		String sql = "SELECT COUNT(*) FROM QA A JOIN R_USER B ON USER_NO=QNA_WRITER WHERE A.STATUS='Y' AND "+keyword+" LIKE ?";
		
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
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<Qna> selectList(Connection conn, PageInfo pi) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectList=SELECT * FROM (SELECT ROWNUM RNUM, A.*
		//FROM (SELECT QNA_NO, QNA_TITLE, USER_ID, COUNT, CREATE_DATE, QNA_PWD, QNA_SECRET\
		//FROM QA A JOIN R_USER B ON QNA_WRITER=USER_NO WHERE A.STATUS = 'Y' ORDER BY A.QNA_NO DESC) A) WHERE RNUM BETWEEN ? AND ?
		String sql = prop.getProperty("selectList");
		
		//where 조건문에는 한 페이지 당 보여지는 게시물(10개)를 보여주기 위해
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; //1
		int endRow = startRow + pi.getBoardLimit() - 1; //10
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			//QNA_NO, QNA_TITLE, USER_ID, COUNT, CREATE_DATE, QNA_PWD, QNA_SECRET, RNUM
			while(rset.next()){
				list.add(new Qna(rset.getInt("QNA_NO"),
								rset.getString("QNA_TITLE"),
								rset.getString("USER_ID"),
								rset.getInt("COUNT"),
								rset.getDate("CREATE_DATE"),
								rset.getString("QNA_PWD"),
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
		
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM ( "
						+ "SELECT QNA_NO, QNA_TITLE, USER_ID, COUNT, CREATE_DATE, QNA_PWD, QNA_SECRET "
						+ "FROM QA A JOIN R_USER B ON QNA_WRITER=USER_NO WHERE A.STATUS = 'Y' AND "+keyword+" LIKE ? "
						+ "ORDER BY A.QNA_NO DESC) A) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKey+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Qna(rset.getInt("QNA_NO"),
						rset.getString("QNA_TITLE"),
						rset.getString("USER_ID"),
						rset.getInt("COUNT"),
						rset.getDate("CREATE_DATE"),
						rset.getString("QNA_PWD"),
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
		
		//insertQna=INSERT INTO QA VALUES (SEQ_QNO.NEXTVAL, ?, 4, ?, ?, DEFAULT, SYSDATE, DEFAULT, ?, ?, DEFAULT)
		String sql = prop.getProperty("insertQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, q.getQnaWriter());
			pstmt.setString(2, q.getQnaTitle());
			pstmt.setString(3, q.getQnaContent());
			pstmt.setString(4, q.getQnaPwd());
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

	public int increaseCount(Connection conn, int qno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//increaseCount=UPDATE QA SET COUNT=COUNT+1 WHERE QNA_NO=?
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Qna selectQna(Connection conn, int qno) {
		Qna q = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectQna=SELECT QNA_NO, QNA_TITLE, USER_ID, CREATE_DATE, COUNT, QNA_CONTENT, QNA_PWD, QNA_SECRET FROM QA A JOIN R_USER B ON QNA_WRITER=USER_NO WHERE A.STATUS='Y' AND QNA_NO=?
		String sql = prop.getProperty("selectQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				q = new Qna(rset.getInt("QNA_NO"),
							rset.getString("QNA_TITLE"),
							rset.getString("USER_ID"),
							rset.getDate("CREATE_DATE"),
							rset.getInt("COUNT"),
							rset.getString("QNA_CONTENT"),
							rset.getString("QNA_PWD"),
							rset.getString("QNA_SECRET")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return q;
	}

	public int updateQna(Connection conn, Qna q) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//updateQna=UPDATE QA SET QNA_TITLE=?, QNA_CONTENT=?, QNA_PWD=?, QNA_SECRET=? WHERE QNA_NO=?
		String sql = prop.getProperty("updateQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, q.getQnaTitle());
			pstmt.setString(2, q.getQnaContent());
			pstmt.setString(3, q.getQnaPwd());
			pstmt.setString(4, q.getQnaSecret());
			pstmt.setInt(5, q.getQnaNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteQna(Connection conn, int qno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//deleteQna=UPDATE QA SET STATUS='N' WHERE QNA_NO=?
		String sql = prop.getProperty("deleteQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Qna selectPwdCheck(Connection conn, int qno, int userNo) {
		Qna q = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectPwdCheck=SELECT QNA_WRITER, QNA_PWD FROM QA WHERE QNA_NO=? AND QNA_WRITER=?
		String sql = prop.getProperty("selectPwdCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			pstmt.setInt(2, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				q = new Qna();
				q.setQnaWriter(rset.getString("QNA_WRITER"));
				q.setQnaPwd(rset.getString("QNA_PWD"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return q;
	}

	public ArrayList<QnaReply> selectRList(Connection conn, int qno) {
		ArrayList<QnaReply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectRList=SELECT QNA_REPLY_NO, QNA_REPLY_CONTENT, USER_ID, CREATE_DATE FROM QA_REPLY A JOIN R_USER B ON REPLY_WRITER=USER_NO WHERE A.STATUS='Y' AND REF_QNO=? ORDER BY QNA_REPLY_NO DESC
		String sql = prop.getProperty("selectRList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				QnaReply qr = new QnaReply();
				qr.setQnaReplyNo(rset.getInt("QNA_REPLY_NO"));
				qr.setQnaReplyContent(rset.getString("QNA_REPLY_CONTENT"));
				qr.setReplyWriter(rset.getString("USER_ID"));
				qr.setCreateDate(rset.getDate("CREATE_DATE"));
				
				list.add(qr);
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

	public int insertReply(Connection conn, QnaReply qr) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//insertReply=INSERT INTO QA_REPLY VALUES (SEQ_RNO.NEXTVAL, ?, ?, ?, SYSDATE, DEFAULT)
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qr.getRefQno());
			pstmt.setInt(2, Integer.parseInt(qr.getReplyWriter()));
			pstmt.setString(3, qr.getQnaReplyContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		
		return result;
	}

	public int deleteReply(Connection conn, int rQno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//UPDATE QA_REPLY SET STATUS='N' WHERE QNA_REPLY_NO=?
		String sql = prop.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rQno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(Connection conn, int rQno, String content) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//updateReply=UPDATE QA_REPLY SET QNA_REPLY_CONTENT=? WHERE QNA_REPLY_NO=?
		String sql = prop.getProperty("updateReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, rQno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Qna> reCountList(Connection conn, PageInfo pi) {
		ArrayList<Qna> reCountList = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//reCount=SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT DISTINCT A.QNA_NO, (SELECT COUNT(*) \
		//FROM QA_REPLY WHERE A.QNA_NO=B.REF_QNO AND QA_REPLY.STATUS='Y') CNT FROM QA A LEFT JOIN QA_REPLY B ON A.QNA_NO=B.REF_QNO \
		//WHERE A.STATUS = 'Y' ORDER BY A.QNA_NO DESC)A) WHERE RNUM BETWEEN ? AND ?
		String sql = prop.getProperty("reCount");
		
		//where 조건문에는 한 페이지 당 보여지는 게시물(10개)를 보여주기 위해
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; //1
		int endRow = startRow + pi.getBoardLimit() - 1; //10
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Qna q = new Qna();
				q.setRowNo(rset.getInt("RNUM"));
				q.setQnaNo(rset.getInt("QNA_NO"));
				q.setReCount(rset.getInt("CNT"));
				
				reCountList.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reCountList;
	}

	public ArrayList<Qna> reCountSearch(Connection conn, PageInfo pi, String keyword, String searchKey) {
		ArrayList<Qna> reCountList = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT DISTINCT A.QNA_NO, "
					  + "(SELECT COUNT(*) FROM QA_REPLY WHERE QNA_NO=REF_QNO AND QA_REPLY.STATUS='Y') CNT "
					  + "FROM QA A LEFT JOIN QA_REPLY B ON A.QNA_NO=B.REF_QNO JOIN R_USER C ON A.QNA_WRITER=C.USER_NO "
					  + " WHERE A.STATUS = 'Y' AND "+keyword+" LIKE ? "
					  + "ORDER BY A.QNA_NO DESC)A) WHERE RNUM BETWEEN ? AND ?";
		
		//where 조건문에는 한 페이지 당 보여지는 게시물(10개)를 보여주기 위해
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; //1
		int endRow = startRow + pi.getBoardLimit() - 1; //10
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKey + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Qna q = new Qna();
				q.setRowNo(rset.getInt("RNUM"));
				q.setQnaNo(rset.getInt("QNA_NO"));
				q.setReCount(rset.getInt("CNT"));
				
				reCountList.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reCountList;
	}

}
