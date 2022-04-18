<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//LoginFormServlet에서 넘어옴
	//값이 있으면 그 userId를 넣어주고, 없으면 빈문자열""
	String userId = (String)request.getAttribute("userId") != null ? (String)request.getAttribute("userId") : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="821072282485-pafufg8p84rr1e1aqd9im0ut4gcueep8.apps.googleusercontent.com">
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
				<input type="checkbox" id= "input_check_Yes" name="input_check" value="Y">&nbsp; 아이디 저장 (7일간 유지)
				<input type="hidden" id= "input_check_No" name="input_check" value="N" checked>
			</div>
			
			<!-- 로그인 버튼 -->
			<button type="submit" class="w-100 btn btn-lg btn-primary mb-2"><b>로그인</b></button>
			<!-- 구글 로그인 버튼 -->
			<!-- onSuccess : 데이터 발행 및 수신 성공을 확인하는 속성 -->
				<div class="g-signin2 btn px-0" data-width="300" data-height="45" data-onsuccess="onSignIn" data-longtitle="true" data-theme="dark"></div>
				<!-- 구글 로그인 취소 버튼 -->
				<!-- 0411 삭제_원하는 데이터만 저장 후 커넥션은 바로 끊어줄 예정 -->
				<!-- <button class="btn btn-primary" type="button" onclick="signOut();">Google Sign Out</button> -->
			
			<!-- 아이디,비번찾기 및 회원가입 링크 -->
			<div class="btns mt-3" align="center">
				<a href="<%=request.getContextPath() %>/findIdForm.do">아이디찾기&nbsp;</a> 
				<a href="<%=request.getContextPath() %>/findPwdForm.do">비밀번호찾기&nbsp;</a> 
				<a href="<%=request.getContextPath() %>/userEnrollForm.do">회원가입</a>
			</div>
			
		</form>
		<!-- 로그인 양식 form 끝 -->

	</main>
		
	<script type="text/javascript">
		//ready() : 문서가 준비되면 매개변수로 넣은 콜백 함수를 실행하라는 의미 
		//jQuery 이벤트 메서드 중 하나이다.
	    $(document).ready(function(){
	    
		    //userId로 저장된 쿠기값 가져오기
			var userInputId = getCookie("userId"); 
			
		    //그 쿠키값을 name이 userId인 요소의 값으로 넣어준다.
		    $("input[name='userId']").val(userInputId); 
			 
		 	//처음 페이지 로딩시 입력칸에 저장된 id가 표시된 상태라면(위의 요소에 아이디가 자동으로 입력되어있으면) => 즉 입력칸이 비어있지 않다면
			if($("input[name='userId']").val() != ""){ 
			    $("#input_check_Yes").attr("checked", true); //아이디 저장하기를 체크 상태로 두기.
			}
			
		 	//아이디 저장하기에 변화 발생시 
			$("#input_check_Yes").change(function(){ 
				//체크상태일때
			    if($("#input_check_Yes").is(":checked")){ 
			        var input_check = $("input[name='userId']").val();
			        setCookie("userId", userInputId, 7); //7일 동안 쿠키 보관
			    }else{ //아이디 저장하기를 체크 해제한다면
			        deleteCookie("userId"); //쿠키 삭제
			    }
			});
			 
			//아이디 저장하기를 체크한 상태에서, 다시 아이디를 입력하는 경우, 이럴 때도 쿠키가 저장되어야 한다.
			$("input[name='userId']").keyup(function(){ //아이디 입력칸에 아이디를 입력할 때(keyup메소드로 키보드입력 감지)
			    if($("#input_check_Yes").is(":checked")){ //아이디저장하기를 체크했다면
			        var userInputId = $("input[name='userId']").val(); //다시 그 아이디의 값을 받아서
			        setCookie("userId", userInputId, 7); //쿠키이름중 userId로 7일 동안 쿠키 보관(쿠키 업데이트 느낌. 덮어쓰기.)
			    }
			});
		});
		
		//쿠키를 등록(저장)하는 함수
		function setCookie(cookieName, value, exdays){
			
			//현재 날짜와 시간으로 객체를 생성해주는 Date
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + exdays);
			
			//escape(value)는 한글깨짐을 막기위한 것이라고 한다. 
			var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
			document.cookie = cookieName + "=" + cookieValue;
		}
		
		//쿠키를 삭제하는 함수
		function deleteCookie(cookieName){
			var expireDate = new Date();
			
			//어제날짜를 소멸날짜로 설정
			expireDate.setDate(expireDate.getDate() - 1);
			document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
		}
		
		//쿠키를 가져오는 함수
		function getCookie(cookieName) {
			
			cookieName = cookieName + '=';
			
			var cookieData = document.cookie;
			var start = cookieData.indexOf(cookieName);
			var cookieValue = '';
			if(start != -1){
			    start += cookieName.length;
			    var end = cookieData.indexOf(';', start);
			    if(end == -1)end = cookieData.length;
			    cookieValue = cookieData.substring(start, end);
			}
			
			return unescape(cookieValue);
		}
		
		//구글에서 가져온 데이터를 갖고 로직을 처리하는 함수
		function onSignIn(googleUser) {
	        //구글 api를 통해 얻어온 값들 확인하여, 사용하고자 하는 데이터(이름, 이메일)는 아래에서 추출하여 변수에 담을 예정.
	        var profile = googleUser.getBasicProfile();
	        console.log("ID: " + profile.getId()); // Don't send this directly to your server!(우리의 서버에 이것을 직접 보내지말것!)
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
	        
	        //구글 api와 연결된 커넥션을 끊어야 하므로, 함수 호출(함수는 아래 만들어놓음)
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
			
			//위의 두 if 조건문을 통과했다면 true를 반환하며, form안의 데이터가 서블릿으로 submit이 되고 로그인 수행 시작
			return true;
		}
		
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>