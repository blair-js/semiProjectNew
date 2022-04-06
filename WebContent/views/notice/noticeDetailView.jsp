<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<div class="row">
			<div class="col-md-12 text-md-end p-3">
				<button class="btn btn-secondary m-1" onclick="location.href='<%=contextPath%>/updateFormNotice.do'"><b>수정</b></button>
				<button class="btn btn-secondary m-1" onclick="location.href='<%=contextPath%>/deleteQna.do'"><b>삭제</b></button>
			</div>
		</div>
		
		<!--  
		<form action="" id="postForm" method="post">
			<input type="hidden" name="nno" value="">
		</form>
		<script>
			function deleteNotice(){
				$("postForm").attr("action", "<%=contextPath%>/deleteNotice.do");
				$("postForm").submit();
			}
		</script> -->
		
		<hr class="bor">
		
		<!-- 글 상세보기 -->
		<table class="table table-condensed pd-1">	
			<tbody>
				<tr id="title">
					<th class="col-md-1"><h3>제목 : </h3></th>
					<td colspan="3"><h3>게시글 제목</h3></td>
				</tr>
				
				<tr id="writer">
					<th><h4>작성자 | </h4></th>
					<td colspan="3"><h4>작성자 이름</h4></td>
				</tr>
				
				<tr>
					<th><h4>작성일 | </h4></th>
					<td><h4>작성일</h4></td>
					<th class="col-md-1"><h4>조회수 | </h4></th>
					<td><h4>3</h4></td>
				</tr>
				
				<tr id="content">
					<td colspan="4">
					입학 상담은 전화와 방문 상담으로 가능합니다. <br><br>
		
										입학 신청을 홈페이지 메인 화면의 입학 신청 버튼을 눌러서 신청해주세요 <br><br>
		
										강아지의 정보를 작성하시면 입학비와 배정될 반이 자동으로 나옵니다. <br><br>
		
										반을 바꾸고 싶거나 가격에 대한 문의가 있으시면 문의 게시판이나 전화를 이용해주세요. <br><br>
		
										감사합니다.<br><br>
					</td>
				</tr>
				<%-- 뿌려줄 첨부파일이 있는지 없는지 확인 --%>
				<%-- for문을 돌려서 첨부파일이 있으면 추가 첨부파일의 수만큼 늘어난다. --%>
				<%-- <td colspan="3">
						<% if(at != null){ %>
						<a download="<%= at.getOriginName() %>" href="<%=contextPath%>/resources/board_upfiles/<%=at.getChangeName()%>"><%= at.getOriginName() %></a>
						<% }else{ %>
						첨부파일이 없습니다.
					<% } %>
				</td> --%>
				<tr id="attachment">
					<th>첨부파일</th>
					<td colspan="3">첨부파일이 없습니다.</td>
				</tr>
			</tbody>	
		</table>
			
		<hr class="bor">
		
		<!-- 목록 버튼-->
		<div class="row">
			<div class="col-md-12 text-md-end p-3">
				<button class="btn btn-secondary" onclick="location.href='<%=contextPath%>/listNotice.do'"><b>목록</b></button>
			</div>
		</div>
	</div>
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>