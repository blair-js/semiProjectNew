<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.semi.user.model.dto.User"%>

<%
	//세션에 담겨있는 로그인 유저의 정보를 가져온다.
	//이 로그인 유저의 정보의 유무에 따라 마이페이지+로그아웃 버튼이 보이는지, 로그인+회원가입 버튼이 보이는지 결정
	User loginUser = (User)session.getAttribute("loginUser");	
	String msg = (String)session.getAttribute("msg");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="assets/img/favicons/fdog.ico">
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="assets/css/theme.css" rel="stylesheet" />
<style>
@font-face {
	font-family: 'GmarketSansMedium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
	}
	@font-face {
    font-family: 'GmarketSansBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}
	h1, h2, h3, h4, h5, h6, p, b {
	    font-family: 'GmarketSansBold';
	}
	.nav-item {
	    font-family: 'GmarketSansBold';
		font-size: 1.2rem;
	}
	
	*{
		font-family: 'GmarketSansMedium';		
	}
	nav.navbar {
   		background-color : rgba( 255, 255, 255, 0.5 );
   }
   
   #navbarSupportedContent ul li a:hover {
   		color: orange;
   }
   
   nav.navbar {
   	background-color : rgba( 255, 255, 255, 0.5 );
   }
   
   .dropdown:hover .dropdown-menu {
    display: block;
    margin-top: 0;
    width: 50%;
	}
.dropdown:hover .dropdown-menu {
	display: block;
	margin-top: 0;
	width: 50%;
}
.linav{
	font-size: 16px;
}
td{
	text-align: center;
}
.spname{
	color: #FDC800;
	color: #0099FF;
}
</style>

<script>
	$(function(){
			let msg = "<%=msg%>";
			if(msg != "null"){
				alert(msg);
				<% session.removeAttribute("msg"); %>
			}
		})
	</script>
</head>
<body>
  		<nav class="navbar navbar-expand-lg navbar-light sticky-top py-3 d-block" data-navbar-on-scroll="data-navbar-on-scroll">
        <div class="container"><a class="navbar-brand" href="index.jsp"><img src="assets/img/gallery/mainlogo.png" height="80" alt="logo" /></a>
        <h1>둥글개 둥글개</h1>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
          <div class="collapse navbar-collapse border-top border-lg-0 mt-4 mt-lg-0" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto pt-2 pt-lg-0 font-base">
              <li class="nav-item px-2 linav"><a class="nav-link active" aria-current="page" href="index.jsp">홈</a></li>
              <li class="nav-item px-2 linav"><a class="nav-link" aria-current="page" href="<%= contextPath %>/intro.do">소개</a></li>
              <li class="nav-item px-2 linav"><a class="nav-link" aria-current="page" href="<%=contextPath%>/listQna.do">Q&A</a></li>
              <li class="nav-item px-2 linav"><a class="nav-link" aria-current="page" href="<%=contextPath%>/listNotice.do">공지사항</a></li>

              <li class="nav-item px-2 linav"><a class="nav-link" aria-current="page" href="#" onclick="goSnack();">나만 먹을개</a></li>
 

              <li class="nav-item px-2 linav"><a class="nav-link" aria-current="page" href="<%=contextPath%>/chargePoint.do">뼈다귀 충전</a></li>


              <!-- 드롭다운 설정 해보기 -->
              <li class="dropdown">
          		<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">오늘 뭐했개<b class="caret"></b></a>
          			<ul class="dropdown-menu">
		            	<li><a class="p-3 text-decoration-none" href="#" onclick="goAnotice();">햇님반 게시판</a></li>
		            	<li><a class="p-3 text-decoration-none" href="#" onclick="goBnotice();">달님반 게시판</a></li>
		            	<li><a class="p-3 text-decoration-none" href="#" onclick="goCnotice();">별님반 게시판</a></li>
            		</ul>
            	</li>
            </ul>
            <%-- 알림장 게시판 회원들만 접근 가능하게 변경 --%>
            <script>
	            function goAnotice(){
	        		<% if(loginUser != null){ %>
	        			location.href ="/classNoticeList.do?classname=햇님반";
	        		<% }else{ %>
	        			alert("회원 전용 게시판입니다. 로그인이 필요합니다.");
	        		<% } %>
	        	}
	            function goBnotice(){
            		<% if(loginUser != null){ %>
            			location.href ="/classNoticeList.do?classname=달님반";
            		<% }else{ %>
            			alert("회원 전용 게시판입니다. 로그인이 필요합니다.");
            		<% } %>
            	}
	            function goCnotice(){
            		<% if(loginUser != null){ %>
            			location.href ="/classNoticeList.do?classname=별님반";
            		<% }else{ %>
            			alert("회원 전용 게시판입니다. 로그인이 필요합니다.");
            		<% } %>
            	}
	            function goSnack(){
	        		<% if(loginUser == null){ %>
	        			location.href ="/snack.do?userNo=0";
	        		<% }else{ %>
	        			location.href ="/snack.do?userNo=<%=loginUser.getUserNo()%>";
	        		<% } %>
	        	}
            </script>
            
            <%-- 로그인 된 user가 없다면 로그인+회원가입 버튼을 보여주고 --%>
            <%if(loginUser == null) {%>
	            <a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/loginForm.do"><b>로그인</b></a>
	            <a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/userEnrollForm.do"><b>회원가입</b></a>
			<%-- 로그인 된 user가 있다면 마이페이지+로그아웃 버튼을 보여준다. --%>            
            <%}else{ %>
            	<table>
            		<tr class="tr1">
				            <td>
           				<%if(loginUser.getAdminChecked().equals("Y")) {%>
           					<!-- 로그인된 유저가 관리자이면 아래 버튼 보여주고 -->
			            	<a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/adminMyPage.do"><b>마이페이지</b></a>
			            <%} else{ %>
			            	<!-- 로그인된 유저가 회원이면 아래 버튼 보여주고 -->
			            	<a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/userMyDetail.do"><b>마이페이지</b></a>
			            <%} %>
				            </td>
				            <td>
				            	<a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/userLogout.do"><b>로그아웃</b></a>
				            </td>
		            </tr>
		            <tr>
		            	<!-- 나중에 로그인 후 버튼은 두개(마이페이지+로그아웃)만 보여지므로 colspan 2로 변경예정 -->
		            	<td colspan="2"><span class="spname"><%=loginUser.getUserName() %></span> 님 환영합니다.</td>
		            </tr>
	            </table>
			<%} %>
            
          </div>
        </div>
      </nav>
     
</body>
</html>