<%@page import="com.semi.user.model.dto.Dog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  import = "com.semi.user.model.dto.*" %> -->

<%
//User user = (User)request.getAttribute("user");
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

	<!-- ( br말고 마진, 패딩 ) 컨테이너 너비 재확인 세션영역안에 넣고 활용  -->

	<%@ include file="../common/menubar.jsp"%>

	<div class="container">
		<!-- 컨에이터 div 시작 -->

		<div class="px-3 py-3 my-4"></div>

		<h1 class="magin" id="center" style="margin: 70px">My Page</h1>
		<!-- br 대신 margin을 사용 -->

		<br>

		<form action="<%=request.getContextPath()%>" method="post">

			<pre>
			<h3 id=center style="float: left">관리자 <b style="color:#0099FF"><%=loginUser.getUserId() %></b>&nbsp님의 마이페이지입니다.</h3>
		</pre>

			<hr style="height: 7px; color: #FDC800" ;  id="center">

			<br>

			<div class="px-1 py-3 my-4">

				<div class="col-sm row gx-1" style="float: left">
					<img src="assets/img/gallery/admin_page_dog.jpg" height="480px" />

				</div>

				<div class="col-sm row gx-10" style="float: right">

					<h4 id="center">이름 :&nbsp<b style="color:#0099FF"><%=loginUser.getUserName() %></b></h4>

					<br> <br>

					<h4 id="center">아이디 :&nbsp <b style="color:#0099FF"><%=loginUser.getUserId() %></b></h4>

				</div>
			</div>
		</form>

		<br> <br>
	</div>

	<br>

	<br>
	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
		id="center" onclick="goSnackInsert()">간식 추가</button>
	<br>
	<br>

	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
		id="center" onclick="goSnackOrder()">회원 간식 구매목록</button>
	<br>
	


	<br>


	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
		id="center" onclick="goUserSearch()">회원 정보 조회</button>
	<br>
	
	<br>
	

	<button class="btn btn-outline-warning btn-lg" style="width: 20%"
		id="center" onclick="goReForm();">
		회원 통학 버스 이용 내역
	</button>

	<!--  		
	<button  class="btn btn-outline-warning btn-lg" style="width: 20%" id="center" onclick="goUserList()">
		회원조회
	</button> <br>
	 -->



	</div>


	<div class="px-6 py-5 my-4 text-center">
		<!-- br 적용 시 체크박스 2, 3이 같이 내려오기에 중간에 여백을 위한 div  줄 바꿈을 위해 b -->
		<p class="display-5 fw-bold"></p>
	</div>


	</div>
	<!-- 컨에이터 div 끝 -->

	<script>
	
			 function goUserList(){ //회원목록을 위한 서블릿 
				//location.href="<%=request.getContextPath()%>/userListForm.do;"	
			}	
						
			function goSnackOrder(){ //회원의 간식 구매 목록을 확인하기 위한 서블릿
				location.href="<%=request.getContextPath()%>/snackOrderListForm.do"
			}
			
			function goSnackInsert(){ //새로운 간식 추가하기위한 서블릿
				location.href="<%=request.getContextPath()%>/snackInsertForm.do"
			}
			function goReForm(){
				console.log("실행 확인");
				location.href="/reservationList.do"
			}
			
			function goUserSearch(){ //회원의 간식 구매 목록을 확인하기 위한 서블릿
				location.href="<%=request.getContextPath()%>/userSearchForm.do"
			 
			}
			
	</script>


	<%@ include file="../common/footer.jsp"%>


</body>
</html>