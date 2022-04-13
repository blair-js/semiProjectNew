package com.semi.schoolbus.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.schoolbus.model.dto.UserReservation;
import com.semi.schoolbus.model.service.SchoolbusService;

/**
 * Servlet implementation class UserReservationFromServlet
 */
@WebServlet("/reservationForm.do")
public class UserReservationFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReservationFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 예약한 회원 정보와 화면전환 해주는 서블릿
		// 예약한 회원 목록을 받아서 넘겨줄거임
		ArrayList<UserReservation> list = new SchoolbusService().selectReList();
		
		if(list != null) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("views/schoolbus/userResForm.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "예약 회원 목록 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
