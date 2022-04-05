<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.semi.class_notice.model.dto.*, com.semi.common.dto.*"%>
<%
ArrayList<Attachment> fileList = (ArrayList<Attachment>) request.getAttribute("fileList");
PageInfo pi = (PageInfo) request.getAttribute("pi");
//Attachment img = fileList.get(0);
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
			<h3>게시물 제목</h3>
			<small>작성자 : khh1212<br> <small class="text-muted">날짜
					| 조회수</small>
			</small>
			<div class="col-lg-12 col-sm-12 text-lg-end text-center">
				<button onclick="location.href='classNoticeUpdateForm.do'">수정</button>
				<button onclick="location.href='classNoticeDelete.do'">삭제</button>
			</div>
			<hr class="line">
			글 내용, 사진 첨부 할 공간
			글 내용, 사진 첨부 할 공간
			글 내용, 사진 첨부 할 공간
			글 내용, 사진 첨부 할 공간
			글 내용, 사진 첨부 할 공간
		</div>
		<div class="container">
			<hr class="line">
			<div class="comment-txt">
				<textarea cols="120" rows="3" id="replyCnt" style="resize: none;"
					placeholder="댓글을 남겨보세요."></textarea>
				<button id="addreply-btn" class="btn btn-dark btn-lg mb-6 pl-3 "
					style="height: 4.5rem">등록하기</button>
			</div>
		</div>
		<% System.out.println("글 번호"+request.getParameter("nNo")); %>
		<div class="container">
			<div class="row">
				<table class="table table-striped"
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