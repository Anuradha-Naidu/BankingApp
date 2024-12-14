<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
    <!-- Include Bootstrap CSS -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
        rel="stylesheet"
    >
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Admin Dashboard</a>
            <a href="AddCustomer.jsp" class="btn btn-primary">Add Customer</a>
            <a href="NewBankAcc.jsp" class="btn btn-primary">Add Bank Account</a>
            <a href="ViewCustomers.jsp" class="btn btn-primary">View Customers</a>
            <a href="ViewTransactions.jsp" class="btn btn-primary">View Transactions</a>
        </div>
    </nav>

    <!-- Welcome Section -->
    <div class="container mt-5 text-center">
        <h1 class="text-primary">Welcome Admin!</h1>
        <p class="lead">Manage Customers, Bank Accounts, and Transactions</p>
    </div>

    <!-- Admin Actions -->
    <div class="container mt-4">
        <div class="row g-4">
            <div class="col-md-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h5 class="card-title">Add New Customer</h5>
                        <a href="AddCustomer.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h5 class="card-title">Add Bank Account</h5>
                        <a href="NewBankAcc.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h5 class="card-title">View Customers</h5>
                        <a href="ViewCustomers.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h5 class="card-title">View Transactions</h5>
                        <a href="ViewTransaction.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
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