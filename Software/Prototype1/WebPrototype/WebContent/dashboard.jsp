<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dto.model.User"%>
<%@ page import="enums.Role"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Security System Dashboard</title>

<!-- jQuery Version 1.11.0 -->
<script src="js/jquery-1.11.0.js"></script>

<link rel="icon" href="\icon.ico">

<!-- Custom Sensor CSS -->
<link href="css/sensor.css" rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Timeline CSS -->
<link href="css/plugins/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="css/plugins/morris.css" rel="stylesheet">

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
		String userName = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		Role role = null;
		if (user == null) {
			response.sendRedirect("login.jsp");
		} else {
			userName = user.getUserName();
			firstName = user.getFirstName();
			lastName = user.getLastName();
			email = user.getEmail();
			role = user.getRole();
		}
	%>

	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#dashboard" role="tab"
			data-toggle="tab">Dashboard</a></li>
		<li><a href="#devices" role="tab" data-toggle="tab">Devices</a></li>
		<li><a href="#users" role="tab" data-toggle="tab">Users</a></li>
		<li><a href="#floorplan" role="tab" data-toggle="tab">Floor
				Plan</a></li>
		<li><a href="#profile" role="tab" data-toggle="tab"> <%=firstName%>
				<%=lastName%> </a></li>
		<li style="float:right;"><a href="/Prototype1/Servlet">Log Out</a></li>		
				
				 
	</ul>


	<!-- ##################################################################
#######################################################################
#######################################################################

#########################	Dashboard	###############################

#######################################################################
#######################################################################
################################################################### -->


	<div class="tab-content">
		<div class="tab-pane active" id="dashboard">

			<div id="wrapper">
				<div id="page-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<h1 class="page-header">Dashboard</h1>
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-mobile fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">3</div>
											<div>Devices running</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-green">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-warning fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">0</div>
											<div>New Security Events</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-warning">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-home  fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">19°C</div>
											<div>Average Room Temperature</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-info">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-cloud fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">40%</div>
											<div>Average Humidity</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
					</div>
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-8">
							<div class="panel panel-default">
								<!-- /.panel-heading -->
								<button type="button" class="btn btn-warning btn-s"
									data-toggle="modal" data-target="#jesperModal"
									style="position: absolute; left: 30%; top: 20%">
									<i class="fa fa-video-camera fa-3x"><h2>1</h2></i>
								</button>
								<div class="panel-body">

									<img src="floor_plan_example.png" width="49%"> <img
										src="floor_plan_example.png" width="49%">
								</div>
								<!-- /.panel-body -->
							</div>
							<!-- /.panel -->
						</div>
						<!-- /.col-lg-8 -->
						<div class="col-lg-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<i class="fa fa-bell fa-fw"></i> Notifications Panel
								</div>
								<!-- /.panel-heading -->

								<div class="panel-body">
									<div class="list-group" id="box"></div>
									<button class="btn btn-primary btn-block" id="more">Show
										more</button>
									<!-- /.list-group -->
								</div>

								<!-- /.panel-body -->
							</div>
							<!-- /.panel -->
						</div>
						<!-- /.col-lg-4 -->
					</div>
					<!-- /.row -->
				</div>

				<!-- @Jesper play video file in modal window -->
				<div class="modal fade" id="jesperModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Camera</h4>
							</div>
							<div class="modal-body">

								<video width="800" id="player" controls autoplay>
									<!--<source src="tmp/video1.mp4"  type="video/mp4" >-->
									<!--<source src="mov_bbb.ogg" type="video/ogg">-->
								</video>

							</div>
						</div>
					</div>
				</div>
			</div>





			<!-- /#page-wrapper -->
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Camera</h4>
						</div>
						<div class="modal-body"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#wrapper -->



		<!-- ##################################################################
#######################################################################
#######################################################################

#########################	Devices		###############################

#######################################################################
#######################################################################
################################################################### -->

		<div role="tabpanel" class="tab-pane" id="devices">
			<div id="wrapper">
				<div id="page-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<h1 class="page-header">Device Management</h1>
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-6">
							<div class="panel panel-default">
								<div class="panel-heading">
									<i class="fa fa-mobile fa-fw"></i> Active Devices
								</div>
								<!-- /.panel-heading -->

								<div class="panel-body">
									<div class="list-group" id="devices"></div>

									<!-- /.list-group -->
								</div>

								<!-- /.panel-body -->
							</div>
							<!-- /.panel -->

						</div>
						<!-- /.panel -->
						<div class="col-lg-6">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											<i class="fa fa-user fa-fw"></i>Configure Device
										</div>
										<div class="panel-body">
											<div class="form-group">
												<div class="col-sm-12">
													<input type="text" class="form-control" id="deviceName"
														placeholder="Device Name">
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-12">
													<input type="text" class="form-control" id="deviceID"
														placeholder="Device ID">
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-12">
													<select id="status" name="Status">
														<option value=0>Activated</option>
														<option value=1>Deactivated</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-12">
													<div class="checkbox">
														<label> <input type="checkbox" id="light"
															value="1">Flashlight Actuator <input
															type="checkbox" id="camera" value="1">Camera
															Actuator <input type="checkbox" id="sound" value="1">Sound
															Actuator
														</label>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<button type="button" id="submit"
														class="btn btn-lg btn-success btn-block" onclick="">Configure</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#wrapper -->


		<!-- ##################################################################
#######################################################################
#######################################################################

#########################	Users		###############################

#######################################################################
#######################################################################
################################################################### -->


		<div role="tabpanel" class="tab-pane" id="users">
			<div class="tab-pane" id="users">
				<div id="wrapper">
					<div id="page-wrapper">
						<div class="row">
							<div class="col-lg-12">
								<h1 class="page-header">User Management</h1>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-4">
								<div class="panel panel-default">
									<div class="panel-heading">
										<i class="fa fa-users fa-fw"></i> Users
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<div class="list-group" id="user"></div>
									</div>
								</div>
							</div>
							<!-----New user credentials----->
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="col-lg-4">
										<div class="panel panel-default">
											<div class="panel-heading">
												<i class="fa fa-user fa-fw"></i>New User
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-4">
										<input type="text" class="form-control" id="username"
											placeholder="Username">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-4">
										<input type="email" class="form-control" id="email"
											placeholder="Email">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-4">
										<input type="text" class="form-control" id="firstname"
											placeholder="First Name">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-4">
										<input type="text" class="form-control" id="lastname"
											placeholder="Last Name">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-lg-8">
										<button class="btn btn-default btn-sm dropdown-toggle"
											type="button" data-toggle="dropdown" aria-expanded="false">
											Role <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#viewer">Viewer</a></li>
											<li><a href="#manager">Manager</a>
										</ul>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-4">
										<div class="checkbox">
											<label> <input type="checkbox">Ask for new
												password on first log on
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-4">
										<button type="submit" class="btn btn-default">Add
											User</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- ##################################################################
#######################################################################
#######################################################################

#########################	Floorplan	###############################

#######################################################################
#######################################################################
################################################################### -->

		<div role="tabpanel" class="tab-pane" id="floorplan">

			<div>
				<div id="sensor1" class="draggable">1</div>

				<div id="sensor2" class="draggable">2</div>

				<div id="sensor3" class="draggable">3</div>

				<div id="sensor4" class="draggable">4</div>

				<div id="floorplan">
					<img src="floor_plan_example.png" width="500px" height="500px" />
				</div>

			</div>

			<div class="col-md-4">
				<div class="panel-body">
					<button onclick="saveFloorplan()" class="btn btn-primary btn-block"
						id="save">Save</button>
				</div>
			</div>
		</div>


		<!-- ##################################################################
#######################################################################
#######################################################################

#########################	Profile	###############################

#######################################################################
#######################################################################
################################################################### -->

		<div role="tabpanel" class="tab-pane" id="profile">
			<div>
				<h2>
					Username: <%=userName%><br>
					Role: <%=role%><br>
					Email: <%=email%><br>
					First Name: <%=firstName%><br>
					Last Name: <%=lastName %>
				</h2>
				<br>
			</div>
		</div>
	</div>
	<!-- ##################################################################
#######################################################################
#######################################################################

#########################	JavaScript	###############################

#######################################################################
#######################################################################
################################################################### -->


	<script src="js/jquery-1.11.0.js"></script>
	<script src="js/jquery.timeago.js"></script>
	<script src="js/interact.js"></script>
	<script src="js/interact dragging.js"></script>
	<script src="js/savefloorplan.js"></script>


	<script type="text/javascript">
		var items;
		$
				.getJSON(
						"http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/events/getAllEvents",
						function(data) {//IN FOR PRODUCTION
							items = data; //IN FOR PRODUCTION
							//var temp = '[{"appID":1,"id":1,"time":1415874489932,"value":4.0},{"appID":1,"id":2,"time":1415875767627,"value":9.889431},{"appID":1,"id":3,"time":1415876233632,"value":3.0}]';//OUT FOR PRODUCTION
							//items = $.parseJSON(temp);//OUT FOR PRODUCTION
							for ( var i in items.reverse()) {
								var type;
								$
										.getJSON(
												"http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/apps/getAppByID?id="
														+ items[i].id,
												function(data) {
													type = data;
													var time = jQuery
															.timeago(new Date(
																	items[i].time));
													var element = '<a href="#" class="list-group-item"><i class="fa fa-shield fa-fw"></i> '
															+ type.eventType
															+ '<span class="pull-right text-muted small"><em>'
															+ time
															+ '</em></span></a>';
													$('#box').append(element);

												});

							}
						});
		$('.list-group-item:gt(4)')
				.hide()
				.last()
				.after(
						$('#more')
								.click(
										function() {
											var a = this;
											$(
													'.list-group-item:not(:visible):lt(5)')
													.fadeIn(
															function() {
																if ($('.list-group-item:not(:visible)').length == 0)
																	$(a)
																			.remove();
															});
											return false;
										}));
	</script>
	<!-- Device list script -->
	<script type="text/javascript">
		var devices;
		//var tempSensors = '[{"appID":1,"id":1,"time":1415874489932,"value":4.0},{"appID":1,"id":2,"time":1415875767627,"value":9.889431},{"appID":1,"id":3,"time":1415876233632,"value":3.0}]';//OUT FOR PRODUCTION
		$
				.getJSON(
						"http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/apps/getAllApps",
						function(data) {//IN FOR PRODUCTION
							devices = data; //IN FOR PRODUCTION
							//sensors = $.parseJSON(tempSensors);//OUT FOR PRODUCTION
							for ( var i in items.reverse()) {
								var id = devices[i].appID;
								var devicetype = devices[i].eventType;
								var element = '<a href="#" class="list-group-item"><i class="fa fa-mobile fa-fw"></i> '
										+ id + ' ' + devicetype + '</a>';
								$('#devices').append(element);
							}
						});
	</script>
	<!-- VIDEOPLAYER SCRIPT -->
	<script>
		var id = 1;
		//var latest = parseInt("0");

		var latest = parseInt($
				.ajax({
					type : "GET",
					url : "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/video/getLatest?id="
							+ id,
					async : false
				}).responseText);
		//Start the player
		$("#player")[0].src = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/video/getVideo?id="
				+ id + "&count=" + latest;

		$("#player")
				.bind(
						"ended",
						function() {
							latest++;
							$("#player")[0].src = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/video/getVideo?id="
									+ id + "&count=" + latest;
						});

		$("#player")
				.bind(
						"error",
						function() {
							latest--;
							$("#player")[0].src = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/video/getVideo?id="
									+ id + "&count=" + latest;
						});
	</script>



	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="js/plugins/morris/raphael.min.js"></script>
	<script src="js/plugins/morris/morris.min.js"></script>
	<script src="js/plugins/morris/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

	<!-- Custom DragNDrop JavaScript -->
	<script src="js/dragndrop.js"></script>


</body>

</html>
