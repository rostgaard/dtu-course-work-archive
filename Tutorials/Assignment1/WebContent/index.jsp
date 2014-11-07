<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login!</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
 <style type="text/css">
      body {
        padding-top: 40px;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        margin-bottom: 15px;
      }
</style>
</head>
<body>
	
	
	
	<div class="container">
		<form action="Login" method="post" class="form-signin">
			<h1 class="form-signin-heading">Halli Hallo, Login!</h1> 
			<input class="form-control" name="username"  type="text" placeholder="Username"/>
			<input class="form-control" name="password"  type="password" placeholder="Password">
			<button class="btn btn-default btn-primary btn-block" name="submit" type="submit">Login</button>
		</form>
	</div>
	
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>