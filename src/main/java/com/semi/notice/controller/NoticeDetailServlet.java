package com.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.Attachment;
import com.semi.notice.model.dto.Notice;
import com.semi.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/detailNotice.do")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//번호를 파라미터로 받는다.
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		//글 내용
		Notice n = new NoticeService().selectNotice(nno);
		//첨부파일
		ArrayList<Attachment> atList = new NoticeService().selectAttachment(nno);
		System.out.println(atList);
		System.out.println(n);
		
		if(n != null) {
			request.setAttribute("n", n);
			request.setAttribute("atList", atList);
			request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "게시글 상세조회에 실패하였습니다.");
			request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);
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
