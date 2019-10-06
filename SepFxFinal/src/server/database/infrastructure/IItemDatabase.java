package server.database.infrastructure;


import shared.model.Item;
import shared.model.ItemList;

public interface IItemDatabase {

	public ItemList getAllItem();
	public int addNewItem(Item item);
	public boolean deleteItem(int itemId);
	public boolean updateItem(Item item);
	
}
