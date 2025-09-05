package com.toyproject.backend.exception;

public class CommentNotFoundException extends RuntimeException{
	public CommentNotFoundException(Long id) {
		super("Entity Not Found with in: " + id);
	}

	public CommentNotFoundException(String message) {
		super(message);
	}

	public CommentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
