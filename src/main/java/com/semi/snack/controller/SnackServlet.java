package com.semi.snack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.Attachment;
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.dto.UserPoint;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class SnackEnrollFormServlet
 */
@WebServlet("/snack.do")
public class SnackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("간식 목록 조회 해오는 서블릿");
		
		ArrayList<Snack> list = new SnackService().selectList();
		
		int userno = Integer.parseInt(request.getParameter("userNo"));
		
		request.setAttribute("up", 0);
		
		if(userno != 0) {
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			UserPoint up = new UserPoint();		
			up.setUserNo(userNo);
			
			UserPoint userPoint = new SnackService().selectUserPoint(up); //유저 포인트 조회를 위한 메서드
			System.out.println(userNo);
			request.setAttribute("up", userPoint.getUserPoint());
		}
		
		
		request.setAttribute("list", list);
		RequestDispatcher view = request.getRequestDispatcher("views/snack/snack.jsp");
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
