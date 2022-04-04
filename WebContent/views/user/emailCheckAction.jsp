<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.dao.UserDao" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
	
	String code = null;

	if(request.getParameter("code") != null){
		code = request.getParameter("code");
	}
	
	UserDao userDao = new UserDao();
	//String userId = null;
			
	//로그인 한 상태
	/*if(session.getAttribute("userId") != null){
		userID = (String)session.getAttribute("userID");
	}
	
	//로그인되지 않은 상태
	if(userID == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요');");
		script.println("location.href='userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}*/
	
	String userEmail = userDao.getUserEmail(userId);
	
	//boolean isRight = (new SHA256().getSHA256(userEmail).equals(code)) ? true : false;
	
	boolean isRight = true;
	
	if(isRight == true){
		//해당 아이디를 이메일인증 ok로 해주는 메소드 호출
		userDao.setUserEmailChecked(userId);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('인증에 성공했습니다.');");
		script.println("location.href='loginForm.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	
	
	
	/*else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 코드입니다.');");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}*/
	
%>



</body>
</html>