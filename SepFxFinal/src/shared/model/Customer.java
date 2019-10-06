package shared.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String fname; 
	private String lName;
	private String email; 
	private String password;
	private String phoneNo;
	private ArrayList<Item> wishList;
	private ArrayList <Address> address; 
	private ArrayList<Order> orders; 
	
	
	public Customer() {
	
		this.wishList = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.address = new ArrayList<>();
		
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public void addAddress(Address addres)
	{
			address.add(addres);
	}
	
	public void addNewOrder(Order order)
	{
		orders.add(order);
	}
	public ArrayList<Order> getHistoryOfOrders()
	{
		return orders;
	}
	
	public void addItemToWishList(Item item)
	{
		wishList.add(item);
	}
	
	public void removeItemFromWishList(Item item)
	{
		wishList.remove(item);
	}


	public void setWishList(ArrayList<Item> wishlist) {
		this.wishList = wishlist;
	}
	
	public ArrayList<Item> getWishList()
	{
		return wishList;
	}
	
	public ArrayList<Address> getAddressList()
	{
		return address;
	}


	public void setAddressList(ArrayList<Address> addresses) {
		this.address = addresses;
		
	}


	public void setOrderHistory(ArrayList<Order> orderHistory) {
		this.orders = orderHistory;
	}
	
	
}
