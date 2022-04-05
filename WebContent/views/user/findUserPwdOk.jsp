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

.form-findId {
	width: 100%;
	max-width: 330px;
	padding: 5px;
	margin: auto;
	margin-bottom: 100px;
	margin-top: 50px;
}

.form-findId .checkbox {
	font-weight: 400;
}

.form-findId input[type="text"] {
	margin-bottom: 1px;
}

.form-findId input[type="password"] {
	margin-bottom: 8px;
}

.rows {
	text-align: center;
}

.btn {
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}
main form div b{
	 color: #0d6efd;
}
main .btns{
	border-color: #0d6efd;
}
.btns b{
	color: #0099FF; 
}
main form{
	 text-align: center; 
	 color: gray;
}
.hr{
	 background-color: #0099FF;
}
</style>
</head>
<body>
	<!-- UserPwdFindServlet에서 여기로 옴 -->
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	<!-- 위 div 시작 -->
	<div class="row rows mt-5">
		<!-- 빈 div -->
		<div class="col-lg-2"></div>
		<!-- 아이디 찾기 -->
		<div class="col-lg-4">
			<h3 style="color: #DDDDDD"><a href="<%=contextPath%>/findIdForm.do;">아이디 찾기</a></h3>
			<hr class="pd-0" style="height: 7px; background-color: #FFFFFF">
		</div>
		<!-- 비밀번호 찾기 -->
		<div class="col-lg-4">
			<h3>비밀번호 찾기</h3>
			<hr style="height: 7px; background: #0099FF">
		</div>
		<!-- 빈 div -->
		<div class="col-lg-2"></div>
	</div>
	<!-- 위 div 끝 -->
	
	<!-- 아래 div 시작 -->
	<main class="form-findId">
		<form>
			<div class="mb-2">
				<b>김지수</b>고객님의<br> 비밀번호 찾기가완료되었습니다.<br>
			</div>
			<div class="mb-4">
				비밀번호는<br><b>jisu****123</b> 입니다.
			</div>
			<!-- 로그인으로 돌아가는 버튼 -->
			<button type="button" class="w-100 btn btns btn-lg mb-2" onclick="goLogin()">
				<b>로그인 화면으로 돌아가기</b>
			</button>
		</form>
	</main>
	<!-- 아래 div 끝 -->

	<!-- 아이디찾기 버튼 클릭시 UserIdFindServlet 로 이동 -->
	<script type="text/javascript">
		function goLogin() {
			location.href="<%= request.getContextPath()%>/loginForm.do;"
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>