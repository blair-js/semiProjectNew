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
	
	<h3>게시물 상세조회 페이지</h3>
	<h4>수정하기, 삭제하기 버튼 생성</h4>
	<button onclick="location.href='classNoticeUpdateForm.do'">수정하기</button>
	<button onclick="location.href='classNoticeDelete.do'">삭제하기</button>
	
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>