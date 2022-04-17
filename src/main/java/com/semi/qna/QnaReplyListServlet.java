package com.semi.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.qna.model.dto.QnaReply;
import com.semi.qna.model.service.QnaService;

/**
 * Servlet implementation class QnaReplyListServlet
 */
@WebServlet("/replyListQna.do")
public class QnaReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		//댓글이 여러 개에 넘어올 수 있기 때문에 arraylist
		ArrayList<QnaReply> list = new QnaService().selectRList(qno);
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일").create();
		
		gson.toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
