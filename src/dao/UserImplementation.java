package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UserImplementation implements UserInterface {

	Connection connection = ConnectionManager.getInstance().getConnection();

	@Override
	public User validateUser(String username, String password)  {
		User user = null;

		String query = "SELECT * FROM user WHERE username=? and password=?";
		ResultSet rs = null;

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, username);
			statement.setString(2, password);

			rs = statement.executeQuery();

			if (rs.next()) {
				user = new User();

				user.setId(rs.getInt("userID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	@Override
	public boolean register(User user) throws SQLException {

		String query = "INSERT INTO imenik.user(firstName,lastName, username, password) VALUES(?,?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}
	
	
}
