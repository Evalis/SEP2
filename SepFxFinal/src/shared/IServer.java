package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.model.*;


public interface IServer extends Remote{
	
	public boolean registerNewCustomer(Customer customer) throws RemoteException;
	public Manager logInManager(String email, String password)throws RemoteException;
	public Customer logInCustomer(String email, String password)throws RemoteException;
	public ItemList getAllItems()throws RemoteException;
	public boolean addItemToWishList(int itemId, String email)throws RemoteException;
	public boolean removeItemFromWishList(int itemId, String email)throws RemoteException;
	public int addNewOrder(Order order, String email)throws RemoteException;
	public int addNewItem(Item item)throws RemoteException;
	public int addNewAddress(Address address, String email) throws RemoteException;
	public boolean deleteItem(int itemId) throws RemoteException;
	public boolean updateCustomerInformation(Customer customer) throws RemoteException;
	public boolean updateItem(Item item) throws RemoteException;
	public boolean updateOrder(Order order) throws RemoteException;
	

}
