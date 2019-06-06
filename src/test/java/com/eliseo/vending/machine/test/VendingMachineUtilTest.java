package com.eliseo.vending.machine.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.eliseo.vending.machine.Money;
import com.eliseo.vending.machine.MoneyWrapper;
import com.eliseo.vending.machine.exceptions.IncorrectCurrencyException;
import com.eliseo.vending.machine.util.VendingMachineUtil;

public class VendingMachineUtilTest {
	
	
	/**
	 * Test that $3.91 of credit get next result 
	 * 2 Dollars 
	 * 1 Dollar
	 * 50 Cents
	 * 25 Cents
	 * 10 Cents
	 * 5 Cents 
	 * 1 Cents 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchangeForOneOfEachCoinsTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.TWO_DOLLAR);
		expectedMoney.addCoin(Money.ONE_DOLLAR);
		expectedMoney.addCoin(Money.FIFTY_CENTS);
		expectedMoney.addCoin(Money.TWENTY_FIVE_CENTS);
		expectedMoney.addCoin(Money.TEN_CENTS);
		expectedMoney.addCoin(Money.FIVE_CENTS);
		expectedMoney.addCoin(Money.ONE_CENT);
		MoneyWrapper resultMoney = new MoneyWrapper();
		resultMoney = VendingMachineUtil.getExchange(  new BigDecimal(3.91));
//		System.out.println(" 3 Dollars 91 cents "+ (expectedMoney.equals(resultMoney) ));
		assertTrue(expectedMoney.equals(resultMoney));
	}
	/**
	 * Test that $2 of credit get next result 
	 * 2 Dollars 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchange2DollarsTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.TWO_DOLLAR);
//		System.out.println("2 Dollars "+ (expectedMoney.equals(VendingMachineUtil.getExchange(2f)) ));
		assertTrue( expectedMoney.equals(VendingMachineUtil.getExchange( new BigDecimal(2) )) );
	}
	/**
	 * Test that $1 of credit get next result  
	 * 1 Dollar
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchange1DollarsTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.ONE_DOLLAR);
		System.out.println("1 Dollars "+ (expectedMoney.equals(VendingMachineUtil.getExchange( new BigDecimal(1))) ));
		assertTrue( expectedMoney.equals(VendingMachineUtil.getExchange( new BigDecimal(1))) );
	}
	/**
	 * Test that $0.5 of credit get next result  
	 * 50 Cents
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchangeFiftyCentsTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.FIFTY_CENTS);
//		System.out.println("50 Cents "+ (expectedMoney.equals(VendingMachineUtil.getExchange(0.50f)) ));
		assertTrue( expectedMoney.equals(VendingMachineUtil.getExchange( new BigDecimal(0.50) )) );
	}
	/**
	 * Test that $.25 of credit get next result 
	 * 25 Cents
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchangeTwentyFiveCentsTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.TWENTY_FIVE_CENTS);
//		System.out.println("25 Cents "+ (expectedMoney.equals(VendingMachineUtil.getExchange(0.25f)) ));
		assertTrue( expectedMoney.equals(VendingMachineUtil.getExchange( new BigDecimal(0.25) )) );
	}
	/**
	 * Test that $0.10 of credit get next result 
	 * 10 Cents
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchangeTenCentsTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.TEN_CENTS);
		assertTrue( expectedMoney.equals(VendingMachineUtil.getExchange( new BigDecimal(0.10) )) );
//		System.out.println("10 Cents "+ (expectedMoney.equals(VendingMachineUtil.getExchange(0.10f)) ));
	}
	/**
	 * Test that $0.05 of credit get next result 
	 * 5 Cents 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchangeFiveCentsTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.FIVE_CENTS);
		assertTrue( (expectedMoney.equals(VendingMachineUtil.getExchange( new BigDecimal(0.05) )) ));
//		System.out.println("5 Cents "+ (expectedMoney.equals(VendingMachineUtil.getExchange(0.05f)) ));
	}
	/**
	 * Test that $0.1 of credit get next result 
	 * 1 Cents 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void getExchangeOneCentTest() throws IncorrectCurrencyException {
		MoneyWrapper expectedMoney = new MoneyWrapper();
		expectedMoney.addCoin(Money.ONE_CENT);
		MoneyWrapper result =VendingMachineUtil.getExchange( new BigDecimal(0.01));
		assertTrue( expectedMoney.equals(result) );
//		System.out.println("1 Cent "+ (expectedMoney.equals(result) ) );
	}
	
	/**
	 * Test that $2 is mapped into 2 Dollars Enum 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void calculate2DollarNoteTest() throws IncorrectCurrencyException {
		Money note = VendingMachineUtil.calculateCoin( new BigDecimal(2));
//		System.out.println("2 dollar = " + (Money.TWO_DOLLAR == note) );
		assertTrue( Money.TWO_DOLLAR.equals(note)  );
	}
	/**
	 * Test that $1 is mapped into 1 Dollars Enum 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void calculate1DollarNoteTest() throws IncorrectCurrencyException {
		Money note = VendingMachineUtil.calculateCoin( new BigDecimal(1));
//		System.out.println("1 dollar = " + (Money.ONE_DOLLAR == note) );
		assertTrue( Money.ONE_DOLLAR.equals(note)  );
	}
	/**
	 * Test that $0.5 is mapped into Fifty cents coin Enum 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void calculate50CentsTest() throws IncorrectCurrencyException {
		Money note = VendingMachineUtil.calculateCoin( new BigDecimal(0.5) );
//		System.out.println("50 cents = " + (Money.FIFTY_CENTS == note) );
		assertTrue( Money.FIFTY_CENTS.equals(note)  );
	}
	/**
	 * Test that $0.25 is mapped into twenty cents coin Enum 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void calculate25CentsTest() throws IncorrectCurrencyException {
		Money note = VendingMachineUtil.calculateCoin( new BigDecimal(0.25));
		assertTrue( Money.TWENTY_FIVE_CENTS.equals( note) );
//		System.out.println("25 cents = " + (Money.TWENTY_FIVE_CENTS == note) );
	}
	/**
	 * Test that $0.10 is mapped into ten cents coin Enum 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void calculate10CentsTest() throws IncorrectCurrencyException {
		Money note = VendingMachineUtil.calculateCoin( new BigDecimal(.10));
		assertTrue( Money.TEN_CENTS.equals( note) );
//		System.out.println("10 cents = " + (Money.TEN_CENTS == note) );
	}
	/**
	 * Test that $0.05 is mapped into Five cents coin Enum 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void calculate5CentsTest() throws IncorrectCurrencyException {
		Money note = VendingMachineUtil.calculateCoin( new BigDecimal(0.05) );
		assertTrue( Money.FIVE_CENTS.equals( note) );
//		System.out.println("5 cents = " + (Money.FIVE_CENTS == note) );
	}
	/**
	 * Test that $0.1 is mapped into One cent coin Enum 
	 * 
	 * @throws IncorrectCurrencyException
	 */
	@Test
	public void calculate1CentTest() throws IncorrectCurrencyException {
		Money note = VendingMachineUtil.calculateCoin( new BigDecimal(0.01) );
		assertTrue(  Money.ONE_CENT.equals( note)  );
//		System.out.println("1 cent = " + (Money.ONE_CENT == note) );
	}
	
	
//	public static void main(String[] args) throws IncorrectCurrency {
//		VendingMachineUtilTest vomop = new VendingMachineUtilTest();
//		vomop.before();
//		vomop.getExchangeForOneOfEachCoinsTest();
//		vomop.getExchange2DollarsTest();
//		vomop.getExchange1DollarsTest();
//		vomop.getExchangeFiftyCentsTest();
//		vomop.getExchangeTwentyFiveCentsTest();
//		vomop.getExchangeTenCentsTest();
//		vomop.getExchangeFiveCentsTest();
//		vomop.getExchangeOneCentTest();
		
		
//		vomop.calculate2DollarNoteTest();
//		vomop.calculate1DollarNoteTest();
//		vomop.calculate50CentsTest();
//		vomop.calculate25CentsTest();
//		vomop.calculate10CentsTest();
//		vomop.calculate5CentsTest();
//		vomop.calculate1CentTest();
//	}
}
