package server.database;


import java.util.ArrayList;

import server.database.core.AddressDatabase;
import server.database.core.CustomerDatabase;
import server.database.core.ItemDatabase;
import server.database.core.ManagerDatabase;
import server.database.core.OrderDatabase;
import server.database.core.WishListDatabase;
import server.database.infrastructure.IAddressDatabase;
import server.database.infrastructure.ICustomerDatabase;
import server.database.infrastructure.IItemDatabase;
import server.database.infrastructure.IManagerDatabase;
import server.database.infrastructure.IOrderDatabase;
import server.database.infrastructure.IWishListDatabase;
import shared.model.*;

public class DatabaseManager {
	private ICustomerDatabase customerData;
	private IManagerDatabase managerData;
	private IWishListDatabase wishlistData;
	private IOrderDatabase orderData;
	private IItemDatabase itemData;
	private IAddressDatabase addressData;
	
	public DatabaseManager ()
	{
		customerData = new CustomerDatabase();
		itemData = new ItemDatabase();
		managerData = new ManagerDatabase();
		wishlistData = new WishListDatabase();
		orderData = new OrderDatabase();
		addressData = new AddressDatabase();
		
	}
	public boolean registerNewCustomer(Customer customer) {
      return customerData.addNewCustomer(customer); 
	}

	public Manager logInManager(String email, String password) {
		Manager manager = managerData.logInManager(email, password);
		manager.setOrders(getAllOrders());
		return manager;
	}

	public Customer logInCustomer(String email, String password) {
		Customer customer = customerData.logInCustomer(email, password);
		if(customer == null)
		{
			return customer;
		}
		
		customer.setWishList(getWishlist(email));
		customer.setAddressList(getAllAddressForCustomer(email));
		customer.setOrderHistory(getOrderHistory(email));
		
		ArrayList<Order> oList = customer.getHistoryOfOrders();
		for (Order o : oList) {
			ArrayList<OrderEntry> oEntries = orderData.getOrderEntries(o.getOrderId());
			o.setOrderEntries(oEntries);
			
		}
		
		return customer;
	}

	public ItemList getItemList() {
		return itemData.getAllItem();
	}

	public boolean addItemToWishList(int itemID, String email) {
		return wishlistData.addToWishList(itemID, email);
	}

	public boolean removeItemFromWishList(int itemID, String email) {

		return wishlistData.removeFromWishlist(itemID, email);
	}
	public int addNewAddress(Address address, String email) {
		return addressData.addNewAddress(address, email);
	}
	

	public int addNewOrder(Order order, String email) {
		int orderID = orderData.addNewOrder(order, email);
		if(orderID == -1)
		{
			return orderID;
		}
		//insert order entries.
		ArrayList<OrderEntry> oes = order.getOrderEntries();
		boolean foundBadEntry = false;
		for (int i = 0; i < oes.size(); i++) 
		{
			OrderEntry oe = oes.get(i);
			oe.getItemId();
			
			foundBadEntry = !orderData.addOrderEntry(orderID, oe.getItemId(),oe.getQuantity());
			
			if(foundBadEntry)
			{
				break;
			}
		}
		
		if(foundBadEntry)
		{
			orderData.rollBack();
			orderID = -5;
		}
		else
		{
			orderData.commit();
		}
		
		
		return orderID;
	}

	public int addNewItem(Item item) {
		return itemData.addNewItem(item);
		
	}
	public ArrayList<Order> getOrderHistory(String email)
	{
		return orderData.getOrderHistory(email);
	}
	
	
	private ArrayList<Item> getWishlist(String email)
	{
		return wishlistData.getWishList(email);
	}
	
	private ArrayList<Address> getAllAddressForCustomer(String email)
	{
		return addressData.getAllAddressForCustomer(email);
	}
	
	
	public boolean deleteItem(int itemId)
	{
		return itemData.deleteItem(itemId);
	}
	
	public boolean updateCustomerInformation(Customer customer)
	{
		return customerData.updateCustomerInformation(customer); 
	}
	
	public boolean updateItem(Item item)
	{
		return itemData.updateItem(item);
	}
	
	public ArrayList<Order> getAllOrders()
	{
		return orderData.getAllOrders();
	}
	public boolean updateOrder(Order order)
	{
		return orderData.updateOrder(order);
	}
	
	

}
