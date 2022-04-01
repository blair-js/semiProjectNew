<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	
   <%@ include file = "../common/menubar.jsp" %>
	
	
	
	<h1>아이디 비번 작성 폼이 열림~~~</h1>
	<h1>여기서 버튼 클릭시 LoginServlet으로 데이터를 보냄!</h1>
	
	<button onclick="goLogin()">
		로그인하기
	</button>
	
	<button onclick="goFindIdForm()">
		아이디찾기
	</button>
	
	<button onclick="goFindPwdForm()">
		비밀번호찾기
	</button>
	
	<script type="text/javascript">
		function goLogin() {
			location.href="<%= request.getContextPath()%>/login.do;"
		}
		
		function goFindIdForm() {
			location.href="<%= request.getContextPath()%>/findIdForm.do;"
		}
		
		function goFindPwdForm() {
			location.href="<%= request.getContextPath()%>/findPwdForm.do;"
		}
	</script>
	
	
	
	
	
	<%@ include file = "../common/footer.jsp" %>
	
</body>
</html>