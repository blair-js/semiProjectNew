package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
	
		return new PasswordAuthentication("kjisu4717", "govlwltn00"); //사용자에게 메일을 보내는 관리자의 계정(내계정)
		
	}
	
}
