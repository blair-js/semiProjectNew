package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

//findIdForm.jsp에서 넘어옴 
@WebServlet("/findId.do")
public class UserIdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserIdFindServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//인코딩
		request.setCharacterEncoding("UTF-8");
		
		//findIdForm.jsp에서 넘어오는 파라미터를 받아주고
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		System.out.println(userName);
		System.out.println(userEmail);
		
		//파라미터로 받아온 이름과 이메일을 인자로 전달하여 메소드 실행
		String userId = new UserService().findUserId(userName, userEmail);
		
		////////////////////////////////////////////////////////////////////
		
		if(userId != null) { //조회되는 아이디가 있다면 
			
			//request 객체에 아이디로 속성값 설정, 이름도 보내줄 것(받아주는 화면에서 필요)
			request.setAttribute("userId", userId);
			request.setAttribute("userName", userName);
			
			//아이디 찾기 완료 페이지로 이동
			request.getRequestDispatcher("views/user/findUserIdOk.jsp").forward(request, response);
			
		}else { //조회되는 아이디가 없다면(null)
			
			//아이디찾기 실패 메세지
			//request.setAttribute("msg", "아이디 찾기에 실패하였습니다.");
			request.getSession().setAttribute("msg", "아이디 찾기에 실패하였습니다.");
			
			//다시 아이디찾기 화면으로
			request.getRequestDispatcher("views/user/findIdForm.jsp").forward(request, response);
			
		}//if~else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
