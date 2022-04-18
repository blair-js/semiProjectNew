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
		//받아온 qno와 가져온 qno가 일치하고, 받아온 pwd와 가져온 pwd가 일치하면 detailList로 간다.
		//넘어온 qno, pwd 받아준다.
		int qno = Integer.parseInt(request.getParameter("qno"));
		String pwd = request.getParameter("pwd");
		int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo(); 

		Qna q = new QnaService().selectPwdCheck(qno, userNo);
		
		System.out.println(q.getQnaWriter());
		//가져온 pwd와 사용자가 입력한 pwd가 같고, 게시글의 작성자의 번호와 현재 접속한 사람의 번호가 같다면
		if(q.getQnaPwd().equals(pwd)) { 
			//비밀번호가 맞으면 detailView로
			response.sendRedirect("detailQna.do?qno="+qno);
		} else {
			//틀리면 error페이지 -> msg로 비밀번호가 틀렸습니다. 다시 입력해주세요
			request.setAttribute("msg", "비밀번호가 틀렸습니다.");
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
