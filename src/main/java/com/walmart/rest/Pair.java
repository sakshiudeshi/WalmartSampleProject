package com.walmart.rest;

public class Pair<String,Integer> {
    private String itemID;
    private Integer quantity;
    public Pair() {
    	
    }
     public Pair(String itemID, Integer quantity){
        this.itemID = itemID;
        this.quantity = quantity;
    }
    public String getItemID(){ return itemID; }
    public Integer getQuantity(){ return quantity; }
    public void setItemID(String itemID){ this.itemID = itemID; }
    public void setQuantity(Integer quantity){ this.quantity = quantity; }
}
