package org.coding.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.coding.model.AttachVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {
	
	// ajax 이용한 파일 업로드
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
		
	}
	
	// 1. 년/월/일 형태의 폴더를 생성
	private String getFolder() {
		
		// 현재날짜 추출
		Date date = new Date();
		
		// 2022-08-24 로 포맷하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 현재 날짜와 날짜 형식을 String 타입으로 연결
		String str = sdf.format(date);  // 2022-08-24
		
		// 2022-08-24 -> 2022\08\24 로 변경
		return str.replace("-", "\\");
	}
	
	// 2. 이미지 파일인지 아닌지
	private boolean checkImgType(File file) {	
		try {
			String contentType = Files.probeContentType(file.toPath()); 
			System.out.println("contentType = "+contentType);
			
			return contentType.startsWith("image");  // 참일때 반환
				
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;  // 거짓일때 반환
	}
	
	
	@RequestMapping(value = "/uploadAjaxAction", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<AttachVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		System.out.println("uploadAjaxAction controller");
		
		ArrayList<AttachVO> list = new ArrayList<>();
		
		// 폴더 경로 설정
		String uploadFolder="C:\\upload";
		
		// 서버 업로드 경로와 getFolder 메서드의 날짜 문자열을 연결해 하나의 폴더 생성
		File uploadPath = new File(uploadFolder,getFolder());
		
		// 폴더 생성 ("D:\\upload\\현재날짜")
		if(uploadPath.exists()==false) {  // uploadPath가 존재하지 않으면,
			uploadPath.mkdirs();
		}
		
		// for(배열명:변수명)		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachVO attachVO = new AttachVO();
						
			//System.out.println(multipartFile.getOriginalFilename());
			//System.out.println(multipartFile.getSize());
			
			// 파일저장
			UUID uuid = UUID.randomUUID();
			System.out.println("UUID = "+uuid.toString());  
			attachVO.setUploadPath(getFolder());
			attachVO.setFileName(multipartFile.getOriginalFilename());
			attachVO.setUuid(uuid.toString());

			File saveFile = new File(uploadPath,uuid.toString()+"_"+multipartFile.getOriginalFilename());

			try {                     
				multipartFile.transferTo(saveFile);  
				
				if(checkImgType(saveFile)) {  // checkImgType 메서드 시작

					attachVO.setImg(true);
                                                                                          
					FileOutputStream thumnail = new FileOutputStream(new File(uploadPath,"s_"+uuid.toString()+"_"+multipartFile.getOriginalFilename()));
                                                                                                 
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumnail,100,100);
					thumnail.close();
				} // checkImgType 메서드 끝
				
				list.add(attachVO);
							
			}catch(Exception e) { 
				System.out.println(e.getMessage());
			}
		}  // for문 끝
		
		return new ResponseEntity<>(list,HttpStatus.OK);  
	}  // uploadAjax 끝
	
	
	// 이미지 주소 생성  (localhost:8080/display?fileName=a.jfif)
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(String fileName) { 
		System.out.println(fileName);
		
		File file = new File("C:\\upload\\"+fileName);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		try {  
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
					headers,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}  // getFile 메서드 끝
	
	
	// 다운로드 주소 생성  (localhost:8080/download?fileName=a.jfif)
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(String fileName) { 
		
		Resource resource = new FileSystemResource("C:\\upload\\"+fileName);
		
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		
		try {                                                                                   
			headers.add("Content-Disposition", "attachment;filename="+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(resource,headers,HttpStatus.OK);		
	}  // downloadFile 메서드 끝
	
}
