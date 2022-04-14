package com.semi.schoolbus.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.schoolbus.model.dto.Schoolbus;
import com.semi.schoolbus.model.dto.UserReservation;
import com.semi.schoolbus.model.service.SchoolbusService;

@WebServlet("/schoolbusForm.do")
public class SchoolbusFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SchoolbusFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 통학버스 화면으로 전환만 시켜주는 서블릿 통학버스 잔여좌석 화면에 뿌려주기위해 조회해서 같이 넘겨준다.
    	// 예약을 한 회원의 경우 중복해서 예약이 불가능하도록 예약테이블도 넘겨준다.
    	ArrayList<Schoolbus> list = new SchoolbusService().selectBList();
    	ArrayList<UserReservation> rList = new SchoolbusService().selectReList();
    	
    	request.setAttribute("list", list);
    	request.setAttribute("rList", rList);
		request.getRequestDispatcher("views/schoolbus/schoolbusForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
