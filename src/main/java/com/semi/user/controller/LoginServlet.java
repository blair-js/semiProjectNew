package com.semi.user.controller;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

//loginForm.jsp에서 여기로 옴
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//post 방식으로 넘어오기 때문에 인코딩_0413삭제_필터에 인코딩 설정해주었음
		//request.setCharacterEncoding("UTF-8");
		
		//아이디와 비번 파라미터 받아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		System.out.println("userId 파라미터 확인" + userId);
		System.out.println("userPwd 파라미터 확인" + userPwd);
		
		//자동로그인여부 체크 파라미터 확인
		String input_check = request.getParameter("input_check");
		System.out.println("input_check 파라미터 확인" + input_check);
		
		//파라미터로 받은 아이디와 비번을 
		//UserService의 loginUser()메소드의 인자로 전달하여 결과 반환받기. => 결과는 User 객체로!
		User loginUser = new UserService().loginUser(userId, userPwd);
		
		///////////////////////////////////////////////////////////////////////////
		
		if(loginUser != null) { //로그인 된 대상이 있는 경우(반환된 객체가 있는경우)
			
			//1.0408_그 객체가 이메일인증을 했는지 체크한다. 
			if(loginUser.getEmailChecked().equals("Y")) {
				
				//이메일 인증을 했다면(하지 않으면 로그인 불가)
				//세션 생성해서
				HttpSession session = request.getSession();
				
				//세션에 로그인 된 User 객체의 정보를 담아준다.
				session.setAttribute("loginUser", loginUser);

				//위에서 세션에 모든 정보를 담고
				//아까 받아두었던 자동로그인여부에 대한 파라미터 값이 "Y"라면 if문 진입
				//아니라면 그냥 pass
				/*if(input_check.equals("Y")) {
					
					//1.회원의 아이디로 쿠키를 생성한다.
					//쿠키 생성(회원의 아이디로 생성)
					Cookie cookie = new Cookie("userId", loginUser.getUserId());
					//유효기간 설정
					cookie.setMaxAge(26*60*60); //1일 
					//생성한 쿠키 객체 전송
					response.addCookie(cookie); 
					
					//2.그다음 DB에서 쿠키여부를 Y로 바꿔준다.(회원가입시 DEFAULT로 N으로 들어가기 때문)
					
					int result = new UserService().updateCookieChecked(userId);

					/////////////////////////////////////////////////////
					
					if(result > 0) {
						loginUser.setCookieChecked("Y");
					}
					
				}//if*/
				
				//로그인이 완료되면 홈 화면으로 돌아가기
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
				
			}else { //해당 아이디와 비번은 있지만(객체는 있지만), 해당 회원이 이메일 인증을 하지 않은 경우 
				
				//로그인 실패화면을 띄워줄 서블릿으로 이동(실패화면_이메일인증x)
				//근데 실패화면으로 userId를 갖고가야한다. => 왜냐하면 그 아이디를 기준으로 사용자가 원하면 인증용 이메일을 재발송해야하므로!
		
				//LoginFailedEmailServlet으로 이동
				request.getRequestDispatcher("loginFailedEmail.do?userId="+userId).forward(request, response);
				
			}//if~else
			
		}else { //해당 아이디와 비번으로 가입되어있는 회원이 없는 경우(반환값이 null인 경우)
			
			//로그인 실패화면을 띄워줄 서블릿으로 이동(실패화면_회원x)
			//LoginFailedNoUserServlet으로 이동 
			request.getRequestDispatcher("loginFailedNoUser.do").forward(request, response);
			
		}//if-else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
