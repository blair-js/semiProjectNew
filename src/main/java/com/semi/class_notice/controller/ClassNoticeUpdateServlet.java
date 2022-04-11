package com.semi.class_notice.controller;

import java.io.File;
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
 * Servlet implementation class ClassNoticeUpdateServlet
 */
@WebServlet("/classNoticeUpdate.do")
public class ClassNoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassNoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 1024 * 1024;
	
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "\\board_upfiles\\";
			System.out.println("savePath : " + savePath);
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());			
			
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
			String className = multiRequest.getParameter("classname");
			
			System.out.println("반 이름 : " + className);
			
			int nno = Integer.parseInt(multiRequest.getParameter("nno"));
			// 수정할 게시글 내용들 담아준다.
			ClassNotice cn = new ClassNotice();
			cn.setClassNoticeNo(nno);
			cn.setClassNoticeTitle(title);
			cn.setClassNoticeContent(content);
			cn.setClassName(className);
			cn.setNoticeWriter(String.valueOf(userNo));
			
			Attachment at = null;
			// 수정할때 첨부하는 파일이 있는경우에만 작성
			if(multiRequest.getOriginalFileName("titleimg") != null) {
				// 대표 이미지를 첨부하지 않으면 게시글 작성이 되지않음. 무조건 첨부파일이 존재
				String originName = multiRequest.getOriginalFileName("titleimg");
				String changeName = multiRequest.getFilesystemName("titleimg");
				
				at = new Attachment();
				at.setUserNo(userNo);
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
				// 기존 첨부파일 삭제
				if(multiRequest.getParameter("originFile") != null) {
					File deleteFile = new File(savePath + multiRequest.getParameter("originFile"));
					deleteFile.delete();
					// 화면에서 넘겨준 파일번호에 가서 수정해주기 위해 파일 번호 세팅
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				}
				
			}
			
			int result = new ClassNoticeService().updateNotice(cn, at);
			
			if(result > 0) {
				request.getSession().setAttribute("msg", "게시물 수정 성공!");
				response.sendRedirect("classNoticeDetail.do?nno=" + nno);
			}else {
				request.setAttribute("msg", "게시물 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
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
