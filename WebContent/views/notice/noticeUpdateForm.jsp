<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.notice.model.dto.*, com.semi.common.dto.*"%>
 <%
 	Notice n = (Notice)request.getAttribute("n");
 	Attachment at = (Attachment)request.getAttribute("at");
 	
 	String nContent = n.getNoticeContent().replace("<br>", "\n");
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
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<h1 id="title" align="center" class="text-primary p-6">공지사항</h1>
	
		<div class="container">
	
		<hr class="bor">
		
		<!-- 글 수정하기 -->
		<!-- 수정할 값을 request에서 꺼내서 뿌려줘야한다. -->
		<form id="enrollForm" action="<%= contextPath %>/updateNotice.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
			<div class="form-group">
				<table class="table table-condensed table-borded pd-1">	
					<tbody>
						<tr>
							<th class="col-md-1"><h3>제목 : </h3></th>
							<td><input type="text" class="form-control form-control-lg rounded-0 mt-3" name="title" value="<%= n.getNoticeTitle()%>"></td>
						</tr>
						<tr>
							<td colspan="2">
								<label for="comment"><h3>내용 : </h3></label>
								<textarea class="form-control form-control-lg rounded-0" name="content" rows="20" style="resize:none"><%= nContent %></textarea>
							</td>
						</tr>
						<tr>
							<% if(at != null) {%>
								<th>첨부파일</th>
								<td colspan="3">
									<%= at.getOriginName()%>
									<input type='hidden' name='originFile' value='<%=at.getChangeName()%>'>
									<input type='hidden' name='originFileNo' value='<%=at.getFileNo()%>'>
								</td>
							<%} %>
						</tr>
						<tr>
							<td>첨부파일</td>
							<td><input type="file" name="upfile"></td>
						</tr>				
					</tbody>	
				</table>
			</div>
			
			<hr class="bor">
			
			<div class="row">
				<div class="col-md-12 text-md-end p-3">
					<input class="btn btn-secondary m-1" type="button" value="취소" onclick="location.href='<%=contextPath%>/listNotice.do'">
					<input class="btn btn-secondary m-1" type="submit" value="수정">
				</div>
			</div>			
		</form>			
	</div>
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>