package com.aurionpro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entity.Transaction;

public class TransactionService {
    public List<Transaction> getTransactionsByCustomerId(int customerId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT t.* FROM Transaction t "
                     + "JOIN Account a ON t.senderAccountNumber = a.accountNumber OR t.receiverAccountNumber = a.accountNumber "
                     + "WHERE a.customerId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("transactionId"),
                    rs.getDate("transactionDate"),
                    rs.getString("transactionType"),
                    rs.getDouble("amount"),
                    rs.getInt("senderAccountNumber"),
                    rs.getInt("receiverAccountNumber")
                );
                transactions.add(transaction);
                System.out.println(transactions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}