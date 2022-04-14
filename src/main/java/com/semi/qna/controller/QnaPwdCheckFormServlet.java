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
 * Servlet implementation class QnaPwdCheckFormServlet
 */
@WebServlet("/pwdCheckFormQna.do")
public class QnaPwdCheckFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaPwdCheckFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//넘어온 qno 받아준다.
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		//qno를 request에 담아 checkform에 보낸다.
		request.setAttribute("qno", qno);
		request.getRequestDispatcher("views/qna/qnaPwdCheckForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
