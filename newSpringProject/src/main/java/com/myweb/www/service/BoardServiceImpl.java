package com.myweb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardDAO bdao;
	private FileDAO fdao;
	private CommentDAO cdao;

	@Autowired
	public BoardServiceImpl(BoardDAO bdao, FileDAO fdao, CommentDAO cdao) {
		this.bdao = bdao;
		this.fdao = fdao;
		this.cdao = cdao;
	}

//	//글 등록
//	@Override
//	public int write(BoardVO bvo) {
//		return bdao.insertBvo(bvo);
//	}

	// 리스트
	@Transactional
	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		List<BoardVO> list = bdao.selectAll(pgvo);

		for (BoardVO bvo : list) {
			bvo.setHasFile(fdao.getTotalCount(bvo.getBno()));// 파일 갯수 set
			bvo.setCmtQty(cdao.getTotalCount(bvo.getBno()));// 댓글 갯수 set
		}
		return list;
	}

	// 게시글 수 구하기
	@Transactional
	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}

	// 글 상세 + 수정 시 기존(수정 전) bvo가져오기
	@Transactional
	@Override
	public BoardVO getDetail(long bno) {
		int num = 1;
		bdao.readCount(bno, num); // 조회수 +1
		return bdao.getDetail(bno);
	}

	// 글 수정
	@Transactional
	@Override
	public int boardModify(BoardDTO bdto) {
		int num = -2;
		bdao.readCount(bdto.getBvo().getBno(), num); // 조회수 -2
		int isOk = bdao.update(bdto.getBvo());
		if (bdto.getFlist() == null) {
			return isOk;
		} else if (isOk > 0 && bdto.getFlist().size() > 0) {
			for (FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	// 글 삭제
	@Override
	public int boardRemove(long bno) {
		return bdao.delete(bno);
	}

	// 글 등록
	@Transactional
	@Override
	public int write(BoardDTO bdto) {
		int isOk = bdao.insertBvo(bdto.getBvo());

		if (bdto.getFlist() == null) { // 파일이 없다면 그냥 ok=1해서 리턴
			return isOk;
		} else {// 등록한 파일이 존재한다면

			// 방금 등록한 게시글 bno 가져오기
			long bno = bdao.selectBno();

			for (FileVO file : bdto.getFlist()) {
				// 모든 file에 bno set하기
				file.setBno(bno);
				// 파일 등록
				isOk *= fdao.insertFile(file);
			}

		}

		return isOk;
	}

	// 파일 리스트 가져오기
	@Override
	public List<FileVO> getFlist(long bno) {
		return fdao.getFlist(bno);
	}

	// 파일 삭제
	@Override
	public int fileRemove(String uuid) {
		return fdao.fileRemove(uuid);
	}

	//최신글 10개만 가져옴
	@Override
	public List<BoardVO> getLatestArticle() {
		return bdao.getLatestArticle();
	}

	//내가 쓴 글 가져옴
	@Override
	public List<BoardVO> getMyArticle(String email) {
		return bdao.getMyArticle(email);
	}

}
