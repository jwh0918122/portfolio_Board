package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	MemberVO seletEmail(String username);

	List<AuthVO> selectAuths(String username);

	boolean updateLastLogin(String authEmail);

	int insert(MemberVO mvo);

	int insertAuth(String email);

}
