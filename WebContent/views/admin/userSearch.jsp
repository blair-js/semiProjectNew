<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.user.model.dto.*"%>
    
     <%@ page import="java.util.ArrayList, com.semi.common.dto.*" %>
     
    <% 
	ArrayList<User> list = (ArrayList<User>) request.getAttribute("list"); 
    PageInfo pi = (PageInfo)request.getAttribute("pi");
  	
  	int listCount = pi.getListCount();
  	int currentPage = pi.getCurrentPage();
  	int maxPage = pi.getMaxPage();
  	int startPage = pi.getStartPage();
  	int endPage = pi.getEndPage();
  	
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style type="text/css">
.tftable {
	font-size: 12px;
	color: #333333;
	width: 100%;
	border-width: 1px;
	border-color: #a9a9a9;
	border-collapse: collapse;
	border-width: 1px;
}

.tftable th {
	font-size: 12px;
	background-color: #FDC800;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9a9a9;
	text-align: center;
}

.tftable tr {
	background-color: #ffffff;
}

.tftable td {
	font-size: 12px;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9a9a9;
}

td {
	height: 30px
}

#center {
	text-align: center;
	margin: auto;
	justify-content: center;
	display: flex;
}
</style>
</head>
<body>

<%@ include file="../common/menubar.jsp"%>

<div class="container">
		<!-- 컨테이너 시작 div -->

		<img class="d-block mx-auto mt-7 mb-4" src="assets/img/gallery/adminDog.png" alt="강아지로고" width="72" height="70">
		<h1 class="magin" id="center" style="margin: 40px">회원 정보 조회</h1>

		<hr style="height: 7px; color: #FDC800;">

		<div class="px-6 py-5 my-4 text-center">
			<!-- br 적용 시 체크박스 2, 3이 같이 내려오기에 중간에 여백을 위한 div  줄 바꿈 -->
			<p class="display-5 fw-bold"></p>

			
			
			
			<table class="tftable" border="1">
				<tr>
					<th style="" width=200px">회원번호</th>
					<th style="" width=200px">아이디</th>
					<th style="" width=200px">이름</th>
					<th style="" width=200px">전화번호</th>
					<th style="" width=200px">가입일</th>
				</tr>
		
				<% for(User u : list) { %>
				<tr>
					<td><%=u.getUserNo() %></td>
					<td><%=u.getUserId() %></td>
					<td><%=u.getUserName() %></td>
					<td><%=u.getPhone() %></td>
					<td><%=u.getEnrollDate() %></td>
				</tr>
			<% } %>
			</table>
				
		

		</div>

	</div>
	
		<!-- 페이징바 만들기 -->
		<div class="pagingArea" align="center">
			<!-- 맨 처음으로 (<<) -->
			<button style="color : #FFFFFF; background-color: #FDC800" onclick="location.href='<%=contextPath%>/userSearchForm.do?currentPage=1'"> &lt;&lt; </button>
		
			<!-- 이전페이지로(<) -->
			<%if(currentPage == 1){ %>
			<button style="color : #FFFFFF; background-color: #FDC800" disabled> &lt; </button>
			<%}else{ %>
			<button style="color : #FFFFFF; background-color: #FDC800" onclick="location.href='<%= contextPath %>/userSearchForm.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
			<%} %>
			
			<!-- 페이지 목록 -->
			<%for(int p=startPage; p<=endPage; p++){ %>
				
				<%if(p == currentPage){ %>
				<button style="color : #FFFFFF; background-color: #FDC800" disabled> <%= p %> </button>
				<%}else{ %>
				<button style="color : #FFFFFF; background-color: #FDC800" onclick="location.href='<%=contextPath %>/userSearchForm.do?currentPage=<%= p %>'"> <%= p %> </button>
				<%} %>
				
			<%} %>
			
			<!-- 다음페이지로(>) -->
			<%if(currentPage == maxPage){ %>
			<button style="color : #FFFFFF; background-color: #FDC800" disabled> &gt; </button>
			<%}else { %>
			<button style="color : #FFFFFF; background-color: #FDC800" onclick="location.href='<%= contextPath %>/userSearchForm.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
			<%} %>
		
			<!-- 맨 끝으로 (>>) -->
			<button style="color : #FFFFFF; background-color: #FDC800" onclick="location.href='<%=contextPath%>/userSearchForm.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>

		</div> 
	<!-- 컨테이너 끝 div -->


	<div class="px-4 py-1 my-5 text-center">
		<p class="display-5 fw-bold"></p>
	</div>


<%@ include file="../common/footer.jsp"%>
</body>
</html>