package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//userEnrollForm.jsp에서 넘어옴
@WebServlet("/insertUser.do")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//회원가입을 시킨다. 
		
		//회원 가입이 정상 완료되면 => 이메일 발송 페이지로 이동 
		RequestDispatcher view = request.getRequestDispatcher("views/user/emailSendAction.jsp");
		
		//0403 주석처리
		//RequestDispatcher view = request.getRequestDispatcher("views/user/loginForm.jsp");
		view.forward(request, response);
		//response.sendRedirect("views/user/loginForm.jsp");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
