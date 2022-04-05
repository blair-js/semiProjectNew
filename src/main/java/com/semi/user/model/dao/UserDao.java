package com.semi.user.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.semi.common.JDBCTemplate.*;
import com.semi.user.model.dto.User;

public class UserDao {
	
	//properties 파일을 읽어옹기 위한 객체 prop
	private Properties prop = new Properties();
	
	//UserDao 생성자
	public UserDao() {
		
		String fileName = UserDao.class.getResource("/sql/user/user-query.properties").getPath();
		System.out.println("fileName " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public User loginUser(Connection conn, String userId, String userPwd) {
		//로그인 메소드
		
		//인자로 들어온 아이디와 비번을 가진 회원이 없으면 null을 반환할 것
		User loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//loginUser=SELECT * FROM R_USER 
		//WHERE USER_ID=? AND USER_PWD=? AND STATUS='Y'
		//String sql = prop.getProperty("loginUser");
		String sql = "SELECT * FROM R_USER";
		
		System.out.println("check");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			//pstmt.setString(1, userId);
			//pstmt.setString(2, userPwd);
			
			//쿼리 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			/*
			 	USER_NO
				USER_ID
				EMAIL
				USER_PWD
				USER_NAME
				PHONE
				SMS_CHECKED
				EMAIL_HASH
				EMAIL_CHECKED
				USER_GENDER
				STATUS
				ENROLL_DATE
				COOKIE_CHECKED
			 */
			
			//조회결과로 오는 행은 1개이므로 반복문은 필요없음 
			if(rset.next()) { //조회 결과가 있다면
				
				System.out.println("db연결 ck");
				
				//User 객체 생성
				loginUser = new User(rset.getInt("USER_NO")
										, rset.getString("USER_ID")
										, rset.getString("EMAIL")
										, rset.getString("USER_PWD")
										, rset.getString("USER_NAME")
										, rset.getString("PHONE")
										, rset.getString("SMS_CHECKED")
										, rset.getString("EMAIL_HASH")
										, rset.getString("EMAIL_CHECKED")
										, rset.getString("USER_GENDER")
										, rset.getString("STATUS")
										, rset.getDate("ENROLL_DATE")
										, rset.getString("COOKIE_CHECKED")
									);
				
			}//if
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println(loginUser.getUserName());
		
		return loginUser; //정보가 담겨있는 User 객체 반환(없으면 null 반환)
	}

	/*public String getUserEmailChecked(String userID) {
		
		String SQL = "SELECT USEREMAIL_CHECKED FROM LECTURE_USER WHERE USERID=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);

			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				return rs.getString(1); //이메일 인증여부 T, F가 넘어갈 것.
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn != null) conn.close();
					if(pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return "F"; //데이터베이스 오류
	
	}*/

	/*public String getUserEmail(String userID) {
		
		String SQL = "SELECT userEmail FROM LECTURE_USER WHERE USERID=?";
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
	
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				return rs.getString(1); 
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn != null) conn.close();
					if(pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return null; //데이터베이스 오류*/
	
	/*public String setUserEmailChecked(String userID) {
		
		String SQL = "UPDATE LECTURE_USER SET USEREMAIL_CHECKED=\'T\' WHERE USERID=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);

			pstmt.executeUpdate();

			return "T";
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn != null) conn.close();
					if(pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return "F"; //데이터베이스 오류
	}*/
}
