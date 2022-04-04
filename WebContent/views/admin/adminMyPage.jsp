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

	
	<h1> 관리자 전용 페이지 </h1>
	
	<button onclick="goUserList()">
		회원조회
	</button>
	 
	 <button onclick="goSnackOrder()">
	   간식 구매목록
	 </button>
	 
	  <button onclick="goSnackInsert()">
	   간식 추가
	 </button>
	 
	  <button onclick="goSnackUpdate()">
	   간식 수정
	 </button>
	 
	   <button onclick="goSnackDelete()">
	   간식 삭제
	 </button>
	 
	 <script>
	
			function goUserList(){ //회원목록을 위한 서블릿 
				location.href="<%= request.getContextPath()%>/userListForm.do;"	
			}	
						
			function goSnackOrder(){ //회원의 간식 구매 목록을 확인하기 위한 서블릿
				location.href="<%= request.getContextPath()%>/snackOrderListForm.do"
			}
			
			function goSnackInsert(){ //새로운 간식 추가하기위한 서블릿
				location.href="<%= request.getContextPath()%>/snackInsertForm.do"
			}
			
			function goSnackUpdate(){//간식 업데이트를 위한 서블릿
				location.href="<%= request.getContextPath()%>/snackUpdateForm.do"
			}
			function goSnackDelete(){//간식 삭제를 하기위해 form으로 페이지 전환
				location.href="<%= request.getContextPath()%>/snackDeleteForm.do"
			}
			
	</script>

	
	 <%@ include file = "../common/footer.jsp" %>

	
</body>
</html>