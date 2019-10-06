package server.database.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import server.database.infrastructure.IWishListDatabase;
import shared.model.Item;

public class WishListDatabase implements IWishListDatabase {

	private Connection connection;

	public WishListDatabase() {

		BasicDataSource dataSource = DatabaseConnectionPool.getDataSource();
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addToWishList(int itemID, String email) {

		String sql = "INSERT INTO shopping.wishlist (itemID, email)" + "VALUES (?, ?)";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, itemID);
			pStatement.setString(2, email);
			pStatement.executeUpdate();
			System.out.println("New Item added to the wishlist");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
			return true;
	}

	@Override
	public boolean removeFromWishlist(int itemID, String email) {

		String sql = "DELETE FROM shopping.wishList " + "WHERE itemID =? AND email = ? ";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, itemID);
			pStatement.setString(2, email);
			pStatement.executeUpdate();
			System.out.println("Item deleted from the wishlist");
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<Item> getWishList(String email) {

		ArrayList<Item> wishlist = new ArrayList<Item>();

		String sql = "SELECT * FROM shopping.wishlist w " 
					+ "JOIN shopping.item i on i.itemid= w.itemid "
					+ "WHERE email = ? ";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, email);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				
				Item item = new Item();
				item.setItemId(resultSet.getInt("itemID"));
				item.setItemName(resultSet.getString("itemName"));
				item.setQuantity(resultSet.getInt("quantity"));
				item.setPrice(resultSet.getDouble("price"));
				item.setInStock(resultSet.getBoolean("inStock"));

				wishlist.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return wishlist;
		}
		return wishlist;
	}
	
	
	
		
}
