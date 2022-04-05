<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div main hr{
	 color: #FDC800; 
}
</style>
</head>
<body>
	<!-- DogDetailServlet에서 여기로 옴 -->
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	<!-- 컨테이너 시작 -->
	<div class="container">
		<main>
			<div class="py-5 text-center mb-3 pb-2">
				<img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png" alt="강아지로고" width="72" height="57">
				<h2>DOG PAGE</h2>
			</div>
			<!-- 회원정보로 돌아가기 버튼 -->
			<div class="col-md-12 text-center">
				<button class="col-md-6 btn btn-primary btn-lg mb-2" type="button" onclick="goUserDetail()">
					<b style="color: white;">회원정보로 돌아가기</b>
				</button>
			</div>
			
			<!-- 기준선 -->
			<hr class="my-4" style="height:7px;">

			<!-- 강아지 정보 보여지는 영역 -->
			<div class="col-md-7 col-lg-8">
				<!-- 0405 class="needs-validation" novalidate 삭제. -->
				<!-- 삭제 사유: 강아지의 정보를 보여주기만 하는 부분으로 제출하지 않으며, 유효성 검사가 필요없음. -->
				<form>
					<!-- 강아지 1 시작 -->
					<div class="row g-3 mb-5">
						<!-- 강아지 이미지 -->
						<div class="col-sm-6" style="text-align: center">
							<img alt="강아지 사진" src="assets/img/gallery/myDog1.jpg" width="300">
						</div>
						<!-- 강아지 정보 -->
						<div class="col-sm-6" style="margin: auto;">
							<!-- 이름 -->
							<div class="col-sm-12">
								<label for="dogName" class="form-label">Dog Name</label> 
								<input type="text" class="form-control" id="dogName" placeholder="해피" value="" readonly>
							</div>
							<!-- 나이 -->
							<div class="col-sm-12">
								<label for="dogAge" class="form-label">Dog Age</label>
								<input type="text" class="form-control" id="dogAge" placeholder="5" readonly>
							</div>
							<!-- 반 -->
							<div class="col-sm-12">
								<label for="dogClass" class="form-label">Class</label> 
								<input type="text" class="form-control" id="dogClass" placeholder="A반" readonly>
							</div>
							<!-- 성별 -->
							<div class="col-sm-12">
								<label for="gender" class="form-label">Gender</label> 
								<input type="text" class="form-control" id="gender" placeholder="남자" readonly>
							</div>
						</div>
						<!-- 강아지 정보 끝 -->
					</div>
					<!-- 강아지 1 끝 -->
					
					<!-- 강아지 2 시작 -->
					<div class="row g-3 mb-5">
						<!-- 강아지 이미지 -->
						<div class="col-sm-6" style="text-align: center">
							<img alt="강아지 사진" src="assets/img/gallery/myDog2.jpg" width="300">
						</div>
						<!-- 강아지 정보 -->
						<div class="col-sm-6" style="margin: auto;">
							<!-- 이름 -->
							<div class="col-sm-12">
								<label for="dogName" class="form-label">Dog Name</label> 
								<input type="text" class="form-control" id="dogName" placeholder="뽀삐" value="" readonly>
							</div>
							<!-- 나이 -->
							<div class="col-sm-12">
								<label for="dogAge" class="form-label">Dog Age</label>
								<input type="text" class="form-control" id="dogAge" placeholder="2" readonly>
							</div>
							<!-- 반 -->
							<div class="col-sm-12">
								<label for="dogClass" class="form-label">Class</label> 
								<input type="text" class="form-control" id="dogClass" placeholder="B반" readonly>
							</div>
							<!-- 성별 -->
							<div class="col-sm-12">
								<label for="gender" class="form-label">Gender</label> 
								<input type="text" class="form-control" id="gender" placeholder="여자" readonly>
							</div>
						</div>
						<!-- 강아지 정보 끝 -->
					</div>
					<!-- 강아지 2 끝 -->
				</form>
			</div>
			<!-- 강아지 정보 보여지는 영역 끝 -->
		</main>

	</div>
	<!-- 컨테이너 끝 -->
	
	<script type="text/javascript">
		//회원정보로 돌아가기 버튼
		function goUserDetail() {
			location.href="<%=request.getContextPath()%>/userMyDetail.do;"
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>