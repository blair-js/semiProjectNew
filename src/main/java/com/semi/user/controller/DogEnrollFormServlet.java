package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//index.jsp에서 입학신청 버튼 클릭시 여기로 옴
@WebServlet("/dogEnrollForm.do")
public class DogEnrollFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DogEnrollFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//힙학신청 폼을 띄워줄 jsp 화면 뿌려주기
		RequestDispatcher view = request.getRequestDispatcher("views/user/dogEnrollForm.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
