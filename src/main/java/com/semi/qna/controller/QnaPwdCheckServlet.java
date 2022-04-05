package com.semi.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QnaPwdCheckServlet
 */
@WebServlet("/pwdCheckQna.do")
public class QnaPwdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaPwdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//받아온 nno와 가져온 nno가 일치하고, 받아온 pwd와 가져온 pwd가 일치하면 detailList로 간다.
		request.getRequestDispatcher("views/qna/qnaDetailView.jsp").forward(request, response);
		//틀리면 error페이지 -> msg로 비밀번호가 틀렸습니다. 다시 입력해주세요
		//request.setAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해주세요."
		//request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
