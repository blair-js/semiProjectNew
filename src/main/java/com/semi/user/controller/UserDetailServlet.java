package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userDetail.do")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//회원에 대한 정보를 조회해와서 
		//jsp에 뿌려주기 
		
		//나의 상세정보
		RequestDispatcher view = request.getRequestDispatcher("views/user/userDetailMyPage.jsp");
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
