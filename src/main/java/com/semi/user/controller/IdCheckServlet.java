package com.semi.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("userId 파라미터 확인 : " + request.getParameter("userId"));
		
		//파라미터 userId를 받아온다.
		String userId = request.getParameter("userId");
		
		//파라미터로 전달받은 userId를 메소드 호출시 넘겨주어 체크한다. 
		int result = new UserService().idCheck(userId);
		
		///////////////////////////////////////////////////////
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) { //중복된 아이디가 있는 경우 => userEnrollForm.jsp로 success에 fail 전달
			out.print("fail");
		}else { //중복된 아이디가 없는 경우
			out.print("success");
		}
	
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
