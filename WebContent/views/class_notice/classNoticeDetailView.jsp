<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.semi.class_notice.model.dto.*, com.semi.common.dto.*"%>
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
			<% if(loginUser.getUserNo() == 1 || loginUser.getUserNo() == 2 || loginUser.getUserNo() == 3){ %>
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
					$("#postForm").attr("action", "/classNoticeDelete.do");
					$("#postForm").submit();
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
				<button onclick="replySave()" id="addreply-btn" class="btn btn-dark btn-lg mb-6 pl-3 " style="height: 4.5rem">등록하기</button>
			
			<%-- 댓글 기능 구현 어렵다... --%>
			<script>
				function replySave(){
					var content = $("#replyCnt").val();
					var nno = <%=cNotice.getClassNoticeNo() %>;
					
					$.ajax({
						type:"post",
						url: "rinsert.do",
						data:{
							content:content,
							nno:nno
						},
						success:function(status){
							if(status == "success"){
								//selectReplyList();
								console.log("통신 성공");
							}
						},
						error:function(){
							console.log("ajax 통신 실패 - 댓글 등록");
						}
					})
				}
			</script>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<table class="table table-striped" id="replyList"
					style="text-align: center; border: 1px solid #dddddd">
					<tbody>
						<tr>
							<td align="left" bgcolor="beige">댓글</td>
						</tr>
						<tr>
							<%--
					CommentDAO commentDAO = new CommentDAO();
					ArrayList<Comment> list = commentDAO.getList(boardID, bbsID);
					for(int i=0; i<list.size(); i++){
				--%>
							<div class="container">
								<div class="row">
									<table class="table table-striped"
										style="text-align: center; border: 1px solid #dddddd">
										<tbody>
											<tr>
												<td align="left">
													<%-- list.get(i).getUserID() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= list.get(i).getCommentDate().substring(0,11) + list.get(i).getCommentDate().substring(11,13) + "시" + list.get(i).getCommentDate().substring(14,16) + "분" --%>
												</td>
												<td colspan="2"></td>
												<td align="right">
													<%--
												if(list.get(i).getUserID() != null && list.get(i).getUserID().equals(userID)){   //댓글 쓴사람과 지금 유저가 같을 때 수정과 삭제를 가능하게 함
												%>
													<form name = "p_search">
														<a type="button" onclick="nwindow(<%=boardID%>,<%=bbsID %>,<%=list.get(i).getCommentID()%>)" class="btn-primary">수정</a>
													</form>	
														<a onclick="return confirm('정말로 삭제하시겠습니까?')" href = "commentDeleteAction.jsp?commentID=<%= list.get(i).getCommentID() %>" class="btn-primary">삭제</a>																	
												<%
												}
												--%>
												</td>
											</tr>
											<tr>
												<td colspan="5" align="left">
													<%--= list.get(i).getCommentText() --%> <%-- 	
												String commentReal = "C:\\Users\\j8171\\Desktop\\studyhard\\JSP\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\BBS\\commentUpload";
												File commentFile = new File(commentReal+"\\"+bbsID+"사진"+list.get(i).getCommentID()+".jpg");
												if(commentFile.exists()){           //해당 댓글에 대응되는 사진이 있을 경우 사진도 보여준다.
											%>	
											<br><br><img src = "commentUpload/<%=bbsID%>사진<%=list.get(i).getCommentID() %>.jpg" border="300px" width="300px" height="300px"><br><br></td>
											<%} %> --%>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<%--
							}
						--%>
						</tr>
				</table>
			</div>
		</div>
	</section>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>