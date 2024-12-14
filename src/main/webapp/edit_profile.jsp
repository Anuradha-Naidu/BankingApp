<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Edit Profile</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Edit Profile</h2>
        <form action="EditProfileController" method="post" class="mt-4">
            <!-- Hidden Field for Customer ID -->
            <input type="hidden" name="customerId" value="${sessionScope.customerId}">
            
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${sessionScope.customerFirstName}" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${sessionScope.customerLastName}" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${sessionScope.customerEmail}" readonly>
            </div>
            <div class="form-group">
                <label for="password">New Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter new password (optional)">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Update Profile</button>
        </form>

        <!-- Display success or error messages -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success mt-3">
                ${successMessage}
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger mt-3">
                ${errorMessage}
            </div>
        </c:if>

        <div class="text-center mt-3">
            <a href="CustomerHome.jsp" class="btn btn-secondary">Back to Home</a>
        </div>
    </div>
</body>
</html>