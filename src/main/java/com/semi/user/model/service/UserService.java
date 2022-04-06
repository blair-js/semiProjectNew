package com.semi.user.model.service;

import com.semi.user.model.dao.UserDao;
import com.semi.user.model.dto.Dog;
import com.semi.user.model.dto.User;

import java.sql.Connection;
import java.util.ArrayList;

import static com.semi.common.JDBCTemplate.*;

public class UserService {

	public User loginUser(String userId, String userPwd) {

		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 아이디, 비번 인자로 전달
		User loginUser = new UserDao().loginUser(conn, userId, userPwd);
		
		//커넥션 닫기
		close(conn);
		
		//정보가 담긴 user객체 반환 
		return loginUser;
	}

	public String findUserId(String userName, String userEmail) {

		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 아이디, 비번 인자로 전달
		String userId = new UserDao().findUserId(conn, userName, userEmail);
		
		//커넥션 닫기
		close(conn);
		
		//아이디 전달 
		return userId;
		
	}

	public String findUserPwd(String userName, String userId, String userEmail) {

		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 이름, 아이디, 이메일을 인자로 전달
		String userPwd = new UserDao().findUserPwd(conn, userName, userId, userEmail);
		
		String newUserPwd = "";
		
		if(userPwd != null) {
			
			//비밀번호를 다 보여주지 않고, 가운데 2개는 별(*)처리를 해서 보여줄 것
			for(int i=0; i<userPwd.length(); i++) {
				char ch = userPwd.charAt(i);
				if(i == 3 || i == 4) { //3번째 4번째만
					ch = '*';
				}
				//i번째의 문자(ch)를 누적
				newUserPwd += ch;
			}	
		}else {
			newUserPwd = null;
		}
		
		//커넥션 닫기
		close(conn);
		
		//변환된 비밀번호 전달 or null
		return newUserPwd;
		
	}

	public User selectUser(String userId) {

		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 아이디를 인자로 전달
		User user = new UserDao().selectUser(conn, userId);
		
		//커넥션 닫기
		close(conn);
		
		//정보가 담긴 user객체 반환 
		return user;
	}

	public int selectUserPoint(String userId) {

		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 아이디를 인자로 전달
		int userPoint = new UserDao().selectUserPoint(conn, userId);
		
		//커넥션 닫기
		close(conn);
		
		//정보가 담긴 user객체 반환 
		return userPoint;
		
	}

	public int updateUser(User user) {
		
		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 user 객체를 인자로 전달
		int result = new UserDao().updateUser(conn, user);
		
		if(result > 0) { //업데이트 성공시
			commit(conn);
		}else { //업데이트 실패시
			rollback(conn);
		}
		
		//커넥션 닫기
		close(conn);
		
		//결과 반환
		return result;
	}

	public int deleteUser(String userId) {

		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 삭제할 회원의 아이디 userId를 인자로 전달
		int result = new UserDao().deleteUser(conn, userId);
		
		if(result > 0) { //삭제 성공시
			commit(conn);
		}else { //삭제 실패시
			rollback(conn);
		}
		
		//커넥션 닫기
		close(conn);
		
		//결과 반환
		return result;
	}

	public ArrayList<Dog> selectDogList(int userNo) {

		//커넥션 생성
		Connection conn = getConnection();
		
		//커넥션 객체와 아이디를 인자로 전달
		ArrayList<Dog> dogList = new UserDao().selectDogList(conn, userNo);
		
		//커넥션 닫기
		close(conn);
		
		//정보가 담긴 user객체 반환 
		return dogList;
	}

}
