package com.semi.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

//charge.jsp에서 결제 성공시 넘어오는 페이지
@WebServlet("/userPointInsert.do")
public class UserPointInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPointInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//파라미터 확인(사용자 포인트 테이블에 데이터를 넣기 위해 회원아이디, 충전(결제)한 금액이 필요하다)
		String userId = request.getParameter("userId"); //회원아이디
		int updatePoint = Integer.parseInt(request.getParameter("point")); //충전금액(포인트)
		System.out.println("point 확인 : " + updatePoint);
		System.out.println("userNo 확인 : " + userId);
		
		
		if(updatePoint != 0 && userId != null) { //회원아이디가 있고 충전포인트가 0이 아닐때
			
			//위에서 얻은 회원아이디를 기준으로 현재 보유 포인트를 얻어온다.
			int originPoint = new UserService().selectUserPoint(userId);
			
			//해당회원이 기존에 갖고있던 보유포인트에 결제한 충전금액을 누적시켜야 한다.
			updatePoint += originPoint;
			
			int result = new UserService().updateUserPoint(userId, updatePoint);
			
			///////////////////////////////////////////////////////////////
			
			if(result > 0) { //포인트 업데이트 성공시
				
				//간식페이지를 뿌려주는 서블릿으로
				request.getRequestDispatcher("snack.do").forward(request, response);
				
			}else { //실패시 
				
				//다시 충전페이지로
				request.getRequestDispatcher("views/common/charge.jsp").forward(request, response);
			}
			
		}else { //둘 중 하나라도 값이 없다면
			
			request.getRequestDispatcher("views/common/charge.jsp").forward(request, response);
			
		}//if~else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
