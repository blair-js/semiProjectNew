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
import com.semi.common.dto.PageInfo;

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
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, className);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();

//			selectList=SELECT * FROM 
//					(SELECT ROWNUM RNUM, A.* FROM 
//					(SELECT CLASS_NOTICE_NO, CLASS_NOTICE_TITLE, USER_ID, COUNT, CREATE_DATE 
//					FROM CLASS_NOTICE B JOIN "USER" ON (NOTICE_WRITER=USER_NO) 
//					WHERE CLASS_NAME= '?' AND B.STATUS='Y' ORDER BY CLASS_NOTICE_NO DESC) A)
//					WHERE RNUM BETWEEN ? AND ?

			while(rset.next()) {
				ClassNotice c = new ClassNotice();
				c.setClassNoticeNo(rset.getInt("RNUM"));
				c.setClassNoticeTitle(rset.getString("CLASS_NOTICE_TITLE"));
				c.setNoticeWriter(rset.getString("USER_ID"));
				c.setCreateDate(rset.getDate("CREATE_DATE"));
				c.setCount(rset.getInt("COUNT"));
				
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

}
