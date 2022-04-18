<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.notice.model.dto.*, com.semi.common.dto.*"%>
<%
	Notice n = (Notice)request.getAttribute("n");
	//ArrayList<Attachment> atList = (ArrayList<Attachment>)request.getAttribute("atList");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style>
	.bor{
		border: 2px solid gray;
	}
	
	<%-- 부트스트랩 테이블 cell padding 적용 --%>
	.table-condensed>thead>tr>th,

	.table-condensed>tbody>tr>th,
	
	.table-condensed>tfoot>tr>th,
	
	.table-condensed>thead>tr>td,
	
	.table-condensed>tbody>tr>td,
	
	.table-condensed>tfoot>tr>td { padding: 15px;}
	
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<h1 id="title" align="center" class="text-primary p-6">공지사항</h1>

	<div class="container p-2">
		<!-- 수정하기 버튼 -> 관리자 아이디일 때만 보이도록 -->
		<!-- 원래는 nno 값이 같이 넘어가야한다.(함수를 사용하거나 ?nno=사용 -->
		<%if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())) {%>
			<div class="row">
				<div class="col-md-12 text-md-end p-3">
					<button class="btn btn-secondary m-1" onclick="location.href='<%=contextPath%>/updateFormNotice.do?nno=<%=n.getNoticeNo()%>'">수정</button>
					<button class="btn btn-secondary m-1" onclick="location.href='<%=contextPath%>/deleteNotice.do?nno=<%=n.getNoticeNo()%>'">삭제</button>
				</div>
			</div>
		<% } %>

		<hr class="bor">
		
		<!-- 글 상세보기 -->
		<table class="table table-condensed pd-1">	
			<tbody>
				<tr>
					<th class="col-md-1"><h3>제목 : </h3></th>
					<td colspan="3"><h3><%=n.getNoticeTitle() %></h3></td>
				</tr>
				
				<tr>
					<th><h4>작성자 | </h4></th>
					<td colspan="3"><h4><%=n.getNoticeWriter() %></h4></td>
				</tr>
				
				<tr>
					<th><h4>작성일 | </h4></th>
					<td><h4><%=n.getCreateDate() %></h4></td>
					<th class="col-md-1"><h4>조회수 | </h4></th>
					<td><h4><%=n.getCount() %></h4></td>
				</tr>
				
				<tr>
					<td colspan="4"><%=n.getNoticeContent() %></td>
				</tr>								
				<tr>
				<%if(at != null) {%>
					<th>첨부파일</th>
					<td colspan="3">
						<a download="<%= at.getOriginName()%>" href="<%=contextPath%>/resources/notice_upfiles/<%=at.getChangeName()%>"><%= at.getOriginName()%></a>
					</td>
				<%} else {%>
					<th>첨부파일</th>
					<td colspan="3">
						첨부파일이 없습니다.
					</td>
				<%} %>
				</tr>									
			</tbody>	
		</table>
		
		<hr class="bor">
		
	</div>			
		
	<!-- 목록 버튼-->
	<div class="container">
		<div class="col-md-12 text-md-end p-3">
			<button class="btn btn-secondary" onclick="location.href='<%=contextPath%>/listNotice.do?currentPage=1';">목록</button>
		</div>
	</div>
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>