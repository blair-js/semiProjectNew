package com.semi.class_notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.class_notice.model.service.ClassNoticeService;
import com.semi.common.dto.Reply;

/**
 * Servlet implementation class ReplyListServlet
 */
@WebServlet("/rlist.do")
public class ReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 번호로 그 게시글의 댓글 뿌려주는 서블릿
		int nno = Integer.parseInt(request.getParameter("nno")); // 참조 게시글 번호 받아온다. 반이름은 필요 없음, 어차피 게시글 번호는 시퀀스 고유하기때문에
		
		System.out.println("참조게시글번호: " +nno);
		ArrayList<Reply> list = new ClassNoticeService().selectRList(nno);
		// 받아온 list Gson 사용해서 넘겨준다.
		// JSON은 사용 전 aaplication 타입, 문자인코딩 지정해주어야 한다. 꼭
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
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
