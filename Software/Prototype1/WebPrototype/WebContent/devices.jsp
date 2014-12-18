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
      <li><a href="/Prototype245/Forward?ref=dashboard" >Dashboard</a></li>
    <li class="active"><a href="/Prototype245/Forward?ref=devices">Devices</a></li>
    <li><a href="/Prototype245/Forward?ref=rules">Rules</a></li>
    <li><a href="/Prototype245/Forward?ref=users">Users</a></li>
    <li><a href="/Prototype245/Forward?ref=log">Event Log</a></li>


    <li style="float: right;"><a href="/Prototype245/LogoutServlet">Log
        Out</a></li>
	 <li style="float: right;"><a href="javascript:void(0);" onclick="javascript:introJs().start();">Demo</a></li>

</ul>

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
                    <div class="panel panel-default" data-step="1" data-intro="View all devices associated with your system.">
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
                            <div class="panel panel-default" data-step="2" data-intro="Configure a device by using it's MAC address. Marking a sensor/actuator and pressing submit will make it available on the specific device">
                                <div class="panel-heading">
                                    <i class="fa fa-mobile fa-fw"></i>Configure Device
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <input type="text" class="form-control" id="deviceMac"
                                                   placeholder="Device Mac">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <input type="text" class="form-control" id="deviceName"
                                                   placeholder="Device Name">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group" style="margin-auto: 0;">
                                        <div class="col-sm-16" style="margin-left: 16px;">
                                        	<p> Mark the sensors and actuator which should be availeble on the device </p>
                                            <div class="checkbox"><label><input
                                                    type="checkbox" id="light">Flashlight</label></div>
                                            <div class="checkbox"><label><input
                                                    type="checkbox" id="camera">Camera</label></div>
                                            <div class="checkbox"><label><input
                                                    type="checkbox" id="sound">Sound</label></div>
                                            <div class="checkbox"><label><input
                                                    type="checkbox" id="userAlerts">User Alerts</label></div>
                                            <div class="checkbox"><label><input
                                                    type="checkbox" id="moveSens">Movement Sensor</label></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <button type="button" id="submit"
                                                    class="btn btn-lg btn-success btn-block" onclick="confDev()">
                                                Submit
                                            </button>
                                        </div>
                                    </div>
                                    <p class="text" id="condev"></p>
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
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
            </div>
            <div class="modal-body" align=center>

                <p class="text" id="macDevice"></p>

                <p class="text" id="nameDevice"></p>

                <p class="text" id="camAct"></p>

                <p class="text" id="soundAct"></p>

                <p class="text" id="lightAct"></p>
                
                <p class="text" id="userAlertAct"></p>
                
                <p class="text" id="movementSens"></p>
                
                
                <script>
var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest";
var activeMac;
                    function deviceInfo(mac) {
						activeMac = mac;
                        $('#camAct').html("This device does not have a active camera.");
                        $('#soundAct').html("This device does not have a active microphone.");
                        $('#lightAct').html("This device does not have a active flashlight.");
                        $('#userAlertAct').html("This device does not have user alerts activated.");
                        $('#movementSens').html("This device does not have a movement sensor activated.");
                        var data;
                        var devices;
                        var URL = webServerPath + "/apps/getApps?mac=" + mac;
                        $.ajax({
                            type: "GET",
                            url: webServerPath + "/devices/getDevice?mac=" + mac,
                            data: data,
                            success: function (data) {
                                var dev = data
                                if(dev != null){
                                $('#nameDevice').html(dev.name);
                                }
                                else{
                                 $('#nameDevice').html("No name for this device");
                                }
                            }
                        });
                        $.ajax({
                            type: "GET",
                            url: URL,
                            data: data,
                            success: function (data) {
                                $('#macDevice').html("Device mac: " + mac);
                                devices = data;
                                for (var i in devices) {
                                    var status = devices[i].status;
                                    var eventTy = devices[i].eventType;
                                    var id = devices[i].id;

                                    if (status) {
                                        if (eventTy == "STARTVIDEORECORDING'") {
                                            $('#camAct').html("Camera is available - Sensor ID: " + devices[i].id);
                                        }
                                        if (eventTy == "PLAYSOUND") {
                                            $('#soundAct').html("Sound is available - Sensor ID: " + devices[i].id);
                                        }
                                        if (eventTy == "FLASHLIGHT") {
                                            $('#lightAct').html("Light is available - Sensor ID: " + devices[i].id);
                                        }
                                        if (eventTy == "USERALERT") {
                                            $('#userAlertAct').html("User Alert is available - Sensor ID: " + devices[i].id);
                                        }
                                         if (eventTy == "ACCELEROMETER") {
                                            $('#movementSens').html("Movement sensor is available - Sensor ID: " + devices[i].id);
                                        }

                                    }
                                }
                                //;???
                            }
                        });
                    }
					$(function() {

                    $('#confMac').click(function setMacField() {
                        $('#deviceMac').val(activeMac);
                        $('#deviceInfoModal').modal('hide');
                    });
                                    
					});
                    

                </script>


               <button type="button" id="confMac" class="button">Configure</button>

            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

	<!--
	@author s124259
	-->
	<div class="panel-footer">
	    <div style="width: 2%; margin: 0 auto;">
	        v0.1.2
	    </div>
	    <div style="width: 19%; margin: 0 auto;">
	        <a href="https://docs.google.com/document/d/1fRxEcnT6e1gi_WQDbheRzWXXZbP5OenC2mTVpi9qMDk/pub"
	           target="_blank">Quick Start Guide</a>
	        |
	        <a href="https://docs.google.com/document/d/1I5P_2qIg8SIz7wvdPGlaI-uMimTQXew2n0-tSskPx6s/pub"
	           target="_blank">Handbook</a>
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
    var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest";
    /** @author s124255
     device tab
     */
    function confDev() {
    var data;
    var md = document.getElementById('deviceMac').value;
    var nd = document.getElementById('deviceName').value;
    
    if(md != ""){
    
    if(nd != ""){
    $.ajax({
          type: "GET",
          url: webServerPath + "/devices/addDeviceName?mac=" + md +"&name="+nd,
          data: data,
          success: function (data){
          if(data == null){
          	$.ajax({
          		type: "PUT",
          		url: webServerPath + "/devices/updateDeviceName?mac=" + md+"&name="+nd,
          		data: data,
          		succes: function (data) {  			
          		}
        	});
          }
          }
          
        });
        
      }

        var lightAppStatus = false;
        var camAppStatus = false;
        var soundAppStatus = false;
        var userAlertsStatus = false;
        var movementSensStatus = false;
       
        var devMac = md;
        if (document.getElementById('light').value == true) {
            lightAppStatus = true;
        }
        if (document.getElementById('camera').value == true) {
            camAppStatus = true;
        }
        if (document.getElementById('sound').value == true) {
            soundAppStatus = true;
        }
        if (document.getElementById('userAlerts').value == true) {
            userAlertsStatus = true;
        }
        if (document.getElementById('moveSens').value == true) {
            movementSensStatus = true;
        }

        $.ajax({
            type: "PUT",
            url: URL = webServerPath + "/apps/updateApp?mac=" + devMac + "&eventType=PLAYSOUND&status=" + soundAppStatus + "",
        });

        $.ajax({
            type: "PUT",
            url: webServerPath + "/apps/updateApp?mac=" + devMac + "&eventType=STARTVIDEORECORDING&status=" + camAppStatus + "",
        });

        $.ajax({
            type: "PUT",
            url: webServerPath + "/apps/updateApp?mac=" + devMac + "&eventType=FLASHLIGHT&status=" + lightAppStatus + "",
        });
        
        $.ajax({
            type: "PUT",
            url: webServerPath + "/apps/updateApp?mac=" + devMac + "&eventType=USERALERT&status=" + userAlertsStatus + "",
        });
        
        $.ajax({
            type: "PUT",
            url: webServerPath + "/apps/updateApp?mac=" + devMac + "&eventType=ACCELEROMETER&status=" + movementSensStatus + "",
        });
        
        	$('#condev').html("Device Configured");
        
        }
        
        else{
        	$('#condev').html("Error in input");
        }

    }

    /** @author s124255
         device tab
         */
        function reloadDevs() {
            var data;
            var URL = webServerPath + "/apps/getDevices";
            $.ajax({
                type: "GET",
                url: URL,
                data: data,
                success: function (data) {
                    var devItems = data;

                    $('#devs').empty();
                    for (var i in devItems) {
                        var printDevName = devItems[i].mac;
						$.ajax({
                			type: "GET",
                			url: webServerPath + "/devices/getDevice?mac="+printDevName,
                			data: data,
                			success: function (data) {
                			 var dev = data
                    			if(dev != null){
                    				printDevName = dev.name;
                    			}
                    		}
                    	});

                        var devElement = '<a href="#" data-toggle="modal" data-target="#deviceInfoModal" class="list-group-item" onclick="deviceInfo(\'' + devItems[i].mac + '\')"><i class="fa fa-mobile fa-fw"></i> '
                                + printDevName
                                + '</a>';
                        $('#devs').append(devElement);
                    };
                }

            });
        }
        reloadDevs();
</script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- "Guided Tour" Plugin -->
<script src="js/intro.js"></script>

</body>

</html>
