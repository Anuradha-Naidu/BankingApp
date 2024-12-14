package com.aurionpro.service;

import java.sql.Connection; // For managing database connections
import java.sql.DriverManager; // To establish JDBC connection
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "chocobar@6";
   

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }


	public Connection connect() throws Exception{
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            throw new SQLException("Database connection failed.", e);
        }
	}
}