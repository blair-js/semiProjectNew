<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.semi.class_notice.model.dto.*, com.semi.common.dto.*"%>

<%
ArrayList<ClassNotice> list = (ArrayList<ClassNotice>) request.getAttribute("list");
PageInfo pi = (PageInfo) request.getAttribute("pi");
String classname = request.getParameter("classname");

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
<style>
#img-center {
	width: 18rem;
	object-fit: cover;
	margin: auto;
}

button:hover {
	color: orange;
}
</style>

</head>
<body>

	<%@ include file="../common/menubar.jsp"%>
	
	<%if(classname.equals("햇님반")) { %>
	<h2 class="text-center"><img src="assets/img/gallery/sun.png" style="height:80px;" alt="logo" /> 햇님반</h2>
	<% } else if(classname.equals("달님반")){ %>
	<h2 class="text-center"><img src="assets/img/gallery/moon.png" style="height:80px;" alt="logo" /> 달님반</h2>
	<% } else { %>
		<h2 class="text-center"><img src="assets/img/gallery/star.png" style="height:80px;" alt="logo" /> 별님반</h2>
	<% } %>
	<p class="page-description text-center">반별 알림장</p>
	<div class="album py-5 bg-light">
		<div class="container">
			<% if (list.isEmpty()) { %>
				<h3>조회된 게시물이 없습니다.</h3>
			<% } else { %>
				<div class="col-md-6" id="search">	
					<form action="" method="get" id="searchForm">
						<div class="input-group mb-3 input-group-sm">
							<!-- 검색 키워드 선택 토글 -->
							<div class="input-group-prepend">
								<select class="form-select border-1 rounded-0" id="searchSelect" name="keyword">
									<option ${(param.keyword == "CLASS_NOTICE_TITLE") ? "selected" : "" } value="CLASS_NOTICE_TITLE">제목</option>
									<option ${(param.keyword == "USER_ID") ? "selected" : "" } value="USER_ID">작성자</option>
								</select>
							</div>
							<input type="hidden" name="classname" value="<%=classname %>">	
							<!-- 검색어 입력 -->		
							<input type="text" class="form-control" id="searchKey" name="searchKey" placeholder="검색어를 입력하세요." value="${param.searchKey }">	
							<!-- 검색 버튼 --> 
							<input type="button" class="btn btn-secondary" onclick="goSearch();" id="searchBtn" value="검색">							
						</div>	
					</form>
				</div>
				<script>
					// 검색어를 입력 했을 경우에만 검색 버튼 눌리도록 설정, 값이 비어있으면 화면전환 X
					function goSearch(){
						if($("#searchKey").text().length() != 0){
							$("#searchForm").attr("action", "/classNoticeList.do");
							$("#searchForm").submit();
						}
					}
				
				</script>
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<% for (ClassNotice n : list) { %>
					<div class="col">
					<input type="hidden" name="nNo" value="<%=n.getClassNoticeNo()%>">
						<div class="card shadow">
							<div id="img-center" style="text-align:center">
							<% if(n.getTitleImg() != null) { %>
								<a href="#"
									style="text-decoration: none"> <img class="img-thumbnail mt-3" style="width:100%; height:16rem;"
									src="/resources/board_upfiles/<%= n.getTitleImg() %>">
								</a>
							<% } %>
							</div>
							<div class="card-body">
								<p class="card-text text-dark">
									<%=n.getClassNoticeTitle()%>
									<%-- 댓글이 있을경우에만 댓글의 개수 화면에 출력하도록 구현 --%>
									<%if(n.getReplyCount() != 0) { %>
									<small class="text-warning">[<%= n.getReplyCount() %>]</small>
									<% } %>
									
									<br><%=n.getNoticeWriter()%>
								</p>
								<div class="d-flex justify-content-between align-items-center">
									<small class="text-muted"><span class="date"><%=n.getCreateDate()%></span>
										| <span class="count text-warning"><%=n.getCount()%></span></small>
								</div>
							</div>
						</div>
					</div>
				<% } %>
			<% } %>
			</div>
			<!-- 로그인한 회원 중 관리자만 글쓰기 버튼이 보이도록 설정 -->
			<div class="col-lg-12 col-sm-12 text-lg-end text-center">
			<% if(loginUser.getAdminChecked().equals("Y")){ %>
				<button style="background-color: white;" class="mt-3"
					onclick="location.href='/classNoticeEnrollForm.do?classname=<%=classname %>'">글쓰기</button>
			<% } %>
			</div>
			<hr style="background-color: black">
		</div>
	</div>
	<!-- 페이징바 만들기 -->
	<div class="pagingArea mt-3 mb-3" align="center">
		<!-- 맨 처음으로 (<<) -->
		<button class="btn btn-outline-dark"
			onclick="location.href='<%=contextPath%>/classNoticeList.do?classname=<%=classname %>&currentPage=1'">
			&lt;&lt;</button>

		<!-- 이전페이지로(<) -->
		<%
		if (currentPage == 1) {
		%>
		<button class="btn btn-outline-dark" disabled>&lt;</button>
		<%
		} else {
		%>
		<button class="btn btn-outline-dark"
			onclick="location.href='<%=contextPath%>/classNoticeList.do?classname=<%=classname %>&currentPage=<%=currentPage - 1%>'">
			&lt;</button>
		<%
		}
		%>

		<!-- 페이지 목록 -->
		<%
		for (int p = startPage; p <= endPage; p++) {
		%>

		<%
		if (p == currentPage) {
		%>
		<button class="btn btn-outline-dark" disabled>
			<%=p%>
		</button>
		<%
		} else {
		%>
		<button class="btn btn-outline-dark"
			onclick="location.href='<%=contextPath%>/classNoticeList.do?classname=<%=classname %>&currentPage=<%=p%>'">
			<%=p%>
		</button>
		<%
		}
		%>

		<%
		}
		%>

		<!-- 다음페이지로(>) -->
		<%
		if (currentPage == maxPage) {
		%>
		<button class="btn btn-outline-dark" disabled>&gt;</button>
		<%
		} else {
		%>
		<button class="btn btn-outline-dark"
			onclick="location.href='<%=contextPath%>/classNoticeList.do?classname=<%=classname %>&currentPage=<%=currentPage + 1%>'">
			&gt;</button>
		<%
		}
		%>

		<!-- 맨 끝으로 (>>) -->
		<button class="btn btn-outline-dark"
			onclick="location.href='<%=contextPath%>/classNoticeList.do?classname=<%=classname %>&currentPage=<%=maxPage%>'">
			&gt;&gt;</button>
	</div>
	<script>
	// 게시글 클릭시 게시글 번호 넘겨주기 위한 함수
		$(function(){
			$(".col").click(function(){
				var nno = $(this).children().eq(0).val();
				location.href="/classNoticeDetail.do?nno=" +nno+"&classname=<%=classname%>";
			})
		})
	</script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>