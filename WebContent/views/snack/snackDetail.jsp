<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import= "java.util.ArrayList, com.semi.snack.model.dto.*"%>
   <%@ page import= 'java.util.ArrayList, com.semi.common.dto.*' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>

   <%
   Snack snack = (Snack)request.getAttribute("snack");
   Attachment at = (Attachment)request.getAttribute("at");
   %>
   

<style>

#center {
   text-align: center;
   margin: auto;
   justify-content: center;
   display: flex;
}

</style>

</head>
<body>

   <%@ include file="../common/menubar.jsp"%>
   
   <div class="container">
      <!-- 컨테이너 시작 div -->


      <!-- 해당 페이지는 로그인된 유저만 들어올 수 있으며 비회원이 클릭 시 alert 창으로 "로그인을 해주세요" 띄워주기 -->

      <div class="px-3 py-3 my-4">
         <!-- 초기 설정 4 5 5 -->

         <h1>간식 상세보기</h1>


         <hr style="height: 7px; color: #FDC800" ;  id="center">

         <p></p>
         <!--  style="float:left" -->

         <p></p>

         <div style="border: 1px solid #FDC800; background-color: #FDC800">

            <br>

            <p>&nbsp&nbsp&nbsp저희 둥글개둥글개 에서 제공하는 간식은</p>

            <p>&nbsp&nbsp&nbsp뼈다귀 충전 후 보유한 포인트에서 차감하여 구매할 수 있습니다.</p>

            <p>&nbsp&nbsp&nbsp구매 한 간식은 식사시간에 견주님의 강아지에게 소분하여 지급되며,</p>

            <p>&nbsp&nbsp&nbsp필요 시 방문 및 통학버스를 이용하여 보호자님께서도</p>

            <p>&nbsp&nbsp&nbsp직접 수령이 가능하십니다.</p>

         </div>
      </div>

      
         <div class="container-md">
            <div class="row">

               <div name="snack_no" id="center">
                  <p id="center">간식 번호 : <%=snack.getSanckNo() %></p>
                  <br>
               </div>

            </div>

         </div>
            
         <p></p>
         
         
         <div class="container-md">
            <div class="row">


               <div name="snack_img" id="center">
                  <table>
                  <td><img src="<%= contextPath %>/resources/FileUpload_test(SNACK)/<%= at.getChangeName() %>" id="img" style="width:297px;"></td>
                  </table>
                  <!-- 현재 올릴 사진은 1개이기에 수업 jsp중 tuhmbnailInsertForm.jsp 참고-->
               </div>


            </div>

         </div>

         <!-- 구매에 대한 체크박스 필요 뼈다귀 수량이 있어야할 컨테이너 -->
         <div class="container-md">
            <div class="row">

               <div class="col-sm row gx-3">
                  <!-- snack 1 div 시작 -->

                  <table id="center">
                     <tr>
                        <td> 
                        
                        <p> <h5> 간식명  :  <input type="text" name="snackName" value="<%=snack.getSanckName() %>"  readonly></h5> </p> 
                         
                        <p> <h5> 뼈다귀  :  <input type="text" name="snackPrice" value="<%=snack.getPrice() %>" readonly></h5> </p> 
                        
                        </td>
                     </tr>

                  </table>


               </div>
               <!-- snack 1 div 끝-->
               

   </div>
   <!-- container 속성이 아래까지 못내려오도록 닫는 div -->
   </div>
   <!-- container 속성이 아래까지 못내려오도록 닫는 div -->


   </div>
   <!-- container 속성이 아래까지 못내려오도록 닫는 div -->
   </div>
   <!-- container 속성이 아래까지 못내려오도록 닫는 div -->


   </div>
   <!-- row div 종료 -->

   </div>
   <!-- container-md 종료 -->



   <div class="container-md">

   <button class="btn btn-outline-warning btn-lg" style="width: 15%"
      id="center" onclick="goSnackUpdateForm()"><b>간식 수정</b></button>
   
   <br>
   
   <button class="btn btn-outline-warning btn-lg" style="width: 15%"
      id="center" onclick="goSnackDelete()"><b>간식 삭제</b></button>
   
   <br>
   
   <button class="btn btn-outline-warning btn-lg" style="width: 15%"
      id="center" onclick="goSnack()"><b>간식 목록</b></button>
   </div>


   <div class="px-4 py-1 my-5 text-center">
      <p class="display-5 fw-bold"></p>
   </div>

   <form action="" id="postForm" method="post"> <!-- updateform 서블릿으로 sno를 가지고 간다. -->
         <input type="hidden" name="sno" value="<%= snack.getSanckNo() %>">
         <input type="hidden" id="userNo" name="userNo" value="<%= loginUser.getUserNo() %>">
      </form>

   </div>
   <!-- 컨테이너 끝 div -->
   

   
   <script>
   

   function goSnackUpdateForm(){
      $("#postForm").attr("action", "<%=contextPath%>/snackUpdateForm.do"); 
      <!--hidden으로 sno 값을 가지고 서블릿으로 간다 액션 속성값을 정의 : -->
      <!-- action = 서식 데이터(form data)를 서버로 보낼 때 해당 데이터가 도착할 URL -->
      $("#postForm").submit();  <!--updateForm을 눌렀을때 submit을 수행-->
   }   
   
   function goSnackDelete(){  //간식 수정을 하기위한 서블릿 여기에 들어가서 snackUpdateServlet를 호출 
      $("#postForm").attr("action", "<%=contextPath%>/snackDelete.do"); 
      <!--hidden으로 sno 값을 가지고 서블릿으로 간다 액션 속성값을 정의 : -->
      <!-- action = 서식 데이터(form data)를 서버로 보낼 때 해당 데이터가 도착할 URL -->
      $("#postForm").submit();  <!--delete를 눌렀을때 submit을 수행-->
   }   
   
   function goSnack(){  
      location.href ="/snack.do?userNo=<%=loginUser.getUserNo()%>";
   }   

   </script>
   
   
   <%@ include file="../common/footer.jsp"%>
</body>
</html>