//package com.myweb.www.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestControllerAdvice //해당 클래스는 전역 예외 처리를 위한 어드바이스로 동작하며, 모든 컨트롤러에서 발생하는 예외를 처리한다
//public class CommonExceptionAdvice {
//	
//	@ExceptionHandler(Exception.class)
//	public String except(Exception ex) {
//		log.info(">>> exception> "+ex.getMessage());
//		return "error_page";
//	}
//	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public String handler404(NoHandlerFoundException ex) {
//		return "custom404";
//	}
//}
