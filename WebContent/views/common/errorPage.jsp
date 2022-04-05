<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@font-face {
	font-family: 'LeferiPoint-BlackA';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'LeferiPoint-BlackA';
}
.btn{
	width: 30%;
	border-radius: 10px;
}
.b1{
	color: #0099FF;
}
</style>
</head>
<body>

	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	<h2 class="mt-7" align="center"><%= message %></h2>
	<div class="mb-5" align="center">
		<button class="btn btn-primary mt-3" onclick = "location.href='<%= request.getContextPath()%>'">
			<b class="b1">홈으로 돌아가기</b>
		</button>
	</div>
	
	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
</body>
</html>