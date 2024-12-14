package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		/* String email = request.getParameter("email"); */
		String role = request.getParameter("role");

		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("Role: " + role);

		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			response.getWriter().write(
					"<script>alert('Username and password are required.'); window.location='login.jsp';</script>");
			return;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db", "root", "chocobar@6");

			String query = null;
			PreparedStatement ps = null;

			if ("admin".equalsIgnoreCase(role)) {
				query = "SELECT * FROM admin WHERE username = ? AND password = ?";
				ps = connection.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, password);
			} else if ("customer".equalsIgnoreCase(role)) {
				query = "SELECT * FROM customers WHERE email = ? AND password = ?";
				ps = connection.prepareStatement(query);
				ps.setString(1, username); // Email
				ps.setString(2, password); // Password
			} else {
				response.getWriter()
						.write("<script>alert('Invalid role selected!'); window.location='Login.jsp';</script>");
				return;
			}

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				HttpSession session = request.getSession();
			// Store customer ID in session
// Get the session object

				if ("admin".equalsIgnoreCase(role)) {
					// Admin login
					session.setAttribute("username", rs.getString("username")); // Store admin username in session
					response.getWriter().write(
							"<script>alert('Admin Login Successful!'); window.location='AdminHome.jsp';</script>");
				} else {
					// Customer login
					long customerId = rs.getLong("customerId"); // Customer ID
					session.setAttribute("customerId", rs.getLong("customerId")); // Store Customer ID
					session.setAttribute("customerName", rs.getString("first_name") + " " + rs.getString("last_name")); // Store
																														// Customer
																														// Full
																														// Name

					// Fetch and store all customer accounts in the session
					List<Long> accountNumbers = getCustomerAccounts(connection, customerId);
					session.setAttribute("accountNumbers", accountNumbers);

					response.getWriter().write(
							"<script>alert('Customer Login Successful!'); window.location='CustomerHome.jsp';</script>");
				}
			} else {
				response.getWriter()
						.write("<script>alert('Invalid Credentials!'); window.location='Login.jsp';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Login Failed: " + e.getMessage());
		}
	}

	 // Helper method to fetch all customer accounts
	 
	private List<Long> getCustomerAccounts(Connection connection, long customerId) throws SQLException {
        List<Long> accounts = new ArrayList<>();
        String query = "SELECT account_number FROM accounts WHERE customerId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accounts.add(rs.getLong("account_number"));
                }
            }
        }
        return accounts;
    }
	 
}
