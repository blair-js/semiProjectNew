package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.common.MyFileRenamePolicy;
import com.semi.common.dto.Attachment;
import com.semi.user.model.dto.Dog;
import com.semi.user.model.service.UserService;

//dogEnrollForm.jsp에서 여기로 옴
@WebServlet("/insertDog.do")
public class DogInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DogInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			//파일 최대크기 지정
			int maxsize = 10 * 1024 * 1024;
			
			//전달받은 파일을 저장할 서버의 폴더 경로 지정 
            String resources = request.getSession().getServletContext().getRealPath("/resources");
            
            //최종적으로 저장되는 경로이며, 위에서 얻은 경로안의 폴더(enroll_dogs)에 넣을 것이다.
            String savePath = resources + "\\enroll_dogs\\";
            System.out.println("savePath" + savePath); //경로 확인
			
            //multiRequest 객체 생성
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxsize, "UTF-8", new MyFileRenamePolicy());	
			
			//파라미터 받기
			int userNo = Integer.parseInt(multiRequest.getParameter("userNo")); //hidden으로 넘어오고 있음
			String dogName = multiRequest.getParameter("dogName");
			int dogAge = Integer.parseInt(multiRequest.getParameter("dogAge"));
			String dogGender = multiRequest.getParameter("dogGender");
			//String dogWeight = multiRequest.getParameter("dogWeight"); //DB에 필요없어서 뺌_0407
			String memo = multiRequest.getParameter("memo");
			String dogClass = multiRequest.getParameter("dogClass");
			String wating = multiRequest.getParameter("wating");
			
			//파라미터 값 확인 
			/*System.out.println(userNo);
			System.out.println(dogName);
			System.out.println(dogAge);
			System.out.println(dogGender);
			System.out.println(dogWeight);
			System.out.println(memo);
			System.out.println(dogClass);
			System.out.println(wating);
			System.out.println("dogImgName : " + dogImgName);*/
			System.out.println("wating 확인" + wating);
			
			//String dogImgName = multiRequest.getOriginalFileName("file");
			
			//파라미터로 넘어오는 파일의 원본명과 새파일명 확인
			String originName = multiRequest.getOriginalFileName("file"); //원본명
     	    String changeName = multiRequest.getFilesystemName("file"); //새파일명
     	    System.out.println(originName);
     	    System.out.println(changeName);
			
			//inser할 Dog 객체를 생성하여 값 셋팅 => 매개변수 생성자 이용(dogNo와 category는 없는 7개의 인자만 필요로하는 생성자 사용)
			Dog dog = new Dog(userNo, dogClass, dogName, dogAge, dogGender, memo, wating);
			
			//Dog 객체와 같이 insert할 첨부파일 객체 생성하고 필요부분 값 셋팅
			//입학신청에서 사진첨부는 필수이므로 사진을 첨부하지 않으면 해당 서블릿으로 넘어오지못함. 그러니 null 선언 필요없음.
			Attachment at = new Attachment();
			at.setFilePath(savePath); 
			at.setOriginName(originName);
			at.setChangeName(changeName);
			
			//강아지 정보와 강아지 이미지를 insert하는 메소드 호출
			//Dog 객체와 첨부파일 객체를 메소드의 매개변수로 전달
			int result = new UserService().insertDog(dog, at);
			
			//////////////////////////////////////////////////////////////////////////////////////////////////
			
			//실패용 test code
			//result = -1;
			
			if(result > 0) { //입학(등록) 성공시
				
				//request 객체에 Dog 객체의 정보 담아주기 => dogInsertOk에서 값을 받아서 정보 출력할 예정!
				request.setAttribute("dog", dog);
				
				//입학신청 성공화면으로 이동
				request.getRequestDispatcher("views/user/dogInsertOk.jsp").forward(request, response);

			}else { //입학(등록) 실패시
				
				request.getSession().setAttribute("msg", "입학신청을 실패하였습니다.");
				
				//다시 마이페이지로 이동
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
				
			}//if~else
			
		}
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
