package com.semi.schoolbus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/schoolbusForm.do")
public class SchoolbusFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SchoolbusFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 통학버스 화면으로 전환만 시켜주는 서블릿
		request.getRequestDispatcher("views/schoolbus/schoolbusForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
