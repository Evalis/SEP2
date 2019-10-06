package server.database.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import server.database.infrastructure.IItemDatabase;
import shared.model.Item;
import shared.model.ItemList;

public class ItemDatabase implements IItemDatabase {

	private Connection connection;
	

	public ItemDatabase() {
	
		BasicDataSource dataSource = DatabaseConnectionPool.getDataSource();
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ItemList getAllItem() {

		ItemList items = new ItemList();

		String sql = "SELECT * FROM  shopping.Item where inStock = true ";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Item item = new Item();
				item.setItemId(resultSet.getInt("itemID"));
				item.setItemName(resultSet.getString("itemName"));
				item.setQuantity(resultSet.getInt("quantity"));
				item.setPrice(resultSet.getDouble("price"));
				item.setInStock(resultSet.getBoolean("inStock"));

				items.addItem(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return items;
		}
		return items;
	}

	@Override
	public int addNewItem(Item item) {
		String sql = "INSERT INTO  shopping.Item "
				+ "(itemName, quantity, price, inStock) "
				+ "values (?,?,?,?)";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, item.getItemName());
			pStatement.setInt(2, item.getQuantity());
			pStatement.setDouble(3, item.getPrice());
			pStatement.setBoolean(4, item.getInStock());
			
			pStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return getItemID(item);
	}

	private int getItemID(Item item) {
		int itemID = -1;
		String sql = "SELECT itemID from  shopping.Item "
				+ "where itemName = ?"
				+ " and quantity = ?"
				+ " and price = ?"
				+ " and inStock = ?";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, item.getItemName());
			pStatement.setInt(2, item.getQuantity());
			pStatement.setDouble(3, item.getPrice());
			pStatement.setBoolean(4, item.getInStock());
			
			ResultSet r = pStatement.executeQuery();
			
			while(r.next())
			{
				itemID = r.getInt("itemID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return itemID;
		}
		return itemID;
	}
	
	public boolean deleteItem(int itemId)
	{
		String sql = "Update shopping.Item set inStock = false " + "WHERE itemID =?  ";
	try
	{
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, itemId);
		pStatement.executeUpdate();
		System.out.println("Item deleted from the Items");
	}catch(SQLException e) {

		e.printStackTrace();
		return false;
	}
	return true;
	}
	
	public boolean updateItem(Item item)
	{
		String  sql = "UPDATE shopping.Item SET "
				+ " quantity = ?, "
				+ " price = ?, "  
				+ " inStock = ? "
				+ " WHERE itemId = ?";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, item.getQuantity());
			pStatement.setDouble(2, item.getPrice());
			pStatement.setBoolean(3, item.getInStock());			
			pStatement.setInt(4, item.getItemId());
			pStatement.executeUpdate();
			System.out.println("Item has been edited.");
			} catch (SQLException e) {
			e.printStackTrace();
			return false;

	}
		return true;
	}
	
	
}
