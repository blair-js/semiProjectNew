<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			              	<form action="<%=contextPath %>/insertUser.do;" method="post">
				                <!-- 아이디 입력 -->
				                <div class="form-floating mb-3">
				                 	<input type="text" class="form-control" id="userId" name="userId" value="jisu123" placeholder="" required>
				                  	<label for="userId">Id</label>
					              <!-- 중복확인 버튼 : 아래의 스크립트에서 함수 실행(ajax 이용) -->
					              <button class="ckBtn mt-1" type="button" id="idCheckBtn" onclick="checkId();">아이디 중복확인</button>
				                </div>
				                <!-- 패스워드 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="password" class="form-control" id="userPwd" name="userPwd" value="jisu123" placeholder="" required>
				                  <label for="userPwd">Password</label>
				                </div>
				                <!-- 이름 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="text" class="form-control" id="userName" name="userName" value="김지수" placeholder="" required>
				                  <label for="userName">Name</label>
				                </div>
				                <!-- 전화번호 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="text" class="form-control" id="phone" name="phone" value="010-1111-2222" placeholder="" required>
				                  <label for="phone">Phone</label>
				                </div>
				                <!-- sms 수신동의여부 -->
					            <div class="mb-3"> &nbsp; SMS 수신 동의 &nbsp;
						            <input type="radio" name="chk_info" value="Y" checked> 예 &nbsp;
						            <input type="radio" name="chk_info" value="N"> 아니오
					            </div>
					            <!-- 이메일 입력 -->
				                <div class="form-floating mb-3">
				                  <input type="email" class="form-control" id="userEmail" name="userEmail" value="ji@su.com" placeholder="" required>
				                  <label for="userEmail">Email</label>
				                </div>
				                <!-- 성별 입력 -->
				                <div class="form-floating mb-3">
					                <select class="field form-dropdown form-control" id="gender" name="gender">
										<option value="M">남자</option>
										<option value="F">여자</option>
									</select>
				                	<label for="gender">Gender</label>
				                </div>
				                <!-- reset, submit 버튼 두개 -->
				                <div class="row form-group">
					               <div class="col-md-12 text-center pb-5 mt-5">
					                  <input type="reset" value="다시 작성" class="btn btn-lg btn-success">
					                  <input type="submit" value="회원 가입" class="btn btn-lg btn-info">
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
			alert('일단 사용 가능')
		}
 		//이메일 중복확인
 		function checkEmail() {
			
		}
 	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>