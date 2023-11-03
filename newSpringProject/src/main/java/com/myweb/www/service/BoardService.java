package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int write(BoardVO bvo);//글등록

	List<BoardVO> getList(PagingVO pgvo);//리스트

	int getTotalCount(PagingVO pgvo);//게시글 수 구하기

	BoardVO getDetail(long bno);

	int boardModify(BoardVO bvo);

	int boardRemove(long bno);



}
