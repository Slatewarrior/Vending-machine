package com.eliseo.vending.machine.model;

import java.math.BigDecimal;

public class Product {

	private String name;
	private String key;
	private BigDecimal cost;
	
	public Product() {
		
	}
	public Product(String name, String key, BigDecimal cost) {
		this.name = name;
		this.key = key;
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * @return true if product has a name and key is not empty 
	 */
	public boolean isValid() {
		return name != null && key != null && !key.isEmpty();
	}
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", key=" + key + ", cost=" + cost + "]";
	}
	
	/**
	 * Considering same hash code object if contains same key and name attribute doesn't mater if contain different price 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/**
	 * Considering same object if contains same key and name attribute doesn't mater if contain different price 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
