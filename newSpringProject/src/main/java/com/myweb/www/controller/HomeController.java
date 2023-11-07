package com.myweb.www.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.ibatis.logging.LogException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Slf4j
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private BoardService bsv;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,Principal principal) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		List<BoardVO> list = bsv.getLatestArticle(); //최신글 10개만 가져옴
		
		String email = principal.getName().toString();		
		List<BoardVO> myList=bsv.getMyArticle(email);//내가 쓴 글 가져옴
		
		model.addAttribute("list", list);
		model.addAttribute("myList", myList);
		log.info("list>>>>>>>>>>>"+list);
		return "index";
	}
	
}
//@GetMapping("list")
//public String boardList(Model model,PagingVO pgvo) {
//	List<BoardVO> list=bsv.getList(pgvo);
//	int totalCount = bsv.getTotalCount(pgvo);
//	PagingHandler ph = new PagingHandler(totalCount,pgvo);
//	log.info("여기 list>>> "+list);
//	log.info("여기 ph>>> "+ph);
//	model.addAttribute("list",list);
//	model.addAttribute("ph", ph);
//	return "/board/boardList";	
//}
