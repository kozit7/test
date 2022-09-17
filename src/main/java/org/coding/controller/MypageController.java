package org.coding.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.coding.model.MemberVo;
import org.coding.model.MypageVO;
import org.coding.model.ReplyVO;
import org.coding.model.UploadFileVO;
import org.coding.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class MypageController {
		
	@Autowired
	MypageService ms;
	// 마이페이지 
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String Mypage (MypageVO mypage, HttpSession session,Model model) { 
		MemberVo login= (MemberVo)session.getAttribute("login");
		//mypage.setId(id);
		
		System.out.println(login.getId());
		model.addAttribute("mypage",ms.mypage(login.getId()));	
				
		return "mypage/mypage";
	}
	
	// 마이페이지 개인정보 변경 GET
	@RequestMapping(value ="/mypage2", method = RequestMethod.GET)
		public String Mypage2 () {
		return "mypage/mypage2";
	
	}
	
	// 마이페이지 개인정보 변경 POST
	@RequestMapping(value ="/mypage2", method = RequestMethod.POST)
		public String UploadAjaxAction(MemberVo member, MultipartFile[] uploadFile) {
		System.out.println("controller="+member);
		ms.mywrite(member);
		return "redirect:/mypage";
	}
	

	
	
private String getFolder() {
		
		// 현재날짜 추출
		Date date = new Date();
		
		// format()을 통해 우리가 원하는 양식으로 바꿀 수 있음
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM--dd");
		
		// 현재날짜와 날짜형식을 연결
		String str=sdf.format(date); // 현재날짜
		
		return str.replace("-", "\\");
	}
	
	// 올리는 팡리이 이미지파일인지 아닌지 구분하는 메소드 선언
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			System.out.println("contentType="+ contentType);
			
			// 파일타입이 image이면 true, 그 외는 false
			return contentType.startsWith("image"); // startsWith는 boolean타입
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
		
	} //checkImageType
	
	// 파일 업로드
	@RequestMapping(value="/uploadAjaxAction3", method=RequestMethod.POST)
	public ResponseEntity<ArrayList<UploadFileVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		//UploadFileVO 클래스 포함
		ArrayList<UploadFileVO> list = new ArrayList<>();
		
		// 폴더 경로
		String uploadFolder="D:\\01-STUDY\\upload";
		
		// 서버 업로드 경로와 getFolder메서드의 날짜문자열을 이어서 하나의 폴더 생성
		File uploadPath = new File(uploadFolder, getFolder());
		
		// 폴더 생성(D:\\01-STUDY\\upload\\현재날짜
		if(uploadPath.exists()==false) {// uploadPath가 존재하지 않으면,
		uploadPath.mkdirs();
		}
		
		
		for(MultipartFile multipartFile : uploadFile) {
			
			// UploadFileVO 클래스의 새로운 주소를 반복적으로 생성하여 list에 저장
			UploadFileVO upfilevo = new UploadFileVO();
		
		System.out.println(multipartFile.getOriginalFilename());
		System.out.println(multipartFile.getSize());
		
		// UUID 적용(UUID_multipartFile.getOriginalFilename());
		UUID uuid = UUID.randomUUID();
		System.out.println("UUID="+uuid.toString());
		
		// uploadPath 변수에 저장
		upfilevo.setUploadPath(getFolder());
		// filename 변수에 저장
		upfilevo.setFileName(multipartFile.getOriginalFilename());
		// uuid 변수에 저장
		upfilevo.setUuid(uuid.toString());
		
		// 파일 저장
		
		File saveFile = new File(uploadPath, uuid.toString()+"_"+multipartFile.getOriginalFilename());
		
		try { 
			
			multipartFile.transferTo(saveFile);
			
			// 내가 서버에 올리고자 하는 파일이 이미지이면
			if(checkImageType(saveFile)) {
				
				upfilevo.setImage(true);
				
				// 파일생성
				FileOutputStream thumnail = new FileOutputStream(new File(uploadPath, "s_"+uuid.toString()+"_"+multipartFile.getOriginalFilename()));
				
				// 썸네일 형식의 파일생성
				Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumnail,100,100);
				
				thumnail.close();
				
			} // checkImageType 끝
			
			list.add(upfilevo);
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
		} // catach
	} // for
		return new ResponseEntity<>(list,HttpStatus.OK);
  }
	@RequestMapping(value="/display2", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(String fileName){
		
		File file = new File ("D:\\01-STUDY\\upload\\"+fileName);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
		
	
	@RequestMapping(value = "/profileUpload", method = RequestMethod.POST) 
	public void profilePost(MemberVo member) {
		ms.pwrite(member);
	}	

}