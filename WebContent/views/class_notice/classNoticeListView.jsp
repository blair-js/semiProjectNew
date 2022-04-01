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
	
	<h2>반별 게시판 목록</h2>
	<h3>작성하기 누르면 작성하기 화면으로 전환</h3>
	<h3>상세조회 버튼 클릭시 게시물 화면으로 전환</h3>
	<button onclick="location.href='classNoticeEnrollForm.do'">작성하기</button>
	<button onclick="location.href='classNoticeDetail.do'">게시물 클릭</button>
	
   <%@ include file = "../common/footer.jsp" %>
	
</body>
</html>