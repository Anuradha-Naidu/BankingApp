<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("customerId") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">New Transaction</h2>
        <form action="NewTransactionController" method="post" class="mt-4">
            <div class="form-group">
                <label for="transactionType">Transaction Type</label>
                <select class="form-control" id="transactionType" name="transactionType" required>
                    <option value="Credit">Credit</option>
                    <option value="Debit">Debit</option>
                    <option value="Transfer">Transfer</option>
                </select>
            </div>
            <div class="form-group">
                <label for="amount">Amount</label>
                <input type="number" step="0.01" class="form-control" id="amount" name="amount" required>
            </div>
            <div class="form-group">
                <label for="toAccountNumber">To Account (For Transfer)</label>
                <input type="number" class="form-control" id="toAccountNumber" name="toAccountNumber">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </form>
        <% if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success mt-3">
                <%= request.getAttribute("successMessage") %>
            </div>
        <% } %>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger mt-3">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        <div class="text-center mt-3">
            <a href="CustomerHome.jsp" class="btn btn-secondary">Back to Home</a>
        </div>
    </div>
</body>
</html>
