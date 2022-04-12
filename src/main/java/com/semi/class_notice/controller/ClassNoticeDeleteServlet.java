package com.semi.class_notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.class_notice.model.service.ClassNoticeService;

/**
 * Servlet implementation class ClassNoticeDeleteServlet
 */
@WebServlet("/classNoticeDelete.do")
public class ClassNoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassNoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 간단하게 DB까지 가서 상태값 변경, 커밋해 주고 결과 값만 전달
		// 삭제후 목록으로 돌아갈때 반별 게시판이기때문에 반 이름이 필요해서 전달받음
		String className = request.getParameter("classname");
		int nno = Integer.parseInt(request.getParameter("nno"));
		System.out.println(className + nno);
		
		// jsp에서 받은 게시글번호, 반이름으로 게시글,댓글,첨부파일 모두 상태 N으로 변경
		int result1 = new ClassNoticeService().deleteNotice(nno);
		
		// 한번에 첨부파일까지 삭제처리하니까 java.sql.SQLException: 접속 종료 오류 발생
		// 그냥 따로 메소드 생성해서 삭제 하니까 잘 됨. 뭐가 문제인지는 잘 모르겠음
		int result2 = new ClassNoticeService().deleteAttachment(nno);
		
		// 게시물을 삭제 했을경우 반별 게시판 목록으로 화면전환, 실패시 에러페이지
		if(result1 * result2 > 0) {
			request.setAttribute("msg", "게시물 삭제 성공");
			request.getRequestDispatcher("classNoticeList.do?classname=" + className).forward(request, response);
		}else {
			request.setAttribute("msg", "게시물 삭제 실패");
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
