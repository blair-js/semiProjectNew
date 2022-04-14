<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.snack.model.dto.*"%>
	
	<%
		UserPoint userPoint = (UserPoint)request.getAttribute("userPoint");
 
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>

<style>
#center {
	text-align: center;
	margin: auto;
	justify-content: center;
	display: flex;
}
</style>

</head>
<body>

	<%@ include file="../common/menubar.jsp"%>


	<div class="container">
		<!-- 컨테이너 시작 div -->


		<!-- 해당 페이지는 로그인된 유저만 들어올 수 있으며 비회원이 클릭 시 alert 창으로 "로그인을 해주세요" 띄워주기 -->

		<div class="px-3 py-3 my-4">
			<!-- 초기 설정 4 5 5 -->

			<h1>나만 먹을개</h1>


			<hr style="height: 7px; color: #FDC800" ;  id="center">

			<p></p>

				<h4>
					<img src="assets/img/gallery/point.jpg" alt="" height="40">&nbsp
					보유중인 뼈다귀 &nbsp:&nbsp<%=userPoint.getUserPoint()%>	
					
				</h4>



			<p></p>

			<div style="border: 1px solid #FDC800; background-color: #FDC800">

				<br>

				<p>&nbsp&nbsp&nbsp저희 둥글개둥글개 에서 제공하는 간식은</p>

				<p>&nbsp&nbsp&nbsp뼈다귀 충전 후 보유한 포인트에서 차감하여 구매할 수 있습니다.</p>

				<p>&nbsp&nbsp&nbsp구매 한 간식은 식사시간에 견주님의 강아지에게 소분하여 지급되며,</p>

				<p>&nbsp&nbsp&nbsp필요 시 방문 및 통학버스를 이용하여 보호자님께서도</p>

				<p>&nbsp&nbsp&nbsp직접 수령이 가능하십니다.</p>

			</div>
		</div>

		<div>


			<div class="text" id="center">

				<h2 style="color: #002147; margin: 20px">구매완료</h2>
				<br>

			</div>

			<h4 id="center" style="margin: 20px">정상적으로 구매가 완료되었습니다.</h4>

			<p id="center" style="margin: 10px; color: #FF0000"><u>구매 내역은 마이페이지에서 확인가능 합니다.</u></p>

			<br> <br>
			<button type="button" class="btn btn-outline-warning btn-lg"
				style="width: 20%" id="center" onclick="goSnack()">

				<b>간식 목록 바로가기</b>

			</button>



			<div class="px-4 py-1 my-5 text-center">
				<p class="display-5 fw-bold"></p>
			</div>



			<script>
	
			
		function goSnack(){//간식 삭제를 하기위해 form으로 페이지 전환
			location.href ="/snack.do?userNo=<%=loginUser.getUserNo()%>";
		}
		
		</script>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>

</body>
</html>