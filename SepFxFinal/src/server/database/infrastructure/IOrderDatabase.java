package server.database.infrastructure;

import java.util.ArrayList;

import shared.model.Order;
import shared.model.OrderEntry;

public interface IOrderDatabase {

	public ArrayList<Order> getOrderHistory(String email);
	public ArrayList<Order> getAllOrders();
	public int addNewOrder(Order order, String email);
	public boolean updateOrder(Order order);
	public ArrayList<OrderEntry> getOrderEntries(int orderID);
	public boolean addOrderEntry(int orderID, int itemID, int quantity);
	public void commit();
	public void rollBack();
}
