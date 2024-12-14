package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
		    
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    Connection connection = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/bank_db", "root", "chocobar@6");

		   
		    String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
		    PreparedStatement ps = connection.prepareStatement(query);
		    
		
		    ps.setString(1, username);
		    ps.setString(2, password);

		
		    ps.executeUpdate();

		
		    response.getWriter().write("<script>alert('Registration Successful!'); window.location='Login.jsp';</script>");
		    
		} catch (Exception e) {
		    
		    e.printStackTrace();
		    
		
		    response.sendRedirect("Login.jsp");
		}
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.sendRedirect("Register.jsp"); 
    }

}
