package com.toyproject.backend.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.toyproject.backend.domain.ResponseData;
import com.toyproject.backend.exception.ArticleNotFoundException;

@ControllerAdvice()
public class ArticleExceptionHandler {

	@ExceptionHandler(ArticleNotFoundException.class)
	public ResponseEntity<?> handleCustomNotFoundException(ArticleNotFoundException e) {
		return new ResponseEntity<>(ResponseData.error(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
