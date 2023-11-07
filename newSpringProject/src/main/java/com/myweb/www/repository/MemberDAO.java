package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	MemberVO seletEmail(String username);

	List<AuthVO> selectAuths(String username);

	boolean updateLastLogin(String authEmail);

	int insert(MemberVO mvo);

	int insertAuth(String email);

	List<MemberVO> selectAll(PagingVO pgvo);

	List<AuthVO> selectAuthList(String email);

	int getTotalCount(PagingVO pgvo);

	MemberVO selectOne(String email);

	int updateMvo(MemberVO mvo);

}
