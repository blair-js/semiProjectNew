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

.test {
	background-color: bg-primary;
}

.ckBtn {
	border: 1px solid gray;
	border-radius: 5px;
	color: gray;
}
</style>
</head>
<body>
	<!-- UserInsertServlet으로 데이터를 보낼 것. -->
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<div class="col-md-12 mt-5 mb-3">
		<h2 class="text-center">회원가입</h2>
	</div>
	<div class="mb-3" style="color: gray; text-align: center">
		이미 둥글개 회원이신가요? 
		<a href="<%=contextPath %>/loginForm.do;" style="color: #0099FF"> 로그인</a>
	</div>

	<div class="row">

		<div class="col-md-1"></div>
		<img class="col-md-3 mb-7" src="assets/img/gallery/enrollDog.jpg"
			alt="강아지사진">
		<!-- 회원가입 양식 시작 -->
		<div class="col-md-7 mb-7">
			<form class="form-horizontal bg-primary"
				action="<%=contextPath %>/insertUser.do;" method="post">
				<!-- 아이디 -->
				<div class="form-group">
					<div class="col-md-2 control-label">
						<label for="id">아이디</label>
					</div>
					<div class="col-md-7 mb-3" style="display: inline-block;">
						<input type="text" class="form-control" name="id" id="id">
					</div>
					<button class="ckBtn" type="button" id="idCheckBtn"
						onclick="checkId();">중복확인</button>
				</div>
				<!-- 비밀번호 -->
				<div class="form-group">
					<div class="col-md-2 control-label">
						<label id="pwd">비밀번호</label>
					</div>
					<div class="col-md-7 mb-3">
						<input type="password" class="form-control" name="pwd" id="pwd">
					</div>
				</div>

				<!-- 이름 -->
				<div class="form-group">
					<div class="col-md-2 control-label">
						<label id="name">이름</label>
					</div>
					<div class="col-md-7 mb-3">
						<input type="text" class="form-control" name="name" id="name">
						<!-- ajax 통신 -->
					</div>
				</div>

				<!-- 연락처 -->
				<div class="form-group">
					<div class="col-md-2 control-label">
						<label id="phone">연락처</label>
					</div>
					<div class="col-md-7 mb-1">
						<input type="text" class="form-control" name="phone" id="phone">
					</div>
				</div>
				<!-- sms 수신동의여부 -->
				<div class="mb-2"> &nbsp; sms 수신 동의 
				<input type="radio" name="chk_info" value="Y" checked> 예 
				<input type="radio" name="chk_info" value="N"> 아니오
				</div>

				<!-- 이메일 -->
				<div class="form-group">
					<div class="col-md-2 control-label">
						<label id="email">이메일</label>
					</div>
					<div class="col-md-7 mb-3" style="display: inline-block;">
						<input type="email" class="form-control" name="email" id="email">
					</div>
					<button class="ckBtn" type="button" id="emailCheckBtn"
						onclick="checkEmail();">중복확인</button>
				</div>

				<!-- 성별 -->
				<div>
					<div class="col-md-2 control-label">
						<label id="gender">성별</label>
					</div>
					<div class="col-md-7 mb-6">
						<select class="form-control">
							<option class="col-md-7" value="M">남자</option>
							<option class="col-md-7" value="F">여자</option>
						</select>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12 text-center pb-5">
						<input type="submit" value="회원가입" class="btn btn-lg btn-info">
						<input type="reset" value="다시작성" class="btn btn-lg btn-success">
					</div>
				</div>

			</form>
		</div>
		<!-- 회원가입 양식 끝 -->

		<div class="col-md-1"></div>
	</div>

	<script>
 		//아이디 중복확인
 		function checkId()() {
			
		}
 		//이메일 중복확인
 		function checkEmail()() {
			
		}
 	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>