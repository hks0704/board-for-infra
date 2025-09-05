package com.toyproject.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseData<T> {
	private static final String SUCCESS_STATUS = "success";
	private static final String FAILURE_STATUS = "failure";
	private static final String ERROR_STATUS = "error";

	private static final String SUCCESS_MESSAGE = "The request has been processed successfully.";
	private static final String FAILURE_MESSAGE = "The request has failed due to an error in the input or request parameters.";
	private static final String ERROR_MESSAGE = "An unexpected error occured while processing the request.";

	private String status;
	private String message;
	private T data;


	public static <T> ResponseData<T> success() {
		return new ResponseData<>(SUCCESS_STATUS, SUCCESS_MESSAGE, null);
	}

	public static <T> ResponseData<T> success(T data) {
		return new ResponseData<>(SUCCESS_STATUS, SUCCESS_MESSAGE, data);
	}

	public static <T> ResponseData<T> success(String message) {
		return new ResponseData<>(SUCCESS_STATUS, message, null);
	}

	public static <T> ResponseData<T> success(String message, T data) {
		return new ResponseData<>(SUCCESS_STATUS, message, data);
	}

	public static <T> ResponseData<T> failure() {
		return new ResponseData<>(FAILURE_STATUS, FAILURE_MESSAGE, null);
	}

	public static <T> ResponseData<T> failure(String message) {
		return new ResponseData<>(FAILURE_STATUS, message, null);
	}

	public static <T> ResponseData<T> error() {
		return new ResponseData<>(ERROR_STATUS, ERROR_MESSAGE, null);
	}

	public static <T> ResponseData<T> error(String message) {
		return new ResponseData<>(ERROR_STATUS, message, null);
	}

	public static <T> ResponseData<T> custom(String status, String message, T data) {
		return new ResponseData<>(status, message, data);
	}

}
