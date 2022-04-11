package com.semi.class_notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.class_notice.model.dto.ClassNotice;
import com.semi.class_notice.model.service.ClassNoticeService;
import com.semi.common.dto.Attachment;

/**
 * Servlet implementation class classNoticeUpdateFormServlet
 */
@WebServlet("/classNoticeUpdateForm.do")
public class ClassNoticeUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassNoticeUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classname = request.getParameter("classname");
		int nno = Integer.parseInt(request.getParameter("nno"));
		// 수정할 게시글 화면에 뿌려주어야 하니까 조회
		ClassNotice cn = new ClassNoticeService().selectNewNotice(nno);
		Attachment at = new ClassNoticeService().selectAttachment(nno);
		
		if(cn != null) {
			request.setAttribute("cNotice", cn);
			request.setAttribute("at", at);
			request.getRequestDispatcher("views/class_notice/classNoticeUpdateForm.jsp").forward(request, response);
			
		}else {
			request.setAttribute("msg", "수정할 게시글을 불러오는데 실패하였습니다.");
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
