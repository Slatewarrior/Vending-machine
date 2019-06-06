package com.eliseo.vending.machine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import com.eliseo.vending.machine.exceptions.ValidationCSVException;
import com.eliseo.vending.machine.model.Product;
import com.eliseo.vending.machine.model.VendingMachine;

public class CSVUtil {
	
	private static final String COMMA = ",";
	
	
	/**
	 * Validate that the file is correct 
	 * Update products into a list 
	 * @param path
	 * @throws ValidationCSV
	 */
	
	public static Integer loadCSV(String path, VendingMachine vmInfo) throws ValidationCSVException {
		int linesWithErros = 0;
		/*Validate that file exists and it's readable */
		validCSV(path);
		
	    try {

			BufferedReader br = new BufferedReader(new FileReader(path));
	        //Skip first line
	        br.readLine();
	        
	        String line = ""; 
	        while ((line = br.readLine()) != null) {
	        	
//	            System.out.println(line);

	            String[] data = line.split(COMMA);        
	            if (!line.isEmpty() && data.length ==3) {//Can be processed
		            	Product product = parselineToProduct(data);
		            	if (product != null && product.isValid()) {//Add to the products list if is valid element
		            		addProduct(product, vmInfo);
		            	}else {//Don't process and increase in one the error counter
		            		linesWithErros++;
		            	}
	            	
	            }else {//Don't process and increase in one the error counter
	            	linesWithErros++;
	            } 
	        }
	        if (linesWithErros >0) {
	        	System.err.println("[RESULT] File "+ new File(path).getAbsolutePath() + " processed with ["+linesWithErros + "] Errors");	        	
	        }else {
	        	System.out.println("[RESULT] File "+ new File(path).getAbsolutePath() + " processed with ["+linesWithErros + "] Errors");
	        }
	        br.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	     catch (IOException e) {
	        e.printStackTrace();
	    }
	    return linesWithErros;
	}
	/**
	 * Do some basic validation to the CSV File
	 * Like file exists and it's readable 
	 * @param path
	 * @throws ValidationCSV
	 */
	private static void validCSV(String path) throws ValidationCSVException {
		if (path == null || path.isEmpty() ) {
	    	throw new ValidationCSVException("CSV file cannot be null.");
	    }else if ( !new File(path).exists()) {
	    	throw new ValidationCSVException("CSV file doesn't exists.");
	    }else if ( new File(path).isDirectory()) {
	    	throw new ValidationCSVException("CSV file is not valid file.");
	    }else if ( !new File(path).canRead()) {
	    	throw new ValidationCSVException("CSV file can not be readed.");
	    }
	}
	
	public static boolean secureValidCSV(String path) {
		boolean isValidFile = true;
		try {
			validCSV(path);
		}catch (Exception e) {
			isValidFile = false;
		}
		return isValidFile;
	}
	/**
	 * Parse each line into a product object
	 * @param data
	 * @return
	 */
	private static Product parselineToProduct( String[] data ) {
		Product product = null;
		/*Check first is the line is well formatted*/
		if (isLineWellFormatted(data)) {
			product = new Product(data[0].trim(), data[1].trim(), new BigDecimal(data[2].trim().replace("$", "")));
		}
		return product;
	}
	/**
	 * Validate that the line is well formatted
	 * @param data
	 * @return 
	 */
	private static boolean isLineWellFormatted(String[] data) {
		boolean wasWellFormated = true;
		if (data[0] == null || data[0].trim().isEmpty()) {
			wasWellFormated = false;
		}
		if (data[1] == null || data[1].trim().isEmpty()) {
			wasWellFormated = false;
		}
		if (data[2] == null || data[2].trim().isEmpty()) {
			wasWellFormated = false;
		}
		try {
			new BigDecimal(data[2].trim().replace("$", ""));  
		}catch (NumberFormatException e) { 
			wasWellFormated = false;
		}
		return wasWellFormated;
	}
	/**
	 * Override price of products with the same key and name 
	 * Avoid duplicated objects with same key and name
	 * @param product
	 */
	private static synchronized void addProduct(Product product, VendingMachine vmInfo) {
		int index = vmInfo.getAvailableProducts().indexOf(product);
		if (index>-1) {
			vmInfo.getAvailableProducts().set(index, product);
		}else {
			vmInfo.getAvailableProducts().add(product);
		}
	}
	
//	 public static void main(String[] args) throws ValidationCSV {
//		 VendingMachine vmInfo = new VendingMachine();
//		CSVUtil.loadCSV("C:/backup/productList.csv", vmInfo);
//	}
	
	 
	
}
