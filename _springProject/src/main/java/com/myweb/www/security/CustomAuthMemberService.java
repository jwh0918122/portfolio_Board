package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

//유저,권한 체크하고 db접근
@Slf4j
public class CustomAuthMemberService implements UserDetailsService {
	
	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username이 DB에 설정되어 있는 email인지를 체크해서
		//인증하여 해당 객체를 AuthMember로 리턴
		MemberVO mvo = mdao.selectEmail(username);
		if(mvo == null) {
			throw new UsernameNotFoundException(username);
		}

		mvo.setAuthList(mdao.selectAuths(username)); 
		log.info(">>>>>> userDetails >>> "+mvo);
		
		return new AuthMember(mvo); 
	}
}