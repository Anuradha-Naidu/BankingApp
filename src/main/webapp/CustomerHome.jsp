<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.aurionpro.service.DBConnection" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Home</title>
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
            <a class="navbar-brand" href="#">Customer Dashboard</a>
            <form action="LogoutController" method="post" class="d-inline">
                <button type="submit" class="btn btn-danger">Log Out</button>
            </form>
        </div>
    </nav>

    <!-- Welcome Section -->
    <div class="container mt-5 text-center">
        <% 
            // Retrieve the logged-in customer ID from the session
          // Avoid creating a new session
            if (session == null || session.getAttribute("customerId") == null) {
                // Redirect to login page if no session or customer ID
                response.sendRedirect("CustomerLogin.jsp");
                return;
            }

            Long customerId = (Long) session.getAttribute("customerId");
            String customerName = "";

            try (Connection connection = DBConnection.getConnection()) {
                String query = "SELECT first_name, last_name FROM customers WHERE customerId = ?";
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setLong(1, customerId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            customerName = rs.getString("first_name") + " " + rs.getString("last_name");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
        <h1 class="text-primary">Welcome, <%= customerName %>!</h1>
        <p class="lead">Manage your transactions, view your passbook, and update your profile easily.</p>
    </div>

    <!-- Customer Actions -->
    <div class="container mt-4">
        <div class="row g-4 justify-content-center">
            <div class="col-md-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h5 class="card-title">New Transaction</h5>
                        <a href="NewTransaction.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h5 class="card-title">Passbook</h5>
                        <a href="Passbook.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h5 class="card-title">Edit Profile</h5>
                        <a href="edit_profile.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-primary text-center text-white py-3 mt-5">
        <p class="mb-0">&copy; 2024 Customer Dashboard</p>
    </footer>

    <!-- Include Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
