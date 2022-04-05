package com.semi.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

   public static Connection getConnection() {
      
		Connection conn = null;

		Properties prop = new Properties();

		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();

		 System.out.println(fileName);

		try {
			prop.load(new FileReader(fileName));

			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("username");
			String password = prop.getProperty("password");

			// 1. 클래스 객체 등록, Driver 등록
			Class.forName(driver);

			// 2. DBMS와 연결
			conn = DriverManager.getConnection(url, user, password);

			conn.setAutoCommit(false);
			
      }catch(Exception e) {
         e.printStackTrace();
      }
      return conn;
   }
   
   public static void close(Connection conn) {
      try {
         if (conn != null && !conn.isClosed())
            conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public static void close(Statement stmt) {
      try {
         if (stmt != null && !stmt.isClosed())
            stmt.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public static void close(ResultSet rs) {
      try {
         if (rs != null && !rs.isAfterLast())
            rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public static void commit(Connection conn) {
      try {
         if (conn != null && !conn.isClosed())
            conn.commit();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public static void rollback(Connection conn) {
      try {
         if (conn != null && !conn.isClosed())
            conn.rollback();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}