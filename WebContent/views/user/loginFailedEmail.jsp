<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = (String)request.getAttribute("userId");
	String reason = (String)request.getAttribute("reason");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.form-failedEmail {
	width: 100%;
	max-width: 600px;
	padding: 5px;
	margin: auto;
	margin-bottom: 100px;
	margin-top: 50px;
	text-align: center;
}
.form-failedEmail .checkbox {
	font-weight: 400;
}

.form-failedEmail .form-floating:focus-within {
	z-index: 2;
}

.rows {
	text-align: center;
}

h5 {
	text-align: center;
	color: gray;
}
main form button b{
	color: black;
}
.hr1{
	background-color: #0099FF;
}
.hr2{
	 background: #FFFFFF;
}

.btnDiv{
	align-content: center;
}
#sendEmail{
	background-color: #f7f7f9;
	border-radius: 5px;
	padding: 2px 2px;
}
.reasonH5 b{
	color: red;
}
</style>
</head>
<body>

	<!-- LoginFailedEmailServlet에서 여기로 옴 -->

	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<!-- 아래 div 시작 -->
	<main class="form-failedEmail">
		<form>
			<input id="userId" type="hidden" value=<%=userId %>>

			<div class="">
				<img class="col-md-3 mb-3" src="assets/img/gallery/errorSign.png" alt="에러로고" style="max-width: 560px">
			</div>
			<div id="sendEmail" class="mt-3">
			
				<div class="">
					<h3 class="mt-4 mb-1">로그인에 실패하였습니다.</h3>
					<h5 class="mt-0 mb-4 reasonH5">실패 사유 : <b><%=reason %></b> </h5>
				</div>
				<!-- 로그으로 돌아가기 버튼 -->
				<div class="btnDiv">
					<button type="button" class="w-50 btn btns btn-lg mb-4 bg-primary" onclick="goSendEmail()">
						<b>인증메일 다시받기</b>
					</button>
				</div>
				
			</div>
		</form>
	</main>
	<!-- 아래 div 끝 -->
	
	<!-- 인증메일 다시받기 버튼 클릭시 EmailSendServlet 로 이동 -->
	<script type="text/javascript">
		function goSendEmail() {
			var userId = $('#userId').val();
			location.href="<%= contextPath %>/sendEmail.do?userId="+userId;
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>