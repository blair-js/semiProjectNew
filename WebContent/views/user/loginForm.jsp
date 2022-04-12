<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//값이 있으면 그 userId를 넣어주고, 없으면 빈문자열""
	String userId = (String)request.getAttribute("userId") != null ? (String)request.getAttribute("userId") : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="952213952257-qfucuv5qsicj7vihnm1kndfbqdseqpaa.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>

<title>둥글개 로그인</title>

<!-- 제이쿼리 사용하기 위해 CDN 연결 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>

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

.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
	background: #EEEEEE;
	border-radius: 10px;
	margin-bottom: 100px;
	margin-top: 100px;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-floating:focus-within {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: 1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 8px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
</head>

<body>
	<!-- LoginFormServlet에서 여기로 넘어옴 -->
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	<!-- main -->
	<main class="form-signin text-center">
		<!-- 로그인 양식 form 시작 -->
		<form action="<%= request.getContextPath() %>/login.do" method="post" onsubmit="return loginValidate();">
		<%-- onsubmit : form에서 데이터를 보내기 전에 데이터의 유효성을 체크해주는 속성이다. 해당 값이 true이어야만 데이터가 전송된다. --%> 
		<%-- 그래서 데이터의 유효성을 체크하기 위한 함수 loginValidate를 생성했다! --%> 
			<h2 class="h2 mb-2 mt-3 fw-normal" style="font-family: 'LeferiPoint-BlackA'">WELCOME</h2>
			<h6 class="h6 mb-0 fw-normal" style="font-family: 'LeferiPoint-BlackA'">둥글개 둥글개 유치원에 오신 걸 환영합니다.</h6>
			<h6 class="h6 mt-0 mb-3 fw-normal" style="font-family: 'LeferiPoint-BlackA'">회원 정보를 입력해 주세요.</h6>
			<img class="mb-4" src="assets/img/gallery/login2.png" alt="사용자로고" width="150">

			<div class="form-floating">
				<input type="text" class="form-control" id="userId" name="userId" value="<%=userId %>" placeholder="아이디를 입력하세요.">
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="userPwd" name="userPwd" value="" placeholder="비밀번호를 입력하세요.">
			</div>

			<div class="checkbox mb-3">
				<input type="checkbox" name="input_check" value="Y" id="input_check" >&nbsp; 로그인 상태 유지
				<input type="hidden" name="input_check" value="N" id="input_check_hidden" >
			</div>
			
			<!-- 로그인 버튼 -->
			<button type="submit" class="w-100 btn btn-lg btn-primary mb-2"><b>로그인</b></button>
			<!-- 구글 로그인 버튼 -->
			<!-- onSuccess : 데이터 발행 및 수신 성공을 확인하는 속성 -->
				<div class="g-signin2 btn px-0" data-width="300" data-height="45" data-onsuccess="onSignIn" data-longtitle="true" data-theme="dark"></div>
				<!-- 구글 로그인 취소 버튼 -->
				<!-- 0411_원하는 데이터만 저장 후 커넥션은 바로 끊어줄 예정 -->
				<!--<button class="btn btn-primary" type="button" onclick="signOut();">Google Sign Out</button> -->
			<!-- 빠른이동 아이디,비번찾기 및 회원가입 -->
			<div class="btns mt-3" align="center">
				<a href="<%=request.getContextPath() %>/findIdForm.do">아이디찾기&nbsp;</a> 
				<a href="<%=request.getContextPath() %>/findPwdForm.do">비밀번호찾기&nbsp;</a> 
				<a href="<%=request.getContextPath() %>/userEnrollForm.do">회원가입</a>
			</div>
			
		</form>
		<!-- 로그인 양식 form 끝 -->

	</main>
		
	
	<script type="text/javascript">
		
		//
		function onSignIn(googleUser) {
	        //구글 api를 통해 얻어온 값들 확인하여, 사용하고자 하는 데이터(이름, 이메일)는 아래에서 추출하여 변수에 담을 예정.
	        var profile = googleUser.getBasicProfile();
	        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
	        console.log('Full Name: ' + profile.getName());
	        console.log('Given Name: ' + profile.getGivenName());
	        console.log('Family Name: ' + profile.getFamilyName());
	        console.log("Image URL: " + profile.getImageUrl());
	        console.log("Email: " + profile.getEmail());
	
	        // The ID token you need to pass to your backend:
	        var id_token = googleUser.getAuthResponse().id_token;
	        console.log("ID Token: " + id_token);
	        
	        //1.사용자의 이름과 이메일을 저장해서 
	        var userName = profile.getName();
	        var userEmail = profile.getEmail();
	        
	        //구글 api와 연결된 커넥션을 끊어야 하므로, 함수 호출
	        signOut();
	        
	        //2.구글전용 로그인 서블릿으로 파라미터를 넘긴다.
	        location.href="<%=contextPath%>/googleLogin.do?userName="+userName+"&userEmail="+userEmail;
	         
	      }
		
		//구글 api와 연결된 커넥션을 끊어주는 함수
		function signOut() {
			gapi.auth2.getAuthInstance().disconnect();
		}
	
		//페이지 로드시 확인
		$(function () {
			//아이디가 input_check인 요소가 check 되었다면
			if($('#input_check').checked){
				//아이디가 input_check_hidden인 요소의 disabled 속성을 true로 대입
				$('#input_check_hidden').disabled = true;
			}
		})
	
		//로그인 폼에서 아이디와 비밀번호를 모두 기입해야만 true 반환하도록 한다.
		function loginValidate() {
			//아이디가 userId인 요소의 값의 길이가 0이라면(즉, 아이디를 입력하지 않았다면)
			if($('#userId').val().trim().length === 0){
				alert('아이디를 입력하세요.');
				$('#userId').focus();
				return false;
			}
			//아이디가 userPwd인 요소의 값의 길이가 0이라면(즉, 아이디를 입력하지 않았다면)
			if($('#userPwd').val().trim().length === 0){
				alert('비밀번호를 입력하세요.');
				$('#userPwd').focus();
				return false;
			}
			//위의 두 if 조건문을 통과했다면 true 반환하며 submit이 되고 로그인 성공!
			return true;
		}
		
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>