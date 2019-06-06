package com.eliseo.vending.machine.exceptions;

public class ValidationCSVException extends Exception{

	
	private static final long serialVersionUID = -5738559402604112582L;
	
	
	public ValidationCSVException(String error) {
		super(error);
	}
	
	public ValidationCSVException(String error, Throwable throwable) {
		super(error, throwable);
	}
}
