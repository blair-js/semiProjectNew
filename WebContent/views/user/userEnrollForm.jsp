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
	<h1>회원가입 양식 보임~</h1>
	<h1>여기서 버튼 클릭시 UserInsertServlet으로 데이터를 보냄!</h1>
	
	<button onclick="goEnroll()">
		회원가입하기
	</button>
	
	<script type="text/javascript">
		function goEnroll() {
			location.href="<%= request.getContextPath()%>/insertUser.do;"
		}
	</script>
	
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>