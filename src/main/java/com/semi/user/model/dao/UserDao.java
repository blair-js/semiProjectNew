package com.semi.user.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.semi.common.JDBCTemplate.*;

import com.semi.common.dto.Attachment;
import com.semi.user.model.dto.Dog;
import com.semi.user.model.dto.User;

public class UserDao {
	
	//properties 파일을 읽어옹기 위한 객체 prop
	private Properties prop = new Properties();
	
	//UserDao 생성자
	public UserDao() {
		
		//읽어올 파일이름(userDao이므로 user쿼리 읽어오기)
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
		
		//아이디와 패스워드를 기준으로 조회
		//loginUser=SELECT * FROM R_USER 
		//WHERE USER_ID=? AND USER_PWD=? AND STATUS='Y'
		String sql = prop.getProperty("loginUser");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			//쿼리 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			/*  조회되는 컬럼(전체 조회 *)
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
				ADMIN_CHECKED
			 */
			
			//조회결과로 오는 행은 1개이므로 반복문은 필요없음 
			if(rset.next()) { //조회 결과가 있다면
				
				//rset의 컬럼 값을 담아서 User 객체를 생성
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
										, rset.getString("ADMIN_CHECKED")
									);
				
			}//if
			
			//확인
			//System.out.println(loginUser.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser; //정보가 담겨있는 User 객체 반환(없으면 null 반환)
	}


	public String findUserId(Connection conn, String userName, String userEmail) {
		//아이디 찾기 메소드
		
		//해당 조건과 일치하는(이름, 이메일) 회원을 찾지못할경우 null 반환
		String userId = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//사용자가 입력한 이름과 이메일 기준으로 아이디만 조회
		//findUserId=SELECT USER_ID FROM R_USER 
		//WHERE USER_NAME=? AND EMAIL=? AND STATUS='Y'
		String sql = prop.getProperty("findUserId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅 
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			
			//쿼리 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			if(rset.next()) { //결과가 있다면
				//조회되는 컬럼의 1번째 가져오기(어짜피 1행 1열로 아이디 값만 조회됨)
				userId = rset.getString(1);
			}//if
			
			//확인
			System.out.println(userId);

		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			close(rset);
			close(pstmt);
		}
		
		return userId; //조회 결과 반환(아이디 or null)
	}


	public String findUserPwd(Connection conn, String userName, String userId, String userEmail) {
		//비밀번호 찾기 메소드
		
		//해당 조건과 일치하는(이름, 아이디, 이메일) 회원을 찾지못할경우 null 반환
		String userPwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//사용자가 입력한 이름, 아이디, 이메일 기준으로 조회
		//findUserPwd=SELECT USER_PWD FROM R_USER 
		//WHERE USER_NAME=? AND USER_ID=? AND EMAIL=? AND STATUS='Y'
		String sql = prop.getProperty("findUserPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅 
			pstmt.setString(1, userName);
			pstmt.setString(2, userId);
			pstmt.setString(3, userEmail);
			
			//쿼리 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			if(rset.next()) { //결과가 있다면
				//조회되는 컬럼의 1번째 가져오기(어짜피 1행 1열로 비밀번호 값만 조회됨)
				userPwd = rset.getString(1);
			}//if
			
			//확인
			System.out.println(userPwd);

		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			close(rset);
			close(pstmt);
		}
		
		return userPwd; //조회 결과 반환(아이디 or null)
	}


	public User selectUser(Connection conn, String userId) {
		//회원의 정보를 조회해오는 메소드
		
		//인자로 들어온 아이디를 가진 회원이 없으면 null을 반환할 것
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectUser=SELECT * FROM R_USER WHERE USER_ID=? AND STATUS='Y'
		String sql = prop.getProperty("selectUser");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userId);
			
			//쿼리 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			//조회결과로 오는 행은 1개이므로 반복문은 필요없음 
			if(rset.next()) { //조회 결과가 있다면
				
				//rset의 컬럼 값을 담아서 User 객체를 생성
				user = new User(rset.getInt("USER_NO")
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
										, rset.getString("ADMIN_CHECKED")
									);
				
			}//if
			
			//확인
			System.out.println(user.toString());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			close(rset);
			close(pstmt);
		}
		
		return user; //정보가 담겨있는 user객체 반환(조회가 되지 않았다면 null 반환)
	}


	public int selectUserPoint(Connection conn, String userId) {
		//사용자 포인트 조회 메소드
		
		//조회되는 포인트가 없으면 0 반환
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//사용자포인트 테이블에는 userId가 없기 때문에, 사용자테이블에서 서브쿼리를 통해 userNo를 얻은 후 조회 
		//selectUserPoint=SELECT POINT 
		//FROM USER_POINT 
		//WHERE USER_NO=(SELECT USER_NO FROM R_USER WHERE USER_ID=?)
		String sql = prop.getProperty("selectUserPoint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userId);
			
			//쿼리 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			//조회되는 컬럼은 포인트 컬럼 1개이므로 반복이 필요없음
			if(rset.next()) {
				result = rset.getInt(1);
			}//if
			
			//확인
			System.out.println("포인트 : " + result);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
				
		//조회된 결과 포인트 반환
		return result; 
	}


	public int updateUser(Connection conn, User user) {
		//사용자 정보 업데이트 메소드
		
		//반환할 결과 변수
		int result = 0;
		PreparedStatement pstmt = null;
		
		//회원번호를 기준으로 사용자가 업데이트하고자 하는 컬럼들 업데이트
		//updateUser=UPDATE R_USER 
		//SET EMAIL=?, USER_NAME=?, PHONE=?, SMS_CHECKED=?, USER_GENDER=? 
		//WHERE USER_NO=? AND STATUS='Y'
		String sql = prop.getProperty("updateUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅(user 객체에 업데이트 내용들이 모두 담겨있으므로 get 해서 쿼리 순서에 맞게 셋팅)
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getSmsChecked());
			pstmt.setString(5, user.getGender());
			pstmt.setInt(6, user.getUserNo());
			
			result = pstmt.executeUpdate();
			
			//확인
			System.out.println("결과 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		//업데이트 결과 반환(1 or 0)
		return result;
		
	}


	public int deleteUser(Connection conn, String userId) {
		//사용자 정보 삭제 메소드(DB에서 아예 삭제하진 않고 상태값만 N으로 바꾼다)
		
		//반환할 결과 변수
		int result = 0;
		PreparedStatement pstmt = null;
		
		//deleteUser=UPDATE R_USER SET STATUS='N' 
		//WHERE USER_ID=? AND STATUS='Y'
		String sql = prop.getProperty("deleteUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userId);
	
			result = pstmt.executeUpdate();
			
			//확인
			System.out.println("결과 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		//업데이트 결과 반환(1 or 0)
		return result;
	}


	public ArrayList<Dog> selectDogList(Connection conn, int userNo) {
		//특정 회원(userId 기준)의 보유 강아지 리스트를 조회해오는 메소드
		
		//강아지 리스트가 담긴 결과 변수
		ArrayList<Dog> dogList = new ArrayList<Dog>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//회원번호를 기준으로 강아지 정보 조회
		//selectDogList=SELECT DOG_NO, CLASS_NAME, DOG_NAME, DOG_AGE, DOG_GENDER 
		//FROM DOG WHERE USER_NO=?
		String sql = prop.getProperty("selectDogList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setInt(1, userNo);
			
			//쿼리 실행 후 결과 반환받기
			rset = pstmt.executeQuery();
			
			//반환되는 행(결과)이 1개 이상일 수 있으므로 반복문 돌리기. (한 회원이 여러마리의 강아지를 보유했을수도 있음!)
			//조회되는 컬럼3개 DOG_NO, CLASS_NAME, DOG_NAME, DOG_AGE, DOG_GENDER
			while(rset.next()) {
				
				//list에 추가할 Dog 객체 생성
				Dog dog = new Dog();
				
				//생성한 dog 객체에 필드 값 셋팅
				//아래의 3개 필드를 제외하고 나머지 필드값은 null
				dog.setDogNo(rset.getInt("DOG_NO"));
				dog.setClassName(rset.getString("CLASS_NAME"));
				dog.setDogName(rset.getString("DOG_NAME"));
				dog.setDogAge(rset.getInt("DOG_AGE"));
				dog.setDogGender(rset.getString("DOG_GENDER"));
				
				//정보가 담긴 dog객체를 dogList 추가
				dogList.add(dog);
				
			}//while
			
			//확인
			for(int i=0; i<dogList.size(); i++) {
				System.out.println("dogList 확인" + dogList.get(i).toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		//해당 회원번호의 보유 강아지 리스트를 반환(empty일수도 있음)
		return dogList;
	}


	public int insertDog(Connection conn, Dog dog) {
		//강아지를 등록하는 메소드(입학)
		
		//입학 결과 반환하는 변수(강아지 등록 실패시 0 반환)
		int result = 0;
		PreparedStatement pstmt = null;
		
		//쿼리에 데이터를 삽입할 컬럼을 생략했으므로, 전컬럼에 대한 데이터 삽입
		//카테고리(분류번호) 컬럼은 1 로 고정값이다.
		//insertDog=INSERT INTO DOG VALUES(SEQ_DNO.NEXTVAL, ?, ?, 1, ?, ?, ?, ?, ?)
		String sql = prop.getProperty("insertDog");
		
		/*	쿼리 셋팅 순서(2개는 정적으로 고정되어있기때문에, 나머지 7개 물음표 순서대로 셋팅)
			USER_NO	NUMBER
			CLASS_NAME	VARCHAR2(15 BYTE)
			DOG_NAME	VARCHAR2(15 BYTE)
			DOG_AGE	NUMBER
			DOG_GENDER	VARCHAR2(1 BYTE)
			MEMO	VARCHAR2(1000 BYTE)
			WAITING	VARCHAR2(1 BYTE) 
		 */
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅(정보가 담겨있는 dog 객체에서 get 해오면 됨)
			pstmt.setInt(1, dog.getUserNo());
			pstmt.setString(2, dog.getClassName());
			pstmt.setString(3, dog.getDogName());
			pstmt.setInt(4, dog.getDogAge());
			pstmt.setString(5, dog.getDogGender());
			pstmt.setString(6, dog.getMemo());
			pstmt.setString(7, dog.getWating());
			
			//쿼리 실행 후 결과 반환
			result = pstmt.executeUpdate();
			
			//확인
			System.out.println("insertDog 결과확인 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result; //강아지 입학 결과 반환(1 or 0)
	}


	public int insertAttachmentDog(Connection conn, Attachment at, int userNo) {
		//입학하는 강아지의 **이미지를** 등록하는 메소드
		
		//반환할 결과변수
		int result = 0;
		PreparedStatement pstmt = null;
		
		//insertAttachmentDog=INSERT INTO ATTACHMENT 
		//VALUES(SEQ_FNO.NEXTVAL, ?, SEQ_DNO.CURRVAL, 1, ?, ?, ?, SYSDATE, DEFAULT)
		String sql = prop.getProperty("insertAttachmentDog");
		
		/* 	쿼리 셋팅 순서 (5개는 고정, 나머지만 4개만 내가 넣어주면 된다)
			USER_NO	NUMBER
			ORIGIN_NAME	VARCHAR2(255 BYTE)
			CHANGE_NAME	VARCHAR2(255 BYTE)
			FILE_PATH	VARCHAR2(1000 BYTE)
		 */
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setInt(1, userNo);
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			//쿼리 실행 후 결과 반환 
			result = pstmt.executeUpdate();
			
			//확인
			System.out.println("insertDog 결과확인 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result; //첨부파일 등록 결과 반환(1 or 0)
	}


	public String selectClassName(Connection conn, String dogWeight) {
		//입학신청을 한 강아지의 반을 배정해주는 메소드
		
		//반환해줄 결과 변수
		String className = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//조건 : 선택한 몸무게 and 그 반의 현재정원이 0이상 입학정원미만 인 경우만 반이름 조회!
		//selectClassName=SELECT CLASS_NAME FROM SCHOOL 
		//WHERE WEIGHT=? AND CURRENT_COUNT BETWEEN 0 AND TOTAL_COUNT-1
		String sql = prop.getProperty("selectClassName");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, dogWeight);
			
			rset = pstmt.executeQuery();
			
			//결과 행은 1개임(1행 1열로 반이름만 조회가 됨)
			if(rset.next()) {
				className = rset.getString(1);
			}//if
			
			//확인
			System.out.println("className 확인 : " + className);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		//결과 반환(조건을 만족하지 못했으면 null 반환)
		return className;
	}


	public ArrayList<Attachment> selectDogImgList(Connection conn, int userNo) {
		//특정 회원(userNO)의 강아지 사진 리스트를 가져오는 메소드 
		
		//반환할 결과 변수
		ArrayList<Attachment> dogImgList = new ArrayList<Attachment>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//회원번호를 기준으로 강아지 사진을 조회해오기
		//selectDogImgList=SELECT * FROM ATTACHMENT WHERE USER_NO=? AND STATUS='Y'
		String sql = prop.getProperty("selectDogImgList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setInt(1, userNo);
			
			//쿼리 실행 후 결과 반환
			rset = pstmt.executeQuery();
			
			/* 	조회되는 컬럼들 
			 	FILE_NO	NUMBER
				USER_NO	NUMBER
				REF_NO	NUMBER
				CATEGORY	NUMBER
				ORIGIN_NAME	VARCHAR2(255 BYTE)
				CHANGE_NAME	VARCHAR2(255 BYTE)
				FILE_PATH	VARCHAR2(1000 BYTE)
				UPLOAD_DATE	DATE
				STATUS	VARCHAR2(1 BYTE)
			 */
			
			//하나 이상이기 때문에 반복문 실행
			while(rset.next()) {
				//첨부파일 객체 생성(전체 컬럼을 조회해서 가져오므로 매개변수 생성자 사용)
				//첨부파일 객체 매개변수 생성자 순서 잘 볼것
				Attachment at = new Attachment(rset.getInt("FILE_NO")
												, rset.getInt("USER_NO")
												, rset.getInt("REF_NO")
												, rset.getInt("CATEGORY")
												, rset.getString("ORIGIN_NAME")
												, rset.getString("CHANGE_NAME")
												, rset.getString("FILE_PATH")
												, rset.getDate("UPLOAD_DATE")
												, rset.getString("STATUS")
											);
				
				//값이 모두 셋팅된 객체 at을 이미지리스트에 추가
				dogImgList.add(at);
			}
			
			//확인
			System.out.println("dogImgList 확인 : " + dogImgList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return dogImgList; //해당 회원의 강아지 리스트가 담겨있는 결과 반환 (없으면 empty)
	}

	public int idCheck(Connection conn, String userId) {
		//회원가입시 호출되는 메소드로, 아이디의 중복여부를 체크하는 메소드(DB에서 userId는 유니크 제약조건이 걸려있음)
		
		//결과 반환 변수(중복된 아이디가 있으면 1, 없으면 0)
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//idCheck=idCheck=SELECT COUNT(*) FROM R_USER 
		//WHERE USER_ID=? AND STATUS='Y'
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			//COUNT 조회이므로 결과 행은 1개 => 조회 결과가 있다는 것은 해당 아이디가 이미 존재한다는 것 => 사용불가!
			if(rset.next()) {
				result = rset.getInt(1); //1번째 컬럼 값 담기
			}
			
			//확인
			System.out.println("result 확인 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return result; //결과 반환
	}


	public int insertUser(Connection conn, User user) {
		//회원을 등록하는 메소드
		
		//반환할 결과변수
		int result = 0; 
		PreparedStatement pstmt = null;
		
		//insertUser=INSERT INTO R_USER 
		//VALUES(SEQ_UNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, SYSDATE, DEFAULT, DEFAULT)
		String sql = prop.getProperty("insertUser");
	
		/*	셋팅해줘야 하는 부분(나머지는 고정값)
			USER_ID	VARCHAR2(30 BYTE)
			EMAIL	VARCHAR2(100 BYTE)
			USER_PWD	VARCHAR2(100 BYTE)
			USER_NAME	VARCHAR2(15 BYTE)
			PHONE	VARCHAR2(13 BYTE)
			SMS_CHECKED	VARCHAR2(1 BYTE)
			EMAIL_HASH	VARCHAR2(64 BYTE)
			EMAIL_CHECKED	VARCHAR2(1 BYTE)
			USER_GENDER	VARCHAR2(1 BYTE)
			ADMIN_CHECKED	VARCHAR2(1 BYTE)
		 */
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getUserPwd());
			pstmt.setString(4, user.getUserName());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getSmsChecked());
			pstmt.setString(7, user.getEmailHash());
			pstmt.setString(8, user.getEmailChecked());
			pstmt.setString(9, user.getGender());
			
			//쿼리 실행 후 결과 반환
			result = pstmt.executeUpdate();

			//확인
			System.out.println("회원 insert 결과 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		
		return result; //회원 가입 결과 반환 1 or 0
	}


	public String getUserEmail(Connection conn, String userId) {
		//회원의 이메일을 얻는(조회해오는) 메소드
		
		//반환해줄 결과 변수
		String userEmail = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//아이디를 기준으로 메일 조회해오기
		//getUserEmail=SELECT EMAIL FROM R_USER WHERE USER_ID=? AND STATUS='Y'
		String sql = prop.getProperty("getUserEmail");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			//결과 행은 1개임(1행 1열로 메일주소만 얻음)
			if(rset.next()) {
				userEmail = rset.getString(1);
			}//if
			
			//확인
			System.out.println("userEmail 확인 : " + userEmail);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		//결과 반환
		return userEmail;
	}


	public String selectEmailHashCode(Connection conn, String userId) {
		//이메일 해쉬코드를 조회해오는 메소드(조회되는 해쉬코드를 이메일 인증시 넘어오는 파라미터와 비교하기 위해 필요)
		
		//반환할 결과 변수
		String emailHashCode = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//아이디를 기준으로 해당 아이디의 이메일 해쉬코드를 얻어옴(해당 해쉬코드는 회원가입시 자동으로 생성되었음)
		//selectEmailHashCode=SELECT EMAIL_HASH 
		//FROM R_USER WHERE USER_ID=? AND STATUS='Y'
		String sql = prop.getProperty("selectEmailHashCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userId);
			
			//쿼리 실행 후 결과 반환
			rset = pstmt.executeQuery();
			
			//결과가 있다면 1건(1행1열임)
			if(rset.next()) {
				emailHashCode = rset.getString(1);
			}
			
			//확인
			System.out.println("DB에서 emailHashCode 확인 : " + emailHashCode);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return emailHashCode; //조회해온 이메일해쉬코드 반환
	}


	public int updateEmailChecked(Connection conn, String userId) {
		//이메일 인증체크여부를 업데이트 해주는 메소드(N -> Y)
		
		//반환할 결과 변수
		int result = 0;
		PreparedStatement pstmt = null;
		
		//회원아이디를 기준으로 이메일체크 컬럼을 Y로 업데이트 해주기
		//updateEmailChecked=UPDATE R_USER SET EMAIL_CHECKED='Y' WHERE USER_ID=? AND STATUS='Y'
		String sql = prop.getProperty("updateEmailChecked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리값셋팅
			pstmt.setString(1, userId);
			
			//쿼리실행 후 결과 반환
			result = pstmt.executeUpdate();
			
			//확인
			System.out.println("이메일 체크 결과확인 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result; //결과 반환
	}


	public int updateCookieChecked(Connection conn, String userId) {
		//자동 로그인 여부를 업데이트 해주는 메소드(N -> Y)
		
		//반환할 결과 변수
		int result = 0;
		PreparedStatement pstmt = null;
		
		//아이디를 기준으로 해당 아이디의 쿠키컬럼을 Y로 변경(회원가입시 디폴트로 N이 들어갔음)
		//updateCookieChecked=UPDATE R_USER SET COOKIE_CHECKED='Y' WHERE USER_ID=? AND STATUS='Y'
		String sql = prop.getProperty("updateCookieChecked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리값셋팅
			pstmt.setString(1, userId);
			
			//쿼리 실행 후 결과 반환
			result = pstmt.executeUpdate();
			
			//확인
			System.out.println("자동로그인 업데이트 결과확인 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result; //결과 반환
	}


	public User selectUserbyGoogle(Connection conn, String userName, String userEmail) {
		//구글 로그인을 위한 객체의 정보를 조회해오는 메소드
		
		//인자로 들어온 아이디와 비번을 가진 회원이 없으면 null을 반환할 것
		User loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//selectUserbyGoogle=SELECT * FROM R_USER WHERE USER_NAME=? AND EMAIL=? AND STATUS='Y'
		String sql = prop.getProperty("selectUserbyGoogle");
		
		/*  조회되는 컬럼 
		 	USER_NO	NUMBER
			USER_ID	VARCHAR2(30 BYTE)
			EMAIL	VARCHAR2(100 BYTE)
			USER_PWD	VARCHAR2(100 BYTE)
			USER_NAME	VARCHAR2(15 BYTE)
			PHONE	VARCHAR2(13 BYTE)
			SMS_CHECKED	VARCHAR2(1 BYTE)
			EMAIL_HASH	VARCHAR2(64 BYTE)
			EMAIL_CHECKED	VARCHAR2(1 BYTE)
			USER_GENDER	VARCHAR2(1 BYTE)
			STATUS	VARCHAR2(1 BYTE)
			ENROLL_DATE	DATE
			COOKIE_CHECKED	VARCHAR2(1 BYTE)
			ADMIN_CHECKED	VARCHAR2(1 BYTE) 
		 */
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			
			//쿼리 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			//조회결과로 오는 행은 1개이므로 반복문은 필요없음 
			if(rset.next()) { //조회 결과가 있다면
				
				//rset의 컬럼 값을 담아서 User 객체를 생성
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
										, rset.getString("ADMIN_CHECKED")
									);
				
			}//if
			
			//확인
			//System.out.println(loginUser.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser; //정보가 담겨있는 User 객체 반환(없으면 null 반환)
	}


	public int updateUserPoint(Connection conn, String userId, int updatePoint) {
		//회원의 포인트를 업데이트 해주는 메소드
		
		//결과 변수
		int result = 0;
		PreparedStatement pstmt = null;
		
		//넘어오는 매개변수는 userId뿐이므로, 회원테이블에서 userId를 기준으로 userNo를 얻는 서브쿼리가 필요
		//포인트테이블에서 해당 userNo의 포인트를 업데이트 해준다.
		//updateUserPoint=UPDATE USER_POINT SET POINT =? 
		//WHERE USER_NO =(SELECT USER_NO FROM R_USER WHERE USER_ID =?)
		String sql = prop.getProperty("updateUserPoint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//쿼리 값 셋팅
			pstmt.setInt(1, updatePoint);
			pstmt.setString(2, userId);
			
			//쿼리 실행 후 결과
			result = pstmt.executeUpdate();
			
			//확인
			System.out.println("포인트 업데이트 결과 확인 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result; //결과 반환
	}

}
