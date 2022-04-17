package com.semi.snack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.dto.SnackOrder;
import com.semi.snack.model.dto.UserPoint;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class Snack
 */
@WebServlet("/snackResult.do")
public class SnackOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SnackOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("구매에 대한 DB 작업후 다시 간식 페이지로 이동 ");

		request.setCharacterEncoding("UTF-8");

		int uno = Integer.parseInt(request.getParameter("userNo")); //포인트조회를 하기위해 받아온 값

		// 구매한 뼈다귀 총합 가격
		int sum = 0;
		

		//구매할때 체크한 스낵의 No를 모두 넘겨받음
		String[] snackNos = request.getParameterValues("snackNo");

		//넘겨받은 스낵의 No를 하나씩 꺼내서 가격을 조회함
		if (snackNos != null) {
			for (int i = 0; i < snackNos.length; i++) {
					
				Snack snack = new SnackService().selectPrice(snackNos[i]);
				//조회한 스넥의 가격을 모두 더함
				sum += snack.getPrice();
			}
		}

		System.out.println("uno 찍어보기 : " + uno);
		// System.out.println("snackArrays 찍어보기 : " + snackArrays.toString()); 그냥 배열을
		// 찍지말고 배열의 인덱스 값을 찍어주기..
		System.out.println("sum 찍어보기 : " + sum);

		
		UserPoint up = new UserPoint();
		up.setUserNo(uno);

		UserPoint userPoint = new SnackService().selectUserPoint(up); // 유저 포인트 조회를 위한 메서드
		
		
		int pointNo = userPoint.getUserPoint();
		
		if(pointNo >= sum) {
			
		
		
		//내 포인트에서 구매한 스넥의 총비용(sum)을 차감한 값(point)
		int point = userPoint.getUserPoint() - sum; //point가 만약 구매할 간식 총 가격보다 작다면 구매 실패 보유 및 뼈다귀 msg 보이도록 하기 조건문으로 걸어주도록 재수정해야함
		
		//계산한 금액을 유저포인트로 업데이트 해주기 위해 값을 넣어줌 뺀 값을
		up.setPoint(point);
		
		//구매후 남은 금액을 보여주기 위하여 넣어줌
		userPoint.setUserPoint(point);

		int result = new SnackService().OrderEnd(up); //사용자의 보유뼈다귀를 업데이트하기 위한 메서드

		// 업데이트 실패 여부를 확인
		if (result != 0) {
				//for문을 통하여 구매한 스낵 1건당 구매내역을 인설트 해줌		
				for (int j = 0; j < snackNos.length; j++) {
					int result2 = new SnackService().insertOrder(snackNos[j], uno);
				}	


			request.setAttribute("userPoint", userPoint);
			request.getRequestDispatcher("views/snack/snackResult.jsp").forward(request, response);
		} /*else {
			request.getSession().setAttribute("msg", "구매 실패 보유 뼈다귀를 확인해주세요");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response); 
		}*/
		}else {
			request.getSession().setAttribute("msg", "구매 실패 보유 뼈다귀를 확인해주세요");
			request.getRequestDispatcher("snack.do?userNo=" + uno).forward(request, response);
		}

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
