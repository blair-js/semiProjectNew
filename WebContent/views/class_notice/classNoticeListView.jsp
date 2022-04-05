<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.semi.class_notice.model.dto.*, com.semi.common.dto.*"%>

<%
ArrayList<ClassNotice> list = (ArrayList<ClassNotice>) request.getAttribute("list");
PageInfo pi = (PageInfo) request.getAttribute("pi");

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

	<h2 class="text-center">햇님반</h2>
	<p class="page-description text-center">반별 알림장</p>

	<div class="album py-5 bg-light">
		<div class="container">
			<% if (list.isEmpty()) { %>
				<h3>조회된 게시물이 없습니다.</h3>
			<% } else { %>
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<% for (ClassNotice n : list) { %>
					<input type="hidden" name="nNo" value="<%=n.getClassNoticeNo()%>">
					<div class="col">
						<div class="card shadow">
							<div id="img-center">
								<a href="<%=contextPath%>/classNoticeDetail.do?bno="
									style="text-decoration: none"> <img class="card-img-top"
									src="assets/img/gallery/fdog.png">
								</a>
							</div>
							<div class="card-body">
								<p class="card-text text-dark">
									<%=n.getClassNoticeTitle()%><br><%=n.getNoticeWriter()%>
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
			<div class="col-lg-12 col-sm-12 text-lg-end text-center">
				<button style="background-color: white;"
					onclick="location.href='classNoticeEnrollForm.do'">글쓰기</button>
			</div>
			<hr style="background-color: black">
		</div>
	</div>
	<!-- 페이징바 만들기 -->
	<div class="pagingArea mt-3 mb-3" align="center">
		<!-- 맨 처음으로 (<<) -->
		<button class="btn btn-outline-dark"
			onclick="location.href='<%=contextPath%>/classNoticeList.do?currentPage=1'">
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
			onclick="location.href='<%=contextPath%>/classNoticeList.do?currentPage=<%=currentPage - 1%>'">
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
			onclick="location.href='<%=contextPath%>/classNoticeList.do?currentPage=<%=p%>'">
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
			onclick="location.href='<%=contextPath%>/classNoticeList.do?currentPage=<%=currentPage + 1%>'">
			&gt;</button>
		<%
		}
		%>

		<!-- 맨 끝으로 (>>) -->
		<button class="btn btn-outline-dark"
			onclick="location.href='<%=contextPath%>/classNoticeList.do?currentPage=<%=maxPage%>'">
			&gt;&gt;</button>
	</div>
	<%--<script>
		function goDetail() {
			var bno = $(this).children().eq(0).val();
			location.href = "<%= contextPath %>/classNoticeDetail.do?bno=" + bno;
		}
	</script> --%>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>