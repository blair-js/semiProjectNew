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

.form-findPwd {
	width: 100%;
	max-width: 330px;
	padding: 5px;
	margin: auto;
	margin-bottom: 100px;
	margin-top: 50px;
}

.form-findPwd .checkbox {
	font-weight: 400;
}

.form-findPwd .form-floating:focus-within {
	z-index: 2;
}

.form-findPwd input[type="text"] {
	margin-bottom: 1px;
}

.form-findPwd input[type="password"] {
	margin-bottom: 1px;
}

.rows {
	text-align: center;
}

h5 {
	text-align: center;
}
main form button b{
	color: #0099FF;
}
.hr1{
	background-color: #FFFFFF;
}
.hr2{
	background-color: #0099FF;
}
</style>
</head>
<body>
	<!-- UserPwdFindFormServlet에서 여기로 넘어옴 -->
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<!-- 위 div 시작 -->
	<div class="row rows mt-5">
		<!-- 빈 div -->
		<div class="col-lg-2"></div>
		<!-- 아이디찾기 div -->
		<div class="col-lg-4">
			<h3 style="color: #DDDDDD">
				<a href="<%=contextPath%>/findIdForm.do;">아이디 찾기</a>
			</h3>
			<hr class="hr1" style="height: 7px;">
		</div>
		<!-- 비밀번호찾기 div -->
		<div class="col-lg-4">
			<h3>비밀번호 찾기</h3>
			<hr class="hr2" style="height: 7px;">
		</div>
		<!-- 빈 div -->
		<div class="col-lg-2"></div>
	</div>
	<!-- 위 div 끝 -->
	
	<!-- 아래 아이디, 이메일, 비밀번호 작성 영역 시작 -->
	<main class="form-findPwd">
		<form>
			<h5 class="h5 mb-2 fw-lighter" style="font-family: 'LeferiPoint-BlackA'; color: gray">비밀번호를잊으셨나요?</h5>
			<h5 class="h5 mb-4 fw-lighter" style="font-family: 'LeferiPoint-BlackA'; color: gray">아래의 정보를입력해주세요.</h5>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력하세요.">
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="비밀번호를 입력하세요.">
			</div>
			<div class="form-floating">
				<input type="email" class="form-control mb-4" id="userEmail" name="userEmail" placeholder="이메일 주소를 입력하세요.">
			</div>
			<button type="button" class="w-100 btn btn-lg btn-primary mb-2" onclick="goFindPwd()">
				<b>비밀번호 찾기</b>
			</button>
		</form>
	</main>

	<!-- 아이디찾기 버튼 클릭시 UserPwdFindServlet 로 이동 -->
	<script type="text/javascript">
		function goFindPwd() {
			location.href="<%= request.getContextPath()%>/findPwd.do;"
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>