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
<!-- ckeditor js 경로 -->
 <script src= "./resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	 <script>
	 	var ckeditor_config = {
	 		height : 600, 					//높이 지정 가능
	 		resize_enaleb : false,			//크기 조절 기능 불가
	 		enterMode : CKEDITOR.ENTER_BR,	
	 		shiftEnterMode : CKEDITOR.ENTER_P,
	 		//filebrowserUploadUrl : '/uploadQna.do'	//ckeditor에는 이미지를 바로 업로드하는 기능이 없어서 javascript로 경로를 설정해줘야 나타난다.
	 	}
	 </script>
	<h1 id="title" align="center" class="text-primary p-6">Q&A</h1>
	
	<div class="container">
	
		<hr class="bor">
		
		<form id="enrollForm" class="form-group" action="<%= contextPath %>/insertQna.do" method="post" enctype="multipart/form-data">
			<div id="titleInput">
				<h3>제목</h3>
				<input type="text" class="form-control form-control-lg rounded-0 mt-1 mb-3" name="title" placeholder="제목을 입력해주세요">
			</div>
			
			<div id="ckeditor">
				<textarea name="content" id="content"></textarea>
			</div>
			
			<hr class="bor">
			
			<div id="secret" class="mt-4 mb-4">
				<span>비밀번호</span>&nbsp;&nbsp;
				<input type="text" class="mx-4" id="pwd" name="pwd" placeholder="비밀번호를 설정하세요.">
			</div>
			
			<div id="secretCheck" class="mb-4">
				<span>비밀글 설정</span>&nbsp;&nbsp;	
				<div class="form-check-inline">
					<input type="radio" class="form-check-input mx-2" id="secret" name="secret" value="N"><span>공개글</span>&nbsp;&nbsp;
					<input type="radio" class="form-check-input mx-2" id="secret" name="secret" value="Y"><span>비밀글</span>
				</div>
			</div>
			
			<hr class="bor">
			
			<div class="row">
				<div class="col-md-12 text-md-end p-3">
					<input class="btn btn-secondary m-1" type="button" value="취소" onclick="location.href='<%=contextPath%>/listQna.do'">
					<input class="btn btn-secondary m-1" type="submit" value="등록">
				</div>
			</div>	
		</form>
		
		<script type="text/javascript">
			CKEDITOR.replace('content', ckeditor_config);			
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