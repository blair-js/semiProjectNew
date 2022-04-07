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

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/updateNotice.do")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//multipart 타입이 잘 전송이 된 경우에 실행
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 10 * 1024 * 1024; //10Mbyte
			
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			
			String savePath = resources + "\\board_upfiles\\";
			System.out.println("savePath : " + savePath);
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			int nno = Integer.parseInt(multiRequest.getParameter("nno"));
			System.out.println(nno);
			
			Notice n = new Notice();
			n.setNoticeTitle(multiRequest.getParameter("title"));
			n.setNoticeContent(multiRequest.getParameter("content"));
			n.setNoticeNo(nno);
			
			System.out.println(n);
			
			ArrayList<Attachment> atList = null;
			for(int i = 1; i <= 3; i++) {
				String name = "upfile" + i;
				
				//첨부파일을 새로 첨부하는 경우
				if(multiRequest.getOriginalFileName(name) != null) {
					 String originName = multiRequest.getOriginalFileName(name);
					 String changeName = multiRequest.getFilesystemName(name);
					 
					 Attachment at = new Attachment();
					 at.setFilePath(savePath);
					 at.setOriginName(originName);
					 at.setChangeName(changeName);
					 
					 atList.add(at);
					 
					 if(multiRequest.getParameter("originFile") != null) {
						 File deleteFile = new File(savePath + multiRequest.getParameter("originFile"));
						 
						 deleteFile.delete();
						 
						 at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					 }else {
						at.setRefNo(nno); 
					 }
				}
			}
			
			int result = new NoticeService().updateNotice(n, atList);
			
			if(result > 0) {
				response.sendRedirect("detailNotice.do?nno="+nno);
			} else {
				request.setAttribute("msg", "공지사항 게시글 수정에 실패하였습니다.");
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
