package com.semi.snack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.Attachment;
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class SnackDetailServlet
 */
@WebServlet("/snackDetail.do")
public class SnackDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//상세 페이지 서블릿
		
		request.setCharacterEncoding("UTF-8");
		
		int sno = Integer.parseInt(request.getParameter("sno"));
		
		Snack snack = new SnackService().selectSnack(sno);
		
		Attachment at = new SnackService().selectAttachment(sno); //체인지 네임만 뽑아서 체인지네임 경로 설정만 해주면
		
		System.out.println(sno);
		
		if(snack != null) {
			request.setAttribute("snack", snack);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/snack/snackDetail.jsp").forward(request, response);;
			
		}else {
			request.getSession().setAttribute("msg", "게시글 상세조회 실패");
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
