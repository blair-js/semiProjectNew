package com.semi.class_notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/classNoticeEnrollForm.do")
public class ClassNoticeEnrollFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClassNoticeEnrollFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 작성하기 화면으로만 전환해주는 서블릿
		String classname = (String)request.getAttribute("classname");
		request.setAttribute("classname", classname);
		request.getRequestDispatcher("views/class_notice/classNoticeEnrollForm.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
