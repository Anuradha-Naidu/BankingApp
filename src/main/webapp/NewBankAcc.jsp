<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Bank Account</title>
    <!-- Include Bootstrap CSS -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
        rel="stylesheet"
    >
    <style>
        .error {
            color: red;
            font-weight: bold;
        }
        .success {
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="AdminHome.jsp">Admin Dashboard</a>
            <a href="AddCustomer.jsp" class="btn btn-primary">Add Customer</a>
            <a href="NewBankAcc.jsp" class="btn btn-primary">Add Bank Account</a>
            <a href="ViewCustomers.jsp" class="btn btn-primary">View Customers</a>
            <a href="ViewTransactions.jsp" class="btn btn-primary">View Transactions</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-5">
        <h1 class="text-center text-primary">Add Bank Account</h1>

        <!-- Display Error or Success Message -->
        <div class="mt-4">
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                String customerDetails = (String) request.getAttribute("customerDetails");
                if (errorMessage != null) { 
            %>
                <div class="alert alert-danger text-center"><%= errorMessage %></div>
            <% } else if (customerDetails != null) { %>
                <div class="alert alert-success text-center">Customer found successfully!</div>
            <% } %>
        </div>

        <!-- Search Form -->
        <form action="SearchCustomerController" method="POST" class="mt-4 p-4 shadow bg-light rounded">
            <div class="mb-3">
                <label for="customerId" class="form-label">Search by Customer ID</label>
                <input type="text" id="customerId" name="customerId" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Submit</button>
        </form>

        <!-- Display Customer Details if Available -->
        <div class="mt-5">
            <% 
                if (customerDetails != null) { 
            %>
                <div class="card shadow">
                    <div class="card-body">
                        <h3 class="card-title text-success">Customer Details</h3>
                        <p class="card-text"><%= customerDetails %></p>
                        <form action="AddAccountController" method="POST">
                            <input type="hidden" name="customerId" value="<%= request.getAttribute("customerId") %>">
                            <button type="submit" class="btn btn-primary">Generate Account Number</button>
                        </form>
                    </div>
                </div>
            <% } %>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-primary text-center text-white py-3 mt-5">
        <p class="mb-0">&copy; 2024 Admin Dashboard</p>
    </footer>

    <!-- Include Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
