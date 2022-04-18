<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.qna.model.dto.*"%>
<%
	Qna q = (Qna)request.getAttribute("q");
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
	
	.table-condensed>thead>tr>th,

	.table-condensed>tbody>tr>th,
	
	.table-condensed>tfoot>tr>th,
	
	.table-condensed>thead>tr>td,
	
	.table-condensed>tbody>tr>td,
	
	.table-condensed>tfoot>tr>td { padding: 15px;}
</style>
<script type="text/javascript" src = "./resources/ckeditor/ckeditor.js"></script>
<script>
	var ckeditor_config = {
			height: 600,
			resize_enaleb : false,
			enterMode : CKEDITOR.ENTER_BR,
			shiftEnterMode : CKEDITOR.ENTER_P
			//filebrowerUploadUrl : "/uploadQna.do"
	};
</script>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<h1 id="title" align="center" class="text-primary p-6">Q&A</h1>
	
		<div class="container">
	
			<hr class="bor">
			
			<!-- ckeditor 사용 -->
			<form id="enrollForm" action="<%= contextPath %>/updateQna.do" method="post">
				<input type="hidden" name="qno" value="<%=q.getQnaNo() %>">
				<div class="form-group">
					<h3 class="mb-1">제목</h3>
					<input type="text" name="title" class="form-control mb-4" placeholder="제목을 입력하세요" value="<%=q.getQnaTitle()%>">
				</div>
				
				<div class="form-group">
					<textarea name="content" id="summernote"><%=q.getQnaContent()%></textarea>
				</div>
	
				<hr>
				
				<div class="my-4">
					<span>비밀글 설정</span>
					<input type="radio" class="form-check-input mx-3" id="secret" name="secret" value="N" checked="checked"><span class="ml-3">공개글</span>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" class="form-check-input mx-3" id="secret" name="secret" value="Y"><span class="ml-2">비밀글</span>
				</div>
				
				<hr>
				
				<div class="my-4">
					<span>비밀번호</span>
					<%if(q.getQnaPwd() == null) {%>
					<input type="text" id="secretPwd" name="secretPwd" class="mx-4" placeholder="비밀번호를 설정하세요" value="">
					<%} else {%>
					<input type="text" id="secretPwd" name="secretPwd" class="mx-4" placeholder="비밀번호를 설정하세요" value="<%=q.getQnaPwd()%>">
					<%} %>
				</div>	
				
				<hr>
				
				<div class="row">
					<div class="col-md-12 text-md-end p-3">
						<input class="btn btn-secondary m-1" type="button" value="취소" onclick="location.href='<%=contextPath%>/listQna.do'">
						<input class="btn btn-secondary m-1" type="submit" value="수정">
					</div>
				</div>	
			</form>
		</div>
		
		<script>
			CKEDITOR.replace("content", ckeditor_config);
		</script>
		
		<script>
			$(function(){
				$('input:submit').on('click', function(){
					if($('input[name=secret]:checked').val() == 'Y'){
						$("#secretPwd").attr("required", true);
					}
				})
			
			});
		</script>
		
		<!-- 글 수정하기 -->
		<!-- 수정할 값을 request에서 꺼내서 뿌려줘야한다. -->
		<!-- <form id="enrollForm" action="<= contextPath %>/updateQna.do" method="post" enctype="multipart/form-data">
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
						<%-- 첨부파일이 있는지 없는지 확인해야 한다. --%>
						<%--
							<tr>
								<th>첨부파일</th>
								<td>
									<% if(at != null){ %> <!-- 기존의 첨부파일이 있었을 경우 -> list로 확인해야한다. -->
										<%= at.getOriginName() %> <br>
										<input type='hidden' name='originFile' value='<%=at.getChangeName()%>'>
										<input type='hidden' name='originFileNo' value='<%=at.getFileNo()%>'>
									<% }%>
									<input type="file" name="upFile">
								</td>			
							</tr>
						 --%>
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
			
			
		</form>			
	</div>-->
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>