package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findPwdForm.do")
public class UserPwdFindFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPwdFindFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//비밀번호 찾기 폼을 띄워줄 jsp 화면 뿌려주기
		RequestDispatcher view = request.getRequestDispatcher("views/user/findPwdForm.jsp");
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
