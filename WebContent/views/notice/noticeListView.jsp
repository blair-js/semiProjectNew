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

	<h2><%= "notice list 성공~!" %></h2><br>
	<button onclick="location.href='<%=contextPath%>/enrollFormNotice.do'">작성하기</button><br><br>
	
	<button onclick="location.href='<%=contextPath%>/detailNotice.do'">detail 글 보기</button>
	
	 <%@ include file="../common/footer.jsp" %>
</body>
</html>