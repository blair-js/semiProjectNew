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
 * Servlet implementation class SchoolbusOneDeleteServlet
 */
@WebServlet("/resdelete.do")
public class SchoolbusOneDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchoolbusOneDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 개별 삭제 버튼 클릭시 버스예약 번호를 받아서 온다.
		// 통학버스 테이블의 좌석수 증가위해서 버스내용도 같이 받아준다.
		int bno = Integer.parseInt(request.getParameter("bno"));
		String content = request.getParameter("content");
		System.out.println("예약 번호 : " + bno);
		// DB에 버스 예약번호로 접근해서 데이터 삭제
		int result = new SchoolbusService().deleteOneSchoolbus(bno, content);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "예약 취소 완료");
			response.sendRedirect("reservationList.do");
		}else {
			request.setAttribute("msg", "예약 취소 실패");
			request.getRequestDispatcher("views/common/errorPage").forward(request, response);
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
