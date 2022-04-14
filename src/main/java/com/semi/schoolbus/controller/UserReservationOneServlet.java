package com.semi.schoolbus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.schoolbus.model.dto.UserReservation;
import com.semi.schoolbus.model.service.SchoolbusService;

/**
 * Servlet implementation class UserReservationOneServlet
 */
@WebServlet("/userRes.do")
public class UserReservationOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReservationOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원의 번호를 받아서 예약테이블의 예약회원번호와 같은 정보를 뽑아온다.
		int uNo = Integer.parseInt(request.getParameter("uNo"));
		
		System.out.println("예약 회원 번호 : " + uNo);
		
		UserReservation re = new SchoolbusService().selectOneRes(uNo);
		// 제이슨 사용전 필수 지정
		response.setContentType("application/json; charset=utf-8");
		// Gson 사용해서 간단하게 변환
		Gson gson = new Gson();
		gson.toJson(re, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
