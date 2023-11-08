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
import com.myweb.www.domain.ProFileVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.FileDAO;
import com.myweb.www.repository.ProFileDAO;
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
	@Inject
	private FileDAO fdao;
//	private ProFileDAO pfdao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Principal principal) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		List<BoardVO> list = bsv.getLatestArticle(); //최신글 10개만 가져옴
		model.addAttribute("list", list);
		log.info("list>>>>>>>>>>>"+list);
		
		try {			
			if(principal.getName() !=null) {
				String 	email =principal.getName();
				log.info("dfdfd "+email);
				List<BoardVO> myList=bsv.getMyArticle(email);//내가 쓴 글 가져옴
				model.addAttribute("myList", myList);
				ProFileVO pvo = fdao.getProfileImg(email);//프로필 이미지
				log.info("PVO>>>"+pvo);
				model.addAttribute("pvo", pvo);
			}			
			
		}catch (Exception e) {
			log.info("null point");
			e.printStackTrace();
		}
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
