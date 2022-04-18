package com.semi.schoolbus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.schoolbus.model.service.SchoolbusService;

@WebServlet("/schoolbusInsert.do")
public class SchoolbusInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SchoolbusInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 통학신청 페이지에서 회원번호와 버스일정번호를 받아서 테이블에 추가해준다.
		// 예약한 버스 일정은 잔여좌석수 -1 해주기위해 버스테이블 update도 같이 해준다.
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int busNo = Integer.parseInt(request.getParameter("scheduleNo"));
		
		int result = new SchoolbusService().insertSchoolbus(userNo, busNo);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "통학버스 신청이 완료되었습니다.");
			response.sendRedirect("userMyDetail.do");
		}else {
			request.setAttribute("msg", "예약신청이 실패하였습니다. 관리자에게 문의하세요.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
