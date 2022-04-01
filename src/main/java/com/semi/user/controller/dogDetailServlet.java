package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dogDetail.do")
public class dogDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public dogDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//도그에 대한 정보를 조회해와서 
		//jsp에 뿌려주기 
		
		//강아지 상세정보
		RequestDispatcher view = request.getRequestDispatcher("views/user/DogDetailPage.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
