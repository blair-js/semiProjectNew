package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

@WebServlet("/userMyDetail.do")
public class UserMyDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserMyDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//회원에 대한 정보를 조회해와서 jsp에 뿌려주기 
		
		//request 객체에 담긴 세션을 얻어온 후, 얻어온 세션에서 loginUser 속성명으로 값을 얻어오기.
		//LoginServlet에서 세션에 해당 속성명으로 값을 담아주었었음.
		
		//loginUser = 현재 로그인 한 회원의 정보를 담고있는 정보
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		//얻어온 값은 객체 형태이고, 그 객체에서 아이디를 get 한다. 
		String userId = loginUser.getUserId();
		
		//인자로 전달하는 아이디 기준으로 User의 정보를 조회해올 것
		User user = new UserService().selectUser(userId);
		
		////////////////////////////////////////////////////////////
		
		//전환할 객체
		RequestDispatcher view = null;
		
		if(user != null) { //회원이 제대로 조회가 되어 null이 아닌 경우
			
			//현재 로그인 유저에 해당 user 객체 값을 set 해주기
			request.setAttribute("loginUser", user);
			view = request.getRequestDispatcher("views/user/userDetailMyPage.jsp"); //마이페이지로 이동
			
		}else { //조회된 회원이 없는경우(null인 경우)
			
			request.setAttribute("msg", "조회실패하였습니다.");
			view = request.getRequestDispatcher("views/common/errorPage.jsp"); //에러페이지로 이동
			
		}
		
		//페이지 전환!
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
