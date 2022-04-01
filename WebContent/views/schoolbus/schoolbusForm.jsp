<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
  		<h2>통학버스 신청하는 화면</h2> <br>
  		<h2>토글 목록으로 조회해서 선택 후 신청하면 저장</h2>
  		<button onclick="location.href='schoolbusInsert.do'">신청하기</button>
  	<%@ include file = "../common/footer.jsp" %>
</body>
</html>