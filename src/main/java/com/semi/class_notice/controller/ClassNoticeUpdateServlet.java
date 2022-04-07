package com.semi.class_notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.class_notice.model.dto.ClassNotice;
import com.semi.class_notice.model.service.ClassNoticeService;

/**
 * Servlet implementation class ClassNoticeUpdateServlet
 */
@WebServlet("/classNoticeUpdate.do")
public class ClassNoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassNoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// is멀티 어쩌구로 파일 확인
		// 첨부파일, 게시물 객체 만들어서 DB 까지 연결 후 수정, 수정 한 게시글 바로 봐야 하니까 게시물 번호 넘겨주기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		ClassNotice cn = new ClassNotice();
		cn.setClassNoticeNo(nno);
		cn.setClassNoticeTitle(title);
		cn.setClassNoticeContent(content);
		
		cn = new ClassNoticeService().updateNotice(cn, nno);
		
		if(cn != null) {
			request.getSession().setAttribute("msg", "게시물 수정 성공!");
			response.sendRedirect("classNoticeDetail.do?nno=" + nno);
		}else {
			request.setAttribute("msg", "게시물 수정 실패");
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
