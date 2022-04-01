package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//userDetailMyPage.jsp에서 탈퇴버튼 클릭시
@WebServlet("/deleteUser.do")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect(request.getContextPath()); //메인페이지로 단순 화면 전환(데이터 노 필요)
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
