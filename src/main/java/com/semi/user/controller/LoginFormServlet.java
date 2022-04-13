package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그인 form을 뿌려주는 서블릿
@WebServlet("/loginForm.do")
public class LoginFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//아이디 찾기시 받아오는 파라미터
		String userId = request.getParameter("userId");
		
		System.out.println("로그인 작성 폼을 뿌려줄 서블릿");
		//로그인 작성 폼을 띄워줄 jsp 화면 뿌려주기
		
		//아이디 찾기 후 로그인화면으로 전환될 때, 아이디 입력란에 바로 뿌려주기 위해 request 객체에 set 해줌!
		request.setAttribute("userId", userId);
		RequestDispatcher view = request.getRequestDispatcher("views/user/loginForm.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
