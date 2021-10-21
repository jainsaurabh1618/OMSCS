package edu.gatech.cs6310;

import java.util.Map;

public class Drone {
	
	private String id;
	private String storeName;
	private int weightCapacity;
	private int refuelThreshold;
	private String pilotAccount;
	
	public Drone(String id, String storeName, String weightCapacity, String refuelThreshold) {
		this.id = id;
		this.storeName = storeName;
		this.weightCapacity = Integer.parseInt(weightCapacity);
		this.refuelThreshold = Integer.parseInt(refuelThreshold);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getWeightCapacity() {
		return weightCapacity;
	}

	public void setWeightCapacity(int weightCapacity) {
		this.weightCapacity = weightCapacity;
	}

	public int getRefuelThreshold() {
		return refuelThreshold;
	}

	public void setRefuelThreshold(int refuelThreshold) {
		this.refuelThreshold = refuelThreshold;
	}
	
	public void setPilotAccount(String pilotAccount) {
		this.pilotAccount = pilotAccount;
	}

	public String getPilotAccount() {
		return pilotAccount;
	}

	public int calculateDroneWeight(Map<String, Order> orderMap, Map<String, Item> itemMap) {
		int droneWeight = 0;
		for(Order order: orderMap.values()) {
			if(this.id.equalsIgnoreCase(order.getDroneId())) {
				droneWeight = droneWeight + order.calculateOrderWeight(itemMap);
			}
		}
		return droneWeight;
	}
	
}
