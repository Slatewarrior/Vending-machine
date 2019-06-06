package com.eliseo.vending.machine.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.eliseo.vending.machine.Money;
import com.eliseo.vending.machine.MoneyWrapper;

public class VendingMachineUtil {

	private final static BigDecimal MINIMUM_VALUE_ACEPTED = new BigDecimal(0.01);
	
	
	/**
	 * Convert a float number into a list of coins 
	 * it's using less amount of coins taking first the bigger money value
	 * 
	 * @param quantityExchange
	 * @return MoneyWrapper
	 */
	
	public static MoneyWrapper getExchange(BigDecimal quantityExchange) {
//		List<Money> coins = new ArrayList<Money>();
		MoneyWrapper mw = new MoneyWrapper();
		
		while (quantityExchange.compareTo( MINIMUM_VALUE_ACEPTED ) >=0) {//>= MINIMUM_VALUE_ACEPTED
			Money coin=calculateCoin(quantityExchange);
//			System.out.print(" quantityExchange = "+quantityExchange  +" - "+coin.getCuantity());
			quantityExchange = quantityExchange.subtract( coin.getCuantity());//-= coin.getCuantity()
//			System.out.println("  = "+quantityExchange);
			mw.addCoin(coin);
		}
		return mw;
	}
	/**
	 * Calculate the maximum Coin that can feed in a quantity then return an enum with that value
	 * 
	 * @param quantityExchange
	 * @return Money Enum
	 */
	public static Money calculateCoin( BigDecimal quantityExchange)  {
		if (quantityExchange.compareTo( Money.TWO_DOLLAR.getCuantity()) >=0 ) {//>= Money.TWO_DOLLAR.getCuantity()
			return Money.TWO_DOLLAR;
			
		}else if (quantityExchange.compareTo( Money.ONE_DOLLAR.getCuantity() ) >=0 ) {
			return Money.ONE_DOLLAR;
			
		}else if (quantityExchange.compareTo( Money.FIFTY_CENTS.getCuantity()) >=0) {//>= Money.FIFTY_CENTS.getCuantity()
			return Money.FIFTY_CENTS;
			
		}else if (quantityExchange.compareTo(Money.TWENTY_FIVE_CENTS.getCuantity() ) >=0 ) {// >= Money.TWENTY_FIVE_CENTS.getCuantity()
			return Money.TWENTY_FIVE_CENTS;
			
		}else if (quantityExchange.compareTo( Money.TEN_CENTS.getCuantity() ) >=0 ) {
			return Money.TEN_CENTS;
			
		}else if (quantityExchange.compareTo( Money.FIVE_CENTS.getCuantity() ) >=0) {
			return Money.FIVE_CENTS;
			
		}else {
			return Money.ONE_CENT;
		}
	}
	/**
	 * Calculate the exactly Coin that can feed in a quantity then return an enum with that value
	 * 
	 * @param quantityExchange
	 * @return Money Enum
	 */
	public static Money calculateExactlyCoin( BigDecimal quantityExchange)  {
		quantityExchange = quantityExchange.setScale(2, RoundingMode.DOWN);
		if (quantityExchange.equals(Money.TWO_DOLLAR.getCuantity())) {//==
			return Money.TWO_DOLLAR;
			
		}else if (quantityExchange.equals(Money.ONE_DOLLAR.getCuantity())) {
			return Money.ONE_DOLLAR;
			
		}else if (quantityExchange.equals(Money.FIFTY_CENTS.getCuantity() )) {
			return Money.FIFTY_CENTS;
			
		}else if (quantityExchange.equals(Money.TWENTY_FIVE_CENTS.getCuantity() )) {
			return Money.TWENTY_FIVE_CENTS;
			
		}else if (quantityExchange.equals(Money.TEN_CENTS.getCuantity()) ) {
			return Money.TEN_CENTS;
			
		}else if (quantityExchange.equals(Money.FIVE_CENTS.getCuantity()) ) {
			return Money.FIVE_CENTS;
			
		}else if (quantityExchange.equals( Money.ONE_CENT.getCuantity() )) {
			return Money.ONE_CENT;
		}else {
			return null;
		}
			
	}

//	public static void main(String[] args) throws IncorrectCurrency {
//		System.out.println(new VendingMachineOperations().getExchange(50.51d).printTotalCoins());
//		new VendingMachineOperations().getExchange(25.27d).printTotalCoins();//Error
//		VendingMachineUtil.getExchange(.31f).printTotalCoins();
//	}
}
