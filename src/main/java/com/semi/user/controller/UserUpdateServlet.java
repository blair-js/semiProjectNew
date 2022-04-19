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

//userDetailMyPage.jsp에서 수정버튼 클릭시
@WebServlet("/updateUser.do")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("회원정보 수정하기 서블릿 도착!");
		
		//파라미터 잘 넘어오는지 확인!
		/*System.out.println(request.getParameter("userNo")); //hidden으로 넘어오는 파라미터
		System.out.println(request.getParameter("userName"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("phone"));
		System.out.println(request.getParameter("smsCheck"));
		System.out.println(request.getParameter("gender"));*/
		
		//넘겨주는 파라미터로 새 User 객체에 set 해주기 
		User user = new User();
		user.setUserNo(Integer.parseInt(request.getParameter("userNo")));
		user.setUserName(request.getParameter("userName"));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setSmsChecked(request.getParameter("smsCheck"));
		user.setGender(request.getParameter("gender"));
		
		//업데이트된 회원의 정보를 다시 조회해오기 위해 필요한 userId
		String userId = request.getParameter("userId");
		
		//위에서 필드 값 셋팅해준 user 객체를 인자로 전달 
		int result = new UserService().updateUser(user);
		
		//48행에서 변수에 담아놓은 userId를 기준으로 새 정보 조회해오기
		User loginUser = new UserService().selectUser(userId);
		
		//////////////////////////////////////////////////////////////
		
		if(result > 0) { //업데이트 성공시
			
			request.getSession().setAttribute("msg", "회원정보를 성공적으로 수정하였습니다.");
			
			//업데이트가 성공적으로 되었다면
			//2.인자로 전달하는 *회원번호* 기준으로 User의 보유 강아지 리스트 가져오기(마이페이지에서 받아주고있음)
			//Dog 객체만 담을 수 있는 list로 반환 받기
			ArrayList<Dog> dogList = new UserService().selectDogList(loginUser.getUserNo());

			//현재 로그인 유저의 보유 강아지 리스트까지 set 해주기
			request.setAttribute("dogList", dogList);
			
			//다시 마이 페이지로 이동하는데 세션 값을 수정해준다
			//기존 세션명 loginUser에 업데이트된 회원의 정보로 값 덮어씀
			request.setAttribute("loginUser", loginUser); 
			request.getRequestDispatcher("views/user/userDetailMyPage.jsp").forward(request, response);
			
		}else { //업데이트 실패시
			
			//에러페이지로 이동
			request.setAttribute("msg", "회원정보 수정에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response); 
			
		}//if-else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
