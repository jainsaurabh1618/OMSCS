

public class Item {
	
	private String name;
	private String storeName;
	private int weight;
	
	public Item(String name, String storeName, String weight) {
		this.name = name;
		this.storeName = storeName;
		this.weight = Integer.parseInt(weight);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
