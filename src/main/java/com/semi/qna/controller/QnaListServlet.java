package com.semi.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.PageInfo;
import com.semi.notice.model.service.NoticeService;
import com.semi.qna.model.dao.QnaDao;
import com.semi.qna.model.dto.Qna;
import com.semi.qna.model.service.QnaService;

/**
 * Servlet implementation class QnaListServlet
 */
@WebServlet("/listQna.do")
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaListServlet() {
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
		
		//검색을 위해 넘어온 파라미터를 받아준다.
		//검색어는 들어올 수도 있고 아닐 수도 있다. -> 임시변수로 받아준다.
		//list?keyword=title&searchKey=?
		String testKeyword = request.getParameter("keyword");
		String testSearchKey = request.getParameter("searchKey");
		
		//사용자가 전달하는 값이 null이 아닌 경우에 대한 조건
		String keyword = "QNA_TITLE"; //전달되지 않았을 때의 기본 값
		if(testKeyword != null && !testKeyword.equals("")) { //아무것도 없을 때 빈문자열이 넘어온다.
			keyword = testKeyword;
		}		
		
		String searchKey = ""; //전달되지 않았을 때의 기본값
		if(testSearchKey != null && !testSearchKey.equals("")) {
			searchKey = testSearchKey;
		}
		System.out.println("keyword: " +  keyword);
		System.out.println("searchKey: " +  searchKey);
		
		//총 게시글 개수
		listCount = new QnaService().getListCount(searchKey, keyword);
		
		//현재 페이지
		currentPage = 1;
		if(request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals("")) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
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
		
		ArrayList<Qna> list = new QnaService().selectList(pi, keyword, searchKey);	
		
		ArrayList<Qna> reCountList = new QnaService().reCountList(pi, keyword, searchKey);
		
		//값이 담겨져 있는 list를 setAttribute해서 담아서 넘긴다.
		request.setAttribute("list", list);
		//페이징 바를 위해서 페이지 정보가 담겨져 있는 pi를 넘긴다.
		request.setAttribute("pi", pi);
		request.setAttribute("reCountList", reCountList);
		
		request.getRequestDispatcher("views/qna/qnaListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
