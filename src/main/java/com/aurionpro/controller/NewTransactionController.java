package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.service.DBConnection;

@WebServlet("/NewTransactionController")
public class NewTransactionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Long customerId = (Long) session.getAttribute("customerId");
        String transactionType = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String toAccount = request.getParameter("toAccountNumber");

        try (Connection conn = DBConnection.getConnection()) {

            // Get the customer's current balance and account details from the customers table
            double currentBalance = getCustomerBalance(conn, customerId);
            if (currentBalance == -1) {
                request.setAttribute("errorMessage", "No account found for the logged-in customer.");
                request.getRequestDispatcher("NewTransaction.jsp").forward(request, response);
                return;
            }

            // Perform the transaction based on type (Credit, Debit, Transfer)
            if ("Credit".equalsIgnoreCase(transactionType)) {
                updateBalance(conn, customerId, amount);  // Credit the amount
                recordTransaction(conn, "Credit", amount, customerId, customerId);  // Record the transaction
            } else if ("Debit".equalsIgnoreCase(transactionType)) {
                if (currentBalance >= amount) {
                    updateBalance(conn, customerId, -amount);  // Debit the amount
                    recordTransaction(conn, "Debit", amount, customerId, customerId);  // Record the transaction
                } else {
                    request.setAttribute("errorMessage", "Insufficient balance for debit transaction.");
                    request.getRequestDispatcher("NewTransaction.jsp").forward(request, response);
                    return;
                }
            } else if ("Transfer".equalsIgnoreCase(transactionType)) {
                int toAccountNumber = Integer.parseInt(toAccount);
                if (currentBalance >= amount) {
                    updateBalance(conn, customerId, -amount);  // Debit the amount from the sender
                    updateBalance(conn, (long) toAccountNumber, amount);  // Credit the amount to the receiver
                    recordTransaction(conn, "Transfer", amount, customerId, (long) toAccountNumber);  // Record the transfer
                } else {
                    request.setAttribute("errorMessage", "Insufficient balance for transfer.");
                    request.getRequestDispatcher("NewTransaction.jsp").forward(request, response);
                    return;
                }
            }

            request.setAttribute("successMessage", "Transaction successful.");
            request.getRequestDispatcher("NewTransaction.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Transaction failed: " + e.getMessage());
            request.getRequestDispatcher("NewTransaction.jsp").forward(request, response);
        }
    }

    private double getCustomerBalance(Connection conn, Long customerId) throws Exception {
        String query = "SELECT balance FROM customers WHERE customerId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        }
        return -1;
    }

    private void updateBalance(Connection conn, Long customerId, double amount) throws Exception {
        String query = "UPDATE customers SET balance = balance + ?, lastTransactionDate = ? WHERE customerId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setLong(3, customerId);
            stmt.executeUpdate();
        }
    }

    private void recordTransaction(Connection conn, String type, double amount, Long senderId, Long receiverId) throws Exception {
        String query = "UPDATE customers SET transactionType = ?, transactionAmount = ?, toAccountNumber = ?, lastTransactionDate = ? WHERE customerId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, type);
            stmt.setDouble(2, amount);
            stmt.setLong(3, receiverId);  // This would be for transfers
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.setLong(5, senderId);
            stmt.executeUpdate();
        }
    }
}
