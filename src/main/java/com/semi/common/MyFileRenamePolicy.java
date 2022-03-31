package com.semi.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {
		
		String originName = originFile.getName(); // 원본 파일 명
		// 수정명 : 파일업로드한시간(년월일시분초) + 10000~99999사이의 랜덤값 (5자리랜덤값) + 확장자
		
		// 원본명  	--> 수정명
		// aaa.jpg 	--> 2019120109065323456.jpg
		
		// 파일 업로드한 시간 알아내기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(new Date());
		
		// 랜덤 값 5자리
		int ranNum = (int)(Math.random() * 90000 + 10000);
		
		// 확장자
		String ext = "";
		// 원본 파일 명에서 . 이 위치해 있는 인덱스 값을 찾기
		int dot = originName.lastIndexOf(".");
		// . 위치부터 마지막까지 추출
		ext = originName.substring(dot);
		
		String fileName = currentTime + ext;
		// 전달된 파일 명으로 파일 객체를 생성해서 리턴
		File renameFile = new File(originFile.getParent(), fileName);
		
		return renameFile;
	}

}
