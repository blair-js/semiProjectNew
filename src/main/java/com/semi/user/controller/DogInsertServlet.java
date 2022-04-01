package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//dogEnrollForm.jsp에서 여기로 옴
@WebServlet("/insertDog.do")
public class DogInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DogInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//입학신청 성공화면으로 
		RequestDispatcher view = request.getRequestDispatcher("views/user/dogInsertOk.jsp");
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
