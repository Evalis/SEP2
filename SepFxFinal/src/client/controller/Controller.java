package client.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import shared.IServer;
import shared.model.*;

public class Controller {

	private Customer customer;
	private Manager manager;
	private ItemList list;
	private Order orderToMake;
	private IServer server;


	public Controller() {
		try {
			server = (IServer) Naming.lookup("rmi://localhost:1099/server");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {

			e.printStackTrace();
		}
	}
	


	public Order getOrderToMake() {
		if(orderToMake == null)
		{
			orderToMake = new Order();
		}
		return orderToMake;
	}



	public void setOrderToMake(Order orderToMake) {
		this.orderToMake = orderToMake;
	}



	public boolean register(Customer customer) {
		try {
			return server.registerNewCustomer(customer);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return false;
	}

	public ItemList getAllItems() {
		try {
			list = server.getAllItems();
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return list;
	}

	public Customer loginCustomer(String email, String password) {
		try {
			customer = server.logInCustomer(email, password);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return customer;
	}

	public Manager loginManager(String email, String password) {
		try {
			manager = server.logInManager(email, password);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return manager;
	}

	public boolean addItemToWishList(int itemId, String email) {
		try {
			return server.addItemToWishList(itemId, email);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean removeItemFromWishList(int idemId, String email) {
		try {
			return server.removeItemFromWishList(idemId, email);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return false;
	}
	
	public int addNewAddress(Address address, String email)
	{
		try {
			return server.addNewAddress(address, email);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int addNewItem(Item item)
	{
		try {
			server.addNewItem(item);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
	public boolean deleteItem(int itemId)
	{
		try {
			return server.deleteItem(itemId);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateCustomerInformation(Customer customer)
	{
		try {
			return server.updateCustomerInformation(customer);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateItem(Item item)
	{
		try {
			return server.updateItem(item);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	public int addNewOrder(Order order, String email)
	{
		try {
			return server.addNewOrder(order, email);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
	public boolean updateOrder(Order order)
	{
		try {
			return server.updateOrder(order);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	public void logoutCustomer() {
		customer = null;
	}
	
	public void logoutManager() {
		manager = null;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void newOrderToMake()
	{
		orderToMake = new Order();
	}

	public Manager getManager() {
		return manager;
	}


}
