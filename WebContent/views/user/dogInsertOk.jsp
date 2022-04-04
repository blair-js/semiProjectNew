<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	<div class="">
		<h3>해피의 입학을 환영합니다.</h3>
	</div>
	<div>
		<img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png"alt="강쥐" width="72" height="57">
	</div>
	
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
	
	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>