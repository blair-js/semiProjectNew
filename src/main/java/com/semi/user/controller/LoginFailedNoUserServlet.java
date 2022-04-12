package com.semi.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그인 실패시(회원의 정보가 없는 경우) 여기로 옴 
@WebServlet("/loginFailedNoUser.do")
public class LoginFailedNoUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginFailedNoUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//로그인 실패 메세지
		request.setAttribute("reason", "아이디와 비밀번호가 일치하는 회원이 없습니다.");
		//request.getSession().setAttribute("msg", "입력하신 아이디와 비밀번호가 일치하는 회원이 없습니다.");
		
		//로그인 실패화면 jsp로 이동
		request.getRequestDispatcher("views/user/loginFailedNoUser.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
