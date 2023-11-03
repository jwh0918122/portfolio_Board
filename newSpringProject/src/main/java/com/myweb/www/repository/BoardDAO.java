package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insertBvo(BoardVO bvo);

	List<BoardVO> selectAll(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	BoardVO getDetail(long bno);

	int update(BoardVO bvo);

	int delete(long bno);

	void readCount(@Param("bno") long bno,@Param("num") int num);

}
