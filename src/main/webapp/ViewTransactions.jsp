<%@ page import="java.util.List" %>
<%@ page import="com.aurionpro.entity.Transaction" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <link rel="stylesheet" href="styles.css"> <!-- Optional: Add your CSS styling -->
</head>
<body>

<h2>Passbook - Transaction Details</h2>

<!-- Filter Section -->
<form action="ViewTransactionController" method="get">
    <label for="accountNumber">Filter by Account Number:</label>
    <input type="text" id="accountNumber" name="accountNumber" placeholder="Enter Account Number">
    <button type="submit">Filter</button>
</form>

<% 
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    if (transactions != null && !transactions.isEmpty()) {
%>
    <table border="1">
        <thead>
            <tr>
                <th>Transaction Type</th>
                <th>Amount</th>
                <th>Sender Account Number</th>
                <th>Receiver Account Number</th>
                <th>Transaction Date</th>
            </tr>
        </thead>
        <tbody>
            <% for (Transaction transaction : transactions) { %>
                <tr>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getSenderAccountNumber() %></td>
                    <td><%= transaction.getReceiverAccountNumber() %></td>
                    <td><%= transaction.getTransactionDate() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
<% 
    } else {
%>
    <p>No transactions found for the given account number.</p>
<% 
    }
%>

</body>
</html>

