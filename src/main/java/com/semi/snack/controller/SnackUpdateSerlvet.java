package com.semi.snack.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.semi.common.MyFileRenamePolicy;
import com.semi.common.dto.Attachment;
import com.semi.snack.model.dto.Snack;
import com.semi.snack.model.service.SnackService;

/**
 * Servlet implementation class SnackUpdate
 */
@WebServlet("/snackUpdate.do")
public class SnackUpdateSerlvet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackUpdateSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      System.out.println("간식수정을 하기위한 서블릿");
      
      request.setCharacterEncoding("UTF-8");
      
      int maxSize = 10 * 1024 * 1024;
      
      // 1_2. 전달된 파일을 저장할 서버의 폴더 경로
      String resources = request.getSession().getServletContext().getRealPath("/resources");
      
      // 폴더의 경로를 잘 가지고 왔으면 저장
      String savePath = resources + "\\FileUpload_test(SNACK)\\";
      
      System.out.println("savePath " + savePath); // 경로 잘찍히는지 찍는 용도

      MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
      
      int sno = Integer.parseInt(multiRequest.getParameter("sno"));
      
      Snack snack = new Snack();
      snack.setSanckName(multiRequest.getParameter("snackName"));
      snack.setPrice (Integer.parseInt(multiRequest.getParameter("snackPrice")));
      snack.setSanckNo(sno);
      
      
      /*
      int sno = Integer.parseInt(multiRequest.getParameter("sno"));
      String snackName = multiRequest.getParameter("snackName");
      int snackPrice = Integer.parseInt(multiRequest.getParameter("snackPrice"));
      
      
      Snack snack = new Snack();
      snack.setSanckName(snackName);
      snack.setPrice(snackPrice);
      snack.setSanckNo(sno);
      */
      Attachment at = null;
      if(multiRequest.getOriginalFileName("file") != null) {
         String originName = multiRequest.getOriginalFileName("file");
         String changeName = multiRequest.getFilesystemName("file");
         
         at = new Attachment();
         at.setFilePath(savePath);
         at.setOriginName(originName);
         at.setChangeName(changeName);
         
         System.out.println("originName" + originName);
         
         if(multiRequest.getParameter("originFile") != null) { //만약 기존에 첨부되어 있는 파일이 있는 경우에
                                                    //현재 snackUpdateForm.jsp에서 getChangeName이 같이 넘어옴    
            File deleteFile = new File(savePath + multiRequest.getParameter("originFileNo"));  //경로를 합쳐서 
            
            deleteFile.delete(); //같이 지워준다.
            
            //originFileNo에 getFileNo가 같이 넘어오기때문에 FileNo를 set해준다.
            at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
         }else {
            //첨부파일을 비어있으니까 그냥 인설트하는 경우
            at.setRefNo(sno);  //해당하는 게시글에 첨부하려고 담아둠
              
         }
      }
      
      int result = new SnackService().updateSnack(snack, at);
      if(result > 0 ) {
         response.sendRedirect("snackDetail.do?sno="+sno); 
      }else {
         request.getSession().setAttribute("msg", "게시글 수정 실패 "); //그기 아니라면
         request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response); //에러페이지 전환
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