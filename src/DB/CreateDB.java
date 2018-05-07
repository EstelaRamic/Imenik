package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	// localhost//imeBazeNaKojuSeSpajamo
	private static final String CONN_STRING = "jdbc:mysql://localhost/?useSSL=false";
	// error? dodati na kraj stringa ?useSSL=false

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		// napraviti SQL query
		String sqlQuery = "CREATE DATABASE Imenik";

		
		try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

				
				// java.sql.Statement
				Statement statement = connection.createStatement();) {
			// pokrenuti query
			statement.executeUpdate(sqlQuery);
			System.out.println("Baza podataka uspjesno kreirana.");
		}catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}

