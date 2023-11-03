package com.myweb.www.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
	
	private CommentDAO cdao;
	
	@Autowired
	public CommentServiceImpl(CommentDAO cdao) {
		this.cdao = cdao;
	}
	
	//댓글 등록
	@Override
	public int commentWrite(CommentVO cvo) {
		return cdao.insert(cvo);
	}

	//댓글 리스트(+페이징)
	@Override
	public PagingHandler getList(long bno, int pageNo) {		
		
		//pgvo
		PagingVO pgvo = new PagingVO(pageNo,5); //qty => 5	
		log.info("pgvo>>>>"+pgvo);
		//totalCount
		int totalCount = cdao.getTotalCount(bno,pgvo);
		log.info("totalCount>>>>"+totalCount);
		//cmtList
		List<CommentVO> list = cdao.getcmtList(bno,pgvo);
		log.info("list>>>>"+list);
		//PagingHandler 	
		PagingHandler ph=new PagingHandler(pgvo, totalCount, list);
		
		return ph;
	}
	


	

	
	
}
