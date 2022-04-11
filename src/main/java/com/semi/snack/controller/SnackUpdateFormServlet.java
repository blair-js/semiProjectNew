package com.semi.snack.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.Attachment;
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class SnackUpdateFormServlet
 */
@WebServlet("/snackUpdateForm.do")
public class SnackUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("간식 수정 서블릿");
		
		request.setCharacterEncoding("UTF-8");
		
		int sno = Integer.parseInt(request.getParameter("sno"));
		
		Snack snack = new SnackService().selectSnack(sno);
		Attachment at = new SnackService().selectAttachment(sno);
		
		String page = "";
		
		if(snack != null) {
			request.setAttribute("snack", snack);
			request.setAttribute("at", at);
		/*	
			try {
				
			}catch(Exception e) {
				page = "error.jsp";
			}finally {
				page = "snackUpdateForm.jsp";
			}*/
			request.getRequestDispatcher("views/snack/snackUpdateForm.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "수정할 간식을 불러오는데 실패하였습니다");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
