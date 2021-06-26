package sk.stuba.fei.uim.upb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sk.stuba.fei.uim.upb.exceptions.DatabaseException;

public class H2Database {

	private Connection connection;

	public H2Database createConnection() throws Exception {
		try {
			Class.forName("org.h2.Driver");
			Connection connection = DriverManager.getConnection("jdbc:h2:~/database/myDB;IFEXISTS=TRUE", "admin", "admin");
			this.connection = connection;
			return this;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public boolean exist(String username) {
		try {
			ResultSet rs = select(username);
			return (rs.next() == true);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public ResultSet select(String username) {
		try {
			PreparedStatement st = connection.prepareStatement("SELECT * FROM USERS WHERE username = ?");
			st.setString(1, username);
			return st.executeQuery();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public void insert(String username, String password, String salt) {
		try {
			PreparedStatement st = connection.prepareStatement("INSERT INTO USERS (username, password, salt) VALUES (?,?,?)");
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, salt);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
}
