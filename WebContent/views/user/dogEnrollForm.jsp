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
	<h1>DogEnrollFormServlet에서 여기로 옴</h1>
	
	<button onclick="goDogEnroll()">
		입학신청하기
	</button>
	
	<script type="text/javascript">
		function goDogEnroll() {
			location.href="<%= request.getContextPath()%>/insertDog.do;"
		}
	</script>
	
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>