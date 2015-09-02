package com.tennis.persistence;

import com.tennis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOjdbcImpl implements UserDAO {
	@Autowired
	DataSource dataSource;

	PreparedStatement preparedStatement;
	User user;

	public User getById(int id) {
		String query = "select userName from Users where userID = ?";
		user = null;
		Connection connection = null;
		preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setUserID(id);
				user.setUserName(resultSet.getString("userName"));
				System.out.println("User Found::" + user);
			} else {
				System.out.println("No User found with id=" + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		// TODO Add implementation
		return new ArrayList<User>();
	}

	public User getByName(String name) {
		String query = "select userName from Users where userName = ?";
		user = null;
		Connection connection = null;
		preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setUserID(resultSet.getInt("userID"));
				user.setUserName(name);
				System.out.println("User Found::" + user);
			} else {
				System.out.println("No User found with name=" + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public void create(User user) {
		String query = "insert into Users (userName, password, email, birthdate, sex) "
				+ "values (?, ?, ?, ?, ?)";
		Connection connection = null;
		preparedStatement = null;
		int numberOfInsertedRows = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getBirthDate());
			preparedStatement.setLong(5, user.getSex());
			numberOfInsertedRows = preparedStatement.executeUpdate();
			if (numberOfInsertedRows == 0) {
				System.out.println("Rows did not inserted!");
			} else {
				System.out.println("Inserted::" + numberOfInsertedRows
						+ " rows");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(int id) {
		String query = "delete from Users where userID = ?";
		Connection connection = null;
		preparedStatement = null;
		int numberOfInsertedRows = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			numberOfInsertedRows = preparedStatement.executeUpdate();
			if (numberOfInsertedRows == 0) {
				System.out.println("Rows did not deleted!");
			} else {
				System.out.println("Deleted Record with ID = " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}