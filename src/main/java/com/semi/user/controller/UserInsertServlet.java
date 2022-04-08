package com.semi.user.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.dto.User;
import com.semi.user.model.service.UserService;

import util.Gmail;
import util.SHA256;

//userEnrollForm.jsp에서 넘어옴
@WebServlet("/insertUser.do")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		System.out.println("회원가입서블릿 체크");
		
		//파라미터 받기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String smsCheck = request.getParameter("chk_sms");
		String gender = request.getParameter("gender");
		String userEmail = request.getParameter("userEmail");
		/*System.out.println(userId);
		System.out.println(userPwd);
		System.out.println(userName);
		System.out.println(phone);
		System.out.println(smsCheck);
		System.out.println(userEmail);
		System.out.println(gender);*/
		
		//파라미터로 객체 생성
		User user = new User(userId, userPwd, userName, phone, smsCheck, gender, userEmail, util.SHA256.getSHA256(userEmail), "N");
		
		int result = new UserService().insertUser(user);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		if(result > 0) {
			
			System.out.println("1.회원가입성공");
			
			//DB에 데이터가 들어갔으면 이메일을 보내야 한다. 
			//여기서 구글 SMTP가 사용된다. 
			String host = "http://localhost:8086/";
			
			String from = "kjisu@gmail.com"; //보내는 사람 메일 계정(고정)
			String to = new UserService().getUserEmail(userId); //받는 사람 메일(메소드 실행하여 가져오기)
			String subject = "회원가입을 위한 이메일 인증 메일입니다."; //메일 제목(고정)
			
			String content = "링크를 누르시면 이메일 인증이 완료됩니다." 
								+ "<a href='" + host + "emailCheck.do?code=" + new SHA256().getSHA256(to) + "&userId=" + userId
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
			}
			
			System.out.println("2.인증용 이메일 발송 완료");
			
			//회원 가입이 정상 완료되면 => 이메일 발송 페이지로 이동 
			
			//jsp에서 이메일주소를 뿌려줘야 하므로 request 객체에 set 해서 전달 
			request.setAttribute("userEmail", userEmail);
			request.getRequestDispatcher("views/user/emailSendAction.jsp").forward(request, response);
			
		}else {
			
			System.out.println("회원가입실패");
			
		}//if~else
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
