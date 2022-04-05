<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					<b>전체글 : 3개</b>
				</div>
			</div>
			
			<!-- 검색창 -->
			<div class="col-md-6" id="search">	
				<form>
					<div class="input-group mb-3 input-group-sm">
						<!-- 검색 키워드 선택 토글 -->
						<div class="input-group-prepend">
							<select class="form-select border-1 rounded-0" id="searchSelect">
								<option selected value="title">제목</option>
								<option value="content">내용</option>
								<option value="writer">작성자</option>
							</select>
						</div>
							
						<!-- 검색어 입력 -->		
						<input type="text" class="form-control" id="searchbar" placeholder="검색어를 입력하세요.">	
						<!-- 검색 버튼 --> 
						<a href="#" class="btn btn-secondary" role="button" id="searchBtn">검색</a>							
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
				<!-- <if(list.isEmpty()) {> //비어있는 경우
					<tr>
						<td colspan="6"> 조회된 리스트가 없습니다.</td>
					</tr>
					<else{>
						 <if(n.getQnaSecret() == "Y") {> //자물쇠 아이콘이 나오기 위한 if -> secret 상태가 y인 경우
						 	<for(Qna q : list) {>
						 	<tr>
						 		<td><q.getQnaNo()></td>
								<td>
									<i class="bi bi-lock-fill"></i> //자물쇠 아이콘 나옴
									<q.getQnaTitle()>
								</td>
								<td><q.getQnaWriter()></td>
								<td><q.getCount()></td>
								<td><q.getCreateDate()</td>						 		
						 	</tr>
						 <else { //비밀글 아닌 경우
						 	<for(Qna q : list) {>
						 	<tr>
						 		<td><q.getQnaNo()></td>
								<td><q.getQnaTitle()></td>
								<if(q.getQnaReply != null){>
									<td>답변 완료</td>
								<} else{>
									<td>미답변</td>
								<td><q.getQnaWriter()></td>
								<td><q.getCount()></td>
								<td><q.getCreateDate()</td>						 		
						 	</tr>
						 -->
				<tr>
					<td>3</td>
					<td>
						<i class="bi bi-lock-fill"></i>
						가격문의 드립니다.
					</td>
					<td>미답변</td>
					<td>권영아</td>
					<td>0</td>
					<td>2022-04-03</td>
				</tr>
				<tr>
					<td>2</td>
					<td>방문 상담 문의 합니다.</td>
					<td>답변완료</td>
					<td>김지수</td>
					<td>2</td>
					<td>2022-04-03</td>
				</tr>
				<tr>
					<td>1</td>
					<td>
						<i class="bi bi-lock-fill"></i>
						반을 바꿀수는 없나요?
					</td>
					<td>답변완료</td>
					<td>임현빈</td>
					<td>2</td>
					<td>2022-04-03</td>
				</tr>
			</tbody>
		</table>

		<%-- 글쓰기 버튼 -> 로그인한 사람에게만 보이도록한다. --%>
		<!-- <if(loginUser != null){> -->
		<div class="text-md-end" id="button">
			<button type="button" class="btn btn-dark m-2" onclick="location.href='<%=contextPath%>/enrollFormQna.do'">글쓰기</button>
		</div>
		<!-- <}> -->
		
		<%-- 부트스트랩에서 제공하는 페이지셔닝 --%>
		<%-- 페이지에 텍스트 대신 아이콘이나 기호를 사용하고 싶다면 aria 속성 및 .sr-only 유틸리티와 함께 적절한 화면 판독을 해야한다.--%>
		<%-- nav 태그의 경우 정렬할 때는 justify-content-* 사용 --%>
		<nav arial-label="pageCount">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<a class="page-link" href="#" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				        <span class="sr-only"></span>
				    </a>
				</li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item">
					<a class="page-link" href="#" arial-label="Next">
						<span aria-hidden="true">&raquo;</span>
						<span class="sr-only"></span>
					</a>
				</li>
			</ul>
		</nav>
	</div>	
	
	<script type="text/javascript">
   	$(function(){
   		$(".table>tbody>tr").click(function() {
   			var nno = $(this).children().eq(0).text(); 
   			
   			if(nno == 3){
   			//if(n.getQnaSecret() == "Y"){   			
   				location.href = "<%=contextPath%>/PwdCheckQna.do?nno="+nno; <%--번호도 같이 가져간다. -> 그 게시글 번호의 비밀번호를 확인 --%>
   			}else {
   				location.href = "<%=contextPath%>/detailQna.do?nno="+nno; <%--번호도 같이 가져간다.--%>
   			}
   		})
   	})
   </script>
	
	 <%@ include file="../common/footer.jsp" %>
</body>
</html>