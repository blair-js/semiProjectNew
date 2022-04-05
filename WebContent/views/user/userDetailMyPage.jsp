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
.hr1{
	 color: #FDC800;
}
main button b{
	color: white;
}
main .sm{
	 color: #0099FF;
}
</style>
</head>
<body>
	<!-- UserMyDetailServlet에서 여기로 넘어옴 -->
		
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<!-- 컨테이너 시작 -->
	<div class="container">
		<main>
			<!-- 위 div -->
			<div class="py-5 text-center">
				<img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png" alt="강아지로고" width="72" height="57">
				<h2>MY PAGE</h2>
			</div>

			<!-- 우측 강아지의 간략한 정보 시작 -->
			<div class="row g-5">
				<div class="col-md-5 col-lg-4 order-md-last">
					<!-- 첫번째 행 -->
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-primary">Your Dog</span> 
						<span class="badge bg-primary rounded-pill">2</span>
					</h4>
					<!-- 두번째 행 -->
					<ul class="list-group mb-3">
						<!-- 두번째 행의 첫번째 -->
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">Dog name</h6>
								<small class="sm">해피</small>
							</div> 
							<span class="text-muted">A반</span>
						</li>
						<!-- 두번째 행의 두번째 -->
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">Dog name</h6>
								<small class="sm">뽀삐</small>
							</div> 
							<span class="text-muted">B반</span>
						</li>
					</ul>
					<!-- 두번째 행 끝 -->
					<!-- 세번째 행 : 버튼 -->
					<button type="button" class="w-100 btn btn-secondary btn-lg" onclick="goDetailDog()">
						<b>강아지 정보 상세보기</b>
					</button>
				</div>
				<!-- 우측 강아지의 간략한 정보 끝 -->
					
				<!-- 강아지 로고가 있는 위쪽과 정보를 뿌려주는 아래쪽과의 구분선 -->	
				<hr class="my-4 hr1" style="height:7px;">

				<!-- 좌측 사용자의 상세한 정보 시작 -->
				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3">Your Info</h4>
					
					<!-- 사용자의 정보를 뿌려주는 form이자 수정이 가능한 form -->
					<form class="needs-validation" novalidate>
						<!-- 첫번째 행 1 : 이름, 가입일 기재 -->
						<div class="row g-3">
							<!-- 1-1 이름 -->
							<div class="col-sm-6">
								<label for="userName" class="form-label">Name</label> 
								<input type="text" class="form-control" id="userName" name="userName" placeholder="" value="김지수" required>
							</div>
							<!-- 1-2 가입일 -->
							<div class="col-sm-6">
								<label for="enrollDate" class="form-label">Enroll Date</label> 
								<input type="text" class="form-control" id="enrollDate" placeholder="" value="2022-04-04" readonly required>
							</div>
							<!-- 두번째 행 2 : 아이디 -->
							<div class="col-12">
								<label for="userId" class="form-label">UserId</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control" id="userId" name="userId" placeholder="" value="jisu123" required>
								</div>
							</div>
							<!-- 세번째 행 3 : 이메일 -->
							<div class="col-12">
								<label for="email" class="form-label">Email</label> 
								<input type="email" class="form-control" id="email" name="email" placeholder="" value="ji@su.com" required>
							</div>
							<!-- 네번째 행 4 : 전화번호 -->
							<div class="col-12">
								<label for="phone" class="form-label">Phone</label> 
								<input type="text" class="form-control" id="phone" placeholder="" value="010-1111-2222" required>
							</div>
							<!-- 다섯번째 행 5 : 성별, 보유뼈다귀(포인트), 상태 -->
							<!-- 5-1 : 성별 -->
							<div class="col-md-5">
								<label for="gender" class="form-label">Gender</label> 
								<select class="form-select" id="gender" required>
									<option value="F">여자</option>
									<option value="M">남자</option>
								</select>
							</div>
							<!-- 5-2 : 보유뼈다귀(포인트) -->
							<div class="col-md-3">
								<label for="point" class="form-label">Point</label> 
								<input type="text" class="form-control" id="point" placeholder="" value="100" required readonly>
							</div>
							<!-- 5-3 : 상태 -->
							<div class="col-md-4">
								<label for="status" class="form-label">Status</label> 
								<input type="text" class="form-control" id="status" placeholder="" value="Y" required readonly>
							</div>
						</div>
						<!-- 사용자 정보 끝 -->

						<hr class="my-4">
						
						<!-- 설문조사 -->
						<!-- 데이터로 전달하는 부분은 아님! 그냥..멋내기용.. -->
						<h4 class="mb-3">설문조사<span class="text-muted"> (Optional)</span></h4>
						<div class="form-check">
							<input type="checkbox" class="form-check-input" id="agree1">
							<label class="form-check-label" for="agree1">둥글개 둥글개 유치원의 교육 프로그램에 만족합니다.</label>
						</div>

						<div class="form-check">
							<input type="checkbox" class="form-check-input" id="agree2">
							<label class="form-check-label" for="agree2">유치원 홈페이지의 이용이 편리합니다.</label>
						</div>

						<hr class="my-4">
						
						<!-- SMS 수신여부확인 -->
						<h4 class="mb-3">SMS 수신여부</h4>
						<div class="my-3">
							<div class="form-check">
								<input id="smsCheck" name="smsCheck" value="Y" type="radio" class="form-check-input" checked> 
								<label class="form-check-label" for="smsCheck">예</label>
							</div>
							<div class="form-check">
								<input id="smsCheck" name="smsCheck" value="N" type="radio" class="form-check-input"> 
								<label class="form-check-label" for="smsCheck">아니오</label>
							</div>
						</div>

						<hr class="my-4">
							
						<!-- 정보수정, 탈퇴 버튼 두개 -->
						<div class="col-md-12 text-center pb-5">
							<button class="w-50 btn btn-primary btn-lg mb-2" type="button" onclick="updateUser()">
								<b>회원정보 수정</b>
							</button>
							<button class="w-50 btn btn-primary btn-lg" type="button" onclick="deleteUser()">
								<b>회원 탈퇴</b>
							</button>
						</div>
						<!-- 버튼 끝 -->
					</form>
					<!-- 사용자의 정보를 form 끝-->
					
				</div>
				<!-- 좌측 사용자의 상세한 정보 끝 -->
			</div>
		</main>
	</div>
	<!-- 컨테이너 끝 -->
	
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