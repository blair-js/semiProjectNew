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
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/updateQna.do")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//넘어오는 게시글 번호를 받아준다.
		int qno = Integer.parseInt(request.getParameter("qno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String secret = request.getParameter("secret");
		
		//비밀글 설정을 하지 않은 경우 비밀번호가 빈값으로 넘어온다.
		String secretPwd = "";
		if(request.getParameter("secretPwd") != "") { //빈값이 아니라면 -> 비밀글 설정을 한 경우
			secretPwd = request.getParameter("secretPwd");
		}
		
		//게시글 작성자 회원번호
		int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Qna q = new Qna();
		q.setQnaNo(qno);
		q.setQnaTitle(title);
		q.setQnaContent(content);
		q.setQnaPwd(secretPwd);
		q.setQnaSecret(secret);
		q.setQnaWriter(String.valueOf("userNo"));
		
		int result = new QnaService().updateQna(q);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시글이 성공적으로 수정되었습니다.");
			response.sendRedirect("detailQna.do?qno="+qno);
		}else {
			request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
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
