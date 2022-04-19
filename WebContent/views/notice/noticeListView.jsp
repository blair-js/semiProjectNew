<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.notice.model.dto.*, com.semi.common.dto.*"%>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
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
<style>	
	#notice{
		text-align : center;
	}
	
	.table>thead>tr{
		background-color : lightgray;
	}
	
	#search{
		width: 500px;
		margin-left: 16%;
	}
	
	#search>#searchSelect{
		width: 10px;
	}

</style>
</head>
<body>
	<!-- menubar  -->
	<%@ include file="../common/menubar.jsp" %>
	
  	<h1 id="title" align="center"  class="text-primary p-6">공지사항</h1>
	
	<%-- main content 시작 --%>
	
	<%-- container : 부트스트랩에서 반응형으로 사용할 HTML 요소들을 둘러싸는 기본 클래스
		 container는 반응형 고정 너비 컨테이너, container-fluid는 화면 너비 전체를 사용(width 100%) --%>
	<div class = "listArea container p-2" id="notice">	

		<%-- row로 감싸서 행을 생성 -> col-md-*, col-lg-*를 사용해 영역을 나눔(디바이스 크기에 따라)--%>
		<div class="row">
			<!-- 전체글 -->
			<div class="col-md-6 text-md-start">
				<%-- text-md(lg/xl)-start : 디바이스 크기에 따라 정렬되는 반응형 정렬 -> 일반 float-left를 쓰면 정렬이 안된다. --%>
				<div class="m-3 mb-3" id="allListCount">
					<b>전체글 : <%= listCount %>개</b>
				</div>
			</div>
			
			<!-- 검색창 -->
			<div class="col-md-6 mb-3" id="search">	
				<form action="<%=contextPath%>/listNotice.do" method="get">
					<div class="input-group mb-3 input-group-sm">
						<!-- 검색 키워드 선택 select,option -->
						<!-- 선택된 값이 keyword라는 이름으로 넘어옴 -->
						<div class="input-group-prepend">
							<!-- 쿼리에 필드값을 넣기 위해 DB의 컬럼명과 같은 이름으로 value를 넣어준다. -->
							<select class="form-select border-1 rounded-0" id="searchSelect" name="keyword">
								<!-- 담겨있는 keyword의 값이 같으면 selected가 계속 유지되도록 한다. -->
								<option ${(param.keyword == "NOTICE_TITLE") ? "selected" : "" } value="NOTICE_TITLE">제목</option>
								<option ${(param.keyword == "NOTICE_CONTENT") ? "selected" : "" } value="NOTICE_CONTENT">내용</option>
								<option ${(param.keyword == "USER_ID") ? "selected" : "" } value="USER_ID">작성자</option>
							</select>
						</div>
							
						<!-- 검색어 입력 -->
						<!-- 검색어 까지 입력하고 버튼을 누르면 list?keyword=title/content/wirter&searchKey=검색내용 -->	
						<!-- 검색어가 있을 경우 계속 유지되도록 한다. -->		
						<input type="text" class="form-control" id="searchKey" name="searchKey" placeholder="검색어를 입력하세요." value="${param.searchKey}"> 
						<!-- 검색 버튼 --> 
						<input type="submit" class="btn btn-secondary" id="searchBtn" value="검색">					
					</div>	
				</form>	
			</div>
		</div>
		
		<!-- 리스트 테이블 시작 -->
		<%-- table table-striped : 표에 줄무늬 행을 적용한다. 홀수 줄이 회색으로 변한다.
		     table table-bordered : 각 칸 마다 줄을 만들어서 각진 느낌의 게시판 느낌을 형성한다.
		     table  table-hover : striped와 유사하지만 마우스를 가져다 댄 행이 회색으로 변한다.--%>
		<table class="table table-bordered table-hover"> 
			<thead>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<%if(list.isEmpty()){ %>
				<tr>
					<td colspan="5" onclick="event.cancelBubble=true">조회된 리스트가 없습니다.</td>
				<tr>
				<%} else { %>
					<% for(Notice n : list) { %>
					<tr>
						<td class="d-none"><%=n.getNoticeNo() %></td>
						<td><%=n.getRowNo() %></td>
						<td><%=n.getNoticeTitle() %></td>
						<td><%=n.getNoticeWriter() %></td>
						<td><%=n.getCount() %></td>
						<td><%=n.getCreateDate() %></td>
					</tr>
					<%} %>
				<%} %>
			</tbody> 
		</table>

		<%-- 글쓰기 버튼 -> 관리자에게만 보이도록한다. --%>
		<% if(loginUser != null && loginUser.getAdminChecked().equals("Y")){ %>
			<div class="text-md-end" id="button">
				<button type="button" class="btn btn-dark m-2" onclick="location.href='<%=contextPath%>/enrollFormNotice.do'"><b>글쓰기</b></button>
			</div>
		<% } %>
		
		<!-- 페이징바 만들기 -->
		<div class="pagingArea mt-4 mb-4" align="center">
			<!-- 맨 처음으로 (<<) -->
			<button class="btn btn-outline-dark" onclick="location.href='<%=contextPath%>/listNotice.do?currentPage=1&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &lt;&lt; </button>
		
			<!-- 이전 페이지로(<) -->
			<%if(currentPage == 1){ %>
			<button class="btn btn-outline-dark" disabled> &lt; </button>
			<%}else{ %>
			<button class="btn btn-outline-dark" onclick="location.href='<%= contextPath %>/listNotice.do?currentPage=<%= currentPage-1 %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &lt; </button>
			<%} %>
			
			<!-- 페이지 목록 -->
			<%for(int p=startPage; p<=endPage; p++){ %>
				
				<%if(p == currentPage){ %>
				<button class="btn btn-outline-dark" disabled> <%= p %> </button>
				<%}else{ %>
				<button class="btn btn-outline-dark" onclick="location.href='<%=contextPath %>/listNotice.do?currentPage=<%= p %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> <%= p %> </button>
				<%} %>				
			<%} %>
			
			<!-- 다음 페이지로(>) -->
			<%if(currentPage == maxPage){ %>
			<button class="btn btn-outline-dark" disabled> &gt; </button>
			<%}else { %>
			<button class="btn btn-outline-dark" onclick="location.href='<%= contextPath %>/listNotice.do?currentPage=<%= currentPage+1 %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &gt; </button>
			<%} %>
		
			<!-- 맨 끝으로 (>>) -->
			<button class="btn btn-outline-dark" onclick="location.href='<%=contextPath%>/listNotice.do?currentPage=<%= maxPage %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &gt;&gt; </button>
		</div> 	
	</div>
	
	<script type="text/javascript">
   	$(function(){
   		$(".table>tbody>tr").click(function() {
   			var nno = $(this).children().eq(0).text(); 
   			
   			location.href = "<%=contextPath%>/detailNotice.do?nno="+nno; <%--번호도 같이 가져간다.--%>
   		})
   	})
   </script>
	
	<!-- footer  -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>