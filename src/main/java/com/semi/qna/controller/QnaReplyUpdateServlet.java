package com.semi.qna.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.qna.model.service.QnaService;

/**
 * Servlet implementation class QnaReplyUpdateServlet
 */
@WebServlet("/updateReplyQna.do")
public class QnaReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rQno = Integer.parseInt(request.getParameter("rQno"));
		String content = request.getParameter("content");
		
		int result = new QnaService().updateReply(rQno, content);
		
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
