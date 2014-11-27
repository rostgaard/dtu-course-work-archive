<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
		if (user == null) {
			user = new User();
			response.sendRedirect("login.jsp");
		}
		
	%>

	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#dashboard" role="tab"
			data-toggle="tab">Dashboard</a></li>
		<li><a href="#devices" role="tab" data-toggle="tab">Devices</a></li>
        <li><a href="#rules" role="tab" data-toggle="tab">Rules</a></li>
		<li><a href="#users" role="tab" data-toggle="tab">Users</a></li>

		<li style="float: right;"><a href="/Prototype2/LogoutServlet">Log
				Out</a></li>


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
											<div id="devicesRunning" class="huge"></div>
											<div>Devices Running</div>
										</div>
									</div>
								</div>
								<div class="panel-footer">
									<button type="button" class="btn btn-link btn-xs btn-block"
										data-toggle="modal" data-target="#deviceModal">View
										Details</button>
								</div>
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
								<!-- 								<a href="#"> 		-->
								<div class="panel-footer">
									<button type="button" class="btn btn-link btn-xs btn-block"
										data-toggle="modal" data-target="#eventModal">View
										Details</button>


									<!-- <span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div> -->
								</div>
								<!-- 								</a>		 -->
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-info">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-laptop  fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div id="lastLogin" style="font-size: 32px;"></div>
											<div>Last Login</div>
										</div>
									</div>
								</div>
								<!-- <a href="#"> -->
								<div class="panel-footer">
									<button type="button" class="btn btn-link btn-xs btn-block"
										data-toggle="modal" data-target="#lastLoginModal">View
										Details</button>


									<!-- 						<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div> -->
								</div>
								<!-- </a> -->
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-red">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-lock fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div id=securityLevel class="huge"></div>
											<div>Security System</div>
										</div>
									</div>
								</div>
								<div class="panel-footer">
									<button type="button" class="btn btn-link btn-xs btn-block"
										data-toggle="modal" data-target="#securityLevelModal">Activate/Deactive
										System</button>

								</div>
							</div>
						</div>
					</div>
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-8">
							<div class="panel panel-default">
								<!-- /.panel-heading -->

								<div class="panel-body">
									<div id="cameraMap">
									
										
									
									<!-- 
										<button type="button" class="draggable" data-toggle="modal"
											data-target="#jesperModal">1</button>
										<button type="button" class="draggable" data-toggle="modal"
											data-target="#jesperModal">2</button>
										<button type="button" class="draggable" data-toggle="modal"
											data-target="#jesperModal">3</button> -->

										<div id="floorplan" class="floorplan">
											<img src="floor_plan_example.png" width="40%" height="40%" />
											<img src="floor_plan_example.png" width="40%" height="40%" />
										</div>

									</div>

									<!-- 									<div class="col-md-4">
										<div class="panel-body">
											<button onclick="saveFloorplan()"
												class="btn btn-primary btn-block" id="save">Save</button>
										</div>
									</div> -->



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

				<!--  play video file in modal window -->
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


				<!--Show devices in modal -->
				<div class="modal fade" id="deviceModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Devices</h4>
							</div>
							<div class="modal-body">
								<h1>
									Device 1<br> Device 2<br> Device 3
								</h1>
							</div>
						</div>
					</div>
				</div>

				<!--Show events in modal -->
				<div class="modal fade" id="eventModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Recent Events</h4>
							</div>
							<div class="modal-body">
								<h1>
									Event 1<br> Event 2<br> Event 3
								</h1>
							</div>
						</div>
					</div>
				</div>


				<!-- Show login in modal -->
				<div class="modal fade" id="lastLoginModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Last Login</h4>
							</div>
							<div class="modal-body">
								<h1>Last Login: now</h1>
							</div>
						</div>
					</div>
				</div>

				<!-- security level in modal -->
				<div class="modal fade" id="securityLevelModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Security Level</h4>
							</div>
							<div class="modal-body">
								<div class="securityStatus"></div>

								<div>
									Set Security Level
									<div class="btn-group-lg" id="securityLevelGroup" role="group"
										aria-label="...">
										<button type="button" id="1" class="btn btn-default">Level
											1</button>
										<button type="button" id="2" class="btn btn-default">Level
											2</button>
										<button type="button" id="3" class="btn btn-default">Level
											3</button>
										<button type="button" id="0" class="btn btn-default">Deactivate
											System</button>
									</div>
								</div>

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
						 @author s124255
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
								<div class="panel-body">
									<div class="list-group" id="devs"></div>
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
														placeholder="Device Mac">
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
												<div class="col-sm-16">
													<div class="checkbox" style="text-align:center"><label><input type="checkbox" id="light" value="1" >Flashlight</label></div>
													<div class="checkbox" style="text-align:center"><label><input type="checkbox" id="camera" value="1">Camera</label></div>
													<div class="checkbox" style="text-align:center"><label><input type="checkbox" id="sound" value="1">Sound</label></div>		
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<button type="button" id="submit"
														class="btn btn-lg btn-success btn-block" onclick="confDev()">Configure</button>
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
		<div class="modal fade" id="deviceInfoModal" tabindex="-1" role="dialog"
		aria-labelledby="deviceInfoModal" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body" align=center>
				
				<p class="text" id="macDevice"></p>
				<p class="text" id="idDevice"></p>
				<p class="text" id="accAct"></p>
				<p class="text" id="soundAct"></p>
				<p class="text" id="lightAct"></p>
				
				<script>
				
				function deviceInfo(mac){
				
				$('#accAct').html("Accelerometer is deactivated");
				$('#soundAct').html("Sound is deactivated");
				$('#lightAct').html("Light is deactivated");
					var data;
					var devices;
					var URL = webServerPath+"/apps/getAppByMac?mac="+mac;
					$.ajax({
		     		type: "GET",
		     		url: URL,
		     		data: data,
		     		success: function(data) {
		     			$('#macDevice').html("Device mac: " + mac);
		     			devices = data;
		     			//var temp = '[{"eventType":"ACCELEROMETER","events":[],"id":1,"mac":"BC:DS:37:SD:E3:7E","status":false},{"eventType":"FLASH_LIGHT","events":[],"id":2,"mac":"B1:DS:37:AD:G3:7E","status":true},{"eventType":"PLAY_SOUND","events":[],"id":89,"mac":"B1:DS:33:AD:E3:7E","status":true}]';//OUT FOR PRODUCTION
						//devices = $.parseJSON(temp);
		     				for(var i in devices)
							{
								$('#idDevice').html("Device ID: " + devices[i].id);
								var status = devices[i].status;
								var eventTy = devices[i].eventType;
								
								if(status){
									if(eventTy =="ACCELEROMETER"){
										$('#accAct').html("Accelerometer is active");
									}
									if(eventTy =="PLAY_SOUND"){
										$('#soundAct').html("Sound is active");
									}
									if(eventTy =="FLASH_LIGHT"){
										$('#lightAct').html("Light is active");
									}
								}
							};
							//onclose del der sætter mac i configure felt til configurering	     		   	
		     			}
		   			});
		   		}
		   	
					</script>
				</div>
			</div>
		</div>
	</div>
		<!-- /#wrapper -->

		<!-- ##################################################################
#######################################################################
#######################################################################

#########################	Rules		###############################

#######################################################################
#######################################################################
################################################################### -->


		<div role="tabpanel" class="tab-pane" id="rules">
			<div class="tab-pane" id="rules">
				<div id="wrapper">
					<div id="page-wrapper">
						<div class="row">
							<div class="col-lg-12">
								<h1 class="page-header">Rule Management</h1>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<div class="panel panel-default">
									<div class="panel-heading">
										<i class="fa fa-filter fa-fw"></i> Rules
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<div class="list-group" id="rules"></div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												<i class="fa fa-filter fa-fw"></i>New Rule
											</div>
											<div class="panel-body">
												<div class="form-group">
													<div class="col-sm-12">
														<input type="text" class="form-control" id="rulename"
															placeholder="Rule description">
													</div>
												</div>
												<div class="form-group">
                                                  <div class="col-sm-12">
                                                      <label for="eventTrigger">Responds to event </label>
                                                      <select id="eventTrigger" name="Even Trigger">
                                                        <option value="ACCELEROMETER">Door open</option>
                                                        <option value="HUMIDITY">Humitidy threshold</option>
                                                        <option value="TEMPERATURE">Temperature threshols</option>
                                                        <option value="USERALERT">User alert</option>
                                                      </select>
                                                    </div>
													
												</div>
												<div class="form-group">
													<div class="col-sm-4">
														<button type="button" id="submit"
															class="btn btn-lg btn-success btn-block"
															onclick="addRule()">Add new rule</button>
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
		</div>
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
							<div class="col-lg-6">
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
							<div class="col-lg-6">
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												<i class="fa fa-user fa-fw"></i>New User
											</div>
											<div class="panel-body">
												<div class="form-group">
													<div class="col-sm-12">
														<input type="text" class="form-control" id="username"
															placeholder="Username"/>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-12">
														<input type="email" class="form-control" id="email"
															placeholder="Email"/>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-12">
														<input type="text" class="form-control" id="firstname"
															placeholder="Firstname"/>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-12">
														<input type="text" class="form-control" id="lastname"
															placeholder="Lastname" />
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-12">
														<select id="role" name="Role">
															<option value="1">Viewer</option>
															<option value="2">Manager</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-12">
														<input type="password" class="form-control" id="password"
														placeholder="Password" />
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-4">
														<button type="button" id="submit"
															class="btn btn-lg btn-success btn-block"
															data-toggle="modal" data-target="#addUserModal" onclick="addUser()">Add User</button>
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
		</div>
	</div>
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
		aria-labelledby="addUserModal" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body" align=center>
				
				<p class="text" id="uname"></p>
				<p class="text" id="mail"></p>
				<p class="text" id="fname"></p>
				<p class="text" id="lname"></p>
				<p class="text" id="role"></p>
				<p class="text" id="pass"></p>
				<p class="text" id="sucess"></p>
				
				<script>
				
				function addUser(){
					var username,firstname,lastname,email,role,password;
				
					username = document.getElementById('username').value;
					email = document.getElementById('email').value;
					firstname = document.getElementById('firstname').value;
					lastname = document.getElementById('lastname').value;
					role = document.getElementById('role').value;
					if(role == 1){
						role = "VIEWER"
					}
					else{
						role="MANAGER"
					}
					password = document.getElementById('password').value;
					
					var URL = webServerPath+"/users/addUser?userName="+username+"&email="+email+"&firsName="+firstname+"&lastName="+lastname+"&role="+role+"&password="+password;
					$.ajax({
		    		type: "GET",
		    		url: URL,
		     		data: data,
		     		success: function(data) {
					
					$('#uname').html("Username: "+username);
					$('#mail').html("Email: "+email);
					$('#fname').html("Firstname: "+firstname);
					$('#lname').html("Lastname: "+lastname);
					$('#role').html("Role: "+role);
					$('#pass').html("Password: "+password);
					$('#sucess').html("User added to the system");
					reloadUsers();
			}
		});
				}
					</script>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog"
		aria-labelledby="userInfoModal" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body" align=center>
				
				<p class="text" id="uName"></p>
				<p class="text" id="eMail"></p>
				<p class="text" id="fName"></p>
				<p class="text" id="lName"></p>
				<p class="text" id="role"></p>
				<p class="text" id="pass"></p>
				
				<script>
				
				function getUser(user){
				//$('#uName').html("Username: "+user);	
				
				var userData = [];
				var URL = webServerPath+"/users/getUserByUserName?userName="+user;
				$.ajax({
		    	type: "GET",
		     	url: URL,
		     	success: function(user) {
	
				//var usertemp = '[{"userName":"Perminator","email":"pr@mail.com","firstName":"Per","lastName":"Kristansen","role":"VIEWER","password":"pertheman"},{"userName":"TomCat","email":"tom@mail.com","firstName":"Tom","lastName":"Catgat","role":"VIEWER","password":"awesomeo"},{"userName":"Charleton","email":"chr@mail.com","firstName":"Charles","lastName":"Tonnisen","role":"VIEWER","password":"tonnibonde"}]';//OUT FOR PRODUCTION
				//var userItems = $.parseJSON(usertemp);//OUT FOR PRODUCTION
				//var user = $.parseJSON(userData);
					
				$('#eMail').text("Email: "+user.email);
				$('#fName').text("Firstname: "+user.userName);
				$('#lName').text("Lastname: "+user.lastName);
				$('#role').text("Role: "+user.role);
				$('#pass').text("Password: "+user.password);
			
			}
		});
				
				}
				</script>
			</div>
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

	<script>
	/*
		@author s124259, Jesper Mark
		Dynamically adding sensor buttons to cameraMap in dashboard
		based on devices from webrservice request
	*/
	
	var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype2/rest";
	//var webServerPath = "http://localhost:8080/Prototype1/rest";
	
	$( document ).ready(function() {
		var data;
		var URL = webServerPath+"/apps/getDevicesWithCamera";
			$.ajax({
			     type: "GET",
			     url: URL,
			     data: data,
			     success: function(data) {
					var devices = $.parseJSON(data);
					for(var i in devices){
						var mac = devices[i].mac;
						 $('<button id="' + mac + '"' + ' type="button" class="draggable" data-toggle="modal" data-target="#jesperModal" />').text(+i + 1).appendTo('#cameraMap');
					}
			   	 }
			 });
	});	
	</script>


	<script>
	(function poll(){
		   setTimeout(function(){
		      $.ajax({ url: "server", success: function(data){
		        //Update your dashboard gauge
		        salesGauge.setValue(data.value);

		        //Setup the next poll recursively
		        poll();
		      }, dataType: "json"});
		  }, 30000);
		})();
	</script>

	<script>

	/*
		@Author s124259
		Refreshing page every 60 seconds
	*/
		setTimeout(function(){
			   window.location.reload(1);
			}, 60000);
	</script>

	<script>
	/*
	@Author s124259
	Getting device count with webservice call
	on page load and update #devicesRunning class with data.
	*/

	$( document ).ready(function() {
		var URL = webServerPath+"/apps/getDeviceCount";
		var data;
		$.ajax({
		     type: "GET",
		     url: URL,
		     data: data,
		     success: function(data) {
		          $('#devicesRunning').html(data);
		     }
		   });
	});	
	</script>

	<script>
	/*
		@Author s124259
		Getting lastLogin based on username with webservice call
		on page load and update #lastLogin class with data.
		Using JQuery timeago plugin for formatting
	*/
	$( document ).ready(function() {
		var baseURI = webServerPath+"/users/getLastLoginByUserName?userName=";
		
		var buildURL = baseURI + userName;
		var data;
		$.ajax({
		     type: "GET",
		     url: buildURL,
		     data: data,
		     success: function(data) {
		          $('#lastLogin').html(jQuery.timeago(new Date(data)));
		     }
		   });		
	});	
	</script>

	<script>
	/*
		@Author s124259
		Getting security level with webservice call
		on page load and update #securityLevel class with data
	*/
	$( document ).ready(function() {
		var URL = webServerPath+"/rules/getSecurityLevel";
		$.ajax({
		     type: "GET",
		     url: URL,
		     data: data,
		     success: function(data) {
		    	 if(data>0){
		          $('#securityLevel').html("Level " + data);
		    	 }
		    	 else{
		    		 $('#securityLevel').html("Deactivated");
		    	 }
		     }
		   });	
	});	
	</script>

	<script type="text/javascript">
	
	/** @author s124255
		*/
	var data;
	var URL = webServerPath+"/apps/getAllEvents";
		$.ajax({
		     type: "GET",
		     url: URL,
		     data: data,
		     success: function(data) {
		    var items = data;
		          for(var i in items.reverse())
					{
					var type = items[i].eventType;
					var time = jQuery.timeago(new Date(items[i].time));
					var element = '<a href="#" class="list-group-item"><i class="fa fa-shield fa-fw"></i> '+type.replace("SHAKE","Door moved").replace("PLAY_SOUND","Sound played")+'<span class="pull-right text-muted small"><em>'+time+'</em></span></a>';
					$('#box').append(element);
					}
				}
		     });
	/** @author s124255
		*/	     
	var devData;
	var URL = webServerPath+"/apps/getDevices";
		$.ajax({
		     type: "GET",
		     url: URL,
		     data: devData,
		     success: function(devData) {
		
		//var temp = '[{"eventType":"ACCELEROMETER","events":[],"id":1,"mac":"BC:DS:37:SD:E3:7E","status":true},{"eventType":"FLASH_LIGHT","events":[],"id":2,"mac":"B1:DS:37:AD:G3:7E","status":true},{"eventType":"FLASH_LIGHT","events":[],"id":3,"mac":"B1:DS:33:AD:E3:7E","status":true}]';//OUT FOR PRODUCTION
		//var devItems = $.parseJSON(temp);//OUT FOR PRODUCTION
		var devItems = devData;//IN FOR PRODUCTION
		
		for(var i in devItems)
		{
		var devElement = '<a href="#" data-toggle="modal" data-target="#deviceInfoModal" class="list-group-item" onclick="deviceInfo(\'' + devItems[i].mac + '\')"><i class="fa fa-mobile fa-fw"></i> '
				+ devItems[i].mac
				+ '</a>';
				$('#devs').append(devElement);
				};
			}
			
		});
		
	/** @author s124255
		*/
		function confDev(){
		
			var lightAppStatus = false;
			var camAppStatus = false;
			var soundAppStatus = false;
			var app = appData;
			var devMac = document.getElementById('mac').value;
			if(document.getElementById('light').value=="1"){
				lightAppStatus = true;
			}
			if(document.getElementById('camera').value=="1"){
				camAppStatus = true;
			}
			if(document.getElementById('sound').value=="1"){
				soundAppStatus = true;
			}	
			
			$.ajax({
		    	type: "PUT",
		     	url: URL = webServerPath+"/apps/update?mac="+devMac+"&eventType='PLAYSOUND'&status="+soundAppStatus+"",

		    });
			
			$.ajax({
		    	type: "PUT",
		     	url: webServerPath+"/apps/update?mac="+devMac+"&eventType='START_VIDEO_RECORDING'&status="+camAppStatus+"",
		     
		    });
			
			$.ajax({
		    	type: "PUT",
		     	url: webServerPath+"/apps/update?mac="+devMac+"&eventType='FLASH_LIGHT'&status="+lightAppStatus+"",
		     	
		    });
				
		}
		
		
		
	</script>

		<!-- 
		 OnClick Event on whole button group (Security Level Buttons)
		 @s124259
	     source: http://stackoverflow.com/questions/9262827/twitter-bootstrap-onclick-event-on-buttons-radio
	  -->
	<script>		
		$('#securityLevelGroup button').click(function() {
		    $(this).addClass('active').siblings().removeClass('active');
		    var baseURI = webServerPath+"/rules/setSecurityLevel?level=";
		    var level = $(this).attr('id');
		    var URL = baseURI + level;		  
		    
		    if(level > 0){
		    	$( "div.securityStatus" ).replaceWith( "<h2>Security Level is now: " + level + "</h2>" );	
		    }
		    else{
		    	$( "div.securityStatus" ).replaceWith( "<h2>Home Security System is now Deactivated </h2>" );
		    }
		    
		    $.ajax({
		    	  type: "PUT",
		    	  url: URL
		    	});
		    
			document.location.reload();

			});
	</script>

	<!-- USER MANAGEMENT SCRIPTS -->
	
	<!-- GetUsers -->
	
	<script>
	
	function reloadUsers() {
		var userData;
		var URL = webServerPath+"/users/getUsers";
		$.ajax({
		     type: "GET",
		     url: URL,
		     data: userData,
		     success: function(userData) {
		
		//var usertemp = '[{"userName":"Perminator","email":"pr@mail.com","firstName":"Per","lastName":"Kristansen","role":"VIEWER","password":"pertheman"},{"userName":"TomCat","email":"tom@mail.com","firstName":"Tom","lastName":"Catgat","role":"VIEWER","password":"awesomeo"},{"userName":"Charleton","email":"chr@mail.com","firstName":"Charles","lastName":"Tonnisen","role":"VIEWER","password":"tonnibonde"}]';//OUT FOR PRODUCTION
		//var userItems = $.parseJSON(usertemp);//OUT FOR PRODUCTION
		var userItems = userData;
		
		$('#user').empty();
		for(var i in userItems)
		{
		var userElement = '<a href="#" data-toggle="modal" data-target="#userInfoModal" class="list-group-item" onclick="getUser(\'' + userItems[i].userName + '\')"><i class="fa fa-user fa-fw"></i> '
				+ userItems[i].userName
				+ '</a>';
				$('#user').append(userElement);
				};
			}
		});
	}
	
	reloadUsers();
	</script>
	
		<script src="js/jquery-1.11.0.js"></script>
		<script src="js/jquery.timeago.js"></script>
		<script src="js/interact.js"></script>
		<script src="js/interact dragging.js"></script>

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
