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

.form-findId {
  width: 100%;
  max-width: 330px;
  padding: 5px;
  margin: auto;
  margin-bottom: 100px;
  margin-top: 50px;
}

.form-findId .checkbox {
  font-weight: 400;
}

.form-findId input[type="text"] {
  margin-bottom: 1px;
}

.form-findId input[type="password"] {
  margin-bottom: 8px;
}

.row{
	text-align: center;
}
.btn{
	transition: color .15s ease-in-out, background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
</style>
</head>

<body>

	<!-- menubar -->
	<%@ include file = "../common/menubar.jsp" %>
	
	
	<div class="row mt-5">
	         
	         <div class="col-lg-2">
	         </div>
	         <div class="col-lg-4">
		         <h3>아이디 찾기</h3>
		         <hr class="pd-0" style="height: 7px; background-color:#0099FF">
	       	 </div>
	         <div class="col-lg-4">
	            <h3 style="color:#DDDDDD">비밀번호 찾기</h3>
	            <hr style="height: 7px; background:#FFFFFF ">
         	 </div>
         	 <div class="col-lg-2">
	         </div>
        </div>
        
 	<main class="form-findId">
	  <form style="text-align: center; color:gray;">
	  	<div class="mb-2">
		  	<b style="color:#0d6efd">김지수</b>고객님의<br> 
		  	아이디 찾기가완료되었습니다.<br>
	  	</div>
	  	<div class="mb-4">
		  	가입하신 아이디는<br>
			<b style="color:#0d6efd">jisu</b>
			입니다.
		</div>
		
	    <button type="button" class="w-100 btn btn-lg mb-2" onclick="goLogin()" style="color:#0099FF; border-color: #0d6efd;">
	    <b>로그인 화면으로 돌아가기</b>
	    </button>
	    
	  </form>
	</main>
	
	<!-- 아이디찾기 버튼 클릭시 UserIdFindServlet 로 이동 -->
	<script type="text/javascript">
		function goLogin() {
			location.href="<%= request.getContextPath()%>/loginForm.do;"
		}
	</script>
	
	<!-- footer -->
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>