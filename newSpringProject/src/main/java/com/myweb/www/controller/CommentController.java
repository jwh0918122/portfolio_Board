package com.myweb.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value = "/commentWrite",consumes = "application/json",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> postComment(@RequestBody CommentVO cvo){
		int isOk=csv.commentWrite(cvo);
		
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK)
				:new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/list/{bno}/{page}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") long bno,@PathVariable("page") int pageNo ){
		log.info("bno>>>>>"+bno);
		log.info("pageNo>>>>>"+pageNo);
		
		//ServiceImpl에서 pgvo,totalCount,cmtList 구해서 ph로 받기
		PagingHandler ph = csv.getList(bno,pageNo);
		
		log.info("ph>>>>>"+ph);
		return new ResponseEntity<PagingHandler>(ph,HttpStatus.OK);
		
	}
	
	

}
