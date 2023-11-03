package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int commentWrite(CommentVO cvo);

	PagingHandler getList(long bno, int pageNo);

}
