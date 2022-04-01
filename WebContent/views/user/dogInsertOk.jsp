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
	<h1>OO의 입학을 환영합니다~~~~</h1>
	
	<button onclick="goPointCharge()">
		뼈다귀충전하러가기
	</button>
	
	<button onclick="goSchoolBus()">
		통학버스신청하러가기
	</button>
	
	<!--27행 경로는 바꿔야함! (영아님 결제페이지) -->
	<!-- 27행, 29행은 현재 404에러임 -->
	<script type="text/javascript">
		function goPointCharge() {
			location.href="<%= request.getContextPath()%>/pointInsert.do;"
		}
		function goSchoolBus() {
			location.href="<%= request.getContextPath()%>/schoolbusInsert.do;"
		}
	</script>
	
	<%@ include file = "../common/footer.jsp" %>
	
	
	
</body>
</html>