package com.myweb.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	
	private CommentService csv;
	
	@Autowired
	public CommentController(CommentService csv) {
		this.csv = csv;
	}
	
	//댓글 등록
	@PostMapping(value = "/commentWrite",consumes = "application/json",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> postComment(@RequestBody CommentVO cvo){
		int isOk=csv.commentWrite(cvo);
		
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK)
				:new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//댓글 리스트 출력
	@GetMapping(value = "/list/{bno}/{page}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") long bno,@PathVariable("page") int pageNo ){
		log.info("bno>>>>>"+bno);
		log.info("pageNo>>>>>"+pageNo);
		
		//ServiceImpl에서 pgvo,totalCount,cmtList 구해서 ph로 받기
		PagingHandler ph = csv.getList(bno,pageNo);
		
		log.info("ph>>>>>"+ph);
		return new ResponseEntity<PagingHandler>(ph,HttpStatus.OK);
	}
	//댓글 수정
	@PutMapping(value = "/cmtModify",consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody CommentVO cvo){
		int isOk=csv.modify(cvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK)
				:new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//댓글 삭제
	@DeleteMapping(value = "/cmtRemove/{cno}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("cno") long cno){
		log.info("cno2>>>>>"+cno);
		int isOk=csv.remove(cno);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK)
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
