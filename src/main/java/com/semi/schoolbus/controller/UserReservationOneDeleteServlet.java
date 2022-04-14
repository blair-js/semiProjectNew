package com.semi.schoolbus.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.schoolbus.model.service.SchoolbusService;

/**
 * Servlet implementation class UserReservationOneDeleteServlet
 */
@WebServlet("/deleteUserRes.do")
public class UserReservationOneDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReservationOneDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 화면에서 ajax 데이터로 버스 예약 번호 전달받는다.
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String content = request.getParameter("content");
		
		int result = new SchoolbusService().deleteOneSchoolbus(bNo, content);
		//ajax 성공시 success 전달 위해 PrintWriter 생성
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.print("success");
		}else {
			out.print("failed");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
