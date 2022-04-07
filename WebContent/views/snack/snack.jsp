<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import= "java.util.ArrayList, com.semi.snack.model.dto.*"%>
	
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

	<div class="container-md">
		<div class="row">

			<div class="col-sm row gx-10" onclick="goDetail()">
				<img src="assets/img/gallery/test_snack_1.JPG" height="250px" />
			</div>
			<div class="col-sm row gx-10" onclick="goDetail()">
				<img src="assets/img/gallery/test_snack_2.JPG" height="250px" />
			</div>
			<div class="col-sm row gx-10" onclick="goDetail()">
				<img src="assets/img/gallery/test_snack_3.JPG" height="250px" />
			</div>

		</div>

	</div>

	<p></p>


		<div class="container-md">
			<div class="row">

				<div class="col-sm row gx-10">
					<!-- snack 1 div 시작 -->

					<table id="center">
						<tr>
							<td>뼈다귀 : 1 &nbsp&nbsp <input type="checkbox" id="snack1"
									name="snack1" value="snack1">
							</td>
						</tr>

					</table>


				</div>
				<!-- snack 1 div 끝-->

				<div class="col-sm row gx-10">
					<!-- snack 2 div 시작 -->


					<table id="center">
						<tr>
							<td>뼈다귀 : 1 &nbsp&nbsp <input type="checkbox" id="snack2"
									name="snack2" value="snack2">
							</td>
						</tr>

					</table>


				</div>
				<!-- snack 2 div 끝-->

				<div class="col-sm row gx-10">
					<!-- snack 3 div 시작 -->


					<table id="center">
						<tr>
							<td>뼈다귀 : 1 &nbsp&nbsp <input type="checkbox" id="snack3"
									name="snack3" value="snack3">
							</td>
						</tr>
					</table>

				</div>
				<!-- snack 3 div 끝-->


				<div class="px-4 py-1 my-5 text-center">
					<!-- br 적용 시 체크박스 2, 3이 같이 내려오기에 중간에 여백을 위한 div  줄 바꿈을 위해 b -->
					<p class="display-5 fw-bold"></p>
				</div>

				<div class="col-sm row gx-10" onclick="goDetail()">
					<img src="assets/img/gallery/test_snack_4.JPG" height="250px" />
				</div>
				<div class="col-sm row gx-10" onclick="goDetail()">
					<img src="assets/img/gallery/test_snack_5.JPG" height="250px" />
				</div>
				<div class="col-sm row gx-10" onclick="goDetail()">
					<img src="assets/img/gallery/test_snack_6.JPG" height="250px" />
				</div>


				<div class="container-md">
					<div class="row">

						<p></p>
						<div class="col-sm row gx-10">
							<!-- snack 4 div 시작 -->

							<table id="center">
								<tr>
									<td>뼈다귀 : 2 &nbsp&nbsp <input type="checkbox" id="snack4"
											name="snack4" value="snack4">
									</td>
								</tr>

							</table>


						</div>
						<!-- snack 4 div 끝-->



						<div class="col-sm row gx-10">
							<!-- snack 5 div 시작 -->


							<table id="center">
								<tr>
									<td>뼈다귀 : 2 &nbsp&nbsp <input type="checkbox" id="snack5"
											name="snack5" value="snack5">
									</td>
								</tr>

							</table>


						</div>
						<!-- snack 5 div 끝-->


						<div class="col-sm row gx-10">
							<!-- snack 6 div 시작 -->


							<table id="center">
								<tr>
									<td>뼈다귀 : 2 &nbsp&nbsp <input type="checkbox" id="snack6"
											name="snack6" value="snack6">
									</td>
								</tr>
							</table>

						</div>
						<!-- snack 6 div 끝 -->

					</div>
					<!-- container 속성이 아래까지 못내려오도록 닫는 div -->
				</div>
				<!-- container 속성이 아래까지 못내려오도록 닫는 div -->


				<div class="px-4 py-1 my-5 text-center">
					<!-- br 적용 시 체크박스 2, 3이 같이 내려오기에 중간에 여백을 위한 div  줄 바꿈을 위해 b -->
					<p class="display-5 fw-bold"></p>
				</div>

				<div class="col-sm row gx-10" onclick="goDetail()">
					<img src="assets/img/gallery/test_snack_7.JPG" height="250px" />
				</div>
				<div class="col-sm row gx-10" onclick="goDetail()">
					<img src="assets/img/gallery/test_snack_8.JPG" height="250px" />
				</div>
				<div class="col-sm row gx-10" onclick="goDetail()">
					<img src="assets/img/gallery/test_snack_9.JPG" height="250px" />
				</div>


				<div class="container-md">
					<div class="row">

						<p></p>


						<div class="container-md">
							<div class="row">

								<div class="col-sm row gx-10">
									<!-- snack 7 div 시작 -->

									<table id="center">
										<tr>
											<td>뼈다귀 : 5 &nbsp&nbsp <input type="checkbox"
													id="snack7" name="snack7" value="snack7">
											</td>
										</tr>

									</table>


								</div>
								<!-- snack 7 div 끝-->

								<div class="col-sm row gx-10">
									<!-- snack 8 div 시작 -->


									<table id="center">
										<tr>
											<td>뼈다귀 : 5 &nbsp&nbsp <input type="checkbox"
													id="snack8" name="snack8" value="snack8">
											</td>
										</tr>

									</table>


								</div>
								<!-- snack 8 div 끝-->

								<div class="col-sm row gx-10">
									<!-- snack 9 div 시작 -->


									<table id="center">
										<tr>
											<td>뼈다귀 : 5 &nbsp&nbsp <input type="checkbox"
													id="snack9" name="snack9" value="snack9">
											</td>
										</tr>
									</table>

								</div>
								<!-- snack 9 div 끝 -->

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