package com.semi.snack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.snack.model.service.SnackService;
import com.semi.user.model.dto.User;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/userSearchForm.do")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원조회를 하기위한 서블릿");
		
		int uno = Integer.parseInt(request.getParameter("userNo"));
		
		
		ArrayList<User> list = new SnackService().userSearch(uno);
		
		request.setAttribute("msg", "조회 성공");
		RequestDispatcher view = request.getRequestDispatcher("views/admin/userSearch.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
