package com.semi.schoolbus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/schoolbusInsert.do")
public class SchoolbusInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SchoolbusInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 신청 정보를 jsp에서 입력받은 값으로 DB에 저장
		// DB 추가 되고 나면 다시 홈으로 화면 전환
		request.getSession().setAttribute("msg", "통학버스 신청이 완료되었습니다.");
		response.sendRedirect("index.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
