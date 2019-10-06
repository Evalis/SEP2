package server.database.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import server.database.infrastructure.IOrderDatabase;
import shared.model.Address;
import shared.model.Item;
import shared.model.Order;
import shared.model.OrderEntry;

public class OrderDatabase implements IOrderDatabase {

	private Connection connection;

	public OrderDatabase() {
		BasicDataSource dataSource = DatabaseConnectionPool.getDataSource();
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit()
	{
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rollBack()
	{
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Order> getOrderHistory(String email) {

		ArrayList<Order> orders = new ArrayList<Order>();
		String sql = "SELECT * FROM  shopping.orders o "
				+ " JOIN shopping.address a "
				+ " ON a.addressId = o.addressId "
				+ "WHERE o.email = ?";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, email);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderID"));
				order.setDate(resultSet.getDate("orderDate"));
				order.setDeliveryName(resultSet.getString("deliveryName"));
				order.setTotalPrice(resultSet.getDouble("totalPrice"));
				order.setDeliveryFee(resultSet.getDouble("deliveryFee"));
				order.setIsDelivered(resultSet.getBoolean("status"));
				
				
				Address address = new Address();
				address.setAddressId(resultSet.getInt("AddressID"));
				address.setStreet(resultSet.getString("street"));
				address.setHouseNumber(resultSet.getString("houseNo"));
				address.setCity(resultSet.getString("city"));
				address.setPostcode(resultSet.getString("postcode"));
				address.setCountry(resultSet.getString("country"));
								
				order.setDeliveryAddress(address);
				orders.add(order);
			}
			return orders;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addNewOrder(Order order, String email) {
		int orderID = -1;
		String sql = "INSERT INTO shopping.orders "
				+ "(orderDate, totalPrice, deliveryName, deliveryFee, status, email, addressId )" + " values (?, ?, ?, ?, ?, ?, ? )"
						+ " returning orderid";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setDate(1, order.getDate());
			pStatement.setDouble(2, order.getTotalPrice());
			pStatement.setString(3, order.getDeliveryName());
			pStatement.setDouble(4, order.getDeliveryFee());
			pStatement.setBoolean(5, order.getIsDelivered());
			pStatement.setString(6, email);
			pStatement.setInt(7, order.getDeliveryAddress().getAddressId());

			ResultSet r = pStatement.executeQuery();
			while (r.next()) {
				orderID = r.getInt("orderid");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return orderID;
		}
		return orderID;
	}

	@Override
	public ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		String sql = "SELECT * FROM  shopping.orders o "
				+ " JOIN shopping.address a "
				+ " ON a.addressId = o.addressId ";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderID"));
				order.setDate(resultSet.getDate("orderDate"));
				order.setDeliveryName(resultSet.getString("deliveryName"));
				order.setTotalPrice(resultSet.getDouble("totalPrice"));
				order.setDeliveryFee(resultSet.getDouble("deliveryFee"));
				order.setIsDelivered(resultSet.getBoolean("status"));
				
				
				Address address = new Address();
				address.setAddressId(resultSet.getInt("AddressID"));
				address.setStreet(resultSet.getString("street"));
				address.setHouseNumber(resultSet.getString("houseNo"));
				address.setCity(resultSet.getString("city"));
				address.setPostcode(resultSet.getString("postcode"));
				address.setCountry(resultSet.getString("country"));
								
				order.setDeliveryAddress(address);
				orders.add(order);
			}
			return orders;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateOrder(Order order) {
		String  sql = "UPDATE shopping.orders SET "
				+ " status = ? " 
				+ " WHERE orderId = ?";
				
				try {
					PreparedStatement pStatement = connection.prepareStatement(sql);
					pStatement.setBoolean(1, order.getIsDelivered());
					pStatement.setInt(2, order.getOrderId());
					
					pStatement.executeUpdate();
					System.out.println("Order has been updated.");
				} catch (SQLException e) {
					e.printStackTrace();
					return false;

				}
				return true;
	}
	
	//Get all orderEntries(item+quantity) for one order.
	@Override
	public ArrayList<OrderEntry> getOrderEntries(int orderID) {

		ArrayList<OrderEntry> orderentries = new ArrayList<OrderEntry>();

		String sql = "SELECT * FROM  shopping.orderEntry o JOIN shopping.Item i on o.itemID = i.itemID "
				+ "WHERE o.orderID = ?";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, orderID);
			ResultSet resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				OrderEntry orderEntry = new OrderEntry();
				orderEntry.setQuantity(resultSet.getInt("amount"));

				Item i = new Item();
				i.setItemId(resultSet.getInt("itemID"));
				i.setItemName(resultSet.getString("itemName"));
				i.setQuantity(resultSet.getInt("quantity"));
				i.setPrice(resultSet.getDouble("price"));

				orderEntry.setItem(i);

				orderentries.add(orderEntry);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return orderentries;

		}

		return orderentries;
	}
	
	@Override
	public boolean addOrderEntry(int orderID, int itemID, int quantity)
	{
		String sql = "INSERT INTO shopping.orderEntry("
				+ " orderID, itemID, amount )  values(?,?,? )";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1,orderID);
			pStatement.setInt(2, itemID);
			pStatement.setInt(3, quantity);
			pStatement.executeUpdate();
			System.out.println("New Order entry added to the order");
		} catch (SQLException e) {
			if(e.getSQLState().equals("23505"))
			{
				return false;
			}
			e.printStackTrace();
			return false;
		}
			return true;
	
	}
}
