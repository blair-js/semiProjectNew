<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.user.model.dto.User"%>

<%
	User loginUser = (User)session.getAttribute("loginUser");	
	String msg = (String)session.getAttribute("msg");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="assets/img/favicons/fdog.ico">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="assets/css/theme.css" rel="stylesheet" />
<style>
	
	@font-face {
    font-family: 'LeferiPoint-BlackA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}
	
   *{
   		font-family: 'LeferiPoint-BlackA';
   		font-weight: bold;
   }
   h1, h2, h3, h4, h5, h6{
   		font-family: 'LeferiPoint-BlackA';
   }
   li{
   		font-size: 1.2rem;
   }
	nav.navbar {
   		background-color : rgba( 255, 255, 255, 0.5 );
   }
   
   .dropdown:hover .dropdown-menu {
    display: block;
    margin-top: 0;
    width: 50%;
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
              <li class="nav-item px-2"><a class="nav-link active" aria-current="page" href="index.jsp">홈</a></li>
              <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="<%= contextPath %>/intro.do">소개</a></li>
              <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="<%=contextPath%>/listQna.do">Q&A</a></li>
              <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="<%=contextPath%>/listNotice.do">공지사항</a></li>
              <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="<%=contextPath%>/snack.do">나만 먹을개</a></li>
              <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="views/common/charge.jsp">뼈다귀 충전</a></li>

              <!-- 드롭다운 설정 해보기 -->
              <li class="dropdown">
          		<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">오늘 뭐했개<b class="caret"></b></a>
          			<ul class="dropdown-menu">
		            	<li><a class="p-3 text-decoration-none" href="<%= contextPath %>/classNoticeList.do">A반 게시판</a></li>
		            	<li><a class="p-3 text-decoration-none" href="<%= contextPath %>/classNoticeList.do">B반 게시판</a></li>
		            	<li><a class="p-3 text-decoration-none" href="<%= contextPath %>/classNoticeList.do">C반 게시판</a></li>
            		</ul>
            	</li>
            </ul>
            
			<!-- 로그인 전 -->
            <!-- LoginFormServlet으로 이동 -->
            <a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/loginForm.do;"><b>로그인</b></a>
            <!-- UserEnrollFormServlet으로 이동 -->
            <a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/userEnrollForm.do;"><b>회원가입</b></a>
            
            <!-- 로그인 후 -->
            <a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/userMyDetail.do;"><b>마이페이지</b></a>
            <a class="btn btn-sm btn-light order-1 m-1" href="index.jsp"><b>로그아웃</b></a>

            <!-- 아직 구분해줄 객체가 없기 때문에 관리자 페이지 임의 생성 조건문으로 경로만 다르게 잡아줄거이기에 버튼은 하나! -->
            <a class="btn btn-sm btn-light order-1 m-1" href="<%= contextPath %>/adminMyPage.do;"><b>Mp</b></a>
            
          </div>
        </div>
      </nav>
</body>
</html>