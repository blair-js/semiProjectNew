package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//findIdForm.jsp에서 넘어옴 
@WebServlet("/findId.do")
public class UserIdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserIdFindServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//아이디 찾기 후 
		//아이디찾기 완료 페이지로 이동
		//response.sendRedirect("views/user/findUserIdOk.jsp");
		RequestDispatcher view = request.getRequestDispatcher("views/user/findUserIdOk.jsp");
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
