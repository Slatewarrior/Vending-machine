package com.eliseo.vending.machine;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.eliseo.vending.machine.exceptions.IncorrectCurrencyException;

public enum Money {

	ONE_CENT(new BigDecimal(0.01),"One Cent"),
	FIVE_CENTS( new BigDecimal(0.05),"Five Cents"),
	TEN_CENTS(new BigDecimal(0.10f),"Ten Cents"),
	TWENTY_FIVE_CENTS( new BigDecimal(0.25),"Twenty Cents"),
	FIFTY_CENTS( new BigDecimal(0.50) ,"Fifty Cents"),
	ONE_DOLLAR( new BigDecimal(1),"One Dollar"),
	TWO_DOLLAR( new BigDecimal(2),"Two Dollars")
	;

	private BigDecimal cuantity;
	private String displayValue;

	private Money(BigDecimal cuantity, String displayValue) {
		cuantity = cuantity.setScale(2, RoundingMode.DOWN);
		this.cuantity = cuantity;
		this.displayValue = displayValue; 
	}

	public static Money getMoneyBy(BigDecimal cuantity) throws IncorrectCurrencyException {
		for(Money money : values() ) {
			if(money != null && money.getCuantity().equals(cuantity)) {
				return money;
			}
		}
		throw new IncorrectCurrencyException("The currency amount is not valid");
	}

	public BigDecimal getCuantity() {
		return cuantity;
	} 
	public String getDisplayValue() {
		return displayValue;
	}
	
	
	public  void setCuantity(BigDecimal quantity) throws IncorrectCurrencyException {
		Money money= getMoneyBy(quantity);
		this.cuantity = money.getCuantity();
		this.displayValue = money.getDisplayValue(); 
	}
	
	
}
