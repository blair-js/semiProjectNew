package com.semi.snack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.dto.PageInfo;
import com.semi.snack.model.service.SnackService;
import com.semi.user.model.dto.User;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/userSearchForm.do")
public class UserSearchServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("회원조회를 하기위한 서블릿");
      
      // 페이징처리

      int listCount; // 총게시글 갯수
      int currentPage; // 현재페이지 (요청한 페이지)
      int startPage; // 현재 페이지 하단에 보여지는 페이징 바의 시작수
      int endPage; // 현재 페이지 하단에 보여지는 페이징 바의 끝수

      int maxPage; // 전체 페이지의 가장 마지막 페이지
      int pageLimit; // 한페이지 하단에 보여질 페이지 최대갯수
      int boardLimit; // 한페이지에 보여질 게시글 최대갯수

      // 총게시글 갯수
      listCount = new SnackService().getListCount();
      System.out.println("listCount : " + listCount);

      // 현재페이지
      currentPage = 1; // 게시판 눌렀을때 처음 보여지는 화면이 1페이지를 담아놔야 컴퓨터가 알아야기 때문에 다른 화면에서 눌러도 1페이지인걸 알게된다.

      // 페이지 전환시 전달받은 페이지가 있을경우 전달받은 페이지를 currentPage에 담기
      if (request.getParameter("currentPage") != null) { // null이 아니면 값이 담아져있다는거니 해당하는값을 다시 담아준다
         currentPage = Integer.parseInt(request.getParameter("currentPage")); // 스트링 타입으로 넘어오기에 형변환
      }

      // 페이지 최대갯수
      pageLimit = 10; // 페이징바가 몇까지인지

      // 게시글 최대갯수
      boardLimit = 10; // 게시글 리스트

      maxPage = (int)Math.ceil((double)listCount/boardLimit); // ceil = 올림

      startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

      // * endPage : 현재 페이지에 보여지는 페이징 바의 끝 수
      // startPage : 1 => endPage : 10
      // startPage : 11 => endPage : 20

      endPage = startPage + pageLimit - 1;

      if (maxPage < endPage) { // 끝페이지를 무분별하게 만들어놓지 않기위해서
         endPage = maxPage;
      }

      PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
      
      ArrayList<User> list = new SnackService().userSearch(pi);
      
      System.out.println("서블릿 list" + list);
      request.setAttribute("pi", pi);
      
      request.setAttribute("msg", "조회 성공");
      request.setAttribute("list", list);
      RequestDispatcher view = request.getRequestDispatcher("views/admin/userSearch.jsp");
      view.forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}