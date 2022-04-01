package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findIdForm.do")
public class UserFindIdFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserFindIdFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//로그인 작성 폼을 띄워줄 jsp 화면 뿌려주기
		RequestDispatcher view = request.getRequestDispatcher("views/user/findIdForm.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
