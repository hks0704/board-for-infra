package com.toyproject.backend.exception;

public class ArticleNotFoundException extends RuntimeException{
	public ArticleNotFoundException(Long id) {
		super("Entity Not Found with in: " + id);
	}

	public ArticleNotFoundException(String message) {
		super(message);
	}

	public ArticleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
