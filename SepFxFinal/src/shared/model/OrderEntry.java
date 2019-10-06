package shared.model;

import java.io.Serializable;

public class OrderEntry implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Item item;
	private int quantity;
	private double price;
	private String itemName;
	private int itemId;
	
	public OrderEntry ()
	{
		this.item = new Item();
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

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		setItemName(item.getItemName());
		setItemId(item.getItemId());
		setPrice(item.getPrice());
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
