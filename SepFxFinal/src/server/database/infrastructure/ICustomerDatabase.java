package server.database.infrastructure;

import shared.model.Customer;

public interface ICustomerDatabase {

	public Customer logInCustomer(String email, String password);

	public boolean addNewCustomer(Customer customer);
	
	public boolean updateCustomerInformation(Customer customer);

}
