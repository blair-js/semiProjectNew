package com.semi.snack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class SnackDeleteServlet
 */
@WebServlet("/snackDelete.do")
public class SnackDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("간식 삭제를 하기위한 서블릿");
		
		request.setCharacterEncoding("UTF-8");
		
		int sno = Integer.parseInt(request.getParameter("sno")); 
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		int result = new SnackService().deleteSnack(sno);
		
		ArrayList<Snack> list = new SnackService().selectList(); 
		
		if(result > 0 ) {
			request.getSession().setAttribute("msg", "간식 삭제 성공");
			request.setAttribute("list", list);
			//response.sendRedirect("views/snack/snack.jsp");
			request.getRequestDispatcher("snack.do?userNo=" + userNo).forward(request, response);
		}else {
			request.setAttribute("msg", "간식 삭제 실패 "); 
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response); //에러페이지로 화면전환
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
