<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String classname = (String)request.getAttribute("classname"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<!-- 스마트 에디터 사용하기 위해 스크립트에 설정 -->
<script type="text/javascript"
	src="./resources/smartEditor/js/HuskyEZCreator.js" charset="utf-8"></script>

</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="container" id="container">
		<form id="frm" action="classNoticeInsert.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="classname" value="<%=classname %>">
		<input type="hidden" name="writer" value="<%= loginUser.getUserNo()%>">
			<p>제목</p>
			<input type="text" id="title" name="title" class="mb-3"
				placeholder="게시글 제목을 입력하세요." style="width: 100%;">
			<div id="smarteditor">
				<p>내용</p>
				<!-- acept 속성을 통해 원하는 확장자로만 제한 (image/* : 이미지 파일들만 업로드 가능) -->
				<div class="thumbnail mb-3 mt-2">썸네일이미지 선택 : <input type="file" accept="image/*" id="titleimg" name="titleimg"></div>
				<textarea name="content" id="content" rows="20" cols="10"
					style="width: 100%"></textarea>
			</div>
			<div class="col-sm-12 text-md-end">
			<input id="save" class="save btn btn-secondary mb-2 mt-2" type="button" value="등록">
			<input id="close" class="close btn btn-secondary mb-2 mt-2" type="button" value="취소">
			</div>
		</form>
	</div>
	<script>
		// 스마트 에디터 로딩 스크립트 부분
		var oEditors = [];
		$(function() {
			nhn.husky.EZCreator.createInIFrame({
				oAppRef : oEditors,
				elPlaceHolder : "content", //textarea에서 지정한 id와 일치하는 값 입력. 
				//SmartEditor2Skin.html 파일이 존재하는 경로
				sSkinURI : "./resources/smartEditor/SmartEditor2Skin.html",
				htParams : {
					// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseToolbar : true,
					// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,
					// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true,
					fOnBeforeUnload : function() {

					}
				},
				fCreator : "createSEditor2"
			});

			//저장버튼 클릭시 form 전송
			$("#save").click(function() {
				oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
				// 에디터 validation 검증
				if (validation()) {
					$("#frm").submit();
				}
			});
			// 필수값 check
			function validation() {
				var contents = $.trim(oEditors[0].getContents());
				// 내용 textarea가 비어있는지 확인 비어있으면 경고창 생성 후 포커스 이동
				var fileCheck = document.getElementById("titleimg").value;
				
				if (contents === '<p>&nbsp;</p>' || contents === '') { // 아무것도 입력 안해도 값이 들어있음
					alert("내용을 입력하세요.");
					oEditors.getById['content'].exec('FOCUS');
					console.log("잘 입력 된지 확인");
					return false;
				} else if ($.trim($("#title").val()) === '') {
					// 제목 입력 부분 비어있는지 체크 비어 있으면 포커스 이동
					alert("제목을 입력하세요.");
					$("#title").focus();
					return false;
				}else if (!(fileCheck)){
					alert("대표 이미지를 선택해주세요.")
					$("#titleimg").focus();
					return false;
				}
				return true;
			}
			// 취소 버튼 클릭시 목록 화면으로 전환 (반 이름 계속 데리고 다녀야 함... 안그럼 오류나네)
			$("#close").click(function() {
				$(location).attr("href", "classNoticeList.do?classname=<%= classname %>");
			});
		});
	</script>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>