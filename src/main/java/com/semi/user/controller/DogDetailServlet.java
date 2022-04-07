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

//userDetailMyPage.jsp에서 강아지 상세보기 버튼시 여기로 옴ㄴ
@WebServlet("/detailDogPage.do")
public class DogDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DogDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("강아지 디테일 서블릿 도착! ");
		
		//userDetailMyPage.jsp에서 쿼리스트링으로 넘겨주는 파라미터를 받는다.
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		//System.out.println("쿼리스트링으로 넘어오는 파라미터 확인" + userNo);

		//userMyDetailServlet에서 만들어놓았던 메소드 호출
		ArrayList<Dog> dogList = new UserService().selectDogList(userNo);
		
		System.out.println("dogList 확인 " + dogList.size());
		
		//나중에 첨부파일 불러오는 메소드도 추가할 예정_0406
		//
		//
		
		if(dogList.isEmpty()) { //보유한 강아지가 없다면
			
			//loginUser = 현재 로그인 한 회원의 정보를 담고있는 정보
			User loginUser = (User)request.getSession().getAttribute("loginUser");
			//1.인자로 전달하는 아이디 기준으로 User의 정보를 조회해올 것
			User user = new UserService().selectUser(loginUser.getUserId());
			
			request.setAttribute("loginUser", user);
			request.setAttribute("dogList", dogList);
			request.getSession().setAttribute("msg", "조회 가능한 강아지가 없습니다.");
			
			//다시 마이페이지로 이동
			RequestDispatcher view = request.getRequestDispatcher("views/user/userDetailMyPage.jsp");
			view.forward(request, response);
			
		}else { //보유한 강아지가 있다면

			//현재 로그인 유저의 보유 강아지 리스트 set 해주기
			//그래야 받아주는 곳에서 dogList 이름으로 get해서 반복을 통해 뿌려줄 것
			request.setAttribute("dogList", dogList);
			
			//강아지 상세정보페이지로 이동
			RequestDispatcher view = request.getRequestDispatcher("views/user/DogDetailPage.jsp");
			view.forward(request, response);
		}//if~else
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
