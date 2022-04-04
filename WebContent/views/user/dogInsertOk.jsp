<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div h4{
	color: #0099FF;
}
div h2 span{
	color: #FDC800;
}

button b {
	color: #0099FF;
}

</style>
</head>
<body>
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	<div class="text-center mt-8">
		<h2><span>해피</span>의 입학을 환영합니다.</h2>
		<h4>A반 친구들이 기다리고 있어요!</h4>
	</div>
	<!-- 환영하는 강아지 이미지 -->
	<div>
		<img class="d-block mx-auto mb-2" src="assets/img/gallery/dogs.png" alt="환영하는강아지들" width="800">
	</div>
	
	<!-- 버튼 -->
	<div class="col-md-12 pb-5 text-center">
		<button class="w-45 btn btn-primary btn-lg" type="button" onclick="goPointCharge()">
			<b>뼈다귀충전하러가기</b>
		</button>
		<button class="w-45 btn btn-primary btn-lg" type="button" onclick="goSchoolBus()">
			<b>통학버스신청하러가기</b>
		</button>
	</div>

	<!--27행 경로는 바꿔야함! (영아님 결제페이지) -->
	<!-- 27행, 29행은 현재 404에러임 -->
	<script type="text/javascript">
		//뼈다귀 충전하기
		function goPointCharge() {
			location.href="<%= request.getContextPath()%>/pointInsert.do;"
		}
		//통학버스 신청하기
		function goSchoolBus() {
			location.href="<%= request.getContextPath()%>/schoolbusForm.do;"
		}
	</script>
	
	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>