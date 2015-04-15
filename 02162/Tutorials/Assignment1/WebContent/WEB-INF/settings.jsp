<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Settings</title>
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
		 <li><a href="Forward?page=home">Home</a></li>
		 <li class="active"><a href="#">Settings</a></li>
		</ul>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row">
		<div class="col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<li class="active"><a href="#">Overview</a></li>
				<li><a href="#">Room 1</a></li>
				<li><a href="#">Room 2</a></li>
				<li><a href="#">Room 3</a></li>
			</ul>
		</div>
		<div class="col-md-10">
			<div class="jumbotron"> 
				<h1> Configure the home <br><small>Add or edit rooms</small></h1>
			</div>
			<div class="col-md-2">
			<button class="btn btn-default btn-primary btn-block" type="submit">New Room</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>