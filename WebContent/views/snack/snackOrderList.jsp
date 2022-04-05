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
	background-color: #b8b8b8;
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

		<h1 class="magin" id="center" style="margin: 40px">간식 구매내역</h1>

		<hr style="height: 7px; color: #FDC800;">


		<div class="px-6 py-5 my-4 text-center">
			<!-- br 적용 시 체크박스 2, 3이 같이 내려오기에 중간에 여백을 위한 div  줄 바꿈 -->
			<p class="display-5 fw-bold"></p>

			<form id="snackOrderList" action="<%=request.getContextPath() %>/snackOrderList.do" method="post">
			
			<table class="tftable" border="1">
				<tr>
					<th style="" width=200px">주문번호</th>
					<th style="" width=200px">구입일자</th>
					<th style="" width=200px">회원 아이디</th>
					<th style="" width=700px">구매목록</th>
				</tr>
				<tr>
					<td>2055482</td>
					<td>2022-00-00</td>
					<td>user123</td>
					<td>프로바이오틱스 비스켓 연어 100g, 오 나의 치즈 망고 7p(닭가슴살 치즈스틱), 유기농 스킨앤코트 살몬 1kg 가수분해 연어 순살</td>
				</tr>
				<tr>
					<td>5418247</td>
					<td>2022-00-00</td>
					<td>user456</td>
					<td>오 나의 치즈 망고 7p(닭가슴살 치즈스틱),유기농 프레쉬 비프 5kg 가수분해 소고기</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
		</form>


		</div>

	</div>
	<!-- 컨테이너 끝 div -->

	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
			id="center"  onclick="goSnackOrderList()">구매 내역 조회</button>


	<div class="px-4 py-1 my-5 text-center">
		<p class="display-5 fw-bold"></p>
	</div>
	
	<script>
	function goSnackOrderList(){ 
				location.href="<%=request.getContextPath()%>/snackOrderList.do;"
		}
	</script>

	<%@ include file="../common/footer.jsp"%>


</body>
</html>


