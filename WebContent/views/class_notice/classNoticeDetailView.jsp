<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.semi.class_notice.model.dto.*, com.semi.common.dto.*, com.semi.user.model.dto.*"%>
<%
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	Attachment at = (Attachment)request.getAttribute("at");
	ClassNotice cNotice = (ClassNotice)request.getAttribute("cn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style>
.line {
	background-color: black;
}

.fullCnt {
	width: 65%;
	margin: auto;
}

.replyList>tr>td{
	margin:auto;
}
tr {
  border-bottom: solid #fff;
}
table>tr>td>pre{
	font-family: 'GmarketSansMedium';
	font-size:13px;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<section class="fullCnt">
		<div class="container">
			<h3><%=cNotice.getClassNoticeTitle() %></h3>
			<small>작성자 : <%=cNotice.getNoticeWriter() %><br> 
			<small class="text-muted"><%=cNotice.getCreateDate() %>
			| <%=cNotice.getCount() %></small>
			</small>
			<div class="text-lg-end mb-3">
			<a download="<%= at.getOriginName() %>" href="/resources/board_upfiles/<%=at.getChangeName()%>">대표이미지 : <%= at.getOriginName() %></a>
			</div>
			<div class="col-lg-12 col-sm-12 text-lg-end text-center">
				<button class="btn btn-secondary" onclick="goList();">목록으로</button>
			<% if(loginUser.getAdminChecked().equals("Y")){ %>
				<button class="btn btn-secondary" onclick="goUpdate();">수정</button>
				<button class="btn btn-secondary" onclick="goDelete();">삭제</button>
			<% } %>
			</div>
			<form action="" id="postForm" method="post">
				<input type="hidden" name="nno" value="<%=cNotice.getClassNoticeNo() %>">
				<input type="hidden" name="classname" value="<%=cNotice.getClassName() %>">
			</form>
			<script>
				function goUpdate(){
					$("#postForm").attr("action", "/classNoticeUpdateForm.do");
					$("#postForm").submit();
				}
				function goDelete(){
					var ans = confirm("게시물을 삭제하시겠습니까?");
					// 취소 입력시 false 반환 함수 종료
					if(!ans) return false;
					
					// 확인 버튼 클릭시 비밀번호 입력, 현재 로그인한 관리자의 비밀번호와 일치할경우
					// delete 서블릿으로 전환
					var pwd = prompt("비밀번호를 입력하세요.");
					
					if(pwd == '<%= loginUser.getUserPwd()%>'){
						$("#postForm").attr("action", "/classNoticeDelete.do");
						$("#postForm").submit();
					}
				}
				function goList(){
					location.href = "classNoticeList.do?classname=<%= cNotice.getClassName()%>";
				}
			</script>
			<hr class="line">
			<%=cNotice.getClassNoticeContent() %>
		</div>
		<div class="container">
			<hr class="line">
			<div class="comment-txt">
			
				<textarea cols="120" rows="3" id="replyCnt" style="resize: none;"
					placeholder="댓글을 남겨보세요."></textarea>
				<button id="addreply-btn" class="btn btn-dark btn-lg mb-6 pl-3 " style="height: 4.5rem">등록하기</button>
				<div class="container">
					<div class="row">
					<div class="bg-secondary text-white text-center">댓글</div>
						<table class="replyList" id="replyList"
							style="text-align: center; border: 1px solid #dddddd">
							<%-- 댓글 목록 넣어지는 공간 --%>
						</table>
					</div>
				</div>
			</div>
		</div>
			<script>
			<%-- 댓글 기능 스크립트 시작 --%>
			
				$(function(){
					selectReplyList(); // 댓글이 달려 있는경우 조회해서 뿌려주기
					$("#addreply-btn").click(function(){
						// 댓글 입력창 내용이 비어있으면 false 반환
						if($("#replyCnt").val().length == 0){
							alert("내용을 입력하세요.");
							return false;
						}
						// 댓글 등록 textarea에 담겨있는 값과, 참조게시글번호 변수에 담아준다.
						var content = $("#replyCnt").val();
						var nno = <%=cNotice.getClassNoticeNo() %>;
						
						$.ajax({
							url: "rinsert.do",
							type:"post",
							data:{
								content:content,
								nno:nno
							},
							success:function(status){
								if(status == "success"){
									selectReplyList();
									alert("댓글 등록 완료");
									$("#replyCnt").val("");
								}
							},
							error:function(){
								console.log("ajax 통신 실패 - 댓글 등록");
							}
						})
					})
				})
				// 댓글 조회 함수
				function selectReplyList(){
					$("#replyList").empty();
					// 현재 로그인된 회원의 아이디
					var rUserId = "<%= loginUser.getUserId() %>";
					console.log(rUserId);
					$.ajax({
						url:"rlist.do",
						data:{nno:<%=cNotice.getClassNoticeNo()%>},
						type:"get",
						success:function(list){
							// 댓글 목록 조회해서 매개변수로 받아온다.
							console.log(list);
							
							var value = "";
							$.each(list, function(index, obj){
								// 서블릿으로 생성한 댓글 객체로 화면에 뿌려주어야함
								console.log(obj);
							if(rUserId == obj.replyWriter){ // 현재 로그인한 회원과 작성자 회원 아이디가 같을경우 수정 | 삭제 보이게
								value += '<tr style="solid #fff;">' +
										 '<td style="text-align:left; border:none;">' + obj.replyWriter+ ' |<small> ' + obj.createDate + '</small></td>'+
										 '<td style="text-align:right; border:none;"><input class="updatebtn btn btn-secondary" type="button" onclick="updateBtn('+ obj.replyId +');" value="수정"> <input class="deletebtn btn btn-secondary" type="button" onclick="deleteReply('+ obj.replyId + ');" value="삭제"> </td>' +
										 '</tr>' +
										 '<tr>' +
										 '<td id="reply'+obj.replyId+'" style="text-align:left;" colspan="2"><pre>' + obj.replyContent + '</pre></td>' +
										 '<!-- <td></td> -->' +
										 '</tr>' + 
										 '<tr id="update'+obj.replyId +'" style="display:none;">' +
										 '<td style="text-align:left;"><textarea id="textarea'+obj.replyId+'" style="resize:none;" cols="100" rows="2">' + obj.replyContent + '</textarea></td>' +
										 '<td>&nbsp<input type="button" onclick="updateReply('+obj.replyId +');" class="btn btn-secondary" value="수정완료"> <input type="button" class="btn btn-secondary" onclick="closeR('+obj.replyId +');" value="취소"></td>'+
										 '</tr>';
							}else{ // 현재 로그인한 아이디와 작성자 아이디가 같지 않을경우
								value += '<tr style="border-top: 10px solid #fff;">' +
								 '<td style="text-align:left; border:none;">' + obj.replyWriter+ ' | <small>' + obj.createDate + '</small></td>'+
								 '<!-- <td></td> -->' +
								 '</tr>' +
								 '<tr>' +
								 '<td style="text-align:left; colspan="2"><pre">' + obj.replyContent + '</pre></td>' +
								 '<!-- <td></td> -->' +
								 '</tr>';
							}
							})
							$("#replyList").html(value);
						},
						error:function(){
							console.log("ajax 통신 실패 - 댓글조회")
						}
					})
				}
				// 수정 취소 함수 취소버튼 클릭시 input박스 다시 숨김처리
				function closeR(rno){
					var id = "update" + rno;
					$('#' + id).css('display', 'none');
					
					//취소 버튼 클릭시 숨겼던 다른 버튼들 및 수정 창 숨기기
					$("#update" + rno).hide();
					$("#reply"+rno).show();
					$(".updatebtn").show();
					$(".deletebtn").show();
				}
				// 댓글 수정 버튼 클릭했을때 수정 input 숨기기 해제
				function updateBtn(rno){
					// 수정하는데 필요한 정보들 변수에 저장
					var id = "update"+rno;
					console.log(id);
					
					// 수정 버튼 클릭시 기존 댓글 내용 숨기고, 수정 textarea 보여주기
					$("#reply"+rno).hide();
					$(".updatebtn").hide();
					$(".deletebtn").hide();
					if($("#"+id).css("display") == "none"){
						$("#"+id).show();
					}
				}
				
				// 댓글 수정 함수 댓글번호를 매개변수로 받음
				function updateReply(rno){
					var textareaId = "textarea" + rno;
					var content = $('#' + textareaId).val();
					// 변수로 id값 생성 (input 박스에 아이디값을 input + 댓글번호로 설정)
					// 수정할 내용을 그 아이디값의 val()로 담아준다.
					$.ajax({
						url:"rupdate.do",
						type:"post",
						data:{
							rno:rno,
							content:content
						},
						success:function(status){
							if(status == "success"){
								selectReplyList();
								alert("댓글 수정 성공");
							}
						},
						error:function(){
							alert("댓글 수정 실패");
						}
					})
				}
				// 댓글 삭제 함수
				function deleteReply(rno){
					var ans = confirm("선택하신 댓글을 삭제하시겠습니까?");
					if(!ans) return false;
					// 확인을 눌렀을경우 ajax 실행
					$.ajax({
						url:"rdelete.do",
						type:"post",
						data:{
							rno:rno
						},
						success:function(status){
							if(status == "success"){
								selectReplyList();
								alert("댓글이 삭제되었습니다.");
							}
						},
						error : function(){
							alert("댓글 삭제에 실패하였습니다.")
						}
					});
				}
			</script>
	</section>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>