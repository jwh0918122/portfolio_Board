package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	int getTotalCount(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	List<CommentVO> getcmtList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

}
