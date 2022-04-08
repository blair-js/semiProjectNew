package com.semi.user.controller;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

//loginForm.jsp에서 옴
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//post 방식으로 넘어오기 때문에 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//아이디와 비번 파라미터 받아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		System.out.println("userId 파라미터 확인" + userId);
		System.out.println("userPwd 파라미터 확인" + userPwd);
		
		//파라미터로 받은 아이디와 비번을 
		//UserService의 loginUser()메소드의 인자로 전달하여 결과 반환받기. => 결과는 User 객체로!
		User loginUser = new UserService().loginUser(userId, userPwd);
		
		///////////////////////////////////////////////////////////////////////////
		
		if(loginUser != null) { //로그인 된 대상이 있는 경우(반환된 객체가 있는경우)
			
			//0408_그 객체가 이메일인증을 했는지 체크한다. 
			if(loginUser.getEmailChecked().equals("Y")) {
				
				//세션 생성해서
				HttpSession session = request.getSession();
				
				//세션에 로그인 된 User 객체의 정보를 담아준다.
				session.setAttribute("loginUser", loginUser);
				
				//로그인이 완료되면 홈 화면으로 돌아가기
				//response.sendRedirect(request.getContextPath());
				
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
				
			}else { //해당 아이디와 비번은 있지만(객체는 있지만), 해당 회원이 이메일 인증을 하지 않은 경우 
				
				//로그인 실패화면을 띄워줄 서블릿으로 이동
				//근데 실패화면으로 userId를 갖고가야한다. => 왜냐하면 그 아이디를 기준으로 사용자가 원할시 메일을 재발송해야하므로!
		
				//LoginFailedEmailServlet으로 이동
				request.getRequestDispatcher("loginFailedEmail.do?userId="+userId).forward(request, response);
				
			}//if~else
			
		}else { //로그인 된 대상이 없는 경우(반환값이 null인 경우)
			
			//로그인 실패화면을 띄워줄 서블릿으로 이동
			//LoginFailedNoUserServlet으로 이동 
			request.getRequestDispatcher("loginFailedNoUser.do").forward(request, response);
			
		}//if-else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
