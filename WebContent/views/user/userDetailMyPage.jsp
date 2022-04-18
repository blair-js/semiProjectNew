<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.util.ArrayList, com.semi.user.model.dto.*"%> 
   
<%
   //UserMyDetailServlet에서 forward해줬으니 현재 회원 정보를 가져온다. 
   //UserMyDetailServlet에서 loginUser 속성명으로 객체에 회원 정보를 set 해줬음
   User u = (User)request.getAttribute("loginUser");

   //u에 담겨있는 값들을 하나씩 뽑아서 변수에 저장
   String userName = u.getUserName();
   Date enrollDate = u.getEnrollDate();
   String userId = u.getUserId();
   String email = u.getEmail();
   String phone = u.getPhone(); 
   //String gender = u.getGender() != null ? u.getGender() : " "; //필수입력사항이 아니므로 대입되는 값을 삼항연산 사용(null 이면 빈 값 대입)
   //String point = "포인트표시";
   String status = u.getStatus();
   //String smschecked = u.getSmsChecked();
   
   //sms체크여부에 따라 라디오 버튼 check해주기 위해 변수 선언
   String smsCheckYes = "";
   String smsCheckNo = "";
   
   if(u.getSmsChecked() != null){
      if(u.getSmsChecked().equals("Y")){
         smsCheckYes = "checked";
      }else{
         smsCheckNo = "checked";
      }
   }//if
   
   //UserMyDetailServlet에서 loginUserPoint 속성명으로 해당 회원의 포인트를 set 해줬음
   String userPoint = (String)request.getAttribute("loginUserPoint");
   
   String checkedGenderFemale = "";
   String checkedGenderMale = "";

   //user의 성별이 남자냐 여자냐에 따라 표시해주는 option태그가 달라져야하므로 if문사용
   if(u.getGender() != null){
      if(u.getGender().equals("F")){
         checkedGenderFemale = "selected";
      }else{
         checkedGenderMale = "selected";
      }
   }//if
   
   String userPwd = u.getUserPwd();
   
   ///////////////////////////////////
   
   //UserMyDetailServlet에서 넘겨주는 강아지리스트
   ArrayList<Dog> dogList = (ArrayList<Dog>)request.getAttribute("dogList");
   int dogCount = dogList.size();
      
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 마이페이지</title>
<style>
@font-face {
   font-family: 'LeferiPoint-BlackA';
   src:
      url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff')
      format('woff');
   font-weight: normal;
   font-style: normal;
}

* {
   font-family: 'LeferiPoint-BlackA';
}

.bd-placeholder-img {
   font-size: 1.125rem;
   text-anchor: middle;
   -webkit-user-select: none;
   -moz-user-select: none;
   user-select: none;
}

@media ( min-width : 768px) {
   .bd-placeholder-img-lg {
      font-size: 3.5rem;
   }
}
.hr1{
    color: #FDC800;
}
main button b{
   color: white;
}
main .sm{
    color: #0099FF;
}
</style>
</head>
<body>
   <!-- UserMyDetailServlet에서 여기로 넘어옴 -->
      
   <!-- menubar -->
   <%@ include file="../common/menubar.jsp"%>

   <!-- 컨테이너 시작 -->
   <div class="container">
      <main>
         <!-- 위 div -->
         <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png" alt="강아지로고" width="72" height="57">
            <h2>MY PAGE</h2>
         </div>



         <!--  -->
         <!-- 우측 강아지의 간략한 정보 시작 -->
         <div class="row g-5">
            <div class="col-md-5 col-lg-4 order-md-last">
               <!-- 첫번째 행 -->
               <h4 class="d-flex justify-content-between align-items-center mb-3">
                  <span class="text-primary">Your Dog</span> 
                  <span class="badge bg-primary rounded-pill"><%=dogCount %></span>
               </h4>
               <!-- 두번째 행 -->
               <!-- 강아지를 보유한 숫자만큼 반복하여 뽑아준다 -->
               <%if(dogList.isEmpty()) {%>
               <ul class="list-group mb-3">
                  <!-- 두번째 행의 첫번째 -->
                  <li class="list-group-item d-flex justify-content-between lh-sm">
                     <div>
                        <h6 class="my-0">Dog name</h6>
                        <small class="sm">강아지가 없습니다.</small>
                     </div> 
                     <span class="text-muted"></span>
                  </li>
               
               <%} else{%>
                  <!-- 향상된 for문을 사용하여 강아지 리스트 뽑아주기 -->
                  <%for(Dog d : dogList) {%>
                     <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                           <h6 class="my-0">Dog name</h6>
                           <small class="sm"><%=d.getDogName() %></small>
                        </div> 
                        <span class="text-muted"><%=d.getClassName() %></span>
                     </li>
                  <%} %>
                  <!-- 두번째 행의 두번째 -->
                   <!-- <li class="list-group-item d-flex justify-content-between lh-sm">
                     <div>
                        <h6 class="my-0">Dog name</h6>
                        <small class="sm">뽀삐</small>
                     </div> 
                     <span class="text-muted">B반</span>
                  </li>  -->
                  <!-- 두번째 행 끝 -->
               </ul> 
               <%} %>
               
               <!-- 세번째 행 : 버튼 -->
               <button type="button" class="w-100 btn btn-secondary btn-lg" onclick="goDetailDog()">
                  <b>강아지 정보 상세보기</b>
               </button>
               
               <!-- 간식구매내역 추가 0414 시작 -->
               <hr>
               <h4 class="d-flex justify-content-between align-items-center mb-3">
                  <span class="text-primary">Your Order History</span> 

               </h4>
               <button type="button" class="w-100 btn btn-secondary btn-lg" onclick="goUserSnackOrder()">
                  <b>나의 간식 구매내역 확인</b>
               </button>
               <!-- 간식구매내역 추가 0414 끝 -->
               <hr>
               <!-- 통학버스 예약 정보 -->
               <h4 class="d-flex justify-content-between align-items-center mb-3">
                  <span class="text-primary">Your Reservation</span>
               </h4>
               <!-- 회원 예약 내역 보여줄 부분 -->
               <div id="resBus">
               </div>

            </div>
            <!-- 우측 강아지의 간략한 정보 끝 -->
            <!--  -->
            
            <script>
            selectResUser();   
            // 회원의 버스 예약정보 조회 함수
               function selectResUser(){
                  if($("#resBus").empty()){
                     $("#resBus").html("통학버스 예약 내역이 존재하지 않습니다.");
                  }
                  
                  $.ajax({
                     url:"userRes.do",
                     data:{uNo : <%= u.getUserNo() %>},
                     type:"get",
                     success:function(userRe){
                        console.log(userRe);
                        var value = "";
                        // 버스를 예약하지 않은 회원의 경우
                        if(userRe.busNo == 0){
                           value='예약된 버스 내역이 존재하지 않습니다.'                           
                        }else{
                           value='<span>예약번호 : ' + userRe.busNo
                                + '<hr style="color:black;">예약차량 : ' + userRe.busDailyNo
                                + '&nbsp;<input type="button" class="btn btn-secondary btn-sm" onclick="deleteRe(\''+userRe.busNo+'\',\''+userRe.busDailyNo+'\');" value="예약취소">'
                        }
                        $("#resBus").html(value);
                     }
                  })
               }
            // 회원 예약 취소 함수
            function deleteRe(bNo, content){
               var ans = confirm("예약을 취소하시겠습니까?");
               if(!ans) return false;
               // 확인을 눌렀을경우 ajax 실행
               $.ajax({
                  url:"deleteUserRes.do",
                  type:"post",
                  data:{
                     bNo:bNo,
                     content:content
                  },
                  success:function(status){
                     if(status == "success"){
                        // 예약 취소된 화면전환위해 조회 함수 실행
                        selectResUser();
                        alert("예약이 취소 되었습니다.");
                     }
                  },
                  error : function(){
                     alert("예약 취소 실패. ajax-통신 에러");
                  }
               });
            }
            </script>   
               
               
            <!-- 강아지 로고가 있는 위쪽과 정보를 뿌려주는 아래쪽과의 구분선 -->   
            <hr class="my-4 hr1" style="height:7px;">

            <!-- 좌측 사용자의 상세한 정보 시작 -->
            <div class="col-md-7 col-lg-8">
               <h4 class="mb-3">Your Info</h4>
               
               <!-- 사용자의 정보를 뿌려주는 form이자 수정이 가능한 form -->
               <form action="<%= contextPath%>/updateUser.do;" id="updateForm" class="needs-validation" method="post" onsubmit="return updatePwdCheck();">
               <!-- 사용자 정보 업데이트시 필요한 부분이므로 hidden으로 넘겨준다. -->
               <input type="hidden" id="userNo" name="userNo" value="<%= u.getUserNo()%>">
               <input type="hidden" id = "userPwd" name ="userPwd" value="<%=userPwd %>" readonly>
               
                  <!-- 첫번째 행 1 : 이름, 가입일 기재 -->
                  <div class="row g-3">
                     <!-- 1-1 이름 -->
                     <div class="col-sm-6">
                        <label for="userName" class="form-label">Name</label> 
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="" value="<%=userName %>" required>
                     </div>
                     <!-- 1-2 가입일 -->
                     <div class="col-sm-6">
                        <label for="enrollDate" class="form-label">Enroll Date</label> 
                        <input type="text" class="form-control" id="enrollDate" placeholder="" value="<%=enrollDate %>" readonly required>
                     </div>
                     <!-- 두번째 행 2 : 아이디 -->
                     <div class="col-12">
                        <label for="userId" class="form-label">UserId</label>
                        <div class="input-group has-validation">
                           <input type="text" class="form-control" id="userId" name="userId" placeholder="" value="<%=userId %>" readonly required>
                        </div>
                     </div>
                     <!-- 세번째 행 3 : 이메일 -->
                     <div class="col-12">
                        <label for="email" class="form-label">Email</label> 
                        <input type="email" class="form-control" id="email" name="email" placeholder="" value="<%=email %>" required>
                     </div>
                     <!-- 네번째 행 4 : 전화번호 -->
                     <div class="col-12">
                        <label for="phone" class="form-label">Phone</label> 
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="" value="<%=phone %>" required>
                     </div>
                     <!-- 다섯번째 행 5 : 성별, 보유뼈다귀(포인트), 상태 -->
                     <!-- 5-1 : 성별 -->
                     <div class="col-md-5">
                        <label for="gender" class="form-label">Gender</label> 
                        <select class="form-select" id="gender" name="gender" required>
                           <option value="F" <%= checkedGenderFemale %>>여자</option>
                           <option value="M" <%= checkedGenderMale %>>남자</option>
                        </select>
                     </div>
                     <!-- 5-2 : 보유뼈다귀(포인트) -->
                     <div class="col-md-3">
                        <label for="point" class="form-label">Point</label> 
                        <input type="text" class="form-control" id="point" placeholder="" value="<%=userPoint %>" required readonly>
                     </div>
                     <!-- 5-3 : 상태 -->
                     <div class="col-md-4">
                        <label for="status" class="form-label">Status</label> 
                        <input type="text" class="form-control" id="status" placeholder="" value="<%=status %>" required readonly>
                     </div>
                  </div>
                  <!-- 사용자 정보 끝 -->

                  <hr class="my-4">
                  
                  <!-- 설문조사 -->
                  <!-- 데이터로 전달하는 부분은 아님! 그냥..멋내기용.. -->
                  <h4 class="mb-3">설문조사<span class="text-muted"> (Optional)</span></h4>
                  <div class="form-check">
                     <input type="checkbox" class="form-check-input" id="agree1">
                     <label class="form-check-label" for="agree1">둥글개 둥글개 유치원의 교육 프로그램에 만족합니다.</label>
                  </div>

                  <div class="form-check">
                     <input type="checkbox" class="form-check-input" id="agree2">
                     <label class="form-check-label" for="agree2">유치원 홈페이지의 이용이 편리합니다.</label>
                  </div>

                  <hr class="my-4">
                  
                  <!-- SMS 수신여부확인 -->
                  <h4 class="mb-3">SMS 수신여부</h4>
                  <div class="my-3">
                     <div class="form-check">
                        <input id="smsCheck" name="smsCheck" value="Y" type="radio" class="form-check-input" <%= smsCheckYes %>> 
                        <label class="form-check-label" for="smsCheck">예</label>
                     </div>
                     <div class="form-check">
                        <input id="smsCheck" name="smsCheck" value="N" type="radio" class="form-check-input" <%= smsCheckNo %>> 
                        <label class="form-check-label" for="smsCheck">아니오</label>
                     </div>
                  </div>

                  <hr class="my-4">

                     
                  <!-- 정보수정, 탈퇴 버튼 두개 -->
                  <div class="col-md-12 text-center pb-5">
                     <button type="submit" class="w-50 btn btn-primary btn-lg mb-2">
                        <b>회원정보 수정</b>
                     </button>
                     <button class="w-50 btn btn-primary btn-lg" type="button" onclick="deleteUser()">
                        <b>회원 탈퇴</b>
                     </button>
                  </div>
                  <!-- 버튼 끝 -->
               </form>
               <!-- 사용자의 정보를 form 끝-->
               
            </div>
            <!-- 좌측 사용자의 상세한 정보 끝 -->
         </div>
      </main>
   </div>
   <!-- 컨테이너 끝 -->
   
   <script type="text/javascript">
      //회원정보 수정 전 비번 체크
      function updatePwdCheck() {
         //비밀번호를 입력받을 프롬프트 창 열기
         var inputPwd = prompt("현재 비밀번호를 입력하세요.");
         var originPwd = $('#userPwd').val(); //위 form안에서 hidden으로 숨기고있는 요소에서 현재 user의 비밀번호를 가져온다. 
         
         if(inputPwd != null){
            if(inputPwd === originPwd){ //회원의 기존 비밀번호와 프롬프트창에 입력한 비밀번호가 같다면
               return true;
            }else{ //다르다면
               alert('비밀번호가 일치하지 않습니다.')
               return false;
            }
         }else{ //비밀번호를 입력하지 않았을시
            alert('현재 비밀번호를 입력하세요.')
            return false;
         }   
      }   
   
      //회원탈퇴 함수
      function deleteUser() {
         
         var ans = confirm("정말 탈퇴하시겠습니까?");
         if(!ans){return false;}
         
         //비밀번호를 입력받을 프롬프트 창 열기
         var inputPwd = prompt("현재 비밀번호를 입력하세요.");
         var originPwd = $('#userPwd').val(); //위 form안에서 hidden으로 숨기고있는 요소에서 현재 user의 비밀번호를 가져온다. 
         
         //현재 user의 비번과 프롬프트에서 입력한 비번이 같아야 탈퇴 진행.
         if(inputPwd != null){
            if(inputPwd === originPwd){ //같다면
               //UserDeleteServlet으로 이동하도록
               //action 속성을 해당 경로로 바꿔준다. 
               location.href = "<%= contextPath%>/deleteUser.do?userId=<%= loginUser.getUserId() %>"
               //submit() 해주기(해당 탈퇴 버튼은 type=button으로 되어있으므로 form안의 데이터가 넘어가지 않음)
            }else if(inputPwd != originPwd){
               alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요.');
            }
         }else{
            alert('회원 탈퇴를 취소하였습니다.');
         }
            
      }
      
      //강아지 정보 상세보기 함수
      function goDetailDog() {
         var userNo = $('#userNo').val();
         //강아지 상세보기 페이지로 이동(이동시 해당 로그인유저의 회원번호를 쿼리스트링으로 전달~)
         location.href="<%= contextPath%>/detailDogPage.do?userNo="+userNo;
      }
      
      //회원의 간식구매내역 확인 함수
       function goUserSnackOrder(){ 
          var userNo = $('#userNo').val();
          location.href="<%=contextPath%>/userOrderListForm.do?userNo="+userNo; 
        }
       
   </script>

   <!-- footer -->
   <%@ include file="../common/footer.jsp"%>

</body>
</html>
