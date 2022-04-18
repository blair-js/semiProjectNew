<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.qna.model.dto.*"%>
<%
	Qna q = (Qna)request.getAttribute("q");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.bor{
		border: 2px solid gray;
	}
	
	<%-- 부트스트랩 테이블 cell padding 적용 --%>
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

	<h1 id="title" align="center" class="text-primary p-6">Q&A</h1>
	
	<div class="container p-2">
		<!-- 수정하기 버튼 -> 로그인한 유저의 아이디일 때만 보이도록 -->
		<!-- 원래는 nno 값이 같이 넘어가야한다.(함수를 사용하거나 ?qno=사용 -->
		<%if(loginUser != null && loginUser.getUserId().equals(q.getQnaWriter())) {%>
			<div class="row">
				<div class="col-md-12 text-md-end p-3">
					<button class="btn btn-secondary m-1" onclick="location.href='<%=contextPath%>/updateFormQna.do?qno=<%=q.getQnaNo()%>'"><b>수정</b></button>
					<button class="btn btn-secondary m-1" onclick="location.href='<%=contextPath%>/deleteQna.do?qno=<%=q.getQnaNo()%>'"><b>삭제</b></button>
				</div>
			</div>
		<% } %>
		
		<hr class="bor">
		
		<!-- 글 상세보기 -->
		<table class="table table-condensed pd-1">	
			<tbody>
				<tr id="title">
					<th class="col-md-1"><h3>제목 : </h3></th>
					<td colspan="3"><h3><%=q.getQnaTitle() %></h3></td>
				</tr>
				
				<tr id="writer">
					<th><h4>작성자 | </h4></th>
					<td colspan="3"><h4><%=q.getQnaWriter() %></h4></td>
				</tr>
				
				<tr>
					<th><h4>작성일 | </h4></th>
					<td><h4><%=q.getCreateDate() %></h4></td>
					<th class="col-md-1"><h4>조회수 | </h4></th>
					<td><h4><%=q.getCount() %></h4></td>
				</tr>
				
				<tr id="content">
					<td colspan="4"><%=q.getQnaContent() %></td>
				</tr>
			</tbody>	
		</table>
		
		<hr class="bor">
	</div>
	
	<div class="container">
	  <!-- 댓글 창 -->
	  <div class="comment-txt">
	  	<!-- 관리자에게만 보이도록 한다. -->
	  	  <% if(loginUser != null && loginUser.getUserId().contains("admin")){ %>
          <textarea rows="3" id="replyCnt" style="resize: none; width:92%" placeholder="댓글을 작성해주세요."></textarea>
          <button id="addreply" class="btn btn-dark btn-lg mb-6 pl-3 "style="height: 4.5rem">등록하기</button>
          <%} else {%>
          <textarea cols="200" rows="3" id="replyCnt" style="resize: none; width:92%" placeholder="관리자만 작성이 가능합니다."></textarea>	
          <button id="addreply" class="btn btn-dark btn-lg mb-6 pl-3 "style="height: 4.5rem" disabled>등록하기</button>
          <%} %>
       </div> 
		
		<!-- 댓글을 보여주는 div -->
      	<div class="row">
      	<div class="bg-secondary text-white text-center">댓글</div>
       	  <table class="table table-striped replyList" id="replyList" style="text-align: center; border: 1px solid #dddddd">
       	  
      	  </table>
        </div>                
    </div>
    
    <!-- 댓글script -->
    <script>
    //댓글 등록
	    $(function(){
	    	selectReplyList(); //onload될 때 댓글이 있다면 댓글을 가져와서 뿌려준다.
	    	$('#addreply').click(function() { //등록버튼을 누르면 실행
	    		var content = $('#replyCnt').val();
	    		var qno = <%=q.getQnaNo()%>
	    		
	    		$.ajax({
	    			url:"insertReplyQna.do",
	    			type:"post",
	    			data:{
	    				content:content,
	    				qno:qno
	    			},
	    			success:function(status){
	    				if(status == "success"){
	    					selectReplyList();
	    					$('#replyCnt').val(""); //value에 있던 댓글 내용을 지운다.
	    				}
	    			}, 
	    			error:function(){
	    				console.log("ajax 통신 실패 -댓글등록");
	    			}
	    		})
	    	})
	    })
	    //댓글 리스트
	    function selectReplyList(){
	    	$('#replyList').empty(); //새로 로드하기 위해 안의 내용 비운다.
	    	var userId = "<%= loginUser.getUserId() %>";
	    	$.ajax({
	    		url:"replyListQna.do",
	    		data:{qno:<%=q.getQnaNo()%>},
	    		type:"get",
	    		success:function(list){
	    			console.log(list)
	    			//반복문 돌리기
	    			var value="";
	    			$.each(list, function(index, obj){
	    				if(userId.includes("admin") || userId == obj.replyWriter){ //관리자와 글 작성자만 댓글을 달 수 있다.
	        				value += '<tr style="border-top: 10px solid #fff;">' +
	        						 '<td style="text-align:left; border:none;">' + obj.replyWriter + ' | ' + obj.createDate + '</td>' +
	        						 '<td style="text-align:left; border:none;"><input class="btn btn-secondary btn-sm" style="background-color:#002147" type="button" onclick="updateBtn('+ obj.qnaReplyNo +');" value="수정">' + 
	        						 '<input class="btn btn-secondary btn-sm mx-2" style="background-color:#002147" type="button" onclick="deleteReply(' + obj.qnaReplyNo + ');" value="삭제"></td>' + 
	        						 '</tr>' + 
	        						 '<tr id="reply'+obj.qnaReplyNo +'">' +
	        						 '<td style="text-align:left;" colspan="2">' + obj.qnaReplyContent + '</td>' +
	        						 '</tr>' +
	        						 '<tr id="update'+obj.qnaReplyNo +'" style="display: none">' +
	        						 '<td><textarea rows="3" id="textarea'+obj.qnaReplyNo+'" style="resize: none; width:92%">'+ obj.qnaReplyContent +'</textarea></td>' +
	        						 '<td>&nbsp<input type="button" onclick="updateReply('+obj.qnaReplyNo +');" class="btn btn-secondary" style="background-color:#002147" value="수정하기">' +
	        						 '<input type="button" class="btn btn-secondary mx-2" style="background-color:#002147" onclick="closeR('+obj.qnaReplyNo +');" value="취소"></td>'+
	        						 '</tr>'
	        						 
	    				}else {
	    					value += '<tr style="border-top: 10px solid #fff;">' +
	    							 '<td style="text-align:left; border:none;">' + obj.replyWriter + ' | ' + obj.createDate + '</td>' +
	    							 '</tr>' +
	    							 '<tr>' +
	        						 '<td style="text-align:left; border:none;">' + obj.qnaReplyContent + '</td>' +     
	        						 '</tr>'
	    							 
	    				}
	    			});
	    			$('#replyList').html(value); //댓글이 나오는 곳에 value를 html로 넣는다.
	    		},
	    		error:function(){
	    			console.log('ajax 통신 실패-댓글 조회');
	    		}
	    	})    	    	
	    }
    	//댓글 수정 버튼 누르면 수정 text area가 나온다.
    	function updateBtn(rQno){
    		var replyId = "reply" + rQno;
    		var updateId = "update" + rQno;
    		
    		$('#' + replyId).hide();
    		$('#' + updateId).show();
    	}
    	
    	//취소 버튼을 누르면 다시 list로 돌아간다.
    	function closeR(rQno){
    		var replyId = "reply" + rQno;
    		var updateId = "update" + rQno;
    		
    		$('#' + replyId).show();
    		$('#' + updateId).hide();
    	}
    	
    	//수정하기 버튼을 누르면 수정이 된다.
    	function updateReply(rQno){
    		//수정된 내용을 보내줘야하기 때문에 textarea에 담긴 value를 변수에 담아서 보낸다.
    		var content = $('#textarea'+rQno).val();
    		
    		$.ajax({
    			url:"updateReplyQna.do",
    			type:"post",
    			data : {
    				rQno:rQno,
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
    	
	    //댓글 삭제
	    function deleteReply(rQno){
		   	 var result = confirm("댓글을 삭제하시겠습니까?");
		   	 //취소를 눌렀을 경우
		   	 if(!result) return false;
		   	 //확인을 눌렀을 경우
		   	 $.ajax({
		   		 url:"deleteReplyQna.do",
		   		 type:"post",
		   		 data:{
		   			 rQno:rQno
		   		 },	   		 
		   		 success:function(status){
		   			 if(status == "success"){
		   				 selectReplyList();
		   				 alert("댓글이 삭제되었습니다.");
		   			 }
		   		 },
		   		 error:function(){
		   			 alert("댓글 삭제에 실패하였습니다.");
		   		 }	 
		   	 });	
	    }
	 
    </script>
    
	<div class="container"> 
		<!-- 목록 버튼-->
		<div class="row">
			<div class="col-md-12 text-md-end p-3">
				<button class="btn btn-secondary" onclick="location.href='<%=contextPath%>/listQna.do'">목록</button>
			</div>
		</div> 
	</div>

	 <%@ include file="../common/footer.jsp" %>	 
</body>
</html>