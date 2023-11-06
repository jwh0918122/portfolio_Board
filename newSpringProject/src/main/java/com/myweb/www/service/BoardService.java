package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int write(BoardDTO bdto);//글등록

	List<BoardVO> getList(PagingVO pgvo);//리스트

	int getTotalCount(PagingVO pgvo);//게시글 수 구하기

	BoardVO getDetail(long bno);

	int boardModify(BoardDTO bdto);

	int boardRemove(long bno);

	List<FileVO> getFlist(long bno);

	int fileRemove(String uuid);



}
