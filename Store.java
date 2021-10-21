package edu.gatech.cs6310;

public class Store {
	
	private String name;
	private int revenue;
	
	public Store(String name, String revenue) {
		this.name = name;
		this.revenue = Integer.parseInt(revenue);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	
	public void updateRevenue(int orderPrice) {
		this.revenue = this.revenue + orderPrice;
		
	}

}
