package server.database.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import server.database.infrastructure.IManagerDatabase;

import shared.model.Manager;

public class ManagerDatabase implements IManagerDatabase {

	private Connection connection;

	public ManagerDatabase() {

		{
			BasicDataSource dataSource = DatabaseConnectionPool.getDataSource();
			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Manager logInManager(String email, String password) {
		Manager m = null;
		String  sql = "SELECT * FROM shopping.Manager" + 
			" WHERE email = ? and userPassword = ?";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, email);
			pStatement.setString(2, password);
			
			ResultSet r = pStatement.executeQuery();
			
			while(r.next())
			{
				m = new Manager();
				m.setEmail(r.getString("email"));
				m.setPassword(r.getString("userPassword"));
				m.setfName(r.getString("firstName"));
				m.setLname(r.getString("lastName"));
				m.setDriverLicence(r.getString("drivingLicence"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
}