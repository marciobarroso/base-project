package com.icodeuplay.base.common.exceptions;

public class BaseCommonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseCommonException() {
		super();
	}

	public BaseCommonException(String message) {
		super(message);
	}

	public BaseCommonException(Throwable cause) {
		super(cause);
	}

	public BaseCommonException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseCommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}