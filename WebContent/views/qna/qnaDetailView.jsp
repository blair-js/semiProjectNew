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

	<h2><%= "qna detail 성공~!" %></h2><br>
	<button onclick="location.href='<%=contextPath%>/updateFormQna.do'">수정하기</button><br><br>
	
	<button onclick="location.href='<%=contextPath%>/deleteQna.do'">삭제하기</button>
	
	 <%@ include file="../common/footer.jsp" %>
</body>
</html>