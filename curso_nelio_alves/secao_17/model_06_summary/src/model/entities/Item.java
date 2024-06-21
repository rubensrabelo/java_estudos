package model.entities;

public class Item {
	public String name;
	public Double unitPrice;
	public Integer quantity;
	
	public Item(){
	}

	public Item(String name, Double unitPrice, Integer quantity) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double total() {
		return getUnitPrice() * getQuantity();
	}

	@Override
	public String toString() {
		return getName() + "," + String.format("%.2f", total()) + "\n";
	}
}
