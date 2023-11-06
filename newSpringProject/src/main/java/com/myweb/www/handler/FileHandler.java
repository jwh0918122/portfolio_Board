package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Component
@Getter
@Setter
@Slf4j
public class FileHandler {
	private final String UP_DIR="D:\\_myweb\\_java\\fileupload";

	public List<FileVO> uploadFiles(MultipartFile[] files){
		
		List<FileVO> flist=new ArrayList<FileVO>();
		
	//1. 파일 경로(날짜를 폴더로 생성하여 일자별로 업로드 파일을 관리)
		LocalDate date = LocalDate.now();//오늘 날짜(LocalDate 객체=>2023-11-05)
		String today = date.toString().replace("-", File.separator);
	
		// D:\\_myweb\\_java\\fileupload 하위에 오늘 날짜(today) 경로 
		File folders = new File(UP_DIR,today);
		//폴더 생성
		if(!folders.exists()) {//folders가 없다면
			folders.mkdirs(); //mkdir은 폴더1개, mkdirs는 파일 여러개 폴더 생성
		}
		
	//2. files 객체에 대한 설정(fvo set)
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today); //2023\11\05
			fvo.setFileSize(file.getSize());
			
			// 실제 파일이름(ex:이벤트.jpg)
			String originalFileName = file.getOriginalFilename();
			//가끔 앞에 경로가 붙어오는 경우가 있어서 한번 걸러줌
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(fileName);
			
			UUID uuid=UUID.randomUUID();
			fvo.setUuid(uuid.toString());//UUID 객체이기 때문에 문자열로 변환해줘야 함
			
	//3. 하단부터 디스크에 저장할 파일 객페 생성(파일 저장)
			//파일 이름 => uuid_fileName / 썸네일 파일 => uuid_th_fileName
			String fullFileName = uuid.toString()+"_"+fileName;
			
			//folders => D:\\_myweb\\_java\\fileupload\\2023\\11\\05
			//fullFileName => uuid_fileName.jpg
			//D:\\_myweb\\_java\\fileupload\\2023\\10\\24\\uuid_fileName.jpg
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile); //파일 저장
				
				//썸네일 생성(이미지 파일만)
				if(isImageFile(storeFile)) {
					//이미지 파일만 타입에 1설정
					fvo.setFileType(1); 
					// 썸네일 파일 => uuid_th_fileName
					File thumbNail = new File(folders,uuid.toString()+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
			//flist에 fvo 추가
			flist.add(fvo);
		}
		return flist;
	}
	
	//이미지 파일인지 확인하는 메서드
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); // image/jpg
		log.info("mimeType>>>>"+mimeType);
		return mimeType.startsWith("image")? true : false;
	}

}
