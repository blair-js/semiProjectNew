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

<h1> 간식 수정 페이지 </h1>

<button onclick="goSnackUpdate()">
		간식 수정
	</button>
	 

	 <script>
	 
	 		//서블릿 잘 다녀오는지 테스트차 만들어봄
			function goSnackUpdate(){ 
				location.href="<%= request.getContextPath()%>/snackUpdate.do;" 	
			}	
	 		
		

	</script>
	  <%@ include file = "../common/footer.jsp" %>
</body>
</html>