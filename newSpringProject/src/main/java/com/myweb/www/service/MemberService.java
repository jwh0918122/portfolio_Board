package com.myweb.www.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.security.MemberVO;

public interface MemberService {

	boolean updateLastLogin(String authEmail);

//	int signUp(MemberVO mvo);

	List<MemberVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	MemberVO getDetail(String email);

	int postModify(MemberVO mvo);

	int signUpProfile(MemberDTO mdto);

}
