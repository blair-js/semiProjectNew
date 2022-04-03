<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 간식 삭제 하는곳 여기서 디테일 또 들어가야함 -->


<%@ include file = "../common/menubar.jsp" %>

<h1> 간식 삭제 페이지 </h1>


<button onclick="goSnackDelete()">
		간식 삭제
	</button>
	 

	 <script>
	 
	 		//서블릿 잘 다녀오는지 테스트차 만들어봄
			function goSnackDelete(){ 
				location.href="<%= request.getContextPath()%>/snackDelete.do;" 	
			}	
	 		
		

	</script>
	  <%@ include file = "../common/footer.jsp" %>
</body>
</html>