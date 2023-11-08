package com.myweb.www.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.apache.ibatis.logging.LogException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.FileDAO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.repository.ProFileDAO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberSerivceImpl implements MemberService{
	private MemberDAO mdao;
	private FileDAO fdao;
//	private ProFileDAO pfdao;

	public MemberSerivceImpl(MemberDAO mdao, FileDAO fdao) {
		this.mdao = mdao;
		this.fdao =fdao;
	}
	
	//lastLogin update
	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail);
	}

	//회원 가입
//	@Transactional
//	@Override
//	public int signUp(MemberVO mvo) {
//	int isOk=mdao.insert(mvo);//회원 등록(member)
//	isOk=mdao.insertAuth(mvo.getEmail()); //권한 주기(auth_memebr)
//	return isOk;		
//	}
	
	//회원 리스트(관리자용)
	@Override
	public List<MemberVO> getList(PagingVO pgvo) {
		
		List<MemberVO> list = mdao.selectAll(pgvo);
		
		for(MemberVO mvo : list) {
		 mvo.setAuthList(mdao.selectAuthList(mvo.getEmail())); //권한 set
		}
		
		return list;
	}
	
	//totalCount
	@Override
	public int getTotalCount(PagingVO pgvo) {
		return mdao.getTotalCount(pgvo);
	}
	
	//회원 상세(관리자 + 유저)
	@Override
	public MemberVO getDetail(String email) {
		return mdao.selectOne(email);
	}

	//회원정보수정(유저-자기정보 수정)
	@Override
	public int postModify(MemberVO mvo) {
		return mdao.updateMvo(mvo);
	}

	@Transactional
	@Override
	public int signUpProfile(MemberDTO mdto) {
		
		int isOk=mdao.insert(mdto.getMvo());//회원 등록(member)
		isOk=mdao.insertAuth(mdto.getMvo().getEmail()); //권한 주기(auth_memebr)
		mdto.getPvo().setEmail(mdto.getMvo().getEmail());
		isOk=fdao.insertProfileImg(mdto.getPvo());
		
		return isOk;
	}
//	public int signUp(MemberVO mvo) {
//	int isOk=mdao.insert(mvo);//회원 등록(member)
//	isOk=mdao.insertAuth(mvo.getEmail()); //권한 주기(auth_memebr)
//	return isOk;		
//	}

	
}
