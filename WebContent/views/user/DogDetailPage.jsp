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

	<div class="container">
		<main>
			<div class="py-5 text-center mb-3 pb-2">
				<img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png"
					alt="강쥐" width="72" height="57">
				<h2>DOG PAGE</h2>
			</div>

			<div class="col-md-12 text-center">
				<button class="col-md-6 btn btn-primary btn-lg mb-2" type="button" onclick="goUserDetail()">
					<b style="color: white;">회원정보로 돌아가기</b>
				</button>
			</div>

			<hr class="my-4" style="height: 7px; color: #FDC800">

			<div class="col-md-7 col-lg-8">
				<!-- <h4 class="mb-3">Dog Info</h4> -->
				<form class="needs-validation" novalidate>

					<!-- 강아지 1 -->
					<div class="row g-3 mb-5">

						<div class="col-sm-6" style="text-align: center">
							<img alt="강아지 사진" src="assets/img/gallery/myDog1.jpg" width="300">
						</div>

						<div class="col-sm-6" style="margin: auto;">
							<div class="col-sm-12">
								<label for="dogName" class="form-label">Dog Name</label> <input
									type="text" class="form-control" id="dogName" placeholder="해피"
									value="" required>
							</div>

							<div class="col-sm-12">
								<label for="dogAge" class="form-label">Dog Age</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control" id="dogAge"
										placeholder="5" required>
								</div>
							</div>

							<div class="col-sm-12">
								<label for="dogClass" class="form-label">Class</label> <input
									type="text" class="form-control" id="dogClass" placeholder="A반">
							</div>

							<div class="col-sm-12">
								<label for="gender" class="form-label">Gender</label> <select
									class="form-select" id="gender" required>
									<option value="M">남자</option>
									<option value="F">여자</option>
								</select>
							</div>
						</div>
					</div>
					<!-- 강아지 2 -->
					<div class="row g-3 mb-5">

						<div class="col-sm-6" style="text-align: center">
							<img alt="강아지 사진" src="assets/img/gallery/myDog2.jpg" width="300">
						</div>

						<div class="col-sm-6" style="margin: auto;">
							<div class="col-sm-12">
								<label for="dogName" class="form-label">Dog Name</label> <input
									type="text" class="form-control" id="dogName" placeholder="뽀삐"
									value="" required>
							</div>

							<div class="col-sm-12">
								<label for="dogAge" class="form-label">Dog Age</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control" id="dogAge"
										placeholder="2" required>
								</div>
							</div>

							<div class="col-sm-12">
								<label for="dogClass" class="form-label">Class</label> <input
									type="text" class="form-control" id="dogClass" placeholder="B반">
							</div>

							<div class="col-sm-12">
								<label for="gender" class="form-label">Gender</label> <select
									class="form-select" id="gender" required>
									<option value="F">여자</option>
									<option value="M">남자</option>
								</select>
							</div>
						</div>

					</div>

				</form>
			</div>

		</main>


	</div>

	<script type="text/javascript">
		function goUserDetail() {
			location.href="<%=request.getContextPath()%>/userMyDetail.do;"
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>