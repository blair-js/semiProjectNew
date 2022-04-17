package com.semi.qna.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.qna.model.dto.QnaReply;
import com.semi.qna.model.service.QnaService;
import com.semi.user.model.dto.User;

/**
 * Servlet implementation class QnaReplyInsertServlet
 */
@WebServlet("/insertReplyQna.do")
public class QnaReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		int qno = Integer.parseInt(request.getParameter("qno"));
		int writer = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
		
		QnaReply qr = new QnaReply();
		qr.setQnaReplyContent(content);
		qr.setRefQno(qno);
		qr.setReplyWriter(String.valueOf(writer));
		
		int result = new QnaService().insertReply(qr);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.print("success");
		}else {
			out.print("fail");
		}
		
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
