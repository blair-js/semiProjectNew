package com.semi.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

//loginForm.jsp에서 구글로그인시 여기로 옴
@WebServlet("/googleLogin.do")
public class GoogleLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoogleLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("구글 전용 로그인 서블릿 도착!");
		//파라미터 확인
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		System.out.println("userName : " + userName);
		System.out.println("userEmail : " + userEmail);
		
		//파라미터를 기준으로 user 객체를 얻어온다.
		User user = new UserService().selectUserbyGoogle(userName, userEmail);
	
		if(user != null) { //해당 이름과 이메일 기준으로 회원가입이 완료된 user가 있다면
			
			//반환받은 user 객체에서 
			String userId = user.getUserId();
			String userPwd = user.getUserPwd();
			
			//위에서 얻은 아이디와 패스워드를 갖고 로그인 서블릿(login.do)으로 이동
			request.getRequestDispatcher("login.do?userId="+userId+"&userPwd="+userPwd).forward(request, response);

			
		}else { //해당 이름과 이메일 기준으로 회원가입이 완료된 user가 없다면
			
			//로그인 실패화면을 띄워줄 서블릿으로 이동
			//LoginFailedNoUserServlet으로 이동 
			request.getRequestDispatcher("loginFailedNoUser.do").forward(request, response);
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
