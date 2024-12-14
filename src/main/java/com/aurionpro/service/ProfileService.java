package com.aurionpro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileService {
    private PreparedStatement preparedStatement;
    private Connection connection;

    public ProfileService() throws Exception {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int editProfile(String email, String firstName, String lastName, String password, String username) throws SQLException {
        String query = "SELECT * FROM customers WHERE email = ?";
        int rowsAffected = 0;
        int customerId = 0;

        try {
            connection.setAutoCommit(false);

            // Fetch customerId based on the email (username)
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet customerProfile = preparedStatement.executeQuery();
            if (customerProfile.next()) {
                customerId = customerProfile.getInt("customerId");
            }

            // Update first_name if provided
            if (firstName != null && !firstName.trim().isEmpty()) {
                query = "UPDATE customers SET first_name = ? WHERE customerId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, firstName);
                preparedStatement.setInt(2, customerId);
                rowsAffected += preparedStatement.executeUpdate();
            }

            // Update last_name if provided
            if (lastName != null && !lastName.trim().isEmpty()) {
                query = "UPDATE customers SET last_name = ? WHERE customerId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, lastName);
                preparedStatement.setInt(2, customerId);
                rowsAffected += preparedStatement.executeUpdate();
            }

            // Update password if provided
            if (password != null && !password.trim().isEmpty()) {
                query = "UPDATE customers SET password = ? WHERE customerId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, password);
                preparedStatement.setInt(2, customerId);
                rowsAffected += preparedStatement.executeUpdate();
            }

            // Update email if provided
            if (email != null && !email.trim().isEmpty()) {
                query = "UPDATE customers SET email = ? WHERE customerId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, customerId);
                rowsAffected += preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return rowsAffected;
    }
}
