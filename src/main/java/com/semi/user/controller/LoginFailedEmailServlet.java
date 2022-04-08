package com.semi.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그인 실패시(회원의 정보는 있지만, 이메일 인증을 하지 않아서 로그인에 실패한 경우) 여기로 옴 
@WebServlet("/loginFailedEmail.do")
public class LoginFailedEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginFailedEmailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//LoginServlet에서 넘어오는 userId 파라미터 받아주기
		String userId = request.getParameter("userId");
		//jsp에서 써야하므로 userId set 해주기
		request.setAttribute("userId", userId);
		
		//request.getSession().setAttribute("msg", "이메일 미인증 회원입니다.");
		//request.setAttribute("msg", "이메일 미인증 회원입니다.");
		
		request.setAttribute("reason", "이메일 미인증 회원입니다.");
		//로그인 실패화면 jsp로 이동
		request.getRequestDispatcher("views/user/loginFailedEmail.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
