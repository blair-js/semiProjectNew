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

}
