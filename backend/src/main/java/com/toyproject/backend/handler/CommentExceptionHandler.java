package com.toyproject.backend.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.toyproject.backend.domain.ResponseData;
import com.toyproject.backend.exception.CommentNotFoundException;

@ControllerAdvice()
public class CommentExceptionHandler {

	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<?> handleCustomNotFoundException(CommentNotFoundException e) {
		return new ResponseEntity<>(ResponseData.error(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
