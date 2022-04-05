package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginForm.do")
public class LoginFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("로그인 작성 폼을 뿌려줄 서블릿");
		//로그인 작성 폼을 띄워줄 jsp 화면 뿌려주기
		RequestDispatcher view = request.getRequestDispatcher("views/user/loginForm.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
