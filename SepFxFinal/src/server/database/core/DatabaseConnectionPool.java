package server.database.core;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnectionPool {

	private static BasicDataSource dataSource;

	private DatabaseConnectionPool() {

	}

	public static BasicDataSource getDataSource() {

		if (dataSource == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			BasicDataSource ds = new BasicDataSource();
			ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
			ds.setUsername("postgres");
			ds.setPassword("1779");
			ds.setMinIdle(5);
			ds.setMaxIdle(10);
			dataSource = ds;
		}
		return dataSource;
	}

}
