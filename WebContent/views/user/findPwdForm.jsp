<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
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

.form-findPwd {
	width: 100%;
	max-width: 330px;
	padding: 5px;
	margin: auto;
	margin-bottom: 100px;
	margin-top: 50px;
}

.form-findPwd .checkbox {
	font-weight: 400;
}

.form-findPwd .form-floating:focus-within {
	z-index: 2;
}

.form-findPwd input[type="text"] {
	margin-bottom: 1px;
}

.form-findPwd input[type="password"] {
	margin-bottom: 1px;
}

.rows {
	text-align: center;
}

h5 {
	text-align: center;
	color: gray;
}
main form button b{
	color: #0099FF;
}
.hr1{
	background-color: #FFFFFF;
}
.hr2{
	background-color: #0099FF;
}
</style>
</head>
<body>
	<!-- UserPwdFindFormServlet에서 여기로 넘어옴 -->
	<!-- form에 입력한 데이터를 UserPwdFindServlet으로 보냄 -->
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<!-- 위 div 시작 -->
	<div class="row rows mt-5">
		<!-- 빈 div -->
		<div class="col-lg-2"></div>
		<!-- 아이디찾기 div -->
		<div class="col-lg-4">
			<h3 style="color: #DDDDDD">
				<a href="<%=contextPath%>/findIdForm.do;">아이디 찾기</a>
			</h3>
			<hr class="hr1" style="height: 7px;">
		</div>
		<!-- 비밀번호찾기 div -->
		<div class="col-lg-4">
			<h3>비밀번호 찾기</h3>
			<hr class="hr2" style="height: 7px;">
		</div>
		<!-- 빈 div -->
		<div class="col-lg-2"></div>
	</div>
	<!-- 위 div 끝 -->
	
	<!-- 아래 아이디, 이메일, 비밀번호 작성 영역 시작 -->
	<main class="form-findPwd">
		<form action="<%=contextPath %>/findPwd.do" method="post" onsubmit="return findPwdValidate();">
			<h5 class="h5 mb-3 fw-lighter" style="font-family: 'LeferiPoint-BlackA';">
			비밀번호를 잊으셨나요?<br>
			아래의 정보를입력해주세요.
			</h5>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="userName" name="userName" placeholder="이름을 입력하세요." required>
			</div>
			<div class="form-floating">
				<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력하세요." required>
			</div>
			<div class="form-floating">
				<input type="email" class="form-control mb-4" id="userEmail" name="userEmail" placeholder="이메일 주소를 입력하세요." required>
			</div>
			<button type="submit" class="w-100 btn btn-lg btn-primary mb-2">
				<b>비밀번호 찾기</b>
			</button>
		</form>
	</main>

	<!-- 아이디찾기 버튼 클릭시 UserPwdFindServlet 로 이동 -->
	<script type="text/javascript">
		//form 안의 데이터를 모두 입력했는지 확인하는 함수
		function findPwdValidate() {
			//아이디 입력 여부 확인
			if($('#userName').val() == null){
				alert('이름을 입력해주세요.');
				$('#userName').focus();
				return false;
			}
			//비밀번호 입력 여부 확인
			if($('#userId').val() == null){
				alert('아이디를 입력해주세요.');
				$('#userId').focus();
				return false;
			}
			//이메일 입력 여부 확인
			if($('#userEmail').val() == null){
				alert('이메일을 입력해주세요.');
				$('#userEmail').focus();
				return false;
			}
			//위의 세 조건 모두 통과시
			return true;
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>