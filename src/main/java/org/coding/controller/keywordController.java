package org.coding.controller;

import org.coding.model.keywordVO;
import org.coding.service.keywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class keywordController {

	@Autowired
	keywordService sws;
	
	@RequestMapping(value = "/keyword/new", method = RequestMethod.POST) 
	public ResponseEntity<String> keyword(@RequestBody keywordVO keyword){
		                                     // 모델을 비동기식으로 처리
		// insert가 성공했으면 result 변수에 1 저장
		// insert가 실패했으면 result 변수에 0 저장
		int result = sws.keyword(keyword);
		
		System.out.println(keyword);
		
		// result가 1이면 HttpStatus.OK		
		// result가 0이면 HttpStatus.INTERNAL_SERVER_ERROR

		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
						: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
