package com.eliseo.vending.machine.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.eliseo.vending.machine.MoneyWrapper;

public class VendingMachine {

	private MoneyWrapper currentCredit = new MoneyWrapper();
	private List<Product> availableProducts = new ArrayList<Product>();
	private List<Product> selectedProducts = new ArrayList<Product>();
	public MoneyWrapper getCurrentCredit() {
		return currentCredit;
	}

	public List<Product> getAvailableProducts() {
		return availableProducts;
	}

	public List<Product> getSelectedProducts() {
		return selectedProducts;
	}

	public void clearAvailableProductsList() {
		availableProducts = new ArrayList<Product>(); 
	}
	public void clearSelectedProductsList() {
		selectedProducts = new ArrayList<Product>(); 
	}
	public void printShoppinCarInfo() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < selectedProducts.size(); i++) {
			Product product = selectedProducts.get(i);
			sb.append("Index["+i+"] Name["+product.getName()+"] Key["+product.getKey()+ "] Price["+product.getCost()+"] \n");
		}
		sb.append("Quantity of products "+selectedProducts.size()+"\n");
		sb.append("Total "+totalOfSelectedProducts()+"\n");
		System.out.println(sb);
	}
	
	
	public BigDecimal totalOfSelectedProducts() {
		BigDecimal result = new BigDecimal(0);
		for (Product product : selectedProducts) {
			result = result.add( product.getCost());
		}
		return result;
	}
}
