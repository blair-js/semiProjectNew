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
	UserFindIdFormServlet에서 넘어옴
	<h1>이름, 이메일 입력받고 </h1>
	<button onclick="goFindId()">
		아이디찾기
	</button>
	
	<!-- 아이디찾기 버튼 클릭시 UserIdFindServlet 로 이동 -->
	<script type="text/javascript">
		function goFindId() {
			location.href="<%= request.getContextPath()%>/findId.do;"
		}
	</script>
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>