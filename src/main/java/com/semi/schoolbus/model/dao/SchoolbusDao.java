package com.semi.schoolbus.model.dao;

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

import com.semi.class_notice.model.dao.ClassNoticeDao;
import com.semi.schoolbus.model.dto.Schoolbus;
import com.semi.schoolbus.model.dto.UserReservation;

public class SchoolbusDao {
	
	private Properties prop = new Properties();
	public SchoolbusDao() {
		String fileName = ClassNoticeDao.class.getResource("/sql/schoolbus/schoolbus-query.properties").getPath();
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

	public ArrayList<Schoolbus> selectBList(Connection conn) {
		// 통학버스 테이블 조회하는 메소드
		
		ArrayList<Schoolbus> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		selectBList=SELECT * FROM SCHOOLBUS
		String sql = prop.getProperty("selectBList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			// 여러건이니까 while문
			while(rset.next()) {
				Schoolbus sb = new Schoolbus();
				sb.setBusDallyNo(rset.getInt("BUS_DAILY_NO"));
				sb.setSeatCount(rset.getInt("SEAT_COUNT"));
				sb.setBusContent(rset.getString("BUS_CONTENT"));
				
				list.add(sb);
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

	public int insertSchoolbus(Connection conn, int userNo, int busNo) {
		// 예약 테이블에 추가하는 메소드
		
		int result = 0;
		PreparedStatement pstmt = null;
		
//		insertSchoolbus=INSERT INTO USER_RESERVATION VALUES(SEQ_RENO.NEXTVAL, ?, ?, DEFAULT)
		String sql = prop.getProperty("insertSchoolbus");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, busNo);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int seatCountupdate(Connection conn, int busNo) {
		// 잔여 좌석수 감소 시켜주는 메소드
		
		int result = 0;
		PreparedStatement pstmt = null;
//		seatCountupdate=UPDATE SCHOOLBUS SET SEAT_COUNT=SEAT_COUNT-1 WHERE BUS_DAILY_NO=?
		String sql = prop.getProperty("seatCountupdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, busNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<UserReservation> selectReList(Connection conn) {
		// 회원 예약 목록 DB에서 가져오기
		ArrayList<UserReservation> list = new ArrayList<UserReservation>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		selectReList=SELECT A.BUS_NO, A.RES_USER_NO , B.BUS_CONTENT, C.USER_ID, C.USER_NAME, C.PHONE \
//				FROM USER_RESERVATION A JOIN SCHOOLBUS B ON (A.BUS_DAILY_NO = B.BUS_DAILY_NO) \
//				JOIN R_USER C ON (A.RES_USER_NO = C.USER_NO) WHERE A.STATUS = 'Y'
		String sql = prop.getProperty("selectReList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
//			(int busNo, String busDailyNo, int resUserNo, String resUserId, String resUserName, String resUserPhone)
			while(rset.next()) {
				list.add(new UserReservation(rset.getInt("BUS_NO"),
											 rset.getString("BUS_CONTENT"),
											 rset.getInt("RES_USER_NO"),
											 rset.getString("USER_ID"),
											 rset.getString("USER_NAME"),
											 rset.getString("PHONE")
						));
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

	public int seatCountReset(Connection conn) {
		// DB 잔여 좌석수 30으로 초기화 메소드
		
		int result = 0;
		Statement stmt = null;
//		seatCountReset=UPDATE SCHOOLBUS SET SEAT_COUNT=30
		String sql = prop.getProperty("seatCountReset");
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		
		return result;
	}

	public int deleteAllSchoolbus(Connection conn) {
		// 통학버스 예약테이블 전체 삭제 메소드
		
		int result = 0;
		Statement stmt = null;
//		deleteAllSchoolbus=UPDATE USER_RESERVATION SET STATUS='N'
		String sql = prop.getProperty("deleteAllSchoolbus");
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}

}
