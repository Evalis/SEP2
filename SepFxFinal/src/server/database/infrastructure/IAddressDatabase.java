package server.database.infrastructure;

import java.util.ArrayList;

import shared.model.Address;


public interface IAddressDatabase {

	ArrayList<Address> getAllAddressForCustomer(String email);
	public int addNewAddress(Address address, String email);

}
