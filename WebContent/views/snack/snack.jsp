<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import= "java.util.ArrayList, com.semi.snack.model.dto.*"%>
	
<%@ page import= "java.util.ArrayList, com.semi.common.dto.*"%>
	
	<%
		ArrayList<Snack> list = (ArrayList<Snack>)request.getAttribute("list"); 
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

	.listArea{
		width:1000px;
		height:1150px;
		margin:auto;
		
	}
	.thumbnail{
		display:inline-block;
		width:300px;
		border:1px solid white;
		margin:10px; 
	}
	
	#img {
	width: 300px;
	height: 250px;
	vertical-align: top;
}
	
</style>

</head>
<body>

	<%@ include file="../common/menubar.jsp"%>


	<div class="container"><!-- 컨테이너 시작 div -->
		

		<!-- 해당 페이지는 로그인된 유저만 들어올 수 있으며 비회원이 클릭 시 alert 창으로 "로그인을 해주세요" 띄워주기 -->

		<div class="px-3 py-3 my-4">
			<!-- 초기 설정 4 5 5 -->

			<h1>나만 먹을개</h1>


			<hr style="height: 7px;  color: #FDC800";  id="center">

			<p></p>
			
			
			
			<h4>
				<img src="assets/img/gallery/point.jpg" alt="" height="40">&nbsp
			보유중인 뼈다귀 &nbsp:&nbsp 5
		</h4>
		<!--  style="float:left" -->

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

		<!-- 스낵 체크박스 체크 후 서블릿으로 이동시키기 위한 form 시작  -->
	<form id="snackOrder"
			action="<%=request.getContextPath()%>/snackResult.do" method="post"
			enctype="multipart/form-data">
		<!-- multipart/form-data 을 사용하여 데이터 전송 -->

		<!-- 구매에 대한 체크박스 필요 뼈다귀 수량이 있어야할 컨테이너 -->

		<div>

		<button type="submit" class="btn btn-outline-warning btn-lg"
				style="width: 20%" id="center" onclick="goSnackResult()">

			<b>구매</b>

		</button>


		<p></p>


	</div>

	<br>
	<br>
	
	
	<div class="listArea">
				<% if (list.isEmpty()) { %>
						<h3>현재 간식은 준비중에 있습니다.</h3>
				<% } else { %>						

			  <%for(Snack s : list) { %>
			
			<div class="thumbnail" align="center">

				<img src="<%= contextPath %>/resources/FileUpload_test(SNACK)/<%= s.getTitleImg() %>" id="img" style="padding:10px">
				
				<br>
				<p>
				<p> &nbsp&nbsp이름 :	<%=s.getSanckName() %></p>

				<p> 뼈다귀 :	 <%=s.getPrice() %></p>
		
			</div>
					<%} %>  
			<% } %>
			

			<br><br>
			<div align="center">

	<p></p>
	
</div>
</div>
</form>

							</div>
							
							<!-- container 속성이 아래까지 못내려오도록 닫는 div -->
						</div>
						<!-- container 속성이 아래까지 못내려오도록 닫는 div -->


					</div>
					<!-- container 속성이 아래까지 못내려오도록 닫는 div -->
				</div>
				<!-- container 속성이 아래까지 못내려오도록 닫는 div -->


			</div>
			<!-- row div 종료 -->

		</div>
		<!-- container-md 종료 -->

	</form>
	<!-- 스낵 체크박스 체크 후 서블릿으로 이동시키기 위한 form 끝  -->

	<br>
	<br>
	
	
	
	<!-- 회원 및 관리자 구분하여주기 -->
	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
			id="center" onclick="goUsermypage()">(회원) 마이페이지 바로가기</button>

	<br>

	<!-- 회원 및 관리자 구분하여주기 -->
	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
			id="center" onclick="goAdminmypage()">(관리자) 마이페이지 바로가기</button>

	<br>

	<!-- 관리자의 간식 추가, 수정, 삭제의 대한 편의성을 높이고자 마이페이지 및 간식페이지 2 경로에서 이동 가능하며, 회원은 해당 버튼을 볼 수 없어야 함 -->
	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
			id="center" onclick="goSnackInsert()">(관리자) 간식 추가</button>

	<br>

	



	<div class="px-4 py-1 my-5 text-center">
		<p class="display-5 fw-bold"></p>
	</div>

</div> <!-- 컨테이너 끝 div -->

	<script>
				//Detail은 관리자만 들어갈 수 있도록 조건문 걸어주기
			function goDetail(){
			    location.href = "<%=request.getContextPath()%>/snackDetail.do;"
			}	
	
		//서블릿 잘 다녀오는지 테스트차 만들어봄
			function goSnackResult() { //간식 구매 완료 후 이동 되는 서블릿
				location.href = "<%=request.getContextPath()%>/snackResult.do;" 	
			}	
			
			function goUsermypage(){//유저 마이페이지 = 조건문으로 회원과 관리자의 마이페이지 경로를 다르게 이동시켜주기
				location.href="<%=request.getContextPath()%>/userMyDetail.do;"
			}
	 
			function goAdminmypage(){//관리자 마이페이지 = 조건문으로 회원과 관리자의 마이페이지 경로를 다르게 이동시켜주기
				location.href="<%=request.getContextPath()%>/adminMyPage.do"
			}
			
			//관리자만 들어갈 수 있는 경로
			function goSnackInsert(){ //새로운 간식 추가하기위한 서블릿
				location.href="<%=request.getContextPath()%>/snackInsertForm.do"
			}
			
			

			
	</script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>