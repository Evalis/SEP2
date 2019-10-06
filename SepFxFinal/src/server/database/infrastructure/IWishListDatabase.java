package server.database.infrastructure;

import java.util.ArrayList;

import shared.model.Item;


public interface IWishListDatabase {

	public boolean addToWishList(int itemID, String email);
	
	public boolean removeFromWishlist(int itemID, String email);
	
	public ArrayList<Item> getWishList(String email);
}
