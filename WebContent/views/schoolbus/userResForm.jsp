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
				<form id="resList" action="/schoolbusAllDelete.do" method="post">
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
									<td><%= re.getBusNo() %></td>
									<td><%= re.getResUserName() %></td>
									<td><%= re.getResUserId() %></td>
									<td><%= re.getResUserPhone() %></td>
									<td><%= re.getBusDailyNo() %></td>
									<td><button class="btn bg-secondary btn-sm" style="color:white;">예약취소</button></td>
								</tr>
							<% } %>
						</tbody>
					</table>
					<button class="btn bg-warning btn-lg" style="color:white;">예약테이블 리셋</button>
				</form>
			</div>
		<% } %>
	</div>
  	<%@ include file = "../common/footer.jsp" %>
</body>
</html>