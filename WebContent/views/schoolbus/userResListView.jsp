<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.schoolbus.model.dto.*"%>
<%
// 예약 회원 목록 담아서 뿌려준다
	ArrayList<UserReservation> list = (ArrayList<UserReservation>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<style>
	#res-container{
		text-align:center;
		margin:auto;
	}
</style>
<script src="https://kit.fontawesome.com/96b0e9ec8b.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp"%>
	<div class="container" id="res-container">
		<h2><i class="fa-solid fa-bus"></i> 통학버스 예약 회원 목록</h2>
		<% if(list.isEmpty()) { %>
			<h3>예약을 신청한 회원이 없습니다.</h3>
		<% } else { %>
			<!-- 예약 회원 전체 삭제, 한명만 삭제 두가지 구현 -->
			<div class="mb-5">
				<form id="resList" action="" method="post">
					<table class="table table-bordered" style="border:solid 1px #808080;">
						<thead class="bg-secondary" style="color:white;">
							<tr>
								<th>예약번호</th>
								<th>회원 이름</th>
								<th>회원 아이디</th>
								<th>회원 전화번호</th>
								<th>예약 차량</th>
								<th>회원별 예약 취소</th>
							</tr>
						</thead>
						<tbody>
							<% for(UserReservation re : list) { %>
								<tr>
									<td style="font-size:1.2rem;"><%= re.getBusNo() %></td>
									<td style="font-size:1.2rem;"><%= re.getResUserName() %></td>
									<td style="font-size:1.2rem;"><%= re.getResUserId() %></td>
									<td style="font-size:1.2rem;"><%= re.getResUserPhone() %></td>
									<td style="font-size:1.2rem;"><%= re.getBusDailyNo() %></td>
									<td style="font-size:1.2rem;"><input class="btn btn-secondary btn-sm" type="button" onclick="deleteOne('<%= re.getBusNo() %>', '<%= re.getBusDailyNo() %>');" value="삭제"></td>
								</tr>
							<% } %>
						</tbody>
					</table>
					<button onclick="allDelete();" class="btn bg-warning btn-lg" style="color:white;">예약내역 전체삭제</button>
				</form>
			</div>
		<% } %>
	</div>
  	<%@ include file = "../common/footer.jsp" %>
  	<script>
  		function allDelete(){
  			var pwd = prompt("비밀번호를 입력하세요.");
			
  			if(pwd == '<%= loginUser.getUserPwd() %>'){
  				$("#resList").attr("action", "/schoolbusAllDelete.do");
  				$("#resList").submit();
  			}else if(pwd != '<%= loginUser.getUserPwd() %>'){
	  			alert("비밀번호를 잘못 입력하셨습니다.");

  		  	}

  			} 

  		// 회원 개별 삭제 기능 구현 함수
  		// 위에서 삭제 버튼 클릭시 매개변수로 버스 예약번호와 버스일정내용을 넘겨준다.
  		function deleteOne(bno, content){
  			var ans = confirm("선택하신 회원의 예약을 취소하시겠습니까?");
  			if(!ans) return false;
			
  			location.href = "resdelete.do?bno="+bno+"&content="+content;
  		}
  	</script>
</body>
</html>