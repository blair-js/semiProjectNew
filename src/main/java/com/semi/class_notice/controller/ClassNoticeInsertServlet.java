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
 * Servlet implementation class ClassNoticeInsertServlet
 */
@WebServlet("/classNoticeInsert.do")
public class ClassNoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassNoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 값으로 제목, 내용 잘 받아오는지 확인하기 위한 코드
		request.setCharacterEncoding("utf-8");
		// 받아 온 값으로 알림장 객체 생성
		// 업로드 이미지도 객체 생성해서 던져줘야하는데 시간도 없고 힘들어서 내일 할 예정
		ClassNotice n = new ClassNotice();
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String className = request.getParameter("classname");
		
		n.setNoticeWriter(writer);
		n.setClassNoticeTitle(title);
		n.setClassNoticeContent(content);
		n.setClassName(className);
		
		int result = new ClassNoticeService().insertNotice(n);
		
		if(result > 0) {
			// 게시글 등록 성공할경우 다시 리스트 목록으로 전환
			request.setAttribute("classname", className);
			request.getRequestDispatcher("classNoticeList.do").forward(request, response);
		}else {
			request.setAttribute("msg", "게시물 등록 실패");
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
