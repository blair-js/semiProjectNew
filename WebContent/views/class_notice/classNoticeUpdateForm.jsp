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
	<h3>상세조회 후 수정하기 눌렀을때 화면</h3>
	<h4>수정하기 누르면 여기 정보 서블릿으로 보내줘야 한다</h4>
	<button onclick="location.href='classNoticeUpdate.do'">수정완료</button>
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>