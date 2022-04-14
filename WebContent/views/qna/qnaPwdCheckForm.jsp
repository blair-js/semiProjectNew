<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.qna.model.dto.*"%>
<%
	int qno = (Integer)request.getAttribute("qno");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
		
	<h1 align="center" class="text-primary p-6">Q&A</h1>
	
	<h2 align="center" class="text-blue p-5">비밀번호 확인</h2>
	
	<div class="container-fluid row justify-content-center">
		<form class="col-sm-3 center" method="post" action="<%=contextPath%>/pwdCheckQna.do">
			<input name="qno" value="<%=qno%>" type="hidden">
			<input type="password" class="form-control m-3" placeholder="비밀번호를 입력하세요." name="pwd">
			<button type="submit" class="w-100 btn btn-primary m-3 mb-6"><b>확인</b></button>
		</form>
	</div>	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>