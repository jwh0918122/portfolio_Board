package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProFileVO {
	private String uuid; 
	private String saveDir;
	private String fileName;
	private int fileType;
	private String email;
	private long fileSize;
}
