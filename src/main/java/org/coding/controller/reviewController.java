package org.coding.controller;


import java.util.ArrayList;

import org.coding.model.AttachVO;
import org.coding.model.BoardVO;
import org.coding.model.CriteriaVO;
import org.coding.model.PageVO;
import org.coding.service.reviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class reviewController {

	@Autowired
	reviewService bs;
	
	// 게시판 목록 리스트
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(Model model, CriteriaVO cri) {
		model.addAttribute("list", bs.list(cri));

		int total=bs.total(cri);

		model.addAttribute("paging", new PageVO(cri, total));
		
		return "board/list";
	}
	
	// 게시판 상세 페이지
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	
	public String detail (BoardVO board, Model model) {
		System.out.println(board);

		// 조회수
		bs.count(board);

		model.addAttribute("detail", bs.detail(board));
		return "board/detail";
	}

	// 게시판 수정
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public String modifyget (BoardVO board, Model model) {
		System.out.println(board);
		model.addAttribute("detail", bs.detail(board));
		return "board/modify";
	}
	
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public String modifypost (BoardVO board, RedirectAttributes rttr) {
		bs.modify(board);
		rttr.addFlashAttribute("result", "modify success");
		return "redirect:/board/list";
	}
	
	// 게시판 삭제
	@RequestMapping(value = "/board/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public String remove (BoardVO board) {
		bs.remove(board);
		return "redirect:/board/list";
	}
	
	
	// 게시판 글쓰기 페이지(화면) 
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	// 게시판 글쓰기 페이지(insert)
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost (BoardVO board) {

		System.out.println(board);

		bs.write(board);
		return "redirect:/board/list";
	}


	// 게시글 글쓰기 첨부파일
	@RequestMapping(value = "/attachlist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<AttachVO>> uploadAjaxPost(int bno) {
		
		return new ResponseEntity<>(bs.attachlist(bno),HttpStatus.OK);
	}
	
}
