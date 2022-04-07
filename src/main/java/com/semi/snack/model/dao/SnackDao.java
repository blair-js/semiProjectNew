package com.semi.snack.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.semi.common.JDBCTemplate.*;

import com.semi.common.dto.Attachment;
import com.semi.snack.model.dto.Snack;

public class SnackDao {
	
	private Properties prop = new Properties();
	public SnackDao() {
		String fileName = SnackDao.class.getResource("/sql/snack/snack-query.properties").getPath();
		System.out.println("fileName" + fileName);
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

	
	
	/*
	public int snackInsert(Connection conn, Snack snack) { //간식추가 메서드
		
		//snackInsert="INSERT INTO SNACK VALUES(SEQ_SNO.NEXTVAL, ?, DEFAULT, ?, DEFAULT)

		int result = 0;
		
		PreparedStatement pstmt = null;	
		
		String sql = prop.getProperty("snackInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, snack.getSanckName());
			pstmt.setInt(2, snack.getPrice());
			
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}


*/

	public int insertSnack(Connection conn, Snack snack) { //간식 추가 메서드
		
		//snackInsert="INSERT INTO SNACK VALUES(SEQ_SNO.NEXTVAL, ?, DEFAULT, ?, DEFAULT)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("snackInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, snack.getSanckName());
			pstmt.setInt(2, snack.getPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int insertAttachment(Connection conn, Attachment at, int userNo) {
		//insertAttachmentSnack=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, ?, SEQ_SNO.CURRVAL, 2, ?, ?, ?, SYSDATE, DEFAULT)
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachmentSnack");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
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


}
