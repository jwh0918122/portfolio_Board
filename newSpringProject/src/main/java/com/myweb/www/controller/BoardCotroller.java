package com.myweb.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardCotroller {
// 폴더명 : board / mapping : board
	// mpapping => /board/register
	// 목적지 => /board/register

	private BoardService bsv;

	@Autowired
	public BoardCotroller(BoardService bsv) {
		this.bsv = bsv;
	}
	
	//register page로 이동
	@GetMapping("/register")
	public void register() {
		log.info("테스트>>>>>>>>>>>>>>>>>>");
	}
	
	//글 등록
	@PostMapping("/register")
	public String postRegister(BoardVO bvo) {
		int isOk=bsv.write(bvo);
		return "redirect:/board/list";
	}
	
	//리스트
	@GetMapping("list")
	public String boardList(Model model,PagingVO pgvo) {
		List<BoardVO> list=bsv.getList(pgvo);
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(totalCount,pgvo);
		
		model.addAttribute("list",list);
		model.addAttribute("ph", ph);
		return "/board/boardList";	
	}
	
	//글 상세
	@GetMapping("detail")
	public String boardDetail(@RequestParam("bno")long bno,Model model) {
		BoardVO bvo = bsv.getDetail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/boardDetail";
	}
	
	//boardModify Page로 이동
	@GetMapping("modify")
	public String boardModify(@RequestParam("bno") long bno,Model model) {
		BoardVO bvo = bsv.getDetail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/boardModify";
	}
	@PostMapping("modify")
	public String postBoardModify(BoardVO bvo) {
		int isOk=bsv.boardModify(bvo);
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	@GetMapping("remove")
	public String boardRemove(@RequestParam("bno") long bno) {
		int isOk=bsv.boardRemove(bno);
		return "redirect:/board/list";
	}
	
	

	
}
