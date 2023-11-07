package com.myweb.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	private MemberService msv;
	private BCryptPasswordEncoder bcEncoder;

	@Autowired
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
	//회원 리스트(관리자용)
	@GetMapping("/list")
	public String getList(Model model,PagingVO pgvo) {
		List<MemberVO> list = msv.getList(pgvo);
		int totalCount = msv.getTotalCount(pgvo);
		
		PagingHandler ph = new PagingHandler(totalCount,pgvo);
		model.addAttribute("list", list);
		model.addAttribute("ph", ph);		
		return "/member/memberList";
		
	}
	
	//회원 상세(유저용)
	@GetMapping("/detail")
	public String getDetail(@RequestParam("email")String email,Model model) {
		MemberVO mvo = msv.getDetail(email);
		model.addAttribute("mvo", mvo);
		return "/member/memberDetail";
	}
	
	//회원정보수정(유저-자기정보 수정)
	@GetMapping("/modify")
	public String getModify(@RequestParam("email")String email,Model model) {
		MemberVO mvo = msv.getDetail(email);
		model.addAttribute("mvo", mvo);
		return "/member/memberModify";
	}
	
	@PostMapping("/Modify")
	public String postModify(MemberVO mvo) {
		int isOk=msv.postModify(mvo);
		return"redirect:/member/memberDetail?email"+mvo.getEmail();
	}
	
	
	
	//로그아웃
		private void logout(HttpServletRequest req, HttpServletResponse res) {
			// SecurityContextHolder.getContext().getAuthentication()는 
			//Spring Security에서 현재 인증된 사용자의 정보를 가져오는 메서드입니다. 
			//이것은 Authentication 객체를 반환하며, 이 객체에는 사용자의 정보와 권한이 포함됩니다.
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			//SecurityContextLogoutHandler는 이 Authentication 객체를 사용하여 
			//사용자를 로그아웃하는 역할을 합니다.
			new SecurityContextLogoutHandler().logout(req, res, auth);
		}
	
}
