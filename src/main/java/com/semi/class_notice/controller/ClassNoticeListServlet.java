package com.semi.class_notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/classNoticeList.do")
public class ClassNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClassNoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Dao까지 가서 DB 결과 끌어와서 리스트 목록 화면에 보여준다
		request.getRequestDispatcher("views/class_notice/classNoticeListView.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
