<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#title{
		padding : 20px;
	}
	
	#notice{
		padding : 10px;
		text-align : center;
	}
	
	.table>thead>tr{
		background-color : lightgray;
	}
	
	#allListCount{
		padding : 10px;
	}
	
	#button{
		padding: 10px;
		color: black;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
  	<h1 id="title" align="center"  class="text-primary">공지사항</h1>
	
	<%-- main content 시작 --%>
	
	<%-- container : 부트스트랩에서 반응형으로 사용할 HTML 요소들을 둘러싸는 기본 클래스
		 container는 반응형 고정 너비 컨테이너, container-fluid는 화면 너비 전체를 사용(width 100%) --%>
	<div class = "container" id="notice">	
		
		<%-- row로 감싸서 행을 생성 -> col-md-*, col-lg-*를 사용해 영역을 나눔(디바이스 크기에 따라)--%>
		<div class="row">
			<div class="col-md-4 col-lg-6">
				<%-- text-md(lg/xl)-start : 디바이스 크기에 따라 정렬되는 반응형 정렬 -> 일반 float-left를 쓰면 정렬이 안된다. --%>
				<div class="text-md-start" id="allListCount">
					<b>전체글 : 3개</b>
				</div>
			</div>
			<div class="col-md-4 col-lg-6">
				<div class="dropdown text-md-end" id="search">
					<button class="btn btn-outline-dark btn-sm dropdown-toggle" data-toggle="dropdown" id="searchDrop">
					검색
					<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li role="presentation"><a href="#">제목</a></li>
						<li role="presentation"><a href="#">내용</a></li>
						<li role="presentation"><a href="#">작성자</a></li>
					</ul>
				</div>
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
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>안녕하세요?</td>
					<td>권영아</td>
					<td>1</td>
					<td>2022-04-03</td>
				</tr>
				<tr>
					<td>2</td>
					<td>화이팅</td>
					<td>김지수</td>
					<td>2</td>
					<td>2022-04-03</td>
				</tr>
				<tr>
					<td>3</td>
					<td>감사합니다</td>
					<td>임현빈</td>
					<td>0</td>
					<td>2022-04-03</td>
				</tr>
			</tbody>
		</table>
		
		<%-- 글쓰기 버튼 --%>
		<div class="text-md-end" id="button">
			<button type="button" class="btn btn-outline-dark"><a href="#">글쓰기</a></button>
		</div>
		
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
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>