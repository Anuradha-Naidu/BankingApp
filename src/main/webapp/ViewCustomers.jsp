<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.aurionpro.service.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Customers</title>
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
            <a href="ViewTransactions.jsp" class="btn btn-primary">View Transactions</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-5">
        <h2 class="text-center text-primary mb-4">Customer List</h2>

        <!-- Filter Form -->
        <form action="ViewCustomers.jsp" method="get" class="mb-4">
            <div class="row">
                <div class="col-md-4">
                    <select name="filterCriteria" class="form-select" required>
                        <option value="" selected disabled>-- Select Filter Criteria --</option>
                        <option value="customerId">Customer ID</option>
                        <option value="first_name">First Name</option>
                        <option value="last_name">Last Name</option>
                        <option value="email">Email</option>
                        <option value="account_number">Account Number</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <input type="text" name="searchTerm" class="form-control" placeholder="Enter Search Term" required>
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary w-100">Filter</button>
                </div>
            </div>
        </form>

        <!-- Customer Table -->
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="table-primary">
                    <tr>
                        <th>Customer ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Account Number</th>
                        <th>Balance</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        Connection connection = null;
                        PreparedStatement ps = null;
                        ResultSet rs = null;
                        try {
                            // Get filter criteria and search term
                            String filterCriteria = request.getParameter("filterCriteria");
                            String searchTerm = request.getParameter("searchTerm");

                            // Base query
                            String query = "SELECT customerId, first_name, last_name, email, account_number, balance FROM customers";

                            // Append WHERE clause if filter criteria is provided
                            if (filterCriteria != null && searchTerm != null && !searchTerm.trim().isEmpty()) {
                                query += " WHERE " + filterCriteria + " LIKE ?";
                            }

                            connection = DBConnection.getConnection();
                            ps = connection.prepareStatement(query);

                            // Set parameter for WHERE clause
                            if (filterCriteria != null && searchTerm != null && !searchTerm.trim().isEmpty()) {
                                ps.setString(1, "%" + searchTerm + "%");
                            }

                            rs = ps.executeQuery();

                            while (rs.next()) {
                    %>
                                <tr>
                                    <td><%= rs.getInt("customerId") %></td>
                                    <td><%= rs.getString("first_name") %></td>
                                    <td><%= rs.getString("last_name") %></td>
                                    <td><%= rs.getString("email") %></td>
                                    <td>
                                        <%= rs.getObject("account_number") == null ? "Not Assigned" : rs.getLong("account_number") %>
                                    </td>
                                    <td><%= rs.getInt("balance") %></td>
                                </tr>
                    <% 
                            }

                            if (!rs.isBeforeFirst()) {
                    %>
                                <tr>
                                    <td colspan="6" class="text-center text-warning">No records found.</td>
                                </tr>
                    <% 
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                    %>
                                <tr>
                                    <td colspan="6" class="text-danger text-center">Error loading customers: <%= e.getMessage() %></td>
                                </tr>
                    <% 
                        } finally {
                            if (rs != null) rs.close();
                            if (ps != null) ps.close();
                            if (connection != null) connection.close();
                        }
                    %>
                </tbody>
            </table>
        </div>

        <!-- Back Button -->
        <div class="text-center mt-4">
            <a href="AdminHome.jsp" class="btn btn-primary">Back to Admin Home</a>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-primary text-white text-center py-3 mt-5">
        <p class="mb-0">&copy; 2024 Admin Dashboard</p>
    </footer>

    <!-- Include Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

