<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <%@ include file = "../common/menubar.jsp" %>
	<h1>아이디 찾기 완료!!!</h1>
	
	<button onclick="goLogin()">
		로그인 화면으로 돌아가기
	</button>
	
	<!-- 아이디찾기 버튼 클릭시 UserIdFindServlet 로 이동 -->
	<script type="text/javascript">
		function goLogin() {
			location.href="<%= request.getContextPath()%>/loginForm.do;"
		}
	</script>
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>