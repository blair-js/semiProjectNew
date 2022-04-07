<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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

.test {
	background-color: bg-primary;
}

.ckBtn {
	border: 1px solid gray;
	border-radius: 5px;
	color: gray;
}

.rows{
	background-color: #F0F4F9;
}
.login {
  min-height: 80vh;
}

.bg-image {
  background-image:	url("assets/img/gallery/enrollDog.jpg");
  background-size: 500 500;
  background-position: center;
}

.login-heading {
  font-weight: 300;
}

.btn-login {
  font-size: 0.9rem;
  letter-spacing: 0.05rem;
  padding: 0.75rem 1rem;
}
.forms{
	background-color: #F0F4F9;
	background-color: #FDC800;
}
.title{
	text-align: center;
}
div h5{
	color: gray;
}
div h5 a{
	color: #0099FF;
}
</style>
</head>
<body>
	<!-- UserEnrollFormServlet에서 여기로 옴 -->
	<!-- UserInsertServlet으로 데이터를 보낼 것 -->

	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	<div class="title mt-7 mb-3">
		<h2>회원가입</h2>
	</div>
	
	<div class="title mb-3">
		<h5>
			이미 둥글개 둥글개 회원이신가요?<a href="<%=contextPath %>/loginForm.do;"> 로그인</a>
		</h5>
	</div>
	
	<!-- 큰 컨테이너 시작 -->
	<div class="container mb-10">
  		<div class="row">
  			<!-- 강아지 이미지 -->
    		<div class="d-none d-md-flex col-md-5 bg-image">
    		
    		</div>
    		
    		<!-- 회원가입 정보 작성 영역 -->
   			<div class="col-md-7 forms">
   				<div class="login d-flex align-items-center">
   					 <!-- 작은 컨테이너 시작 -->
   					 <div class="container">
   					 	  
   					 	  <!-- row 시작 -->
     					  <div class="row">
         					<div class="col-md-9 mx-auto">
   								<h3 class="login-heading mb-4">Welcome Dogg World !</h3>


			              	<!-- 회원가입 양식 시작(우측) -->
			              	<form action="<%=contextPath %>/insertUser.do;" id="enrollForm" method="post" onsubmit="return enrollValidate();">
				                <!-- 아이디 입력 -->
				                <div class="form-floating mb-3">
				                 	<input type="text" class="form-control" id="userId" name="userId" required>
				                  	<label for="userId">Id</label>
					              <!-- 중복확인 버튼 : 아래의 스크립트에서 함수 실행(ajax 이용) -->
					              <button class="ckBtn mt-1" type="button" id="idCheckBtn" onclick="checkId();">아이디 중복확인</button>
				                </div>
				                <!-- 패스워드 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="password" class="form-control" id="userPwd" name="userPwd" required>
				                  <label for="userPwd">Password</label>
				                </div>
				                <!-- 이름 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="text" class="form-control" id="userName" name="userName" required>
				                  <label for="userName">Name</label>
				                </div>
				                <!-- 전화번호 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="text" class="form-control" id="phone" name="phone" required>
				                  <label for="phone">Phone</label>
				                </div>
				                <!-- sms 수신동의여부 -->
					            <div class="mb-3"> &nbsp; SMS 수신 동의 &nbsp;
						            <input type="radio" name="chk_sms" value="Y" checked> 예 &nbsp;
						            <input type="radio" name="chk_sms" value="N"> 아니오
					            </div>
					            <!-- 이메일 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="email" class="form-control" id="userEmail" name="userEmail" required>
				                  <label for="userEmail">Email</label>
				                </div>
				                <!-- 성별 입력 -->
				                <div class="form-floating mb-3">
					                <select class="field form-dropdown form-control" id="gender" name="gender">
										<option value=" ">선택</option>
										<option value="M">남자</option>
										<option value="F">여자</option>
									</select>
				                	<label for="gender">Gender</label>
				                </div>
				                <!-- reset, submit 버튼 두개 -->
				                <div class="row form-group">
					               <div class="col-md-12 text-center pb-5 mt-5">
					                  <input type="reset" value="다시 작성" class="btn btn-lg btn-success">
					                  <!-- 위에서 아이디 중복확인 후 ok되어야만 disabled 속성이 풀려서 회원가입이 진행된다. -->
					                  <input type="submit" id="joinBtn" value="회원 가입" class="btn btn-lg btn-info" disabled>
					               </div>
					            </div>
				                <!-- 버튼 두개 끝 -->
   							</form>
        					<!-- 회원가입 양식 끝(우측) -->
        					
        					
        				</div>
       				</div>
       				<!-- row 끝 -->
     			</div>
     			<!-- 작은 컨테이너 끝 -->
     			
		      </div>
		    </div>
		    <!-- 회원가입 정보 작성 영역 끝 -->
		    
		  </div>
		</div>
		<!-- 큰 컨테이너 끝 -->

	<script>
		//아이디 중복확인
		function checkId() {
			//아이디가 enrollForm인 것의 자식 중 input에서 name이 userId인 요소 선택(**값이 들어간 것이 아님에 주의**)
			//값은 여기서 담지 않고 아래의 if문을 통과한 후 
			//ajax 내의 데이터 넣어줄 것 
			var userId = $("#enrollForm input[name=userId]");
			
			//만약 아이디를 입력하지 않고 중복확인 버튼을 누르면 안되므로 
			if(userId.val() == ""){
				alert('아이디를 입력해주세요.')
				return false;
			}
			
			//위의 if 조건문을 통과했다면
			$.ajax({
				
				url: "idCheck.do",
				
				type: "post",
				
				data: {
					userId: userId.val()
				},
				
				success:function(result){
					//결과(result)에 따라 alert
					if(result == "fail"){
						alert('사용이 불가능한 아이디입니다.');
						userId.focus();
					}else{
						//fail이 아닌경우
						if(confirm('사용이 가능한 아이디입니다. 사용하시겠습니까?')){
						//예 선택시 
						userId.attr("readonly", "true"); //readonly 속성 추가
						$('#joinBtn').removeAttr("disabled"); //disabled 속성을 지운다
						}else{ //confirm창엥서 아니오 선택시
							userId.focus(); //다시 아이디 입력창에 focus
						}
					}//if~else
				},
				
				error:function(){
					console.log('Ajax통신실패')
				}
				
			})
	}
	
		//회원가입 양식 유효성검사(아이디는 대소문자숫자로만, 이름은 한글로 두자리이상만)
		function enrollValidate() {
			//아이디 검사
			if(!(/[a-zA-Z0-9]/.test($("#enrollForm input[name=userId]").val()))){
				alert('아이디는 영어 대/소문자와 숫자만 가능합니다.')
				$("#enrollForm input[name=userId]").focus();
		        return false;
			}
			
			//비밀번호 검사
			if(!(/[a-zA-Z0-9]/.test($("#enrollForm input[name=userPwd]").val()))){
				alert('비밀번호는 영어 대/소문자와 숫자만 가능합니다.')
				$("#enrollForm input[name=userPwd]").focus();
		        return false;
			}
			
			//이름 검사
			if(!(/^[가-힣]{2,}$/.test($("#enrollForm input[name=userName]").val()))){
				 alert('이름은 한글로 2자이상 기재해주세요.')
				 $("#enrollForm input[name=userName]").focus();
		        return false;
			}
			//위의 조건을 모두 통과했다면 true 반환
			return true;
		}
 		
 	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>