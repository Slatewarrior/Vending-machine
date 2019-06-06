package com.eliseo.vending.machine.exceptions;

public class IncorrectCurrencyException extends Exception{

	
	private static final long serialVersionUID = -5738559402604112582L;
	
	
	public IncorrectCurrencyException(String error) {
		super(error);
	}
	
	public IncorrectCurrencyException(String error, Throwable throwable) {
		super(error, throwable);
	}
}
