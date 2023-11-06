package com.myweb.www.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

@Service
public class MemberSerivceImpl implements MemberService{
	private MemberDAO mdao;

	public MemberSerivceImpl(MemberDAO mdao) {
		this.mdao = mdao;
	}
	
	//lastLogin update
	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail);
	}

	//회원 가입
	@Transactional
	@Override
	public int signUp(MemberVO mvo) {
	int isOk=mdao.insert(mvo);//회원 등록(member)
	isOk=mdao.insertAuth(mvo.getEmail()); //권한 주기(auth_memebr)
	return isOk;		
	}
	
	
}
