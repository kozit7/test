package org.coding.controller;

import java.util.ArrayList;

import org.coding.model.ReplyVO;
import org.coding.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReplyController {
	
	@Autowired
	ReplyService rs;
	
	// 1. 댓글 쓰기
	@RequestMapping(value = "/replies/new", method = RequestMethod.POST) 
	public ResponseEntity<String> replywrite(@RequestBody ReplyVO reply){

		int result = rs.rewrite(reply);
		
		System.out.println(reply);

		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
						: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	// 2. 댓글 목록 리스트
	@RequestMapping(value = "/replies/{bno}", method = RequestMethod.GET) 
	public ResponseEntity<ArrayList<ReplyVO>> getList(@PathVariable int bno){	
		//System.out.println(bno);
		// rs.list(bno);
		return new ResponseEntity<>(rs.list(bno),HttpStatus.OK);
	}
	
	// 3. 댓글 수정
	@RequestMapping(value = "/replies/modify", method = RequestMethod.PUT) 
	public ResponseEntity<String> replymodify(@RequestBody ReplyVO reply){
		//System.out.println(reply);	                                    

		int result = rs.modify(reply);

		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
						: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 3. 댓글 수정2
	@RequestMapping(value = "/replies/remove/{rno}", method = RequestMethod.DELETE) 
	public ResponseEntity<String> replyremove(@PathVariable int rno){
		//System.out.println(rno);	                                    
		int result = rs.remove(rno);

		return result==1? new ResponseEntity<>("success",HttpStatus.OK)
						: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
						
	}

}
