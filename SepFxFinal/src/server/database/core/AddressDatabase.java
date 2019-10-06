package server.database.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import server.database.infrastructure.IAddressDatabase;
import shared.model.Address;

public class AddressDatabase implements IAddressDatabase {

	private Connection connection;

	public AddressDatabase() {

		BasicDataSource dataSource = DatabaseConnectionPool.getDataSource();
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Client gets address.
	@Override
	public ArrayList<Address> getAllAddressForCustomer(String email) {

		ArrayList<Address> addresses = new ArrayList<Address>();

		String sql = "SELECT addressID, street, houseNo,city,postcode,country FROM shopping.address "
				+ " WHERE email = ? ";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, email);
			ResultSet resultSet = pStatement.executeQuery();
			System.out.println("Get addresses.  " + email);

			while (resultSet.next()) {
				Address address = new Address();
				address.setAddressId(resultSet.getInt("addressID"));
				address.setStreet(resultSet.getString("street"));
				address.setHouseNumber(resultSet.getString("houseNo"));
				address.setCity(resultSet.getString("city"));
				address.setPostcode(resultSet.getString("postcode"));
				address.setCountry(resultSet.getString("country"));

				addresses.add(address);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			return addresses;

		}
		return addresses;

	}

	// Client can add new Address
	@Override
	public int addNewAddress(Address address, String email) {
		String sql = "INSERT INTO  shopping.address("
				+ " street, houseNo, city, postcode, country, email)  values(?,?,?,?,?,?)";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);

			pStatement.setString(1, address.getStreet());
			pStatement.setString(2, address.getHouseNumber());
			pStatement.setString(3, address.getCity());
			pStatement.setString(4, address.getPostcode());
			pStatement.setString(5, address.getCountry());
			pStatement.setString(6, email);
			pStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return getAddressID(address, email);
	}

	private int getAddressID(Address address, String email) {
		int addressID = -1;
		String sql = "SELECT AddressID FROM  shopping.address" 
				+ " where street = ? " 
				+ " and houseNo = ? "
				+ " and city = ? " 
				+ " and postcode = ? " 
				+ " and country = ? " 
				+ " and email = ? ";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);

			pStatement.setString(1, address.getStreet());
			pStatement.setString(2, address.getHouseNumber());
			pStatement.setString(3, address.getCity());
			pStatement.setString(4, address.getPostcode());
			pStatement.setString(5, address.getCountry());
			pStatement.setString(6, email);
			ResultSet r = pStatement.executeQuery();

			while (r.next()) {
				addressID = r.getInt("addressID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return addressID;
		}
		return addressID;

	}

}
