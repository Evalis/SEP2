package server.sever;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.database.DatabaseManager;
import shared.IServer;
import shared.model.Address;
import shared.model.Customer;
import shared.model.Item;
import shared.model.ItemList;
import shared.model.Manager;
import shared.model.Order;

public class Server implements IServer{
	
	private DatabaseManager dataManager; 
	
	public Server() throws RemoteException {
		this.dataManager = new DatabaseManager();
		UnicastRemoteObject.exportObject(this, 0);
		
	}
	@Override
	public boolean registerNewCustomer(Customer customer) throws RemoteException {
		return dataManager.registerNewCustomer(customer);
		
	}

	@Override
	public Manager logInManager(String email, String password) throws RemoteException {
		
		return dataManager.logInManager(email, password);
	}

	@Override
	public Customer logInCustomer(String email, String password) throws RemoteException {
		
		return dataManager.logInCustomer(email, password);
	}

	@Override
	public ItemList getAllItems()throws RemoteException{
	
		return dataManager.getItemList();
	}

	@Override
	public boolean addItemToWishList(int itemId, String email) throws RemoteException {
		return dataManager.addItemToWishList(itemId, email);
		
	}

	@Override
	public boolean removeItemFromWishList(int itemId, String email) throws RemoteException {
		return dataManager.removeItemFromWishList(itemId, email);
		
	}
	
	@Override
	public int addNewItem(Item item) throws RemoteException {
		return dataManager.addNewItem(item);
		
	}
	@Override
	public int addNewAddress(Address address, String email) throws RemoteException {
		
		return dataManager.addNewAddress(address, email);
	}
	@Override
	public boolean deleteItem(int itemId) throws RemoteException {
		
		return dataManager.deleteItem(itemId);
	}
	@Override
	public boolean updateCustomerInformation(Customer customer)throws RemoteException {
		
		return dataManager.updateCustomerInformation(customer);
	}
	@Override
	public boolean updateItem(Item item) throws RemoteException {
		
		return dataManager.updateItem(item);
	}
	@Override
	public int addNewOrder(Order order, String email) throws RemoteException {
		
		return dataManager.addNewOrder(order, email);
	}
	@Override
	public boolean updateOrder(Order order) throws RemoteException {
		
		return dataManager.updateOrder(order);
	}
	

	}
	
	
	
	
	
	
	
	
	
	
	
	


