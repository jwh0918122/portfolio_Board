package com.myweb.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
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
	private FileHandler fh;

	@Autowired
	public BoardCotroller(BoardService bsv,FileHandler fh) {
		this.bsv = bsv;
		this.fh=fh;
	}
	
	//register page로 이동
	@GetMapping("/register")
	public void register() {
		log.info("테스트>>>>>>>>>>>>>>>>>>");
	}
	
	//글 등록
	@PostMapping("/register")
	public String postRegister(BoardVO bvo,MultipartFile[] files) {
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist =fh.uploadFiles(files);			
		}
		
		int isOk=bsv.write(new BoardDTO(bvo,flist));
		return "redirect:/board/list";
	}
	
	//리스트
	@GetMapping("list")
	public String boardList(Model model,PagingVO pgvo) {
		List<BoardVO> list=bsv.getList(pgvo);
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(totalCount,pgvo);
		log.info("여기 list>>> "+list);
		log.info("여기 ph>>> "+ph);
		model.addAttribute("list",list);
		model.addAttribute("ph", ph);
		return "/board/boardList";	
	}

	//글 상세
	@GetMapping("detail")
	public String boardDetail(@RequestParam("bno")long bno,Model model) {
		BoardVO bvo = bsv.getDetail(bno);
		List<FileVO> flist = bsv.getFlist(bno);
		BoardDTO bdto = new BoardDTO(bvo,flist);
		model.addAttribute("bdto",bdto);
		return "/board/boardDetail";
	}
	
	//boardModify Page로 이동
	@GetMapping("modify")
	public String boardModify(@RequestParam("bno") long bno,Model model) {
		BoardVO bvo = bsv.getDetail(bno);
		List<FileVO> flist = bsv.getFlist(bno);
		BoardDTO bdto = new BoardDTO(bvo,flist);
		model.addAttribute("bdto",bdto);
		return "/board/boardModify";
	}
	//게시글, 파일 수정
	@PostMapping("modify")
	public String postBoardModify(BoardVO bvo,MultipartFile[] files) {
		List<FileVO> flist =null;
		if(files[0].getSize()>0) {
			flist=fh.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo,flist);
		int isOk=bsv.boardModify(bdto);
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	//게시글 삭제(해당 bno의 전체 파일 자동 삭제)
	@GetMapping("remove")
	public String boardRemove(@RequestParam("bno") long bno) {
		int isOk=bsv.boardRemove(bno);
		return "redirect:/board/list";
	}
	//파일 삭제
	@DeleteMapping(value = "/fileRemove/{uuid}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> fileRemove(@PathVariable("uuid") String uuid) {
		int isOk=bsv.fileRemove(uuid);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK)
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
}
