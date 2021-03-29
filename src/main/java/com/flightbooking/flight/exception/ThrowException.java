package com.flightbooking.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ThrowException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ThrowException(String msg)
	{
		super(msg);
	}

}
