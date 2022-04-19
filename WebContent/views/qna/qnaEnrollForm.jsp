<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			enterMode: CKEDITOR.ENTER_BR,
			shiftEnterMode : CKEDITOR.ENTER_P
	};
</script>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<h1 id="title" align="center" class="text-primary p-6">Q&A</h1>
	
	<div class="container">
	
		<hr class="bor">
		<!-- ckeditor 사용 -->
		<form id="enrollForm" action="<%= contextPath %>/insertQna.do" method="post">
			<div class="form-group">
				<h3 class="mb-1">제목</h3>
				<input type="text" name="title" class="form-control mb-4" placeholder="제목을 입력하세요" required>
			</div>
			
			<div class="form-group">
				<textarea name="content" id="summernote"></textarea>
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
				<input type="text" id="secretPwd" name="secretPwd" class="mx-4" placeholder="4자리 숫자만 입력해주세요.">
			</div>
			
			<hr>
			
			<div class="row">
				<div class="col-md-12 text-md-end p-3">
					<input class="btn btn-secondary m-1" type="button" value="취소" onclick="location.href='<%=contextPath%>/listQna.do'">
					<input class="btn btn-secondary m-1" type="submit" value="등록">
				</div>
			</div>	
		</form>
		
		<script>
			CKEDITOR.replace("content", ckeditor_config);
		</script>
		
		<script>
			$(function(){				
				$('input:submit').on('click', function(){
					//비밀번호 숫자인지 확인하기 위한 정규식 
					var pwdVal = $('#secretPwd').val();
					var regex = /^[0-9]{4}$/;
					
					if($('input[name=secret]:checked').val() == 'Y'){
						
						if(!regex.test(pwdVal)){
							alert("비밀번호는 숫자 4자리를 입력해주세요.");
							$('#secretPwd').val(pwdVal.replace(regex, ''));
							return false;
						}
					}
					
					//글 내용이 비어있을 경우 -> ckeditor는 CKEDITOR.instances.textarea id 작성.getData()로 글 내용을 확인
					if(CKEDITOR.instances.summernote.getData()  == '' || CKEDITOR.instances.summernote.getData().length == 0){
						alert("글 내용을 입력해주세요.");
						return false;
					}
					
				});
			
			});						
		</script>	
	</div>
		
	<%@ include file="../common/footer.jsp" %>
</body>
</html>