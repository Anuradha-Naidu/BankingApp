<%@ page import="java.util.List" %>
<%@ page import="com.aurionpro.entity.Transaction" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Passbook</title>
    <link rel="stylesheet" href="styles.css"> <!-- Optional: Add your CSS styling -->
</head>
<body>

<h2>Passbook - All Transactions</h2>

<%
    // Retrieve the transactions list from the request
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    if (transactions != null && !transactions.isEmpty()) {
%> 
<form action="ViewPassbookController" method="GET">
    <table border="1">
        <thead>
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Transaction Type</th>
                <th>Amount</th>
                <th>To Account Number</th>
                <th>Transaction Date</th>
            </tr>
        </thead>
        <tbody>
            <% for (Transaction transaction : transactions) { %>
                <tr>
                    <td><%= transaction.getCustomerId() %></td>
                    <td><%= transaction.getCustomerName() %></td>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getToAccountNumber() %></td>
                    <td><%= transaction.getTransactionDate() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    
   </form>
<% 
    } else {
%>
    <p>No transactions available.</p>
<% 
    }
%>

</body>
</html>

