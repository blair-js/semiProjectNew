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
	
	.table-condensed>thead>tr>th,

	.table-condensed>tbody>tr>th,
	
	.table-condensed>tfoot>tr>th,
	
	.table-condensed>thead>tr>td,
	
	.table-condensed>tbody>tr>td,
	
	.table-condensed>tfoot>tr>td { padding: 15px;}
</style>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 

<!-- summernote 사용을 위한 import -->
<link rel="stylesheet" href="./resources/summernote/css/summernote-lite.css">
<script src="./resources/summernote/js/summernote-lite.js"></script>
<script src="./resources/summernote/lang/summernote-ko.KR.js"></script>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<h1 id="title" align="center" class="text-primary p-6">Q&A</h1>
	
	<div class="container">
	
		<hr class="bor">
		<!-- summernote를 사용하기 위해 선언, 호출 -> textarea or div 두 가지의 방법이 있다. -->
		<form id="enrollForm" action="<%= contextPath %>/insertQna.do" method="post">
			<textarea name="editordata"" id="summernote"></textarea>
		</form>
		
		<script>
			//summernote 웹 에디터 로딩
			$(document).ready(function() {
				$('#summernote').summernote({
					height: 300,		//에디터 높이
					minHeight: null,	//최소 높이
					maxHeight: null,	//최대 높이
					focus: true,		//에디터 로딩 후 포커스를 맞출지 여부
					lang: "ko-KR",		//한글 설정
					placeholder: '내용을 입력하세요.'
				});
			});
			
		</script>
		<!-- 글 작성하기 -->
		<!-- <form id="enrollForm" action="<%= contextPath %>/insertQna.do" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<table class="table table-condensed table-borded pd-1">	
					<tbody>
						<tr>
							<th class="col-md-1"><h3>제목 : </h3></th>
							<td><input type="text" class="form-control form-control-lg rounded-0 mt-3" name="title" placeholder="제목을 입력해주세요"></td>
						</tr>
						<tr>
							<td colspan="2">
								<label for="comment"><h3>내용 : </h3></label>
								<textarea class="form-control form-control-lg rounded-0" name="content" rows="20" style="resize:none"></textarea>
							</td>
						</tr>
						<tr>
							<td>첨부파일1</td>
							<td><input type="file" name="upfile1"></td>
						</tr>
						<tr>
							<td>첨부파일2</td>
							<td><input type="file" name="upfile2"></td>
						</tr>
						<tr>
							<td>첨부파일3</td>
							<td><input type="file" name="upfile3"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="text" id="pwd" name="pwd" placeholder="비밀번호를 설정하세요."></td>
						</tr>
						<tr>
							<td>비밀글설정</td>
							<td colspan="2">
								<div class="form-check-inline">
									<input type="radio" class="form-check-input" id="secret" name="secret" value="N"><span class="ml-3">공개글</span>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" class="form-check-input" id="secret" name="secret" value="Y"><span class="ml-2">비밀글</span>
								</div>
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
			
			<hr class="bor">
			
			<div class="row">
				<div class="col-md-12 text-md-end p-3">
					<input class="btn btn-secondary m-1" type="button" value="취소" onclick="location.href='<%=contextPath%>/listQna.do'">
					<input class="btn btn-secondary m-1" type="submit" value="등록">
				</div>
			</div>			
		</form>	 -->		
	</div>
		
	<%@ include file="../common/footer.jsp" %>
</body>
</html>