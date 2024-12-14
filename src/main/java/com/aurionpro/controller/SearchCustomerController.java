package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.DBConnection;

@WebServlet("/SearchCustomerController")
public class SearchCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET request (optional, redirects to search page)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("NewBankAcc.jsp");
    }

    // Handle POST request
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");

        // Validate input
        if (customerIdParam == null || customerIdParam.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Customer ID cannot be empty.");
            request.getRequestDispatcher("NewBankAcc.jsp").forward(request, response);
            return;
        }

        try {
            int customerId = Integer.parseInt(customerIdParam); // Convert to integer

            // Query database for customer details
            Connection connection = DBConnection.getConnection();
            String query = "SELECT first_name, last_name, email FROM customers WHERE customerId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Customer found
                String customerDetails = "Name: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                         ", Email: " + rs.getString("email");
                request.setAttribute("customerDetails", customerDetails);
                request.setAttribute("customerId", customerId);
            } else {
                // Customer not found
                request.setAttribute("errorMessage", "Customer not found.");
            }

            // Forward to JSP page
            request.getRequestDispatcher("NewBankAcc.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("errorMessage", "Invalid Customer ID format. Please enter a number.");
            request.getRequestDispatcher("NewBankAcc.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing the request.");
            request.getRequestDispatcher("NewBankAcc.jsp").forward(request, response);
        }
    }
}

