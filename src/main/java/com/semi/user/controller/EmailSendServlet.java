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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

import util.Gmail;
import util.SHA256;

//이메일 보내주는 서블릿
@WebServlet("/sendEmail.do")
public class EmailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmailSendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//UserInsertServlet에서 DB에 회원 등록이 정상적으로 완료되면
		//파라미터로 userId를 보내주고 여기로 와서 그 userId를 받고 이메일을 발송한다! 
		//또 이메일인증 재요청시에도 userId는 파라미터로 넘어온다!
		String userId = request.getParameter("userId");
		System.out.println("userId 파라미터 확인 : " + userId);
		
		//DB에 데이터가 들어갔으면 이메일을 보내야 한다. 
		//여기서 구글 SMTP가 사용된다. 
		String host = "http://localhost:8086/"; //보내는 서버 
		
		String from = "kjisu4717@gmail.com"; //보내는 사람 메일 계정(고정)
		String to = new UserService().getUserEmail(userId); //받는 사람 메일(userId를 기준으로 메소드 실행하여 가져오기)
		String subject = "회원가입을 위한 이메일 인증 메일입니다."; //메일 제목(고정)
		
		//메일 내용
		//인증하기 링크를 클릭하면 내가 설정해놓은 서블릿으로 오게됨.
		String content = "링크를 누르시면 이메일 인증이 완료됩니다." 
							+ "<a href='" + host + "emailCheck.do?code=" + new SHA256().getSHA256(to) + "&userId=" + userId
							+ "'>이메일 인증하기</a>";

		//서버의 정보를 설정하는 부분
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com"); //이메일 발송을 처리해줄 smtp 서버
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true"); //로그인시 TLS를 사용하겠다는 의미(TLS : 인터넷에서의 정보를 암호화해서 송수신하는 프로토콜)
		p.put("mail.smtp.auth", "true"); //smtp의 인증을 사용하겠다는 의미
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		//이메일을 전송하는 부분
		try{
			
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth); //세션 생성(첫번째 매개변수는 위에서 서버의 정보를 설정한 객체 p)
			ses.setDebug(true);
			
			MimeMessage msg = new MimeMessage(ses); //Message 추상클래스를 상속받은 인터넷 메일을 위한 클래스로, 위에서 생성한 세션 ses를 담아 msg객체를 생성
			msg.setSubject(subject);
			
			Address fromAddr = new InternetAddress(from); //보내는 사람 메일주소 설정
			msg.setFrom(fromAddr);

			Address toAddr = new InternetAddress(to); //받는 사람 메일주소 설정
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			
			msg.setContent(content, "text/html;charset=UTF8"); //제목 설정
			
			Transport.send(msg); //실질적으로 메일을 보내주는 코드. 객체 msg에 위에서 set해준 정보들이 모두 담겨있다!
			
		}catch(Exception e){
			e.printStackTrace(); 
		}
		
		System.out.println("인증용 이메일 발송 완료");
		
		//jsp에서 이메일주소를 뿌려줘야 하므로 request 객체에 set 해서 전달 
		request.setAttribute("userEmail", to);
		request.getRequestDispatcher("views/user/emailSendAction.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
