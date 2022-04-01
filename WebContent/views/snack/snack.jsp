<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

	    
	<h1>간식 페이지</h1>
	
	
	<button onclick="goSnack()">
		구매
	</button>
	 
	 <button onclick="gomypage()">
	 마이페이지 바로가기	
	 </button>
	
	 <script>
	 
	 		//서블릿 잘 다녀오는지 테스트차 만들어봄
			function goSnack(){ //고메인 함수를 사용하여 이동
				location.href="<%= request.getContextPath()%>/snackResult.do;" 	
			}	
			
			function goUserMyPage(){
				location.href="<%= request.getContextPath()%>/userMyPage.do"
			}
			
			//나만먹을개
			function snackForm(){
				location.href="<%= request.getContextPath()%>/snack.do;"
			
			}
			
	</script>
	  <%@ include file = "../common/footer.jsp" %>
</body>
</html>