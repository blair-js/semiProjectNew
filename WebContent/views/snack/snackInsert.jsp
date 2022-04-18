<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>

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

         <h1>간식 추가</h1>


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
      
      <!-- 스낵 체크박스 체크 후 서블릿으로 이동시키기 위한 form 시작  -->
      <form id="snackInsert_img"
         action="<%=request.getContextPath()%>/snackInsert.do" method="post" enctype="multipart/form-data" onsubmit="return insertCheck();">
         <input type="hidden" id="userNo" name="userNo" value="<%= loginUser.getUserNo() %>">
         <!--  enctype="multipart/form-data" 일단 주석 -->
      
    

      <br> <br>

      
         <div class="container-md">
            <div class="row">

               <div name="snack_img" id="center">
                  <table>
                  <tr>
                  <td><img id="snackImg" height="250px" width="369.33px" /></td>
                  </tr>
                  </table>
                  <!-- 현재 올릴 사진은 1개이기에 수업 jsp중 tuhmbnailInsertForm.jsp 참고-->
               </div>

            </div>

         </div>
            
         <div id="fileArea">   
         <br> <input type="file" id="file" name="file"
            onchange="loadImg(this, 1);" >
         </div>
         
         <p></p>

         <!-- multipart/form-data 을 사용하여 데이터 전송 -->

         <!-- 구매에 대한 체크박스 필요 뼈다귀 수량이 있어야할 컨테이너 -->
         <div class="container-md">
            <div class="row">

               <div class="col-sm row gx-3">
                  <!-- snack 1 div 시작 -->

                  <table id="center">
                     <tr>
                        <td> 
                        
                        <p> <h5> 간식명  :  <input type="text" name="snackName" placeholder="간식 명을 입력해주세요."  required></h5> </p> 
                         
                         
                        <p> <h5> 뼈다귀  :  <input type="text" name="snackPrice" placeholder="수량 을 입력해주세요." id="snackPrice" required></h5> </p> 
                        
                        </td>
                     </tr>

                  </table>
                        
               </div>
               </div>

               </div>
               <!-- snack 1 div 끝-->
               
                 <div>

         <button type="submit" class="btn btn-outline-warning btn-lg"
            style="width: 20%" id="center" >

            <b>간식 추가</b>

         </button>


         <p></p>


      </div>
               
      </form>

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


   <!-- 스낵 체크박스 체크 후 서블릿으로 이동시키기 위한 form 끝  -->

   <br>
   <br>



   <div class="px-4 py-1 my-5 text-center">
      <p class="display-5 fw-bold"></p>
   </div>



   </div>
   <!-- 컨테이너 끝 div -->

   <script>
   
   
   console.log(<%=loginUser.getUserNo()%>)
   $(function(){
      $("#fileArea").hide();
      
      
      $("#snackImg").click(function(){ 
         $("#file").click();
   
      });
         
   });

   

   function loadImg(inputFile, num){ 
      if(inputFile.files.length == 1){
         var reader = new FileReader(); // 파일 읽어 들이는 객체 생성 (미리보기)
         
         //파일이 존재하면 URL을 읽어와서 가져오겠다는 함수
         reader.readAsDataURL(inputFile.files[0]); // 파일을 일단 가져오면 배열의 0번째 인덱스에 들어있다. 파일 읽어 들이는 메소드
         
         <!-- 파일리더 API = https://developer.mozilla.org/ko/docs/Web/API/FileReader -->
         reader.onload = function(e){ // 파일 읽기가 다 완료되면 실행
            switch(num){
            // e.target.result(URL 형식) 결과값을 src에 다 담아주고 있다. 각각의 파일을 읽어들여서 미리보기가 가능하게된다.
            case 1 : $("#snackImg").attr("src", e.target.result); break; //src 속성을 titleImg 속성을 걸어주니가 load가 가능한거이다.
         
            }
         }
      }
   }
   
   function insertCheck(){
	   
      var fileCheck = document.getElementById("file").value; 
      
      if(!(fileCheck)) {
         alert("간식 이미지를 선택해주세요")
         $("#snackImg").focus();
         return false;
      }
      return true;
   }
   

   </script>

   <%@ include file="../common/footer.jsp"%>
</body>
</html>