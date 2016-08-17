package com.walmart.rest;

import java.util.ArrayList;

//import javafx.util.Pair;

public class Order {
	private String address;
//	private String orderID;
	private String name;
	private ArrayList<Pair<String, Integer>> items = new ArrayList<Pair<String, Integer>>();
	
	public Order() {
		
	}
	public Order(String address, String name) {
		this.address = address;
//		this.orderID = orderID;
		this.name = name;
	}
	
	public void addItem(String itemID, Integer quantity) {
		Pair<String, Integer> p = (new Pair<String, Integer>(itemID, quantity));
		items.add(p);
	}
	
	public String getName() {
		return this.name;
	}
	
//	public String getOrderID() {
//		return this.orderID;
//	}
	
	public String getAddress() {
		return this.address;
	}
	
	public ArrayList<Pair<String, Integer>> getItems() {
		return items;
	}
	

}
