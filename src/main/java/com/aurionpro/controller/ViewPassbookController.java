/*
 * package com.aurionpro.controller;
 * 
 * import java.io.IOException; import java.sql.Connection; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.util.ArrayList; import java.util.List;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import com.aurionpro.entity.Transaction; import
 * com.aurionpro.service.DBConnection;
 * 
 * @WebServlet("/ViewPassbookController") public class ViewPassbookController
 * extends HttpServlet { private static final long serialVersionUID = 1L;
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { HttpSession session =
 * request.getSession(false);
 * 
 * if (session == null || session.getAttribute("customerId") == null) {
 * response.sendRedirect("Login.jsp"); return; }
 * 
 * Long customerId = (Long) session.getAttribute("customerId"); String
 * accountNumber = request.getParameter("account_number");
 * 
 * try (Connection conn = DBConnection.getConnection()) { List<Transaction>
 * transactions = getTransactions(conn, customerId, accountNumber);
 * request.setAttribute("transactions", transactions);
 * request.getRequestDispatcher("Passbook.jsp").forward(request, response); }
 * catch (Exception e) { e.printStackTrace();
 * request.setAttribute("errorMessage", "Failed to fetch transactions: " +
 * e.getMessage());
 * request.getRequestDispatcher("CustomerHome.jsp").forward(request, response);
 * } }
 * 
 * private List<Transaction> getTransactions(Connection conn, Long customerId,
 * String accountNumber) throws Exception { List<Transaction> transactions = new
 * ArrayList<>(); String query =
 * "SELECT customerId, customerName, transactionType, transactionAmount, toAccountNumber, lastTransactionDate "
 * + "FROM customers " + "WHERE customerId = ?";
 * 
 * if (accountNumber != null && !accountNumber.isEmpty()) { query +=
 * " AND (accountNumber = ? OR toAccountNumber = ?)"; }
 * 
 * try (PreparedStatement stmt = conn.prepareStatement(query)) { stmt.setLong(1,
 * customerId); if (accountNumber != null && !accountNumber.isEmpty()) {
 * stmt.setString(2, accountNumber); stmt.setString(3, accountNumber); }
 * ResultSet rs = stmt.executeQuery();
 * 
 * while (rs.next()) { Transaction transaction = new Transaction();
 * transaction.setCustomerId(rs.getLong("customerId"));
 * transaction.setCustomerName(rs.getString("customerName"));
 * transaction.setTransactionType(rs.getString("transactionType"));
 * transaction.setAmount(rs.getDouble("transactionAmount"));
 * transaction.setToAccountNumber(rs.getInt("toAccountNumber"));
 * transaction.setTransactionDate(rs.getTimestamp("lastTransactionDate"));
 * 
 * } } return transactions; } }
 */

package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.entity.Transaction;
import com.aurionpro.service.DBConnection;

@WebServlet("/ViewPassbookController")
public class ViewPassbookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Long customerId = (Long) session.getAttribute("customerId");

        try (Connection conn = DBConnection.getConnection()) {
            List<Transaction> transactions = getTransactions(conn, customerId);
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("Passbook.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to fetch transactions: " + e.getMessage());
            request.getRequestDispatcher("CustomerHome.jsp").forward(request, response);
        }
    }

    private List<Transaction> getTransactions(Connection conn, Long customerId) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT customerId, customerName, transactionType, transactionAmount, toAccountNumber, lastTransactionDate "
                     + "FROM customers "
                     + "WHERE customerId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setCustomerId(rs.getLong("customerId"));
                transaction.setCustomerName(rs.getString("customerName"));
                transaction.setTransactionType(rs.getString("transactionType"));
                transaction.setAmount(rs.getDouble("transactionAmount"));
                transaction.setToAccountNumber(rs.getInt("toAccountNumber"));
                transaction.setTransactionDate(rs.getTimestamp("lastTransactionDate"));

                transactions.add(transaction);  
            }
        }
        return transactions;
    }
}




