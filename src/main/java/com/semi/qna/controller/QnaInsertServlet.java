package com.semi.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.qna.model.dto.Qna;
import com.semi.qna.model.service.QnaService;
import com.semi.user.model.dto.User;

/**
 * Servlet implementation class QnaInsertServlet
 */
@WebServlet("/insertQna.do")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터를 받아온다.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String secret = request.getParameter("secret");
		
		//pwd가 빈 값으로 넘어오는 경우 처리
		String secretPwd = "";
		if(request.getParameter("secretPwd") != "") {
			secretPwd = request.getParameter("secretPwd");
		}
		
		//작성자
		int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
		
		//객체에 담는다.
		Qna q = new Qna();
		q.setQnaTitle(title);
		q.setQnaContent(content);
		q.setQnaSecret(secret);
		q.setQnaPwd(secretPwd);
		q.setQnaWriter(String.valueOf(userNo));
		
		int result = new QnaService().insertQna(q);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시글이 성공적으로 등록되었습니다.");
			response.sendRedirect("listQna.do");
		}else {
			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
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
