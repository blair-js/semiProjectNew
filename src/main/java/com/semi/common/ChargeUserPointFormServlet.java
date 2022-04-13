package com.semi.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//charge.jsp로 화면전환만 해주는 서블릿임
@WebServlet("/chargePoint.do")
public class ChargeUserPointFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChargeUserPointFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null) {
			
			request.setAttribute("loginUser", loginUser);
			request.getRequestDispatcher("views/common/charge.jsp").forward(request, response);
			
		}else {
			
			request.setAttribute("loginUser", null);
			request.getRequestDispatcher("views/common/charge.jsp").forward(request, response);
		}*/
		
		request.getRequestDispatcher("views/common/charge.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
