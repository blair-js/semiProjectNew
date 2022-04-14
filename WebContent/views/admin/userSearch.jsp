<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style type="text/css">
.tftable {
	font-size: 12px;
	color: #333333;
	width: 100%;
	border-width: 1px;
	border-color: #a9a9a9;
	border-collapse: collapse;
	border-width: 1px;
}

.tftable th {
	font-size: 12px;
	background-color: #FDC800;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9a9a9;
	text-align: center;
}

.tftable tr {
	background-color: #ffffff;
}

.tftable td {
	font-size: 12px;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9a9a9;
}

td {
	height: 30px
}

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

		<img class="d-block mx-auto mt-7 mb-4" src="assets/img/gallery/adminDog.png" alt="강아지로고" width="72" height="70">
		<h1 class="magin" id="center" style="margin: 40px">회원 정보 조회</h1>

		<hr style="height: 7px; color: #FDC800;">


		<div class="px-6 py-5 my-4 text-center">
			<!-- br 적용 시 체크박스 2, 3이 같이 내려오기에 중간에 여백을 위한 div  줄 바꿈 -->
			<p class="display-5 fw-bold"></p>

			<form id="userSearchList" action="<%=request.getContextPath() %>/userOrderListForm.do" method="post">
			
			<table class="tftable" border="1">
				<tr>
					<th style="" width=200px">회원번호</th>
					<th style="" width=200px">가입일</th>
					<th style="" width=200px">아이디</th>
					<th style="" width=200px">이름</th>
					<th style="" width=200px">전화번호</th>
				</tr>
		
	
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
		
			</table>
				
		
	</form>

		</div>

	</div>
	

		</div> 
	<!-- 컨테이너 끝 div -->


	<div class="px-4 py-1 my-5 text-center">
		<p class="display-5 fw-bold"></p>
	</div>
	
	<script>
	console.log(<%=loginUser.getUserNo()%>)
	function gouUserOrderList() { //간식 구매 완료 후 이동 되는 서블릿
		document.getElementById("userSearchList").submit();		
		}	
	
	</script>


<%@ include file="../common/footer.jsp"%>
</body>
</html>