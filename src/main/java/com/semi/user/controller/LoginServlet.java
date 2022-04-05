package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

//loginForm.jsp에서 옴
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//post 방식으로 넘어오기 때문에 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//아이디와 비번 파라미터 받아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		System.out.println("userId 파라미터 확인" + userId);
		System.out.println("userPwd 파라미터 확인" + userPwd);
		
		//파라미터로 받은 아이디와 비번을 
		//UserService의 loginUser()메소드의 인자로 전달하여 결과 반환받기. => 결과는 User 객체로!
		User loginUser = new UserService().loginUser(userId, userPwd);
		
		///////////////////////////////////////////////////////////////////////////
		
		
		
		//로그인 성공시 다시 시작화면으로 이동
		//response.sendRedirect(request.getContextPath());
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
