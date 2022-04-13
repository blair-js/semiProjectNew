package com.semi.user.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

import util.Gmail;
import util.SHA256;

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
		String gender = request.getParameter("gender");
		String userEmail = request.getParameter("userEmail");
		/*System.out.println(userId);
		System.out.println(userPwd);
		System.out.println(userName);
		System.out.println(phone);
		System.out.println(smsCheck);
		System.out.println(userEmail);
		System.out.println(gender);*/
		
		//받은 파라미터로 객체 생성(매개변수 생성자 사용)
		User user = new User(userId, userPwd, userName, phone, smsCheck, gender, userEmail, util.SHA256.getSHA256(userEmail), "N");
		
		//해당 객체를 인자로 넣어 회원등록 메소드 호출 후 결과 받기
		int result = new UserService().insertUser(user);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		if(result > 0) { //회원가입 성공시
			
			System.out.println("회원가입성공");
			
			//회원가입(등록)이 정상적으로 완료되었다면
			//이메일을 보내주는 서블릿(EmailSendServlet)으로 이동(단, 회원의 아이디가 필요하다)
			request.getRequestDispatcher("sendEmail.do?userId="+userId).forward(request, response);
			
		}else { //회원가입 실패시
			
			System.out.println("회원가입실패");
			request.getSession().setAttribute("msg", "회원가입에 실패하였습니다.");
			
			//다시 회원가입양식으로 보냄
			response.sendRedirect("userEnrollForm.do");
			
		}//if~else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
