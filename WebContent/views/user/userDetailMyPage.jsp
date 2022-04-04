<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@font-face {
	font-family: 'LeferiPoint-BlackA';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'LeferiPoint-BlackA';
}

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
</head>
<body>

	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<div class="container">
		<main>
			<div class="py-5 text-center">
				<img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png"
					alt="강쥐" width="72" height="57">
				<h2>MY PAGE</h2>
			</div>

			<!-- 강아지의 간략한 정보 -->
			<div class="row g-5">
				<div class="col-md-5 col-lg-4 order-md-last">
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-primary">Your Dog</span> <span
							class="badge bg-primary rounded-pill">2</span>
					</h4>
					<ul class="list-group mb-3">
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">Dog name</h6>
								<small class="" style="color: #0099FF">해피</small>
							</div> <span class="text-muted">A반</span>
						</li>

						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">Dog name</h6>
								<small class="" style="color: #0099FF">뽀삐</small>
							</div> <span class="text-muted">B반</span>
						</li>
					</ul>

					<button class="w-100 btn btn-secondary btn-lg" type="button"
						style="display: inline-block;" onclick="goDetailDog()">
						<b style="color: white;">강아지 정보 상세보기</b>
					</button>
				</div>

				<!--  -->

				<hr class="my-4" style="height: 7px; color: #FDC800">

				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3">Your Info</h4>
					<form class="needs-validation" novalidate>
						<div class="row g-3">
							<div class="col-sm-6">
								<label for="name" class="form-label">Name</label> <input
									type="text" class="form-control" id="name" placeholder="김지수"
									value="" required>
							</div>

							<div class="col-sm-6">
								<label for="enrollDate" class="form-label">Enroll Date</label> <input
									type="text" class="form-control" id="enrollDate"
									placeholder="2022-04-04" value="" readonly="readonly">
							</div>

							<div class="col-12">
								<label for="userId" class="form-label">UserId</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control" id="userId"
										placeholder="jisu123" required>
								</div>
							</div>

							<div class="col-12">
								<label for="email" class="form-label">Email</label> <input
									type="email" class="form-control" id="email"
									placeholder="ji@su.com">
								<div class="invalid-feedback">Please enter a valid email
									address for shipping updates.</div>
							</div>

							<div class="col-12">
								<label for="phone" class="form-label">Phone</label> <input
									type="text" class="form-control" id="phone"
									placeholder="010-1111-2222" required>
								<div class="invalid-feedback">Please enter your shipping
									address.</div>
							</div>

							<div class="col-md-5">
								<label for="gender" class="form-label">Gender</label> <select
									class="form-select" id="gender" required>
									<option value="F">여자</option>
									<option value="M">남자</option>
								</select>
							</div>

							<div class="col-md-3">
								<label for="point" class="form-label">Point</label> <input
									type="text" class="form-control" id="point" placeholder="100"
									required readonly>
							</div>

							<div class="col-md-4">
								<label for="status" class="form-label">Status</label> <input
									type="text" class="form-control" id="status" placeholder="Y"
									required readonly>
							</div>

						</div>

						<hr class="my-4">

						<h4 class="mb-3">
							설문조사<span class="text-muted"> (Optional)</span>
						</h4>
						<div class="form-check">
							<input type="checkbox" class="form-check-input" id="agree1">
							<label class="form-check-label" for="agree1">둥글개 둥글개 유치원의
								교육 프로그램에 만족합니다.</label>
						</div>

						<div class="form-check">
							<input type="checkbox" class="form-check-input" id="agree2">
							<label class="form-check-label" for="agree2">유치원 홈페이지의
								이용이 편리합니다.</label>
						</div>

						<hr class="my-4">

						<h4 class="mb-3">SMS 수신여부</h4>
						<div class="my-3">
							<div class="form-check">
								<input id="smsCheck" name="paymentMethod" type="radio"
									class="form-check-input" required> <label
									class="form-check-label" for="smsCheck">예</label>
							</div>
							<div class="form-check">
								<input id="smsCheck" name="paymentMethod" type="radio"
									class="form-check-input" required> <label
									class="form-check-label" for="smsCheck">아니오</label>
							</div>
						</div>

						<hr class="my-4">

						<div class="col-md-12 text-center pb-5">
							<button class="w-50 btn btn-primary btn-lg mb-2" type="button"
								style="display: inline-block;" onclick="updateUser()">
								<b style="color: white;">회원정보 수정</b>
							</button>
							<button class="w-50 btn btn-primary btn-lg" type="button"
								style="display: inline-block;" onclick="deleteUser()">
								<b style="color: white;">회원 탈퇴</b>
							</button>
						</div>
					</form>
				</div>
			</div>
		</main>


	</div>

	<script type="text/javascript">
		function updateUser() {
			location.href="<%= request.getContextPath()%>/updateUser.do;"
		}
		
		function deleteUser() {
			location.href="<%= request.getContextPath()%>/deleteUser.do;"
			//프롬프트 창으로 비밀번호 확인 후 탈퇴 
		}
		function goDetailDog() {
			location.href="<%= request.getContextPath()%>/detailDogPage.do;"
			//프롬프트 창으로 비밀번호 확인 후 탈퇴 
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>