package shared.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Manager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7220844629084849041L;
	private String fName;
	private String Lname;
	private String email;
	private String password;
	private String driverLicence;
	private ArrayList<Order>orders;
	
	
	public Manager()
	{
		orders = new ArrayList<>();
	}
	
	public Order getOrder(int orderId)
	{
		for(int i = 0; i< orders.size(); i++)
		{
			if(orders.get(i).getOrderId()==(orderId))
			{
				return orders.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Order> getAllOrder()
	{
		return orders;
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

	public String getDriverLicence() {
		return driverLicence;
	}
	
	

	public void setDriverLicence(String d) {
		this.driverLicence = d;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public ArrayList<Order> searchOrder(int orderID)
	{
		ArrayList<Order> temp = new ArrayList<Order>();
		
		
		for(int i = 0; i<orders.size(); i++)
		{
			String s = ""+orders.get(i).getOrderId();
			if(s.contains(""+ orderID))
			{
				temp.add(orders.get(i));
			}
		}
		return temp;
	}

	
	

	
	
	

}
