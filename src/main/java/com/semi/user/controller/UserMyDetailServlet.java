package com.semi.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.dto.Dog;
import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

//회원의 마이페이지를 뿌려주기 위한 서블릿
@WebServlet("/userMyDetail.do")
public class UserMyDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserMyDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request 객체에 담긴 세션을 얻어온 후, 얻어온 세션에서 loginUser 속성명으로 값을 얻어오기.
		//LoginServlet에서 세션에 해당 속성명으로 값을 담아주었었음.
		
		//loginUser = 현재 로그인 한 회원의 정보를 담고있는 정보
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		//얻어온 값은 객체 형태이고, 그 객체에서 아이디를 get 한다. 
		String userId = loginUser.getUserId();
		
		//1.인자로 전달하는 아이디 기준으로 User의 정보를 조회해올 것
		User user = new UserService().selectUser(userId);
		
		//2.인자로 전달하는 아이디 기준으로 User의 *포인트만* 가져오기
		String userPoint = String.valueOf(new UserService().selectUserPoint(userId));
		
		////////////////////////////////////////////////////////////////////////////////
		
		//전환할 화면의 경로를 담을 객체
		RequestDispatcher view = null;
		
		//test code
		//user = null;
		
		if(user != null) { //회원이 제대로 조회가 되어 null이 아닌 경우
			
			//현재 로그인 유저에 해당 user 객체 값을 set 해주기
			//현재 로그인 유저의 포인트 값도 set 해주기
			request.setAttribute("loginUser", user);
			request.setAttribute("loginUserPoint", userPoint);
			
			//회원이 제대로 조회가 되었다면 아래 메소드 실행
			//3.인자로 전달하는 *회원번호* 기준으로 User의 보유 강아지 리스트 가져오기
			//Dog 객체만 담을 수 있는 list로 반환 받기
			ArrayList<Dog> dogList = new UserService().selectDogList(user.getUserNo());

			//현재 로그인 유저의 보유 강아지 리스트까지 set 해주기(보유 강아지가 없으면 null이 넘어갈 것)
			request.setAttribute("dogList", dogList);
			
			view = request.getRequestDispatcher("views/user/userDetailMyPage.jsp"); //마이페이지로 이동
			
		}else { //조회된 회원이 없는경우(null인 경우) 
			
			request.getSession().setAttribute("msg", "조회실패하였습니다.");
			view = request.getRequestDispatcher("index.jsp"); //에러페이지로 이동
			
		}//if~else
		
		//페이지 전환!
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
