package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static ConnectionManager instance = null;
	// DB credentials
	private final  String USERNAME = "root";
	private final  String PASSWORD = "";
	private final  String CONN_STRING = "jdbc:mysql://localhost/imenik";
	private final  String DRIVER = "com.mysql.jdbc.Driver";

	// connection object
	private static Connection connection = null;

	// constructor
	private ConnectionManager() {

	}

	public static ConnectionManager getInstance() {

		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	private  boolean openConnection() {
		try {
			
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e);
			return false;
		}
	}

	public Connection getConnection() {
		if (connection == null) {
			if (openConnection()) {
				return connection;
			} else {
				return null;
			}
		}
		return connection;
	}

	void close() {

		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
