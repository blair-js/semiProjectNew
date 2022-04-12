package com.semi.snack.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.common.MyFileRenamePolicy;
import com.semi.snack.model.dto.UserPoint;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class Snack
 */
@WebServlet("/snackResult.do")
public class SnackOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SnackOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("구매에 대한 DB 작업후 다시 간식 페이지로 이동 ");

		request.setCharacterEncoding("UTF-8");

		
			int uno = Integer.parseInt(request.getParameter("userNo"));
			
			String[] snackArrays = request.getParameterValues("snackArray");
			
			String snackArray = "";
			if(snackArrays != null) {
				snackArray = String.join(",", snackArray);
			}
			
			UserPoint up = new UserPoint();
			up.setUserNo(uno);
				
			UserPoint userPoint = new SnackService().selectUserPoint(up); //유저 포인트 조회를 위한 메서드
			
			if (userPoint != null) {
				request.setAttribute("userPoint", userPoint);
				request.getRequestDispatcher("views/snack/snackResult.jsp").forward(request, response);
			}

		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
