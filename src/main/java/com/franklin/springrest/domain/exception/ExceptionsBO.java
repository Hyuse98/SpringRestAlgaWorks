package com.franklin.springrest.domain.exception;

public class ExceptionsBO extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExceptionsBO(String message) {
		super(message);		
	}

}
