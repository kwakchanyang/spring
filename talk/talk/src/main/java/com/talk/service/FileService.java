package com.talk.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {
	@Value("${filePath}")
	private String filePath;
	
	public String uploadFile(MultipartFile multipartFile) throws IOException { // 하나씩 업로드
		
		String originalName = multipartFile.getOriginalFilename(); // 원본이름(사용자의 컴퓨터에서 올린 파일)
		String fileName="";
		String fileUrl="";
		fileName = fileSave( originalName, multipartFile.getBytes());
		fileUrl = "/files/"+fileName; // 웹(html)에서 사용할 파일경로(실제로 파일이 없지만 실제있는것처럼 사용)
		return fileUrl;
	}
	public List<String> uploadFile(List<MultipartFile> multipartFiles) throws IOException { // 여러개 업로드
		
		List<String> fileUrls = new ArrayList<>();
		for(MultipartFile multipartFile : multipartFiles) {
			String fileurl = uploadFile(multipartFile);
			fileUrls.add(fileurl);
		}
		return fileUrls;
	}
	private String fileSave(String originalName, byte[] fileData) throws IOException { // 진짜 업로드 해주는 메서드
		
		UUID uuid = UUID.randomUUID(); // 이름 중복을 방지하기 위한 랜덤 문자열 생성
		// 업로드 하는 파일의 확장자 추출하기 -> .jpg, .PNG, hwp
		String ext = originalName.substring(originalName.lastIndexOf(".") ); // 이름.확장자 > 뒤에서 .까지 가져옴
		// 실제 업로드 해서 저장될 이름
		String saveName = uuid.toString() + ext;
		//업로드 경로와 업로드파일명
		String fileUploadUrl = filePath + "/" + saveName; // /는 안으로 들어간다. 
		//진짜 파일 업로드 실행
		
		FileOutputStream fos = new FileOutputStream( fileUploadUrl);
		fos.write(fileData);
		fos.close();
		
		return saveName;
	}
}
