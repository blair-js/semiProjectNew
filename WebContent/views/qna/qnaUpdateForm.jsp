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
	
	<h2><%="QnA UpdateForm!!!"%></h2>
	
	<button onclick="location.href='<%=contextPath%>/updateQna.do'">수정하기 완료</button><br><br>	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>