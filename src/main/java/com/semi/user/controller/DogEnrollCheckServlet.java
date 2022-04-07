package com.semi.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

//dogEnrollForm.jsp에서 입학가능여부 조회 버튼 클릭시 여기로 넘어옴!
//Ajax 통신으로 결과를 전달
@WebServlet("/dogEnrollChecked.do")
public class DogEnrollCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DogEnrollCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		//파라미터 확인
		String dogWeight = request.getParameter("dogWeight");
		System.out.println("dogWeight" + dogWeight);
		
		//Ajax를 사용하여 처리 결과를 반환할 때, 
		//한글이 포함되어 있다면 응답 인코딩을 반드시 'UTF-8'로 지정해야 한다.
		//아래의 인코딩 과정이 없으면 응답할 때 => ??? : ??, ?? : 2 이런식으로 나옴^^;; => 테스트 ok
		response.setCharacterEncoding("UTF-8");
	
		//전달받은 Dog 몸무게로 반이름을 조회해올 것
		String className = new UserService().selectClassName(dogWeight);
		
		if(className != null) {
			//결과 응답해주기!
			response.getWriter().print(className);
		}else {
			//결과 응답해주기!
			response.getWriter().print("입학불가");
		}
		
		//처리결과 반환
		/*if(dogWeight.equals("S")) {
			className = "햇님반";
		}else if(dogWeight.equals("M")) {
			className = "별님반";
		}else {
			className = "달님반";
		}*/
		
		//결과 응답해주기!
		//response.getWriter().print(className);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
