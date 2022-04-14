package com.semi.class_notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.class_notice.model.dto.ClassNotice;
import com.semi.class_notice.model.service.ClassNoticeService;
import com.semi.common.MyFileRenamePolicy;
import com.semi.common.dto.Attachment;
import com.semi.user.model.dto.User;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 1024 * 1024;
//			// 1_2. 전달된 파일을 저장할 서버의 폴더 경로
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "\\board_upfiles\\";
			System.out.println("savePath : " + savePath);
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());			
			
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
			String className = multiRequest.getParameter("classname");
			
			System.out.println("반 이름 : " + className);
			// 받아 온 값으로 알림장 객체 생성
			ClassNotice n = new ClassNotice();
			
			// 현재 로그인한 회원번호 받아서 담아줌
			n.setNoticeWriter(String.valueOf(userNo));
			n.setClassNoticeTitle(title);
			n.setClassNoticeContent(content);
			n.setClassName(className);
//			위에까지가 게시물 객체 생성
			String originName = multiRequest.getOriginalFileName("titleimg");
			String changeName = multiRequest.getFilesystemName("titleimg");
			
			System.out.println("파일 원본 이름 : " + originName);
			System.out.println("파일 변경 이름 : " + changeName);
			
			Attachment at = new Attachment();
			
			at.setUserNo(userNo);
			at.setFilePath(savePath);
			at.setOriginName(originName);
			at.setChangeName(changeName);

			int result = new ClassNoticeService().insertNotice(n, at);

			if (result > 0) {
				// 게시글 등록 성공할경우 다시 리스트 목록으로 전환
				request.getRequestDispatcher("classNoticeList.do?classname=" + className).forward(request, response);
//				response.sendRedirect("classNoticeList.do?classname=" + className);
			} else {
				request.setAttribute("msg", "게시물 등록 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
