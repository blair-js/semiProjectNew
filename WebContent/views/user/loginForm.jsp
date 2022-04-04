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
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
	
*{
	font-family: 'LeferiPoint-BlackA';
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
  background: #EEEEEE;
  border-radius: 10px;
  margin-bottom: 100px;
  margin-top: 100px;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="text"] {
  margin-bottom: 1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 8px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}


</style>
</head>

<!-- 해당 폼에서 로그인버튼 클릭시 LoginServlet으로 데이터를 보냄! -->
<body class="text-center">
	
	<!-- menubar -->
   	<%@ include file = "../common/menubar.jsp" %>
	
	<main class="form-signin">
	  <form>
	    <h2 class="h2 mb-3 mt-3 fw-normal" style="font-family: 'LeferiPoint-BlackA'">WELCOME</h2>
	    <h6 class="h6 mb-0 fw-normal" style="font-family: 'LeferiPoint-BlackA'">둥글개 둥글개 유치원에 오신 걸 환영합니다.</h6>
	    <h6 class="h6 mb-3 fw-normal" style="font-family: 'LeferiPoint-BlackA'">회원 정보를 입력해 주세요.</h6>
	    <img class="mb-4" src="assets/img/gallery/login2.png" alt="" width="150">
	
	    <div class="form-floating">
	      <input type="text" class="form-control" id="floatingInput" placeholder="아이디를 입력하세요.">
	    </div>
	    <div class="form-floating">
	      <input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호를 입력하세요.">
	    </div>
	
	    <div class="checkbox mb-3">
	      <label>
	        <input type="checkbox" value="remember-me"> 로그인 상태 유지
	      </label>
	    </div>
	    
	    <button class="w-100 btn btn-lg btn-primary mb-2" onclick="goLogin()"><b>로그인</b></button>
	    
	    <div class ="btns" align="center">
			<a href="<%=request.getContextPath() %>/findIdForm.do">아이디찾기 &nbsp;</a>
			<a href="<%=request.getContextPath() %>/findPwdForm.do">비밀번호찾기 &nbsp;</a>
			<a href="<%=request.getContextPath() %>/userEnrollForm.do">회원가입</a>
		</div>

	  </form>
	</main>
	
	<script type="text/javascript">
		//로그인 클릭 이벤트시 실행되는 함수
		function goLogin() {
			location.href="<%= request.getContextPath()%>/login.do;"
		}
	</script>
	
	<!-- footer -->
	<%@ include file = "../common/footer.jsp" %>
	
</body>
</html>