package com.semi.class_notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.class_notice.model.service.ClassNoticeService;
import com.semi.common.dto.Reply;
import com.semi.user.model.dto.User;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/rinsert.do")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax로 화면에서 넘겨준 값들 변수에 담아준다.
		String content = request.getParameter("content");
		int nno = Integer.parseInt(request.getParameter("nno"));
		// 작성자 회원 번호
		int writer = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
		System.out.println(content + nno + "작성자 회원번호 : " + writer);
		// 댓글 객체 생성
//		REPLY_NO	NUMBER 시퀀스
//		REF_NO	NUMBER 참조게시글 번호 받았음
//		REF_WRITER	NUMBER 회원번호 받았음
//		CREATE_DATE	DATE 시스데이트
//		REPLY_CONTENT	VARCHAR2(1000 BYTE) 내용 받았음
//		STATUS	VARCHAR2(1 BYTE) 기본 값 y
		
		Reply r = new Reply();
		r.setRefBoardId(nno);
		r.setReplyContent(content);
		r.setReplyWriter(String.valueOf(writer));
		
		int result = new ClassNoticeService().insertReply(r);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.print("success");
		}else {
			out.print("failed");
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
