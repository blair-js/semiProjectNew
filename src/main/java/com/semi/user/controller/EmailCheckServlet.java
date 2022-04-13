package com.semi.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

//회원가입시 작성했던 이메일에서 인증 버튼을 누르면 
//이메일 해쉬코드가 맞는지 확인하고, 인증완료 페이지를 보여주기 위한 서블릿!
@WebServlet("/emailCheck.do")
public class EmailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmailCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이메일에서 오는 파라미터 확인
		System.out.println("이메일에서 오는 이메일해쉬코드 파라미터 확인 : " + request.getParameter("code"));
		System.out.println("이메일에서 오는 회원아이디 파라미터 확인 : " + request.getParameter("userId"));
		
		String emailHashCode = request.getParameter("code");
		String userId = request.getParameter("userId");
		
		//파라미터로 오는 code와 회원가입시 생성된 DB의 이메일해쉬코드를 비교한 후
		//동일하면 DB에 인증완료 컬럼을 N에서 Y로 바꿔준 후 로그인 인증완료 페이지를 보여준다. 
		
		//먼저 파라미터로 넘어온 userId를 기준으로 이메일해쉬코드 조회
		String checkEmailHashCode = new UserService().selectEmailHashCode(userId);
		
		if(emailHashCode.equals(checkEmailHashCode)) {
			
			//1.이메일에서 온 해쉬코드와, DB의 해쉬코드가 일치한다면 
			System.out.println("두 해쉬코드 모두 일치!");
			//2.DB의 이메일인증 컬럼을 Y로 바꿔주기(회원가입시 DB에 디폴트로 N이 들어갔음) => 바꿔줘야 최종적으로 로그인이 가능!
			int result = new UserService().updateEmailChecked(userId);
			
			//////////////////////////////////////////////////////////////
			
			if(result > 0) { //이메일 체크 업데이트까지 성공시 
				
				//이메일 인증완료 페이지로 이동
				request.getRequestDispatcher("views/user/emailCheckAction.jsp").forward(request, response);
				
			}else { //실패시
				
				request.setAttribute("msg", "이메일 체크 업데이트 실패");
				//에러 페이지로 이동
				request.getRequestDispatcher("views/commom/errorPage.jsp").forward(request, response);
			
			}//if~else
			
		}else { //이메일에서 온 해쉬코드와, DB의 해쉬코드가 일치하지 않는다면 
			
			request.setAttribute("msg", "이메일 인증 실패");
			//에러 페이지로 이동
			request.getRequestDispatcher("views/commom/errorPage.jsp").forward(request, response);
			
		}//if~else
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
