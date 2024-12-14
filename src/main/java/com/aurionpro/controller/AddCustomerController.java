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

@WebServlet("/AddCustomerController")
public class AddCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO customers (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

  
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, password);

      
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No rows inserted");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int customerId = rs.getInt(1);
   
                response.getWriter().write("<script>alert('Customer added successfully! Customer ID: " + customerId + "'); window.location='ViewCustomers.jsp';</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();

            response.getWriter().write("<script>alert('Error adding customer: " + e.getMessage() + "'); window.location='AddCustomer.jsp';</script>");
        }
    }
}

