package com.myweb.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	private MemberService msv;
	private BCryptPasswordEncoder bcEncoder;

	public MemberController(MemberService msv,BCryptPasswordEncoder bcEncoder) {
		this.msv = msv;
		this.bcEncoder=bcEncoder;
	}
	//회원가입 페이지로 이동
	@GetMapping("/register")
	public void register() {}
	
	//회원가입
	@PostMapping("/signUp")
	public String signUp(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));//비밀번호 암호화
		int isOk=msv.signUp(mvo);
		return "index";
	}
	
	//로그인 페이지로 이동
	@GetMapping("/login")
	public void login() {}
	
	//로그인
	@PostMapping("/login")//로그인 실패할 경우 이걸 탐...?
	public String loginFailur(HttpServletRequest request, RedirectAttributes re){
		//로그인 실패 시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		//다시 로그인 유도
		log.info(">>>맴버컨트롤러errMsg >> "+request.getAttribute("errMsg"));
		re.addAttribute("email",request.getAttribute("email"));
		re.addAttribute("email",request.getAttribute("errMsg"));
		
		return "redirect:/member/login";
	}
	
	//로그아웃
	private void logout(HttpServletRequest req,HttpServletResponse res) {
		Authentication auth = SecurityContextHolder.getContext()
	}
	
}
