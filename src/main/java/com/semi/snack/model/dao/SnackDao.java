package com.semi.snack.model.dao;

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

import com.semi.common.dto.Attachment;
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.dto.UserPoint;

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



	public ArrayList<Snack> selectList(Connection conn) {
		
		ArrayList<Snack> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			System.out.println("담긴 값 확인하기 : " + rset);
			
			while(rset.next()) {
				Snack snack = new Snack(); //rset을 하나만 담는게 아닌 여러개를 담기위해서 while 안에 넣어야한다.
				snack.setSanckNo(rset.getInt("SNACK_NO"));
				snack.setSanckName(rset.getString("SNACK_NAME"));
				snack.setPrice(rset.getInt("PRICE"));
				snack.setTitleImg(rset.getString("CHANGE_NAME"));
				
				list.add(snack);

			}
				
			System.out.println("list에 담겨있는 값 : " + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return list;
		
		
	}



	public Snack selectSnack(Connection conn, int sno) {
		
		Snack snack = null;
		
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSnack");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			
			rset = pstmt.executeQuery();
			System.out.println(rset + "찍어보자");
			
			if(rset.next()) {
			
				
				
			snack = new Snack(rset.getInt("SNACK_NO"),
							  rset.getString("SNACK_NAME"),
							  rset.getInt("PRICE")
							  
							  
							  

							  		);
		}
							
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("snack에 담긴 값" + snack);
		return snack;
	}



	public Attachment selectAttachment(Connection conn, int sno) {
		
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			
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



	public int updateSnack(Connection conn, Snack snack) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateSnack");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, snack.getSanckName());
			pstmt.setInt(2, snack.getPrice());
			pstmt.setInt(3, snack.getSanckNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int updateAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getChangeName());//쿼리문에 적혀있는
			pstmt.setString(2, at.getOriginName());//순서
			pstmt.setString(3, at.getFilePath());//대로 적어주고
			pstmt.setInt(4, at.getFileNo());//jsp에서 받아왔던 FileNo: 파일번호를 조건절에 적어준다 이번호인애를 업데이트
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int insertNewAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, at.getRefNo());
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



	public int deleteSnack(Connection conn, int sno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteSnack");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int deleteAttachment(Connection conn, int sno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public UserPoint selectUserPoint(Connection conn, int userNo) { //유저 포인트 조회를 하기위한 메서드
		
		UserPoint userPoint = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectUserPoint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				userPoint = new UserPoint(rset.getInt("USER_NO"),
										  rset.getInt("POINT"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return userPoint;
	}



}
