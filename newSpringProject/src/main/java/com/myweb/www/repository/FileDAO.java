package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.ProFileVO;

public interface FileDAO {

	int insertFile(FileVO file);

	List<FileVO> getFlist(long bno);

	int fileRemove(String uuid);

	int getTotalCount(long bno);

	List<FileVO> selectAllFiles();

	int insertProfileImg(ProFileVO pvo);

	ProFileVO getProfileImg(String email);


}
