package com.semi.class_notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.class_notice.model.dto.ClassNotice;
import com.semi.class_notice.model.service.ClassNoticeService;
import com.semi.common.dto.PageInfo;

@WebServlet("/classNoticeList.do")
public class ClassNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClassNoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount; // 총 게시글 개수
		int currentPage; // 현재 페이지 (요청한 페이지)
		int startPage; // 현재 페이지 하단에 보여지는 페이징 바의 시작 수
		int endPage; // 현재 페이지 하단에 보여지는 페이징 바의 끝 수
		int maxPage; // 전체 페이지의 가장 마지막 페이지
		int pageLimit; // 한 페이지 하단에 보여질 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글 최대개수
		
		// 수업때와는 다르게 반이름을 받아서 매개변수로 넘겨줘야 될 듯, 그래야 어떤 반에 게시글인지 알 수 있으니까
		// 임의로 반 이름 설정
		String className = request.getParameter("classname");
		// 총 게시글 개수 DB 조회 후 받아 옴
		listCount = new ClassNoticeService().getListCount(className);
		System.out.println("listCount : " + listCount);
		
		// 현재 페이지
		currentPage = 1;
		// 페이지 전환시 전달받은 페이지가 있을경우 전달받은 페이지를 currentPage에 담기
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 10;
		boardLimit = 9;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);

		ArrayList<ClassNotice> list = new ClassNoticeService().selectList(pi, className);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("classname", className);
		
		// Dao까지 가서 DB 결과 끌어와서 리스트 목록 화면에 보여준다
		request.getRequestDispatcher("views/class_notice/classNoticeListView.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
