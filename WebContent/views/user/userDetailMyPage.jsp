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
	<h1>회원의 상세정보 </h1>
	이름
	나이
	
	간식 구매내역?
	
	
	<button onclick="updateUser()">
		회원정보수정
	</button>
	
	<button onclick="deleteUser()">
		회원탈퇴
	</button>
	
	<script type="text/javascript">
		function updateUser() {
			location.href="<%= request.getContextPath()%>/updateUser.do;"
		}
		
		function deleteUser() {
			location.href="<%= request.getContextPath()%>/deleteUser.do;"
		}
	</script>
	<%@ include file = "../common/footer.jsp" %>	
</body>
</html>