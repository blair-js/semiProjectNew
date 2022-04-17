package com.semi.snack.model.dao;

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
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.dto.SnackOrder;
import com.semi.snack.model.dto.UserPoint;
import com.semi.user.model.dto.User;

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



   public int insertAttachment(Connection conn, Attachment at, int userNo) { //간식 (첨부파일 추가 메서드)
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



   public ArrayList<Snack> selectList(Connection conn) { //간식에 대한 정보를 화면에 뿌려줄 메서드
      
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



   public Snack selectSnack(Connection conn, int sno) { //첨부파일 없이 필요한 간식 정보만 조회하는 메서드
      
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



   public Attachment selectAttachment(Connection conn, int sno) { //첨부파일 조회하는 메서드
      
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



   public int updateSnack(Connection conn, Snack snack) { //간식 수정 메서드
      
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



   public int updateAttachment(Connection conn, Attachment at) { //간식 첨부파일 수정 메서드
      
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



   public int insertNewAttachment(Connection conn, Attachment at) { //간식 수정 새로운 첨부파일 메서드
      
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



   public int deleteSnack(Connection conn, int sno) { //간식 삭제 메서드
      
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



   public int deleteAttachment(Connection conn, int sno) { //간식 첨부파일 삭제 메서드
      
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


   public int OrderEnd(Connection conn, UserPoint up) {
      
      int result = 0;
      
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("orderEnd");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, up.getPoint());
         pstmt.setInt(2, up.getUserNo());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }


   public int insertOrder(Connection conn, String snackNo, int uno) { //간식구매한 정보를 insert해줄 메서드
      
      int result2 = 0;
      
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("insertOrder");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, snackNo);
         pstmt.setInt(2, uno);
         
         result2 = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      
      return result2;
   }


   public Snack selectSnack(Connection conn, String snackNo) { //간식 가격을 알기위한 메서드
      
      Snack snack = null;
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectPrice");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, snackNo);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
         
         snack = new Snack(rset.getInt("PRICE"));
         
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      
      return snack;
   }

   public ArrayList<SnackOrder> selectSnackOrderList(Connection conn, PageInfo pi) {
      //SELECT A.ORDER_NO, A.ORDER_DATE, C.USER_ID, B.SNACK_NAME FROM SNACK_ORDER A JOIN SNACK B ON A.SNACK_NO = B.SNACK_NO JOIN R_USER C ON A.USER_NO = C.USER_NO
      ArrayList<SnackOrder> list = new ArrayList<>();
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectSnackOrderList");
      
      
      int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1; //물음표에 값을 넣어주기 위해 구해준다 pi에서 다 받아온거에서 가져오는거임 담겨있는걸 쓰는거임
      int endRow = startRow + pi.getBoardLimit() -1;
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         
         rset = pstmt.executeQuery();
         
         System.out.println( "rset 잘 담았는지 체크" + rset );
         
         while(rset.next()) {
            SnackOrder so = new SnackOrder();
            so.setOrderNo(rset.getInt("ORDER_NO"));
            so.setOrderDate(rset.getDate("ORDER_DATE"));
            so.setUserId(rset.getString("USER_ID"));
            so.setSnackName(rset.getString("SNACK_NAME"));
            
            list.add(so);
         }
         
         System.out.println("list에 담긴 값" + list);
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      
      return list;
   }
         
      

   public int getListCount(Connection conn) { //총 게시글 갯수를 구하는 메서드
      
      int listCount = 0;
      
      Statement stmt = null; 
      ResultSet rset = null;
      
      String sql = prop.getProperty("getListCount");
      
      try {
         stmt = conn.createStatement(); 
         rset = stmt.executeQuery(sql);
         
         if(rset.next()) {//( 카운트, 썸, 에버리지 = 하나만 값이 나오기에 if문 사용 )
            listCount = rset.getInt(1); //딱 하나만 나오기에 한 행만 나오기에 1번째 컬럼만 가져오겠다는 뜻 //COUNT(*) 집계함수 숫자하나만 나옴  
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(rset);
         close(stmt);
      }
      return listCount;
      
   }


   public int getUserListCount(Connection conn, int uno) {
      int listCount = 0;
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("getUserListCount");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, uno);
         
         rset = pstmt.executeQuery();
         
         
         if(rset.next()) {//( 카운트, 썸, 에버리지 = 하나만 값이 나오기에 if문 사용 )
            listCount = rset.getInt(1); //딱 하나만 나오기에 한 행만 나오기에 1번째 컬럼만 가져오겠다는 뜻 //COUNT(*) 집계함수 숫자하나만 나옴  
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

   public ArrayList<SnackOrder> userSnackOrderList(Connection conn, PageInfo pi, int uno) {
      
      //SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT B.ORDER_NO, B.ORDER_DATE, C.USER_ID, D.SNACK_NAME FROM SNACK_ORDER B JOIN R_USER C ON B.USER_NO = C.USER_NO JOIN SNACK D ON B.SNACK_NO = D.SNACK_NO WHERE C.USER_NO = ? ORDER BY B.ORDER_NO) A) WHERE RNUM BETWEEN ? AND ?
      
      ArrayList<SnackOrder> list = new ArrayList<>();
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("userSnackOrderList");
      int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1; //물음표에 값을 넣어주기 위해 구해준다 pi에서 다 받아온거에서 가져오는거임 담겨있는걸 쓰는거임
      int endRow = startRow + pi.getBoardLimit() -1;
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, uno);
         pstmt.setInt(2, startRow);
         pstmt.setInt(3, endRow);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            SnackOrder so = new SnackOrder();
            so.setOrderNo(rset.getInt("ORDER_NO"));
            so.setOrderDate(rset.getDate("ORDER_DATE"));
            so.setUserId(rset.getString("USER_ID"));
            so.setSnackName(rset.getString("SNACK_NAME"));
            
            list.add(so);
         }
         
         System.out.println("dao list에 담은 값" + list);
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }


   public ArrayList<User> userSearch(Connection conn, PageInfo pi) {
            
      ArrayList<User> list = new ArrayList<>();
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("userSearch");
      //쿼리문 짜고 snackOrder에 생성자 만들고 멤버 변수도 만들어줘야함 
      
      int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1; //물음표에 값을 넣어주기 위해 구해준다 pi에서 다 받아온거에서 가져오는거임 담겨있는걸 쓰는거임
      int endRow = startRow + pi.getBoardLimit() -1;
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            User u = new User();
            u.setUserNo(rset.getInt("USER_NO"));
            u.setUserId(rset.getString("USER_ID"));
            u.setUserName(rset.getString("USER_NAME"));
            u.setPhone(rset.getString("PHONE"));
            u.setEnrollDate(rset.getDate("ENROLL_DATE"));
            
            list.add(u);
         }
         
         System.out.println("userlist에 담은 값" + list);
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      
      return list;
   }



}