<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<h2><%="notice 등록하기~!" %></h2>
	<div class="btns" align="center">
		<button onclick="goInsert();">등록하기</button>				
	</div>
	
	<script>
	function goInsert(){
		location.href="<%=contextPath%>/insertNotice.do";
	}
	</script>
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>