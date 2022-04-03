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

<h1> 간식 구매내역 페이지 </h1>

	<h1>회원 1     뭐뭐 구매    구매일자</h1>
	
	<h1>회원 2     뭐뭐 구매    구매일자</h1>


<button onclick="goSnackOrderList()">
		간식 구매내역
	</button>
	 

	<script>
	function goSnackOrderList(){ 
				location.href="<%= request.getContextPath()%>/snackOrderList.do;" 	
			}	
	</script>	


 <%@ include file = "../common/footer.jsp" %>

	
</body>
</html>


