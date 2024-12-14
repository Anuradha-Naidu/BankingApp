package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.DBConnection;

@WebServlet("/AddAccountController")
public class AddAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get customerId from the form
        String customerIdParam = request.getParameter("customerId");
        if (customerIdParam == null || customerIdParam.isEmpty()) {
            response.getWriter().write("<script>alert('Customer ID is missing.'); history.back();</script>");
            return;
        }

        Long customerId;
        try {
            customerId = Long.parseLong(customerIdParam);
        } catch (NumberFormatException e) {
            response.getWriter().write("<script>alert('Invalid Customer ID.'); history.back();</script>");
            return;
        }

        // Generate a 12-digit random account number
        long accountNumber = 100000000000L + (long)(new Random().nextDouble() * 900000000000L);


        try (Connection connection = DBConnection.getConnection()) {
            // SQL query to update the account number for the given customerId
        	String query = "UPDATE customers SET account_number = ? WHERE customerId = ?";
        	//String query = "UPDATE customers SET account_number = ? WHERE customerId = ?";
        	//String query = "INSERT INTO accounts (account_number, customer_id, balance) VALUES (?, ?, 0)";
        	PreparedStatement ps = connection.prepareStatement(query);
        	ps.setLong(1, accountNumber);
        	ps.setLong(2, customerId); // Assuming customerId is still an int

            int rowsUpdated = ps.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                response.getWriter().write(
                    "<script>alert('Account added successfully!'); window.location='ViewCustomers.jsp';</script>"
                );
            } else {
                response.getWriter().write(
                    "<script>alert('Customer not found. Please check the Customer ID.'); history.back();</script>"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(
                "<script>alert('Database error: " + e.getMessage() + "'); history.back();</script>"
            );
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(
                "<script>alert('An unexpected error occurred.'); history.back();</script>"
            );
        }
    }
}
