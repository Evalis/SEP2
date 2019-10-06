package shared.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	private int orderId;
	private Date date;
	private double totalPrice;
	private double deliveryFee;
	private Address deliveryAddress;
	private String deliveryName;
	private ArrayList<OrderEntry> orderEntries;
	private boolean isDelivered;

	public Order() {
		this.date = Date.valueOf(LocalDate.now());
		this.deliveryAddress = new Address();
		this.orderEntries = new ArrayList<>();
	}

	public void addOrderEntry(OrderEntry orderEntry) {
		orderEntries.add(orderEntry);
	}

	public void remove(int index) {
		orderEntries.remove(index);
	}

	public int getOrderId() {
		return orderId;
	}

	public Date getDate() {
		return date;
	}


	public double getTotalPrice() {

		for (int i = 0; i < orderEntries.size(); i++) {
			totalPrice += orderEntries.get(i).getItem().getPrice();
		}

		return totalPrice;
	}

	public ArrayList<OrderEntry> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(ArrayList<OrderEntry> orderEntries) {
		this.orderEntries = orderEntries;
	}

	public boolean getIsDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	
	public void setDeliveryName(String deliveryName)
	{
		this.deliveryName = deliveryName;
	}
	
	public String getDeliveryName()
	{
		return deliveryName;
	}
	

	

}
