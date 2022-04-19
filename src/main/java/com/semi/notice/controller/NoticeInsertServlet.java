package com.semi.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.common.MyFileRenamePolicy;
import com.semi.common.dto.Attachment;
import com.semi.notice.model.dto.Notice;
import com.semi.notice.model.service.NoticeService;
import com.semi.user.model.dto.User;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/insertNotice.do")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//multipart로 전송이 잘 될 경우 실행
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 10 * 1024 * 1024;
			
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			
			String savePath = resources + "\\notice_upfiles\\";
			System.out.println("savePath : " + savePath);
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
			
			Notice n = new Notice();
			n.setNoticeTitle(title);
			n.setNoticeContent(content.replace("\n", "<br>"));
			n.setNoticeWriter(String.valueOf(userNo));
			
			//파일이 하나
			Attachment at = null;
					
			if(multiRequest.getOriginalFileName("upfile") != null) {
				String originName = multiRequest.getOriginalFileName("upfile");
				String changeName = multiRequest.getFilesystemName("upfile");
				
				at = new Attachment();
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);				
			}
						
			int result = new NoticeService().insertNotice(n, at);
			
			if(result > 0) {
				request.getSession().setAttribute("msg", "게시글이 성공적으로 등록되었습니다.");
				response.sendRedirect("listNotice.do");
			}else {				
				File failedFile = new File(savePath + at.getChangeName());				
				failedFile.delete();
				
				request.setAttribute("msg", "사진 게시글 등록 실패");
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
