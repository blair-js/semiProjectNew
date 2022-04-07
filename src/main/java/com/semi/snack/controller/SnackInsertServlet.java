package com.semi.snack.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class SnackInsert
 */
@WebServlet("/snackInsert.do")
public class SnackInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("간식 추가를 하기위한 서블릿");
		
		request.setCharacterEncoding("UTF-8");
		
		String snackName = request.getParameter("snackName");
		
		int snackPrice = Integer.parseInt(request.getParameter("snackPrice"));
		
		//처음에 서블릿에서 서비스 보내서 다오 보내서 디비에 데이터를 넣을 건데 데이터를 넣을 파라미터 매개변수 값을 객체에 담아서 보내줄건지 스트링, 인트 등 기본타입으로 넣어서 보내줄지를 선택 하는거이다.
		
		//기본생성자를 이용해서 값을 담아준거지 매개변수를 만든거는 아니다
		
		Snack snack = new Snack(); //객체 생성 후 값을 담아줌
		
		snack.setSanckName(snackName);
		snack.setPrice(snackPrice);
		
		
		int result = new SnackService().snackInsert(snack);

		if(result > 0 ) {
			request.getSession().setAttribute("msg", "간식 등록 성공");
			response.sendRedirect("/adminMyPage.do");
		}else {
			request.setAttribute("msg", "간식 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}
		
	}


	
	private Object SnackService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
