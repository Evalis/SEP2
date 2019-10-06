package test;

import server.database.core.AddressDatabase;
import server.database.core.CustomerDatabase;
import server.database.core.ItemDatabase;
import server.database.core.WishListDatabase;
import server.database.infrastructure.IAddressDatabase;
import server.database.infrastructure.ICustomerDatabase;
import server.database.infrastructure.IItemDatabase;
import server.database.infrastructure.IWishListDatabase;
import shared.model.Address;
import shared.model.Customer;
import shared.model.Item;

public class Test {

	public static void main(String[] args) {
		testAddingAddress();
//		testAddingItemToWishlist();
		
		

	}
	
	static void testAddingItem()
	{
		IItemDatabase ItemDatabase = new ItemDatabase();
		Item item = new Item();
		item.setItemName("auflasfdfcuf");
		item.setQuantity(100000);
		item.setPrice(0);
		item.setInStock(true);
		
		int result = ItemDatabase.addNewItem(item);
		
		System.out.println(result);
	}
	
	static void testAddingCustomer()
	{
		ICustomerDatabase customerDatabase = new CustomerDatabase();
		Customer c = new Customer();
		
		c.setEmail("whatANiceEmail@nice.com");
		c.setPassword("someNastyPassword");
		c.setFname("WhoAmI?");
		c.setlName("SeriouslyWhoAmI?");
		c.setPhoneNo("11110000");
		
		Boolean result = customerDatabase.addNewCustomer(c);
		
		System.out.println(result);
	}
	
	static void testAddingItemToWishlist()
	{
		IWishListDatabase wish = new WishListDatabase();
		Boolean result = wish.addToWishList(4, "frank@gmail.com");
		System.out.println(result);
	}
	
	static void testRemovingItemToWishlist()
	{
		IWishListDatabase wish = new WishListDatabase();
		Boolean result = wish.removeFromWishlist(4, "frank@gmail.com");
		System.out.println(result);
	}
	static void testAddingAddress()
	{
		IAddressDatabase addressDatabase = new AddressDatabase();
		Address a = new Address();
		a.setStreet("blu");
		a.setHouseNumber("12");
		a.setCity("nice city");
		a.setPostcode("8700");
		a.setCountry("Denmark");
		
		
		int result = addressDatabase.addNewAddress(a, "frank@gmail.com");
		
		System.out.println(result);
	}
}
