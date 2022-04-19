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
			
			String savePath = resources + "\\notice_upfiles\\";
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			int nno = Integer.parseInt(multiRequest.getParameter("nno"));
			
			//userNo를 가져와서 넘겨준다.
			int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
			
			//삭제 체크 박스 체크 된 경우
			//String delFile = multiRequest.getParameter("delFile");
			
			Notice n = new Notice();
			n.setNoticeTitle(multiRequest.getParameter("title"));
			n.setNoticeContent(multiRequest.getParameter("content").replace("\n", "<br>"));
			n.setNoticeNo(nno);
			n.setNoticeWriter(String.valueOf(userNo));
			
			//넘어오는 첨부파일이 여러 개일 수 있기 때문에 arrayList
			//ArrayList<Attachment> atList = new ArrayList<Attachment>();
			
			//넘어오는 첨부파일 1개
			Attachment at = null;
			//for(int i = 1; i <= atList.size(); i++) {
				//String name = "upfile" + i;
				
				//첨부파일을 새로 첨부하는 경우
				if(multiRequest.getOriginalFileName("upfile") != null) {
					 String originName = multiRequest.getOriginalFileName("upfile");
					 String changeName = multiRequest.getFilesystemName("upfile");
					 System.out.println("originName : " + originName);
					 System.out.println("changeName : " + changeName);
					 
					 at = new Attachment();
					 at.setFilePath(savePath);
					 at.setOriginName(originName);
					 at.setChangeName(changeName);
					 
					 //atList.add(at);
					 
					 if(multiRequest.getParameter("originFile") != null) { //기존 첨부파일이 있는 경우
						 File deleteFile = new File(savePath + multiRequest.getParameter("originFile"));					 
						 deleteFile.delete();
						 
						 at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					 }else {
						at.setRefNo(nno); 
					 }
				}
			//}
			
			int result = new NoticeService().updateNotice(n, at);
			
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
