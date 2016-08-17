package com.walmart.rest;

import java.util.ArrayList;

public class Order {
	private String address;
	private String orderID;
	private String name;
	private ArrayList<String> itemID = new ArrayList<String>();
	
	public Order() {
		
	}
	public Order(String address, String orderID, String name) {
		this.address = address;
		this.orderID = orderID;
		this.name = name;
	}
	
	public void addItem(String itemID) {
		this.itemID.add(itemID);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getOrderID() {
		return this.orderID;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public ArrayList<String> getItems() {
		return itemID;
	}
	

}
