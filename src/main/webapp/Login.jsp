<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin/Customer Login</title>
 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow">
        <div class="card-header bg-primary text-white text-center">
          <h3>Login Admin/Customer</h3>
        </div>
        <div class="card-body">
          <form action="LoginController" method="post">
        
            <div class="mb-3">
              <label for="role" class="form-label">Login As:</label>
              <select id="role" name="role" class="form-select" required>
                <option value="admin">Admin</option>
                <option value="customer">Customer</option>
              </select>
            </div>
           
            <div class="mb-3">
              <label for="username" class="form-label">Username/Email</label>
              <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username or email" required>
            </div>
         
            <div class="mb-3">
              <label for="password" class="form-label">Password</label>
              <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
            </div>
            
            <div class="d-grid">
              <button type="submit" class="btn btn-primary">Login</button>
            </div>
          </form>
          <div class="text-center mt-3">
            <p>Don't have an account? <a href="Register.jsp" class="text-primary">Sign Up Here</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>