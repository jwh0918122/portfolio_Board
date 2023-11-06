package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int commentWrite(CommentVO cvo);//댓글 등록

	PagingHandler getList(long bno, int pageNo);//댓글 리스트 출력

	int modify(CommentVO cvo);//댓글 수정

	int remove(long cno);

}
