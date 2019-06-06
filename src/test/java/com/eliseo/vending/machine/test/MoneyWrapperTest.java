package com.eliseo.vending.machine.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import com.eliseo.vending.machine.Money;
import com.eliseo.vending.machine.MoneyWrapper;

public class MoneyWrapperTest {

	private MoneyWrapper mw = new MoneyWrapper();
	
	@Before
	public void init() {
		mw.addCoin(Money.ONE_CENT);
		mw.addCoin(Money.ONE_CENT);
		mw.addCoin(Money.ONE_CENT);//.03
		mw.addCoin(Money.FIVE_CENTS);
		mw.addCoin(Money.FIVE_CENTS);
		mw.addCoin(Money.FIVE_CENTS);//.18
		mw.addCoin(Money.TEN_CENTS);
		mw.addCoin(Money.TEN_CENTS);
		mw.addCoin(Money.TEN_CENTS);//.48
		mw.addCoin(Money.TWENTY_FIVE_CENTS);
		mw.addCoin(Money.TWENTY_FIVE_CENTS);
		mw.addCoin(Money.TWENTY_FIVE_CENTS);//1.23	
		mw.addCoin(Money.FIFTY_CENTS);
		mw.addCoin(Money.FIFTY_CENTS);
		mw.addCoin(Money.FIFTY_CENTS);//2.73
		mw.addCoin(Money.ONE_DOLLAR);
		mw.addCoin(Money.ONE_DOLLAR);
		mw.addCoin(Money.ONE_DOLLAR);//5.73
		mw.addCoin(Money.TWO_DOLLAR);
		mw.addCoin(Money.TWO_DOLLAR);
		mw.addCoin(Money.TWO_DOLLAR);//11.73
	}
	/**
	 * Test all the coins sum value equals expected value
	 */
	@Test
	public void getTotalAmountTest() {
//		System.out.println((mw.getTotalAmount() == 11.73f)+ " mw.getTotalAmount()"+mw.getTotalAmount());
		System.out.println(mw.getTotalAmount());
		assertEquals(mw.getTotalAmount(), new BigDecimal("11.73").setScale(2, RoundingMode.DOWN) );
	}
	/**
	 * Test number of coins added
	 */
	@Test
	public void printTotalCoinsTest() {
//		System.out.println( (mw.printTotalCoins()== 21)+ " mw.printTotalCoins() "+mw.printTotalCoins() );
		assertEquals(mw.printTotalCoins(), 21);
	}
	
	
//	public static void main(String[] args) {
//		 MoneyWrapperTest mwt = new MoneyWrapperTest();
//		 mwt.init();
//		 mwt.getTotalAmountTest();
//		 mwt.printTotalCoinsTest();
//	}
}
