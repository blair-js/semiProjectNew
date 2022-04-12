package com.semi.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.notice.model.service.NoticeService;

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
		
		//총 게시글 개수
		listCount = new NoticeService().getListCount(searchKey, keyword);
		System.out.println(listCount);
		
		
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
