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
import javax.servlet.http.HttpSession;

import com.aurionpro.service.DBConnection;

@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        // Get the session to ensure the customer is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Retrieve session data
        Long customerId = (Long) session.getAttribute("customerId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        // Retrieve the existing password from the database
        String existingPassword = getExistingPassword(customerId);

        // If no existing password found, show an error
        if (existingPassword == null) {
            request.setAttribute("errorMessage", "Error retrieving customer data.");
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
            return;
        }

        // If the password is not provided, keep the existing password
        String updatedPassword = (password == null || password.isEmpty()) ? existingPassword : password;

        // Update the customer profile
        boolean isUpdated = updateCustomerProfile(customerId, firstName, lastName, updatedPassword);

        // Set success or error messages based on the update result
        if (isUpdated) {
            session.setAttribute("customerFirstName", firstName);
            session.setAttribute("customerLastName", lastName);

            request.setAttribute("successMessage", "Profile updated successfully!");
        } else {
            request.setAttribute("errorMessage", "Profile update failed. Please try again.");
        }

        // Forward to the edit profile page with appropriate message
        request.getRequestDispatcher("edit_profile.jsp").forward(request, response);
    }

    // Retrieve the existing password from the database
    private String getExistingPassword(Long customerId) {
        String query = "SELECT password FROM customers WHERE customerId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update the customer profile in the database
    private boolean updateCustomerProfile(Long customerId, String firstName, String lastName, String password) {
        String query = "UPDATE customers SET first_name = ?, last_name = ?, password = ? WHERE customerId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, password);
            stmt.setLong(4, customerId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


