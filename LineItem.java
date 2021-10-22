

public class LineItem {
	
	private String itemName;
	private int quantity;
	private int unitPrice;
	
	public LineItem(String itemName, String quantity, String unitPrice) {
		this.itemName = itemName;
		this.quantity = Integer.parseInt(quantity);
		this.unitPrice = Integer.parseInt(unitPrice);
	}

	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	

}
