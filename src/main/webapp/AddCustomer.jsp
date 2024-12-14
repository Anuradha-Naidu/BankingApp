<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Customer</title>
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
            <a class="navbar-brand" href="AdminHome.jsp">Admin Dashboard</a>
            <a href="AddCustomer.jsp" class="btn btn-primary">Add Customer</a>
            <a href="NewBankAcc.jsp" class="btn btn-primary">Add Bank Account</a>
            <a href="ViewCustomers.jsp" class="btn btn-primary">View Customers</a>
            <a href="ViewTransaction.jsp" class="btn btn-primary">View Transactions</a>
        </div>
    </nav>

    <!-- Add Customer Form Section -->
    <div class="container mt-5">
        <h2 class="text-center text-primary">Add New Customer</h2>
        <form action="AddCustomerController" method="post" class="mt-4 p-4 shadow rounded bg-light">
            <div class="mb-3">
                <label for="first_name" class="form-label">First Name</label>
                <input type="text" id="first_name" name="first_name" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="last_name" class="form-label">Last Name</label>
                <input type="text" id="last_name" name="last_name" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Add Customer</button>
                <a href="AdminHome.jsp" class="btn btn-secondary">Back to Admin Home</a>
            </div>
        </form>
    </div>

    <!-- Footer -->
    <footer class="bg-primary text-center text-white py-3 mt-5">
        <p class="mb-0">&copy; 2024 Admin Dashboard</p>
    </footer>

    <!-- Include Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
