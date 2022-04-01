package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//findPwdform.jsp에서 옴
@WebServlet("/findPwd.do")
public class UserPwdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPwdFindServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//비밀번호 찾기 후 
		//비밀번호 완료 페이지로 이동
		//response.sendRedirect("views/user/findUserPwdOk.jsp");
		RequestDispatcher view = request.getRequestDispatcher("views/user/findUserPwdOk.jsp");
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
