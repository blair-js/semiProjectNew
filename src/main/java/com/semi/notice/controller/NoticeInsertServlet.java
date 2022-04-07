package com.semi.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
			
			//파일이 여러 개
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			
			for(int i = 1; i <= 3; i++) {
				String name = "upfile" + i;
				
				if(multiRequest.getOriginalFileName(name) != null) {
					String originName = multiRequest.getOriginalFileName(name);
					String changeName = multiRequest.getFilesystemName(name);
					
					Attachment at = new Attachment();
					at.setFilePath(savePath);
					at.setOriginName(originName);
					at.setChangeName(changeName);
					
					fileList.add(at);
				}
			}
			
			int result = new NoticeService().insertNotice(n, fileList);
			
			if(result > 0) {
				response.sendRedirect("listNotice.do");
			}else {
				//실패할 경우 업로드 된 파일을 지운다.
				for(int i = 0; i < fileList.size(); i++) {
					File failedFile = new File(savePath + fileList.get(i).getChangeName());
					
					failedFile.delete();
				}
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
