package com.eliseo.vending.machine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

import com.eliseo.vending.machine.Money;
import com.eliseo.vending.machine.MoneyWrapper;
import com.eliseo.vending.machine.model.DisplayMenuManager;
import com.eliseo.vending.machine.model.Product;
import com.eliseo.vending.machine.model.VendingMachine;
import com.eliseo.vending.machine.util.CSVUtil;
import com.eliseo.vending.machine.util.VendingMachineUtil;

public class VendingMachineUIService {
	
	private  String showProductsMessage ;
	private  DisplayMenuManager displayManager = new DisplayMenuManager();
	private VendingMachine vendingDto ;
	private final static int NUMBER_OF_PRODUCTS_TO_SHOW_PER_PAGE = 15;
	private final static String VALID_CURRENCY = "-Valid currency are 2, 1, 0.5, 0.25, 0.10, 0.05, 0.1 \n";
	private  String principalMenu=
			"===============Welcome to my vending machine=============\n"
			+"Select one operation \n"
			+"A) Show avaiable products \n"
			+"B) Update products via csv file\n"
			+"C) Cancel (Take refund)\n"
			+"D) Show my shoping car products \n"
			+"E) Show my coins and notes \n"
			+"F) Buy shopping car products \n"
			+"H) Help (Show menu) \n"
			+"INSERT) example INSERT 1\n"
			+"SELECT) example SELECT [INDEX]\n"
			+"DISCARD) example DISCARD [CAR_INDEX]\n"
			+"--Note \n"
			+"****************************\n"
			+ "To add money type INSERT \nexample: INSERT 2 , this will add 2 dollars of credit \n"
			+VALID_CURRENCY
			+"****************************\n"
			+"To add a product into the car press SELECT then index of the product \nexample: SELECT 1\n";  
	
	private String updateProductMenu = 
			 "============Select one operation fololled by absolute file path ========\n"
			+"---WARNING UPDATE THE CAR ITEMS WILL CLEAN SHOPPING CAR---\n"
			+"A) Replace current product list with the one in the csv file\n"
			+"B) (Default option) Incremental update products with a csv file \n"
			+"C) Return to previews menu \n"
			+"--------------------------------------------\n"
			+"Example  A C:/backup/productList.csv \n"
			+"Use this format csv \n"
			+"Item name, Code, Cost\n" 
			+"Nuts, N2 , $0.50\n";
	
	
	
	public VendingMachineUIService(VendingMachine vendingDto){
		this.vendingDto = vendingDto;
		showProductsMessage = getshowBuyProductsMessage();
	}
	
	private  String getshowBuyProductsMessage() {
		return 	"======== This vending machine contains "+vendingDto.getAvailableProducts().size()+" products \n"
				+"Will be shown in groups of "+NUMBER_OF_PRODUCTS_TO_SHOW_PER_PAGE+", press enter to continue or C to go back.\n";
	}
	
	/**
	 * Show products menu with pager 
	 * 
	 * @param sc
	 * @return
	 */
	public  int showProducts(Scanner sc) {
		String input = "";
		int totalOfProducts = vendingDto.getAvailableProducts().size();
		if (totalOfProducts == 0) {
			System.err.println("Product list is empty please upload a csv file please");
			displayManager.hideMenu();
			return 0;
		}
		System.out.println(showProductsMessage);
		System.out.println("Index,    Item Name,     Code,    Cost");
		for (int x = 0; x< totalOfProducts ; x++) {
			Product product = vendingDto.getAvailableProducts().get(x);
			System.out.println(x+"\t "+product.getName()+" \t"+product.getKey()+" \t $"+product.getCost());
			if (x%NUMBER_OF_PRODUCTS_TO_SHOW_PER_PAGE == NUMBER_OF_PRODUCTS_TO_SHOW_PER_PAGE -1) {
				System.out.println("Press enter to continue or C, CANCEL to go back.");
				input =sc.nextLine();
				if ("C".equalsIgnoreCase(input) || "CANCEL".equalsIgnoreCase(input)){
					break;
				}
			}
			
		}
		if (totalOfProducts >20) {
			displayManager.showMenu();			
		}else {
			displayManager.hideMenu();
		}
		return totalOfProducts;
	}
	
	/**
	 * Add product into the shopping car list
	 * @param indexProduct
	 */
	public void addProductToShoppingCar(String indexProduct) {
		String indexString = indexProduct.substring("SELECT".length()) .trim();
		if (!indexString.trim().isEmpty()) {
			int index = -1;
			try {
				index = new Integer(indexString.replace("[", "").replaceAll("]", ""));
			}catch (NumberFormatException e) {
				System.err.println("Invalid product index format try SELECT 0");
				displayManager.hideMenu();
				return;
			}
			if (index < 0 || index >=  vendingDto.getAvailableProducts().size()) {
				System.err.println("Invalid select product list index max is " + (vendingDto.getAvailableProducts().size() -1) +" and min is 0");
			}else {
				Product select = vendingDto.getAvailableProducts().get(index);
				vendingDto.getSelectedProducts().add(select);
				System.out.println("\nAdded to the shopping car, Index["+index+"] Name ["+select.getName()+"] Key ["+select.getKey()+ "] Price["+select.getCost()+"] ");
				System.out.println("Total of products "+vendingDto.getSelectedProducts().size());
				System.out.println("Shopping car total "+vendingDto.totalOfSelectedProducts());
			}
		}
		displayManager.hideMenu();
	}
	
	/**
	 * Remove selected product 
	 * check that index exists first
	 * 
	 * @param indexProduct
	 * @return
	 */
	public boolean discartProductInShoppingCar(String indexProduct) {
		String indexString = indexProduct.substring("DISCARD".length()) .trim();
		if (!indexString.trim().isEmpty()) {
			int index = -1;
			try {
				index = new Integer(indexString.replace("[", "").replaceAll("]", ""));
			}catch (NumberFormatException e) {
				System.err.println("Invalid shopping car index format try DISCARD 0");
				displayManager.hideMenu();
				return false;
			}
			if (index<0 || index >=  vendingDto.getSelectedProducts().size()) {
				System.err.println("Invalid select product list index max is " + (vendingDto.getSelectedProducts().size() -1) +" and min is 0");
				return false;
			}else {
				Product select = vendingDto.getSelectedProducts().remove(index);
				System.out.println("\nRemoved from the shopping car Index["+index+"] Name ["+select.getName()+"] Key ["+select.getKey()+ "] Price["+select.getCost()+"] ");
				System.out.println("Total of products "+vendingDto.getSelectedProducts().size());
				System.out.println("Shopping car total "+vendingDto.totalOfSelectedProducts());
				return true;
			}
		}
		displayManager.hideMenu();
		return false;
	}
	 /**
	  * Display shopping car summary
	  * @return amount of products inside
	  */
	public int showShoppingCar() {
		System.out.println("=====Shopping car info=====\n");
		vendingDto.printShoppinCarInfo();
		if (vendingDto.getSelectedProducts().size()>20) {
			displayManager.showMenu();
		}else {
			displayManager.hideMenu();
		}
		return vendingDto.getSelectedProducts().size();
	}
	
	
	/**
	 * Insert a coin into this vending machine
	 * @param money object
	 */
	public boolean insertCoin(String money) {
		String quantity = money.substring("INSERT".length()) .trim();
		displayManager.hideMenu();
		if (!quantity.trim().isEmpty()) {
			BigDecimal value = new BigDecimal(0.0);
			try {
				value = new BigDecimal(quantity.replace("$", ""));
			}catch (NumberFormatException e) {
				System.err.println("Invalid coin value");
				return false;
			}
			Money coin  = VendingMachineUtil.calculateExactlyCoin(value);
			if (coin == null) {
				System.err.println("Invalid input \n"+VALID_CURRENCY);
				return false;
			}else {
				vendingDto.getCurrentCredit().addCoin(coin);
				System.out.println("Total money inserted "+vendingDto.getCurrentCredit().getTotalAmount());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Print the report of current inserted money
	 * reset the money amount
	 */
	public BigDecimal takeRefund() {
		if (vendingDto.getCurrentCredit().getTotalAmount().compareTo(new BigDecimal(0.01) ) <0 ) { // < 0.01 
			System.err.println("Noting to refund it's necessary to add money first");
		}else {
			showMyMoney();
			vendingDto.getCurrentCredit().resetAmount();
			vendingDto.clearSelectedProductsList();
		}
		displayManager.hideMenu();
		return vendingDto.getCurrentCredit().getTotalAmount();
	}
	/**
	 * Print the report of current inserted money
	 */
	public void showMyMoney() {
		vendingDto.getCurrentCredit().printTotalCoins();
		displayManager.hideMenu();
	}
	/**
	 * Validate that inserted coins total are more than shopping car total
	 * Validate that shopping car is not empty
	 * Show bought products
	 * Show return additional money
	 * Show total inserted Coin
	 * Clear shopping car
	 * Clear inserted coins
	 */
	public boolean buySelectedProducts() {
//		vendingDto.getCurrentCredit().printTotalCoins();
		displayManager.hideMenu();
		if (vendingDto.getSelectedProducts().isEmpty()) {
			System.out.println("Shopping car is empty, plese select some products before buy them");
			return false;
		}else if (vendingDto.getCurrentCredit().getTotalAmount().compareTo( vendingDto.totalOfSelectedProducts()) <0) {//< vendingDto.totalOfSelectedProducts()
			System.out.println("Money is not enough inserted money ");
			System.out.println("Inserted "+vendingDto.getCurrentCredit().getTotalAmount());
			System.out.println("Shopping car total "+vendingDto.totalOfSelectedProducts());
			System.out.println("Missing "+ (vendingDto.totalOfSelectedProducts().subtract( vendingDto.getCurrentCredit().getTotalAmount()) ) );// - vendingDto.getCurrentCredit().getTotalAmount()
			return false;
		}else {
			System.out.println("Total of inserted money is "+vendingDto.getCurrentCredit().getTotalAmount());
			System.out.println("Shopping car total "+vendingDto.totalOfSelectedProducts());
			System.out.println("Products bought ");
			vendingDto.printShoppinCarInfo();
			System.out.println("Returning your extra money");
			MoneyWrapper additionalMoney =VendingMachineUtil.getExchange(vendingDto.getCurrentCredit().getTotalAmount().subtract( vendingDto.totalOfSelectedProducts() )  );//- vendingDto.totalOfSelectedProducts()
			System.out.println(additionalMoney.printTotalCoins());
			vendingDto.getCurrentCredit().resetAmount();
			vendingDto.clearSelectedProductsList();
			return true;
		}
		
	}
	
	/**
	 * Show 2 different options to update the csv file
	 * @param sc
	 */
	
	public void uploadProductsMenu(Scanner sc) {
		String input = "";
		System.out.println(updateProductMenu);
		while(!"C".equalsIgnoreCase(input) || !"CANCEL".equalsIgnoreCase(input)) {
			
			input=  sc.nextLine();
			if (input != null && !input.isEmpty()) {
					if ( input.trim().toUpperCase().startsWith("A ") ) {//A) Replace current product list with the the one in the csv file
						if (replaceProducts(input.substring(1).trim() ) ) {//Was updated successful
							System.out.println("Returning preview menu");
							showProductsMessage = getshowBuyProductsMessage();
							break;
						}
					}else if ( input.trim().toUpperCase().startsWith("B ")  ) {//B) Incremental update products with a csv file
						if (incrementalUpdate(input.substring(1).trim() )) {//Was updated successful
							System.out.println("Returning preview menu");
							showProductsMessage = getshowBuyProductsMessage();
							break;
						};
					}else if ( input.trim().equalsIgnoreCase("C") || input.trim().equalsIgnoreCase("CANCEL")) {
						displayManager.showMenu();
						break;
					}else {//Default option
						if (incrementalUpdate(input.trim() )) {//Was updated successful
							System.out.println("Returning preview menu");
							showProductsMessage = getshowBuyProductsMessage();
							displayManager.showMenu();
							break;
						};
					}
			}
		}
		try {			Thread.sleep(4000);		}catch (InterruptedException e) {		}
		displayManager.showMenu();
	}
	/**
	 * If file exists will delete everything and update with new list of products
	 * @param path
	 * @return wasUpdatedOk
	 */
	public boolean replaceProducts(String path) {
			boolean wasUpdatedOk = true;
			if(CSVUtil.secureValidCSV(path)) {
				vendingDto.clearAvailableProductsList();
			}
			try{
				CSVUtil.loadCSV(path, vendingDto);
				vendingDto.clearSelectedProductsList();
			}catch (Exception e) {
				System.err.println(e.getMessage());
				wasUpdatedOk = false;
			}
			return wasUpdatedOk;
	}
	/**
	 * Add new products and update product value that matches name and key
	 * @param path
	 * @return wasUpdatedOk
	 */
	public boolean incrementalUpdate(String path)  {
		boolean wasUpdatedOk = true;
		try{
			CSVUtil.loadCSV(path, vendingDto);
			vendingDto.clearSelectedProductsList();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			wasUpdatedOk = false;
		}
		return wasUpdatedOk;
	}
	
	public  boolean isDisplayEnabled() {
		return displayManager.isDisplayEnabled();
	}
	public  void showMenu() {
		displayManager.showMenu();
	}
	public  void hideMenu() {
		displayManager.hideMenu();
	}
	public String getPrincipalMenu() {
		return principalMenu;
	}
}
