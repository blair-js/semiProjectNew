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

<h1> 간식 추가 페이지 </h1>

<button onclick="goSnackInsert()">
		간식 추가
	</button>
	 

	<script>
	function goSnackInsert(){ 
				location.href="<%= request.getContextPath()%>/snackInsert.do;" 	
			}	
	</script>	
	
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>