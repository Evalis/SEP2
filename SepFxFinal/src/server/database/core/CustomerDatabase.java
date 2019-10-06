package server.database.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import server.database.infrastructure.ICustomerDatabase;
import shared.model.Customer;

public class CustomerDatabase implements ICustomerDatabase {

	private Connection connection;

	public CustomerDatabase() {
		BasicDataSource dataSource = DatabaseConnectionPool.getDataSource();
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// As a client, I want to be able to own a separate account for myself.
	@Override
	public boolean addNewCustomer(Customer customer) {
		String  sql = "INSERT INTO shopping.customer("
				+ "firstName, lastName, userPassword, email, phoneno) values(?,?,?,?,?)";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, customer.getFname());
			pStatement.setString(2, customer.getlName());
			pStatement.setString(3, customer.getPassword());
			pStatement.setString(4, customer.getEmail());
			pStatement.setString(5, customer.getPhoneNo());
			pStatement.executeUpdate();
			System.out.println("New Customer added to database.");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
		return true;
	}

//As a client, I want to be able to log in to my account.
	@Override
	public Customer logInCustomer(String email, String password) {
		Customer c = null;
		String  sql = "SELECT * FROM shopping.Customer" + 
			" WHERE email = ? and userPassword = ?";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, email);
			pStatement.setString(2, password);
			
			ResultSet r = pStatement.executeQuery();
			
			while(r.next())
			{
				c = new Customer();
				c.setEmail(r.getString("email"));
				c.setPassword(r.getString("userPassword"));
				c.setFname(r.getString("firstName"));
				c.setlName(r.getString("lastName"));
				c.setPhoneNo(r.getString("phoneno"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public boolean updateCustomerInformation(Customer customer) {
		String  sql = "UPDATE shopping.customer SET "
				+ "firstName = ?,"
				+ " lastName = ?,"
				+ " userPassword = ?,"
				+ " phoneno = ?" + 
				" WHERE email = ? ";
				try {
					PreparedStatement pStatement = connection.prepareStatement(sql);
					pStatement.setString(1, customer.getFname());
					pStatement.setString(2, customer.getlName());
					pStatement.setString(3, customer.getPassword());
					pStatement.setString(4, customer.getPhoneNo());
					pStatement.setString(5, customer.getEmail());
					pStatement.executeUpdate();
					System.out.println("Customer has been edided.");
				} catch (SQLException e) {
					e.printStackTrace();
					return false;

				}
				return true;
			}

	}



