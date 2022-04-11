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

@WebServlet("/classNoticeDetail.do")
public class ClassNoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassNoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에서 조회 해온 뒤 화면에 조회한 게시글 보여준다
		// 게시물 객체, 첨부파일 객체 setAttribute로 request에 변경
		int nno = Integer.parseInt(request.getParameter("nno"));
		ClassNotice cn = new ClassNoticeService().selectNotice(nno);
		Attachment at = new ClassNoticeService().selectAttachment(nno);
		
		String view = "";
		if(cn != null) {
			request.setAttribute("cn", cn);
			request.setAttribute("at", at);
			view = "views/class_notice/classNoticeDetailView.jsp";
		}else {
			request.setAttribute("msg", "게시물 조회에 실패했습니다.");
			view = "views/common/errorPage.jsp";
		}
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
