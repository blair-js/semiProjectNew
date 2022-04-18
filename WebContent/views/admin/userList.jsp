<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
</head>
<body>

<%@ include file = "../common/menubar.jsp" %>	

<h1> 회원 조회 페이지 (나만먹을개 구현 후 만들 페이지 중요도★)</h1>


	<h2>회원1</h2>
	<h2>회원2</h2>
	<h2>회원3</h2>



<button onclick="goUserList()">
		회원 삭제
	</button>
	 

	<script>
	function goUserList(){ 
				location.href="<%= request.getContextPath()%>/userList.do;" 	
			}	
	</script>	
	 <%@ include file = "../common/footer.jsp" %>
</body>
</html>

