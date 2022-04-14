package com.semi.schoolbus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.schoolbus.model.service.SchoolbusService;

/**
 * Servlet implementation class SchoolbusAllDeleteServlet
 */
@WebServlet("/schoolbusAllDelete.do")
public class SchoolbusAllDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchoolbusAllDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 예약 테이블 값 모두 없애기 값을 없애줄때 통학버스 테이블의 잔여 좌석수 모두 30으로 초기화 시켜준다.
		
		int result = new SchoolbusService().deleteAllSchoolbus();
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "통학버스 예약내용이 모두 삭제 되었습니다.");
			response.sendRedirect("reservationForm.do");
		}else{
			request.setAttribute("msg", "통학버스 예약내용 전체 삭제 실패");
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
