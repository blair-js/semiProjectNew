<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.dao.UserDao"%>
<%@ page import="util.SHA256"%>
<%@ page import="util.Gmail"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="javax.mail.*"%>

<%@ page import="javax.mail.Transport"%>
<%@ page import="javax.mail.Message"%>
<%@ page import="javax.mail.Address"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="javax.mail.Session"%>
<%@ page import="javax.mail.Authenticator"%>

<%-- <%
	UserDao userDao = new UserDao();
	String userId = null;
	//회원가입을 한 상태라면 (회원가입 서블릿에서 세션에 userId 넣어주기)
	if(session.getAttribute("userId") != null){
		userId = (String)session.getAttribute("userId");
	}
	
	//회원가입을 하지 않은 상태라면 
	if(userId == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입을 해주세요.");
		script.println("location.href='userEnrollForm.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	//특정 사용자 userId가 이메일 인증이 되었는지 확인 
	String emailChecked = userDao.getUserEmailChecked(userId);
	
	if(emailChecked == "T"){ //이메일 인증을 한 상태
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 인증된 회원입니다.');");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
		//여기서 구글 SMTP가 사용된다. 
		String host = "http://localhost:8086/semiProjectNew/";
		
		String from = "kjisu@gmail.com"; //보내는 사람 메일 계정
		String to = userDao.getUserEmail(userId); //받는 사람 메일
		String subject = "강의평가를 위한 이메일 인증 메일입니다."; //메일 제목
		
		String content = "다음 링크에 접속하여 이메일 인증을 진행하세요." 
							+ "<a href='" + host + "emailCheckAction.jsp?code=" + new SHA256().getSHA256(to)
							+ "'>이메일 인증하기</a>";

		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		//이메일을 전송하는 부분
		try{
			
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);

			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			
			msg.setContent(content, "text/html;charset=UTF8");
			
			Transport.send(msg);
			
		}catch(Exception e){
			e.printStackTrace(); 
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
%>	
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@font-face {
	font-family: 'LeferiPoint-BlackA';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'LeferiPoint-BlackA';
}
.alert{
	background-color: #f7f7f9;
}
div .btns{
	background-color: #0099FF;
}
div h5{
	color: #0099FF;
}
.send b{
	color: #0099FF;
}
</style>
</head>
<body>

	<!-- UserInsertServlet에서 회원가입 성공시 여기로 옴 -->
	<!-- 사용자의 이메일로 이메일이 발송되도록 한다. -->

	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>

	<div class="row mt-5">
		<!-- 비어있는 div -->
		<div class="col-md-3"></div>
		<!-- 인증메일요청내용 -->
		<div class="col-md-6 text-center">
			<div class="">
				<img class="col-md-3 mb-2" src="assets/img/gallery/email.png" alt="이메일로고" style="max-width: 560px">
			</div>
			<h2>인증 메일이 발송되었습니다.</h2>
			<div class="mt-3 send">
				<b>jisu@com</b>에서 인증 메일을 확인 바랍니다.<br>
				이메일에서 인증하기를 클릭하시면 인증이 완료됩니다.
			</div>
		</div>
		<!-- 비어있는 div -->
		<div class="col-md-3"></div>
	</div>

	<!-- 주의사항 div -->
	<div class="container mt-4 mb-5" style="max-width: 560px;">
		<div class="alert text-center" role="alert">
			<h5>주의사항</h5>
			이메일 인증이 정상적으로 이루어져야<br> 해당 웹사이트의 서비스를 이용하실 수 있습니다.<br>
			<div class="col-md-12 mt-3 text-center">
				<button class="w-100 btn btn-lg mb-2 btns" onclick="goLogin()">
					<b style="color: white;">로그인</b>
				</button>
			</div>
		</div>
	</div>
	<!-- 주의사항 div 끝 -->

	<script type="text/javascript">
		//로그인 함수
		function goLogin() {
			location.href="<%= request.getContextPath()%>/loginForm.do;"
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>