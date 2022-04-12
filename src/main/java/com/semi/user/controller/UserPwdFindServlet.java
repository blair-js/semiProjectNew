package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

//findPwdform.jsp에서 옴
@WebServlet("/findPwd.do")
public class UserPwdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPwdFindServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//인코딩
		request.setCharacterEncoding("UTF-8");
		
		//findPwdform.jsp에서 넘어오는 파라미터(이름, 아이디, 이메일)를 받아주고
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String userEmail = request.getParameter("userEmail");
		
		//파라미터로 받아온 이름, 아이디, 이메일을 인자로 전달하여 메소드 실행
		String userPwd = new UserService().findUserPwd(userName, userId, userEmail);
		
		//System.out.println(userPwd);
		
		/////////////////////////////////////////////////////////////////////////////
		
		if(userPwd != null) {
			
		
			//request 객체에 패스워드로 속성값 설정, 이름도 보내줄 것(받아주는 화면에서 필요)
			request.setAttribute("userPwd", userPwd);
			request.setAttribute("userName", userName);
			request.setAttribute("userId", userId);
			
			//비밀번호 찾기 완료 페이지로 이동
			request.getRequestDispatcher("views/user/findUserPwdOk.jsp").forward(request, response);

		}else {
			
			//비밀번호 찾기 실패 메세지
			request.getSession().setAttribute("msg", "비밀번호 찾기에 실패하였습니다.");
			//request.setAttribute("msg", "비밀번호 찾기에 실패하였습니다.");
			
			//다시 아이디찾기 화면으로
			request.getRequestDispatcher("views/user/findPwdForm.jsp").forward(request, response);
			
		}//if~else
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
