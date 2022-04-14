package util;

import java.security.MessageDigest;

public class SHA256 {
	
	public static String getSHA256(String input) { //특정한 입력값 input => 우리 프로그램은 email 값이 들어올 것
		
		//StringBuffer 객체 생성
		StringBuffer result = new StringBuffer();
		
		try {
			
			//MessageDigest.getInstance(String algorithm) 
			//메소드의 인자로 사용하고자하는 알고리즘 이름을 지정하면 
			//해당 알고리즘에서 해시값을 계산하는 MessageDigest를 구해준다. 
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt".getBytes(); //해킹 방지를 위해 salt 값을 생성
			
			digest.reset();
			digest.update(salt); //위에서 salt 변수에 담긴 문자열까지 연결해서 엄청 길어진 문자열로 업데이트 된다.
			
			byte[] chars = digest.digest(input.getBytes("UTF-8")); //해쉬를 적용한 값을 chars에 담아준다.
			
			//문자열 형태로 만들어주는 과정
			for(int i=0; i<chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length() == 1) { //한자리수라면
					result.append("0"); // 0을 붙여 16진수로 만들어줘야 한다. 
				}
				result.append(hex); //위의 결과를 반환해준다.
			}//for
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//아래와 같이 암호화된 해시값을 반환
		//0bba5e7636ab40cc88c032f6e7398d23c922146b0a8679cdcc9b063981d7eadc 
		return result.toString(); 
	}
	
}
