package com.semi.snack.controller;

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
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class SnackInsert
 */
@WebServlet("/snackInsert.do")
public class SnackInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SnackInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("간식 추가를 하기위한 서블릿");

		request.setCharacterEncoding("UTF-8");

		if (ServletFileUpload.isMultipartContent(request)) { // 만약에 서블릿파일업로드 멀티파트 컨텐트가 트루이면 실행

			int maxSize = 10 * 1024 * 1024;

			// 1_2. 전달된 파일을 저장할 서버의 폴더 경로
			String resources = request.getSession().getServletContext().getRealPath("/resources");

			// 폴더의 경로를 잘 가지고 왔으면 저장
			String savePath = resources + "\\FileUpload_test(SNACK)\\";

			System.out.println("savePath " + savePath); // 경로 잘찍히는지 찍는 용도

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			String snackName = multiRequest.getParameter("snackName");
			int userNo = Integer.parseInt(multiRequest.getParameter("userNo"));
			int snackPrice = Integer.parseInt(multiRequest.getParameter("snackPrice"));

			
			Snack snack = new Snack(); //객체생성 생성자이용
			
			snack.setUserNo(userNo);
			snack.setSanckName(snackName);
			snack.setPrice(snackPrice);;
			

				Attachment at = null;// 첨부파일을 안넣을수도 있어서 null 선언
				if(multiRequest.getOriginalFileName("file") != null) { //file = snackInsert.jsp에서 넘겨준 파라미터
				String originName = multiRequest.getOriginalFileName("file"); // 원본명
				String changeName = multiRequest.getFilesystemName("file"); // 바꾼이름

				System.out.println("originName : " + originName);
				System.out.println("changeName : " + changeName);
				

				at = new Attachment(); // 첨부파일이 있으면 객체 생성
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
				}
				
				 
				
				
			int result = new SnackService().insertSnack(snack, at);

			if (result > 0) {
				request.getSession().setAttribute("msg", "간식 등록 성공");
				request.getRequestDispatcher("snack.do?userNo=" + userNo).forward(request, response);
				System.out.println(result);
				

			} else { // 등록실패
				if (at != null) {
					File failedFile = new File(savePath + at.getChangeName());
					failedFile.delete();
				}

				request.setAttribute("msg", "간식 등록 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);

			}
		}
	}

	/*
	 * String snackName = request.getParameter("snackName");
	 * 
	 * int snackPrice = Integer.parseInt(request.getParameter("snackPrice"));
	 * 
	 * //처음에 서블릿에서 서비스 보내서 다오 보내서 디비에 데이터를 넣을 건데 데이터를 넣을 파라미터 매개변수 값을 객체에 담아서 보내줄건지
	 * 스트링, 인트 등 기본타입으로 넣어서 보내줄지를 선택 하는거이다.
	 * 
	 * //기본생성자를 이용해서 값을 담아준거지 매개변수를 만든거는 아니다
	 * 
	 * Snack snack = new Snack(); //객체 생성 후 값을 담아줌
	 * 
	 * snack.setSanckName(snackName); snack.setPrice(snackPrice);
	 * 
	 * 
	 * int result = new SnackService().snackInsert(snack);
	 * 
	 * if(result > 0 ) { request.getSession().setAttribute("msg", "간식 등록 성공");
	 * response.sendRedirect("/adminMyPage.do"); }else { request.setAttribute("msg",
	 * "간식 등록 실패");
	 * request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,
	 * response);
	 * 
	 * }
	
	}
 */
	private Object SnackService() {
		// TODO Auto-generated method stub
		return null;
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
