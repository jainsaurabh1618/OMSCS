package edu.gatech.cs6310;

import java.util.Map;
import java.util.TreeMap;

public class Order {
	
	private String orderId;
	private String storeName;
	private String custAcc;
	private String droneId;
	public Map<String, LineItem> lineItemMap = new TreeMap<String, LineItem>();
	
	
	public Order(String orderId, String storeName, String droneId, String custAcc) {
		this.orderId = orderId;
		this.storeName = storeName;
		this.droneId = droneId;
		this.custAcc = custAcc;
	}


	public String getCustAcc() {
		return custAcc;
	}


	public void setCustAcc(String custAcc) {
		this.custAcc = custAcc;
	}


	public String getDroneId() {
		return droneId;
	}


	public void setDroneId(String droneId) {
		this.droneId = droneId;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public int calculateOrderPrice() {
		int orderPrice = 0;
		for(LineItem lineItem : lineItemMap.values()) {
			orderPrice = orderPrice + lineItem.getQuantity() * lineItem.getUnitPrice();
		}
		return orderPrice;
	}
	
	public int calculateOrderWeight(Map<String, Item> itemMap) {
		int orderWeight = 0;
		for(LineItem lineItem : lineItemMap.values()) {
			orderWeight = orderWeight + lineItem.getQuantity() * itemMap.get(this.storeName+"_"+lineItem.getItemName()).getWeight();
		}
		return orderWeight;
	}


	public void requestItem(String itemName, String quantity, String unitPrice) {
		if(!this.lineItemMap.keySet().contains(itemName)) {
			this.lineItemMap.put(itemName, new LineItem(itemName, quantity, unitPrice));
		}
		
	}
	
}
