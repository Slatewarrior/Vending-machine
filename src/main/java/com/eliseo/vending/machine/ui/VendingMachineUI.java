package com.eliseo.vending.machine.ui;

import java.util.Scanner;

import com.eliseo.vending.machine.model.VendingMachine;

public class VendingMachineUI {
	
	private VendingMachine vmInfo = new VendingMachine();
	private VendingMachineUIService vms = new VendingMachineUIService(vmInfo);
	
	public void excecute() {
		String inputValue = "";
		while(!"CLOSE".equalsIgnoreCase(inputValue)) {
			if (vms.isDisplayEnabled()) {
				System.out.println("\n"+vms.getPrincipalMenu());				
			}
			Scanner sc =new Scanner(System.in);
			inputValue=  sc.nextLine();
			if (inputValue != null ) {
				 	
					if ( inputValue.trim().equalsIgnoreCase("A") ) {//Show products
						vms.showProducts(sc);
					}else if ( inputValue.trim().equalsIgnoreCase("B") ) {//Upload products list
						vms.uploadProductsMenu(sc);
					}else if ( inputValue.trim().equalsIgnoreCase("C") ) {//Take refund 
						vms.takeRefund();
					}else if ( inputValue.trim().equalsIgnoreCase("D") ) {//Show shopping car
						vms.showShoppingCar();
					}else if ( inputValue.trim().equalsIgnoreCase("E") ) {//Show my money
						vms.showMyMoney();
					}else if ( inputValue.trim().equalsIgnoreCase("F") ) {//buy car products
						vms.buySelectedProducts();
					}else if ( inputValue.trim().equalsIgnoreCase("H") ) {//Show help
						vms.showMenu();
					}else if ( inputValue.trim().toUpperCase().startsWith("SELECT ") ) {//add product to shopping car
						vms.addProductToShoppingCar(inputValue.trim());
					}else if ( inputValue.trim().toUpperCase().startsWith("INSERT ") ) {//Insert coin or note
						vms.insertCoin(inputValue.trim());
					}else if ( inputValue.trim().toUpperCase().startsWith("DISCARD ") ) {//Insert coin or note
						vms.discartProductInShoppingCar(inputValue.trim());
					}else {
						vms.hideMenu();
					} 
			}
			else {
				vms.hideMenu();
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		new VendingMachineUI().excecute();
	}
	
	
}
