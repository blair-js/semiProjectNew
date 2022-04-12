<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.dao.UserDao" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style type="text/css">
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

	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<div class="row mt-5 mb-1">
		<!-- 비어있는 div -->
		<div class="col-md-3"></div>
		<!-- 인증메일요청내용 -->
		<div class="col-md-6 text-center">
			<div class="">
				<img class="col-md-3 mb-2" src="assets/img/gallery/okSign.png" alt="오케이로고" style="width: 150px">
			</div>
			<h2>이메일 인증이 완료되었습니다.</h2>
		</div>
		<!-- 비어있는 div -->
		<div class="col-md-3"></div>
	</div>

	<!-- 주의사항 div -->
	<div class="container mt-3 mb-5" style="max-width: 560px;">
		<div class="alert text-center" role="alert">
			<span class="warn"><b>WELCOME</b></span>
			<br>회원가입을 축하합니다.<br> 로그인 후 서비스를 이용해주세요.<br>
			<div class="col-md-12 mt-3 text-center">
				<button class="w-100 btn btn-lg mb-2 btns" onclick="goLogin()">
					<b style="color: white;">로그인</b>
				</button>
			</div>
		</div>
	</div>
	<!-- 주의사항 div 끝 -->

	<script type="text/javascript">
	
		$(function() {
			alert('이메일 인증이 완료되었습니다. 로그인 후 서비스를 이용해주세요.')
		})
		
		//로그인 함수
		function goLogin() {
			location.href="<%= request.getContextPath()%>/loginForm.do;"
		}
	</script>
	
	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>