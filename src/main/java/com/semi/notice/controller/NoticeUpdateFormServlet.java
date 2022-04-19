package com.semi.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.Attachment;
import com.semi.notice.model.dto.Notice;
import com.semi.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeUpdateFormServlet
 */
@WebServlet("/updateFormNotice.do")
public class NoticeUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//update는 폼이 뜰 때 게시글의 정보를 가지고 와야한다.
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		Notice notice = new NoticeService().selectUpdateNotice(nno);
		Attachment at = new NoticeService().selectAttachment(nno);
		
		if(notice != null) {
			request.setAttribute("n", notice);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/notice/noticeUpdateForm.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "수정할 공지사항 게시글 불러오는데 실패하였습니다.");
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
