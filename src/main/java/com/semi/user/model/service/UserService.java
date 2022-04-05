package com.semi.user.model.service;

import com.semi.user.model.dao.UserDao;
import com.semi.user.model.dto.User;

import java.sql.Connection;
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

}
