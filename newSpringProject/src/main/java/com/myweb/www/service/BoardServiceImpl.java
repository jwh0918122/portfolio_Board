package com.myweb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardDAO bdao;

	@Autowired
	public BoardServiceImpl(BoardDAO bdao) {
		this.bdao = bdao;
	}

	//글 등록
	@Override
	public int write(BoardVO bvo) {
		return bdao.insertBvo(bvo);
	}
	
	//리스트
	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.selectAll(pgvo);
	}
	
	//게시글 수 구하기
	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}
	
	//글 상세 + 수정 시 기존(수정 전) bvo가져오기
	@Override
	public BoardVO getDetail(long bno) {
		int num=1;
		bdao.readCount(bno,num); //조회수 +1
		return bdao.getDetail(bno);
	}
	
	//글 수정
	@Override
	public int boardModify(BoardVO bvo) {
		int num=-2;
		bdao.readCount(bvo.getBno(),num); //조회수 -2
		return bdao.update(bvo);
	}

	//글 삭제
	@Override
	public int boardRemove(long bno) {
		return bdao.delete(bno);
	}

	
}
