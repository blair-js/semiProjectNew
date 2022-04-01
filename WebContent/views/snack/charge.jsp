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

	<h2><%= "charge form~!" %></h2><br>
	<button onclick="location.href='<%=contextPath%>/payment.do'">결제하기</button><br><br>
		
	 <%@ include file="../common/footer.jsp" %>
</body>
</html>