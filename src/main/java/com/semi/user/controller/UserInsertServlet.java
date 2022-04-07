package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//userEnrollForm.jsp에서 넘어옴
@WebServlet("/insertUser.do")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		System.out.println("회원가입서블릿 체크");
		
		//파라미터 받기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String smsCheck = request.getParameter("chk_sms");
		String userEmail = request.getParameter("userEmail");
		String gender = request.getParameter("gender");
		/*System.out.println(userId);
		System.out.println(userPwd);
		System.out.println(userName);
		System.out.println(phone);
		System.out.println(smsCheck);
		System.out.println(userEmail);
		System.out.println(gender);*/
		
		
		//회원 가입이 정상 완료되면 => 이메일 발송 페이지로 이동 
		request.getRequestDispatcher("views/user/emailSendAction.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
