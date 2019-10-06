package shared.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable{

	private int itemId, quantity;
	private double price;
	private String itemName;
	private boolean inStock;
	
	public Item()
	{
		
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemID) {
		this.itemId = itemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean getInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

}
