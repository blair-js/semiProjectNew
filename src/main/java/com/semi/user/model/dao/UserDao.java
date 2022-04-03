package com.semi.user.model.dao;

public class UserDao {

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
