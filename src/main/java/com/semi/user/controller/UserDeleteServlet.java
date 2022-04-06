package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.user.model.service.UserService;

//userDetailMyPage.jsp에서 탈퇴버튼 클릭시
@WebServlet("/deleteUser.do")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("삭제 서블릿 도착! ");

		//userDetailMyPage.jsp의 form에서 많은 데이터를 넘겨주긴 하지만
		//필요한 파라미터만 받아오면 된다. => 아이디 기준으로 삭제할 것이므로 아이디만 받아오기!
		String userId = request.getParameter("userId");
		
		//확인
		//System.out.println("삭제할 아이디 : " + userId);
		
		int result = new UserService().deleteUser(userId);
		
		//////////////////////////////////////////////////////
		
		if(result > 0) { //탈퇴 성공시
			
			HttpSession session = request.getSession();
			
			//세션 삭제
			//invalidate() 써도 되지만특정 속성만 지우는 방법으로 세션을 삭제해도 됨
			session.removeAttribute("loginUser"); 
			session.setAttribute("msg", "회원탈퇴가 완료 되었습니다.");
			
			response.sendRedirect("index.jsp"); //메인페이지로 단순 화면 전환(데이터 노 필요)
			
		}else {
			
			request.setAttribute("msg", "회원탈퇴에 실패하였습니다.");
			
			//에러페이지로 이동
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}//if-else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
