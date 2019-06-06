package com.eliseo.vending.machine.model;

public class DisplayMenuManager {
	private boolean displayEnabled = true;
	
	public void showMenu() {
		displayEnabled = true;
	}
	public void hideMenu() {
		displayEnabled = false;
	}
	public boolean isDisplayEnabled() {
		return displayEnabled;
	}
	@Override
	public String toString() {
		return "DisplayPrincipalMenu [display=" + displayEnabled + "]";
	}
	
	
}
