package com.eliseo.vending.machine.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.eliseo.vending.machine.exceptions.ValidationCSVException;
import com.eliseo.vending.machine.model.VendingMachine;
import com.eliseo.vending.machine.util.CSVUtil;

public class CSVUtilTest {
	private final static String INVALID_PRODUCT_LIST_FILE = "invalid_Product_List.csv";//File is empty
	private final static String FILE_WITH_CORRUPTED_DATA = "product_List_with_Corrupted_Data.csv";//File with corrupted data
	private final static String FILE_VALID = "valid_Product_List.csv";//File is empty
	VendingMachine vmInfo = new VendingMachine();
	
	/**
	 * Trying to load empty CSV file 
	 * @throws ValidationCSV
	 */
	@Test
	public  void testloadCSV_emptyFile() throws ValidationCSVException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(INVALID_PRODUCT_LIST_FILE).getFile());
		int errors  = CSVUtil.loadCSV(file.getAbsolutePath(), vmInfo);
		assertEquals(errors, 0);
	}
	
	/**
	 * Load a CSV file that contains some corrupted rows
	 * @throws ValidationCSV
	 */
	@Test
	public  void testloadCSV_FileDataCorrupted() throws ValidationCSVException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILE_WITH_CORRUPTED_DATA).getFile());
		int errors  = CSVUtil.loadCSV(file.getAbsolutePath() , vmInfo);//3
		assertEquals(errors, 3);
	}
	
	/**
	 * Load a valid file CSV
	 * @throws ValidationCSV
	 */
	@Test
	public  void testloadCSV_ValidFile() throws ValidationCSVException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILE_VALID).getFile());
		int errors  = CSVUtil.loadCSV(file.getAbsolutePath(), vmInfo);
		assertEquals(errors, 0);
	}
	
	/**
	 * Trying to load incorrect file CSV and expected ValidationCSV Exception
	 * @throws ValidationCSV
	 */
	@Test(expected = ValidationCSVException.class)
	public  void testloadCSV_FileDoesNotExist() throws ValidationCSVException {
		CSVUtil.loadCSV("IncorrectFile", vmInfo);
	}
	
//	public static void main(String[] args) throws ValidationCSV {
//		new CSVUtilTest().testloadCSV_emptyFile();
//		new CSVUtilTest().testloadCSV_FileDataCorrupted();
//		new CSVUtilTest().testloadCSV_ValidFile();
//		new CSVUtilTest().testloadCSV_FileDoesNotExist();
//	}
}
