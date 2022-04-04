<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.semi.class_notice.model.dto.*"%>

<%
ArrayList<ClassNotice> list = (ArrayList<ClassNotice>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style>
#img-center {
	width: 18rem;
	object-fit: cover;
	margin: auto;
}
</style>

</head>
<body>

	<%@ include file="../common/menubar.jsp"%>

	<h2 class="text-center">햇님반</h2>
	<p class="page-description text-center">반별 알림장</p>

	<div class="album py-5 bg-light">
		<div class="container">

			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
				<a href="#" style="text-decoration: none" onclick="goDetail();">
					<div class="col">
						<div class="card shadow">
							<div id="img-center">
								<img class="card-img-top" src="assets/img/gallery/fdog.png">
							</div>
							<div class="card-body">
								<p class="card-text text-dark">
									게시물 제목<br>작성자
								</p>
								<div class="d-flex justify-content-between align-items-center">

									<small class="text-muted">날짜 | 조회수</small>
								</div>
							</div>
						</div>
					</div>
				</a> <a href="#" style="text-decoration: none" onclick="goDetail();">
					<div class="col">
						<div class="card shadow">
							<div id="img-center">
								<img class="card-img-top" src="assets/img/gallery/fdog.png">
							</div>
							<div class="card-body">
								<p class="card-text text-dark">
									게시물 제목<br>작성자
								</p>
								<div class="d-flex justify-content-between align-items-center">

									<small class="text-muted">날짜 | 조회수</small>
								</div>
							</div>
						</div>
					</div>
				</a> <a href="#" style="text-decoration: none" onclick="goDetail();">
					<div class="col">
						<div class="card shadow">
							<div id="img-center">
								<img class="card-img-top" src="assets/img/gallery/fdog.png">
							</div>
							<div class="card-body">
								<p class="card-text text-dark">
									게시물 제목<br>작성자
								</p>
								<div class="d-flex justify-content-between align-items-center">

									<small class="text-muted">날짜 | 조회수</small>
								</div>
							</div>
						</div>
					</div>
				</a> <a href="#" style="text-decoration: none" onclick="goDetail();">
					<div class="col">
						<div class="card shadow">
							<div id="img-center">
								<img class="card-img-top" src="assets/img/gallery/fdog.png">
							</div>
							<div class="card-body">
								<p class="card-text text-dark">
									게시물 제목<br>작성자
								</p>
								<div class="d-flex justify-content-between align-items-center">
									<small class="text-muted">날짜 | 조회수</small>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<script>
		function goDetail() {
			location.href = "classNoticeDetail.do";
		}
	</script>


	<h3>작성하기 누르면 작성하기 화면으로 전환</h3>
	<h3>상세조회 버튼 클릭시 게시물 화면으로 전환</h3>
	<button onclick="location.href='classNoticeEnrollForm.do'">작성하기</button>
	<button onclick="location.href='classNoticeDetail.do'">게시물 클릭</button>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>