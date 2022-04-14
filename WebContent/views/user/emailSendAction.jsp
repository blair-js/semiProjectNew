<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.dao.UserDao"%>
<%
	String userEmail = request.getParameter("userEmail");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style type="text/css">
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
.alert{
	background-color: #f7f7f9;
}
div .btns{
	background-color: #0099FF;
}
div h5{
	color: #0099FF;
}
.send b{
	color: #0099FF;
}
div .warn b{
	color: #0099FF;
	font-size: 20px;
}
</style>
</head>
<body>

	<!-- UserInsertServlet에서 회원가입 성공시 여기로 옴 -->
	<!-- 사용자의 이메일로 이메일이 발송되도록 한다. -->

	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<div class="row mt-5">
		<!-- 비어있는 div -->
		<div class="col-md-3"></div>
		<!-- 인증메일요청내용 -->
		<div class="col-md-6 text-center">
			<div class="">
				<img class="col-md-3 mb-2" src="assets/img/gallery/email.png" alt="이메일로고" style="max-width: 560px">
			</div>
			<h2>인증 메일이 발송되었습니다.</h2>
			<div class="mt-3 send">
				<b>${requestScope.userEmail }</b>&nbsp;에서 인증 메일을 확인 바랍니다.<br>
				이메일에서 인증하기를 클릭하시면 인증이 완료됩니다.
			</div>
		</div>
		<!-- 비어있는 div -->
		<div class="col-md-3"></div>
	</div>

	<!-- 주의사항 div -->
	<div class="container mt-4 mb-5" style="max-width: 560px;">
		<div class="alert text-center" role="alert">
			<span class="warn"><b>주의사항</b></span>
			<br>이메일 인증이 정상적으로 이루어져야<br> 둥글개 둥글개의 서비스를 이용하실 수 있습니다.<br>
			<div class="col-md-12 mt-3 text-center">
				<button class="w-100 btn btn-lg mb-2 btns" onclick="goLogin()">
					<b style="color: white;">로그인</b>
				</button>
			</div>
		</div>
	</div>
	<!-- 주의사항 div 끝 -->

	<script type="text/javascript">
		//로그인 버튼 클릭시 실행되는 함수
		function goLogin() {
			location.href="<%= request.getContextPath()%>/loginForm.do;"
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>