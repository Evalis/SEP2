package shared.model;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class ItemList implements Serializable {

	private ArrayList<Item>items;
	
	public ItemList()
	{
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	public void remove(Item item)
	{
		items.remove(item);
	}
	
	public ArrayList<Item> getAllItems()
	{
		return items;
	}
	
	public ArrayList<Item> searchItem(String word)
	{
		String searchword= word.toLowerCase();
		ArrayList<Item> temp = new ArrayList<Item>();
		for(int i = 0; i< items.size(); i++)
		{
			if(items.get(i).getItemName().contains(searchword))
			{
				temp.add(items.get(i));
			}
		}
		return temp;
	}
}
