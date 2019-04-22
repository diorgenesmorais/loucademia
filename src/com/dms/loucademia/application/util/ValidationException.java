package com.dms.loucademia.application.util;

import javax.ejb.ApplicationException;

@ApplicationException
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -508864073406535603L;

	public ValidationException() {
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

}
