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
          
    <!-- Add IntroJs styles -->
    <link href="css/introjs.css" rel="stylesheet">
    
<!--     DataTables CSS
	<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css"> -->
	
	    <!-- DataTable Bootstrap styles -->
    <link href="http://cdn.datatables.net/plug-ins/9dcbecd42ad/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
	
	
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->

</head>

<body>
<%

/*
 *		@author s124259
 *		Jesper Mark
 */
//allow access only if session attribute "user" is set beforehand
User user = (User) session.getAttribute("user");
if (user == null) {
user = new User(); // dirty trick to avoid nullpointer exception when DOM is created (code is put here because exception is only thrown if user is not authed)
response.sendRedirect("login.jsp");
}

%>

<ul class="nav nav-tabs" role="tablist" data-step="1" data-intro="Browse the different pages using the top tab">
    <li><a href="/Prototype245/Forward?ref=dashboard">Dashboard</a></li>
    <li><a href="/Prototype245/Forward?ref=devices">Devices</a></li>
    <li><a href="/Prototype245/Forward?ref=rules">Rules</a></li>
    <li><a href="/Prototype245/Forward?ref=users">Users</a></li>
    <li class="active"><a href="/Prototype245/Forward?ref=log">Event Log</a></li>
    <li style="float: right;"><a href="/Prototype245/LogoutServlet">Log Out</a></li>
    <li style="float: right;"><a href="javascript:void(0);" onclick="javascript:introJs().start();">Demo</a></li>
</ul>


<div role="tabpanel" class="tab-pane" id="log">
    <div class="tab-pane">
        <div id="wrapper">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Event Log</h1>
                    </div>
                </div>
                <div class="row">
                    
                    <!--DATA TABLE  --> 
                    <div class="table-responsive" data-step="1" data-intro="This table provides a history of all events fired in the past.">      
	                    <table id="example" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
						    <thead>
						        <tr>
						            <th>Source App ID</th>
						            <th>Event Type</th>
						            <th>Event ID</th>
						            <th>Time</th>
						            <th>Value</th>				   
						        </tr>
						    </thead>
						 
						    <tfoot>
						        <tr>
						            <th>Source App ID</th>
						            <th>Event Type</th>
						            <th>Event ID</th>
						            <th>Time</th>
						            <th>Value</th>	
						        </tr>
						    </tfoot>					 
						</table>
					</div>
	            </div>
            </div>
        </div>
    </div>
</div>

<!--
@author s124259
-->
<div class="panel-footer">
    <div style="width: 2%; margin: 0 auto;">
        v0.1.2
    </div>
    <div style="width: 19%; margin: 0 auto;">
        <a href="https://docs.google.com/document/d/1fRxEcnT6e1gi_WQDbheRzWXXZbP5OenC2mTVpi9qMDk/pub" target="_blank">Quick
            Start Guide</a>
        |
        <a href="https://docs.google.com/document/d/1I5P_2qIg8SIz7wvdPGlaI-uMimTQXew2n0-tSskPx6s/pub" target="_blank">Handbook</a>
        |
        <a href="https://docs.google.com/spreadsheets/d/1xKfce3ZwyUpaoCnxguzYpSRH6URlFPQwEO1oNtJMgug/pubhtml?gid=0&single=true"
           target="_blank">Known Bugs</a>
    </div>
    <div style="width: 13%; margin: 0 auto;">
        <a href="https://docs.google.com/spreadsheets/d/1xKfce3ZwyUpaoCnxguzYpSRH6URlFPQwEO1oNtJMgug/pubhtml?gid=0&single=true"
           target="_blank">Device App</a>
        |
        <a href="https://docs.google.com/spreadsheets/d/1xKfce3ZwyUpaoCnxguzYpSRH6URlFPQwEO1oNtJMgug/pubhtml?gid=0&single=true"
           target="_blank">User Alarm App</a>
    </div>
</div>


<script>
$( document ).ready(function() {
	$.ajax({
	    type: "GET",
	    dataType: 'json',
	    url: "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/events/getAllEvents",
	    success: function (data) {
	    	$('#example').dataTable( {
	    		"data": data,
	            "columns": [
	                { "data": "appID" },
	                { "data": "eventType" },
	                { "data": "id" },
	                { "data": "time"},           
	                { "data": "value" }
	            ]
	        } );
	    }
	});
});
</script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

    <!-- "Guided Tour" Plugin -->
    <script src="js/intro.js"></script>
    
    	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="js/jquery.dataTables.js"></script>
	
	<!--DataTable Bootstrap style  -->
	<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/plug-ins/9dcbecd42ad/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	
	    
    
</body>

</html>
