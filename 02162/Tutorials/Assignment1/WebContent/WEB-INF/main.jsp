<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>
<body>


<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
 		  <span class="sr-only">Toggle navigation</span> 
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">Monitoring System</a>
		</div>
		<div class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
		 <li class="active"><a href="#">Home</a></li>
		 <li><a href="Forward?page=settings">Settings</a></li>
		</ul>
		</div>
	</div>
</div>

<div class="container">
	<div class="jumbotron">
		<h1>Username: ${username} <br/> Password: ${password}</h1>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default">
  				<div class="panel-heading"> Room 1 </div>
  				<div class="panel-body">Temperature: 899 <br> DB:90 </div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-primary">
  				<div class="panel-heading"> Room 2 </div>
  				<div class="panel-body">Temperature: 89 <br> DB:50</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-danger">
  				<div class="panel-heading"> Room 4 </div>
  				<div class="panel-body">Temperature: 8 <br> DB:1 </div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-danger">
  				<div class="panel-heading"> Security </div>
  				<div class="panel-body">
  				Potential risk 
  				<div class="progress">
  					<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" 
  					style="width: 80%">
  					80% 
  					</div>
  				</div>
  				Decibel:
  				<div class="progress">
  					<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" 
  					style="width: 20%">
  					</div>
  				</div>
  				</div>
			</div>
		</div>
	</div>
</div>




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>