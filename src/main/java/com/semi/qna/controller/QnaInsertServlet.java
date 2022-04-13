package com.semi.qna.controller;

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
import com.semi.qna.model.dto.Qna;
import com.semi.qna.model.service.QnaService;
import com.semi.user.model.dto.User;

/**
 * Servlet implementation class QnaInsertServlet
 */
@WebServlet("/insertQna.do")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertServlet() {
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
			
			String savePath = resources + "\\qna_upfiles\\";
			System.out.println("savePath : " + savePath);
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String pwd = multiRequest.getParameter("pwd");
			String secret = multiRequest.getParameter("secret");
			System.out.println("secret" + secret);
			int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
			
			Qna q = new Qna();
			q.setQnaTitle(title);
			q.setQnaContent(content.replace("\n", "<br>"));
			q.setQnaWriter(String.valueOf(userNo));
			
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			for(int i = 1; i <= fileList.size(); i++) {
				//ckeditor는 파일이 upload라는 name으로 넘어온다.
				if(multiRequest.getOriginalFileName("upload") != null) {
					String originName = multiRequest.getOriginalFileName("upload");
					String changeName = multiRequest.getFilesystemName("upload");
					
					Attachment at = new Attachment();
					at.setFilePath(savePath);
					at.setOriginName(originName);
					at.setChangeName(changeName);
					
					fileList.add(at);				
				}
			
			}
			
			int result = new QnaService().insertQna(q, fileList);
			
			if(result > 0) {
				request.getSession().setAttribute("msg", "게시글이 성공적으로 등록되었습니다.");
				request.getRequestDispatcher("views/qna/qnaListView.jsp").forward(request, response);
			} else {
				//실패할 경우 업로드된 파일 삭제
				for(int i = 0; i < fileList.size(); i++) {
					File failedFile = new File(savePath + fileList.get(i).getChangeName());
					
					failedFile.delete();
				}
				request.setAttribute("msg", "게시글 등록 실패");
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
