<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="dto.model.User"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Smart Home Security - Login</title>

<link rel="icon" href="..\WebContent\icon.ico">

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<%
		//allow access only if session attribute "user" is set beforehand
		User user = (User) session.getAttribute("user");
		if (user != null) {
			response.sendRedirect("dashboard.jsp");
		}
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="LoginServlet" method="POST">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Username" name="username" type="text"
										id="username" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" id="password" value="">
								</div>

								<button type="button" class="btn btn-xs btn-success btn-block"
									data-toggle="modal" data-target="#myModal">Forgot
									password</button>

								<button type="submit" class="btn btn-lg btn-success btn-block"
									name="login" value="Login">Login</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body" align=center>
					<h3>Sorry to hear that :-(</h3>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery Version 1.11.0 -->
	<script src="js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

	<script>
	function proceed () {
	    var form = document.createElement('form');
	    form.setAttribute('method', 'post');
	    form.setAttribute('name', 'login');
	    form.setAttribute('value', 'login');
	    form.style.display = 'hidden';
	    form.setAttribute("type", "hidden");
	    document.body.appendChild(form)
	    form.submit();
	}
	</script>

</body>

</html>