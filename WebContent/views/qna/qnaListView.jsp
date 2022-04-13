<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.qna.model.dto.*, com.semi.common.dto.* "%>
<%
	ArrayList<Qna> list = (ArrayList<Qna>)request.getAttribute("list");
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
<title>Insert title here</title>
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
	<%@ include file="../common/menubar.jsp" %>

	 <h1 id="title" align="center"  class="text-primary p-6">Q&A</h1>
	
	<%-- main content 시작 --%>
	
	<%-- container : 부트스트랩에서 반응형으로 사용할 HTML 요소들을 둘러싸는 기본 클래스
		 container는 반응형 고정 너비 컨테이너, container-fluid는 화면 너비 전체를 사용(width 100%) --%>
	<div class = "container p-2" id="notice">	

		<%-- row로 감싸서 행을 생성 -> col-md-*, col-lg-*를 사용해 영역을 나눔(디바이스 크기에 따라)--%>
		<div class="row">
			<!-- 전체글 -->
			<div class="col-md-6 text-md-start">
				<%-- text-md(lg/xl)-start : 디바이스 크기에 따라 정렬되는 반응형 정렬 -> 일반 float-left를 쓰면 정렬이 안된다. --%>
				<div class="m-3" id="allListCount">
					<b>전체글 : <%=listCount %>개</b>
				</div>
			</div>
			
			<!-- 검색창 -->
			<div class="col-md-6" id="search">	
				<form action="<%=contextPath%>/listQna.do" method="get">
					<div class="input-group mb-3 input-group-sm">
						<!-- 검색 키워드 선택 토글 -->
						<div class="input-group-prepend">
							<select class="form-select border-1 rounded-0" id="searchSelect" name="keyword">
								<option ${(param.keyword == "QNA_TITLE") ? "selected" : "" } value="QNA_TITLE">제목</option>
								<option ${(param.keyword == "QNA_CONTENT") ? "selected" : "" } value="QNA_CONTENT">내용</option>
								<option ${(param.keyword == "USER_ID") ? "selected" : "" } value="USER_ID">작성자</option>
							</select>
						</div>
							
						<!-- 검색어 입력 -->		
						<input type="text" class="form-control" id="searchKey" name="searchKey" placeholder="검색어를 입력하세요." value="${param.searchKey }">	
						<!-- 검색 버튼 --> 
						<input type="submit" class="btn btn-secondary" id="searchBtn" value="검색">							
					</div>	
				</form>	
			</div>
		</div>
		
		<%-- table table-striped : 표에 줄무늬 행을 적용한다. 홀수 줄이 회색으로 변한다.
		     table table-bordered : 각 칸 마다 줄을 만들어서 각진 느낌의 게시판 느낌을 형성한다.
		     table  table-hover : striped와 유사하지만 마우스를 가져다 댄 행이 회색으로 변한다.--%>
		<table class="table table-bordered table-hover"> 
			<thead>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>답변여부</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
			<!-- 넘어오는 글이 비밀글인 경우-> 비밀글이 Y인 경우 앞에 자물쇠 이미지와 비밀번호를 작성하라는 alret창 -->
			<% if(list.isEmpty()) {%> <!-- 비어있는 경우 -->
				<tr>
					<td colspan="6"> 조회된 리스트가 없습니다.</td>
				</tr>
			<%} else{ %>
				<% for(Qna q : list) {%>
				 	<% if(q.getQnaSecret().equals("Y")) { %> <!-- 자물쇠 아이콘이 나오기 위한 if -> secret 상태가 y인 경우  -->
				 	<tr>
				 		<td class="d-none"><%= q.getQnaNo()%></td>
				 		<td><%= q.getRowNo()%></td>
						<td>
							<i class="bi bi-lock-fill"></i> <!-- 자물쇠 아이콘 나옴 -->
							<%= q.getQnaTitle()%>
						</td>
						<% if(q.getAnswer() != null) {%>
							<td>답변 완료</td>
						<% } else{ %>
							<td>미답변</td>
						<%} %>
						<td><%= q.getQnaWriter()%></td>
						<td><%= q.getCount()%></td>
						<td><%= q.getCreateDate()%></td>						 		
				 	</tr>
				 	<%} else { %> <!-- 비밀 글이 아닌 경우 -->
				 	<tr>
				 		<td class="d-none"><%= q.getQnaNo()%></td>
				 		<td><%= q.getRowNo()%></td>
						<td><%= q.getQnaTitle()%></td>
						<% if(q.getAnswer() != null) {%>
							<td>답변 완료</td>
						<% } else{ %>
							<td>미답변</td>
						<%} %>
						<td><%=q.getQnaWriter()%></td>
						<td><%=q.getCount()%></td>
						<td><%=q.getCreateDate()%></td>						 		
				 	</tr>
				 	<%} %>
				<%} %>
			<% } %>
			</tbody>
		</table>

		<%-- 글쓰기 버튼 -> 로그인한 사람에게만 보이도록한다. --%>
		<%if(loginUser != null){ %>
		<div class="text-md-end" id="button">
			<button type="button" class="btn btn-dark m-2" onclick="location.href='<%=contextPath%>/enrollFormQna.do'">글쓰기</button>
		</div>
		<% } %>
		
			<!-- 페이징바 만들기 -->
		<div class="pagingArea mt-4 mb-4" align="center">
			<!-- 맨 처음으로 (<<) -->
			<button class="btn btn-outline-dark" onclick="location.href='<%=contextPath%>/listQna.do?currentPage=1&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &lt;&lt; </button>
		
			<!-- 이전 페이지로(<) -->
			<%if(currentPage == 1){ %>
			<button class="btn btn-outline-dark" disabled> &lt; </button>
			<%}else{ %>
			<button class="btn btn-outline-dark" onclick="location.href='<%= contextPath %>/listQna.do?currentPage=<%= currentPage-1 %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &lt; </button>
			<%} %>
			
			<!-- 페이지 목록 -->
			<%for(int p=startPage; p<=endPage; p++){ %>
				
				<%if(p == currentPage){ %>
				<button class="btn btn-outline-dark" disabled> <%= p %> </button>
				<%}else{ %>
				<button class="btn btn-outline-dark" onclick="location.href='<%=contextPath %>/listQna.do?currentPage=<%= p %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> <%= p %> </button>
				<%} %>				
			<%} %>
			
			<!-- 다음 페이지로(>) -->
			<%if(currentPage == maxPage){ %>
			<button class="btn btn-outline-dark" disabled> &gt; </button>
			<%}else { %>
			<button class="btn btn-outline-dark" onclick="location.href='<%= contextPath %>/listQna.do?currentPage=<%= currentPage+1 %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &gt; </button>
			<%} %>
		
			<!-- 맨 끝으로 (>>) -->
			<button class="btn btn-outline-dark" onclick="location.href='<%=contextPath%>/listQna.do?currentPage=<%= maxPage %>&keyword=${param.keyword}&searchKey=${param.searchKey}'"> &gt;&gt; </button>
		</div> 	
	</div>	
	
	<script type="text/javascript">
   	$(function(){
   		$(".table>tbody>tr").click(function() {
   			var nno = $(this).children().eq(0).text(); 
   			
   			if(n.getQnaSecret() == "Y"){   			
   				location.href = "<%= contextPath%>/PwdCheckQna.do?nno="+nno; <%--번호도 같이 가져간다. -> 그 게시글 번호의 비밀번호를 확인 --%>
   			}else {
   				location.href = "<%= contextPath%>/detailQna.do?nno="+nno; <%--번호도 같이 가져간다.--%>
   			}
   		})
   	})
   </script>
	
	 <%@ include file="../common/footer.jsp" %>
</body>
</html>