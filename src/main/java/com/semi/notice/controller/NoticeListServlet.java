package com.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.PageInfo;
import com.semi.notice.model.dto.Notice;
import com.semi.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/listNotice.do")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이징 처리를 위한 페이지 정보 변수 선언
		int listCount;
		int currentPage;
		int startPage;
		int endPage;
		
		int maxPage;
		int pageLimit;
		int boardLimit;
		
		//총 게시글 개수
		listCount = new NoticeService().getListCount();
		System.out.println(listCount);
		
		//현재 페이지
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currnetPage"));
		}
		
		//페이지 최대 개수
		pageLimit = 10;
		
		//한 페이지 당 게시글 최대 개수
		boardLimit = 10;
		
		//총 페이지 수
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//현재 페이지에 보여지는 페이징 바의 시작 수 
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		//현재 페이지에 보여지는 페이징 바의 끝 수 
		endPage = startPage + pageLimit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		//페이지 정보를 담아주는 객체 생성
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
		
		ArrayList<Notice> list = new NoticeService().selectList(pi);
		
		//listView에 list를 뿌려주기 위해 가져온 list를 request에 담아준다.
		request.setAttribute("list", list);
		//페이징 바를 위한 pi객체
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
