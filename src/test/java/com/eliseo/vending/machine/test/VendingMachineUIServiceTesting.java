package com.eliseo.vending.machine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.eliseo.vending.machine.model.VendingMachine;
import com.eliseo.vending.machine.ui.VendingMachineUIService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VendingMachineUIServiceTesting {
	VendingMachineUIService vms ;
	VendingMachine vendingDto = new VendingMachine();
	private final static String FILE_WITH_CORRUPTED_DATA = "product_List_with_Corrupted_Data.csv";//File with corrupted data
	private final static String FILE_VALID = "valid_Product_List.csv";//File is empty
	
	Scanner mockScanner = new Scanner(System.in);
	
	
	@Before
	public void a_ainit() {
		vms = new VendingMachineUIService(vendingDto);
	}
	/**
	 * Test load products list using incremental update via CSV
	 */
	@Test
	public void a_loadProductsIncrementalUdateTest() {
		System.out.println("Loading products");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILE_VALID).getFile());
		boolean wasOk = vms
				.incrementalUpdate(
						file
						.getAbsolutePath());
		assertTrue(wasOk);
	}
	
	/**
	 * Test method add product to shopping car
	 */
	@Test
	public void b_addProductToShoppingCar() {
		a_loadProductsIncrementalUdateTest();
		String inputUser = "SELECT 0";
		vms.addProductToShoppingCar(inputUser);
		assertEquals(vendingDto.getSelectedProducts().size(), 1);
		
	}
	
	/**
	 * Test replace method updating products using file that contains corrupted lines
	 */
	@Test
	public void c_replaceProducts() {
	
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILE_WITH_CORRUPTED_DATA).getFile());
		boolean wasOk = vms.replaceProducts(file.getAbsolutePath());
		assertTrue(wasOk);
	
	}
	
	/**
	 * Test incremental update list of products
	 */
	@Test
	public void d_incrementalUpdate() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILE_VALID).getFile());
		boolean wasOk = vms.incrementalUpdate(file.getAbsolutePath());
		assertTrue(wasOk);
		assertEquals(vendingDto.getSelectedProducts().size(), 0);
		
	}
	
	/**
	 * Show first page products menu
	 */
	@Test
	public void e_showProductsTest() {
		a_loadProductsIncrementalUdateTest();
		System.setIn(new ByteArrayInputStream("CANCEL".getBytes()));
		Scanner scanner = new Scanner(System.in);
		int totalProducts =vms.showProducts(scanner);
		assertTrue(totalProducts >0);
	}
	
	/**
	 * Testing method add product to shopping car
	 */
	@Test
	public void f_addProductToShoppingCar() {
		a_loadProductsIncrementalUdateTest();
		String inputUser = "SELECT 0";
		vms.addProductToShoppingCar(inputUser);
		assertTrue(vendingDto.getSelectedProducts().size() >0);
	}
	
	/**
	 * Adding invalid index to shopping car
	 */
	@Test
	public void f_addProductToShoppingCarInvalidIndex() {
		a_loadProductsIncrementalUdateTest();
		String inputUser = "SELECT 900";
		vms.addProductToShoppingCar(inputUser);
		assertEquals(vendingDto.getSelectedProducts().size(), 0);
	}
	
	/**
	 * Test product after added the shopping car is there
	 */
	@Test
	public void g_showShoppingCar() {
		a_loadProductsIncrementalUdateTest();
		f_addProductToShoppingCar();
		assertTrue(vms.showShoppingCar() >0);
	}
	
	/**
	 * Adding one product shopping car then discard it and verify is not there
	 */
	@Test
	public void h_discartOneProductInShoppingCar() {
		String inputUser = "DISCARD 0";
		a_loadProductsIncrementalUdateTest();
		f_addProductToShoppingCar();
		vms.discartProductInShoppingCar(inputUser);
		assertEquals(vendingDto.getSelectedProducts().size() , 0);
	}
	/**
	 * Adding one product shopping car when a incorrect index
	 */
	@Test
	public void h_discartOneProductInShoppingCarInvalidIndex() {
		String inputUser = "DISCARD 100";
		a_loadProductsIncrementalUdateTest();
		f_addProductToShoppingCar();
		boolean wasOk = vms.discartProductInShoppingCar(inputUser);
		assertFalse(wasOk);
	}
	/**
	 * Adding one product shopping car then discard it and verify is not there
	 */
	@Test
	public void i_addOtherProductToShoppingCar() {
		a_loadProductsIncrementalUdateTest();
		f_addProductToShoppingCar();
		String inputUser = "SELECT 0";
		vms.addProductToShoppingCar(inputUser);
		assertTrue(vendingDto.getSelectedProducts().size() >0);
	}
	
	/**
	 * Adding a coin 
	 */
	@Test
	public void j_insertValidCoin() {
		String inputUser = "INSERT 0.01";
		boolean wasOk = vms.insertCoin(inputUser);
		assertTrue(wasOk);
	}
	
	/**
	 * Test invalid coin
	 */
	@Test
	public void k_insertInvalidCoin() {
		String inputUser = "INSERT 3.1416";
		boolean wasOk =vms.insertCoin(inputUser);
		assertFalse(wasOk);
	}
	
	
	/**
	 * Trying to buy products without enough money
	 */
	@Test
	public void l_buySelectedProductsNotEnoughMoney() {
		i_addOtherProductToShoppingCar();
		j_insertValidCoin();
		assertFalse( vms.buySelectedProducts());
	}
	
	/**
	 * Test add different coins
	 */
	@Test
	public void m_insertDifferentsValidCoin() {
		String inputUser1 = "INSERT 2";
		String inputUser2 = "INSERT 1";
		String inputUser3 = "INSERT .5";
		String inputUser4 = "INSERT 0.5";
		boolean wasOk =vms.insertCoin(inputUser1);
		assertTrue(wasOk);
		wasOk =vms.insertCoin(inputUser2);
		assertTrue(wasOk);
		wasOk =vms.insertCoin(inputUser3);
		assertTrue(wasOk);
		wasOk =vms.insertCoin(inputUser4);
		assertTrue(wasOk);
	}
	
	/**
	 * Trying to buy with enought money
	 */
	@Test
	public void n_buySelectedProductsSuccess() {
		m_insertDifferentsValidCoin();
		i_addOtherProductToShoppingCar();
		assertTrue( vms.buySelectedProducts());
	}
	
	/**
	 * Testing adding more money
	 */
	@Test
	public void o_insertMoreValidCoin() {
		String inputUser1 = "INSERT .01";
		String inputUser2 = "INSERT .1";
		String inputUser3 = "INSERT .25";
		String inputUser4 = "INSERT 0.5";
		boolean wasOk =vms.insertCoin(inputUser1);
		assertTrue(wasOk);
		wasOk =vms.insertCoin(inputUser2);
		assertTrue(wasOk);
		wasOk =vms.insertCoin(inputUser3);
		assertTrue(wasOk);
		wasOk =vms.insertCoin(inputUser4);
		assertTrue(wasOk);
	}
	/**
	 * Testing adding a product to shopping car and check is there
	 */
	@Test
	public void p_addMoreProductToShoppingCar() {
		String inputUser = "SELECT 0";
		a_loadProductsIncrementalUdateTest();
		vms.addProductToShoppingCar(inputUser);
		assertTrue(vendingDto.getSelectedProducts().size() > 0);
	}
	/**
	 * Test is reseted the money amount
	 */
	@Test
	public void q_cancell_TakeRefund() {
		o_insertMoreValidCoin();
		assertTrue( vms.takeRefund() .equals( new BigDecimal(0)) );//==0
		assertEquals( vendingDto.getCurrentCredit().getTotalAmount(),  new BigDecimal(0) );
		assertEquals(vendingDto.getSelectedProducts().size(), 0);
	}
	
}
