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
                                            <div class="checkbox" style="text-align:center"><label><input
                                                    type="checkbox" id="light" value="1">Flashlight</label></div>
                                            <div class="checkbox" style="text-align:center"><label><input
                                                    type="checkbox" id="camera" value="1">Camera</label></div>
                                            <div class="checkbox" style="text-align:center"><label><input
                                                    type="checkbox" id="sound" value="1">Sound</label></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <button type="button" id="submit"
                                                    class="btn btn-lg btn-success btn-block" onclick="confDev()">
                                                Configure
                                            </button>
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

                <p class="text" id="nameDevice"></p>

                <p class="text" id="camAct"></p>

                <p class="text" id="soundAct"></p>

                <p class="text" id="lightAct"></p>

                <script>
var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest";
                    function deviceInfo(mac) {

                        $('#accAct').html("Camera is not available");
                        $('#soundAct').html("Sound is not available");
                        $('#lightAct').html("Light is not available");
                        var data;
                        var devices;
                        var URL = webServerPath + "/apps/getApps?mac=" + mac;
                        $.ajax({
                            type: "GET",
                            url: webServerPath + "/devices/getDeviceName?mac" + mac,
                            data: data,
                            success: function (data) {
                                var dev = data
                                $('#nameDevice').html(dev.name);
                            }
                        });
                        $.ajax({
                            type: "GET",
                            url: URL,
                            data: data,
                            success: function (data) {
                                $('#macDevice').html("Device mac: " + mac);
                                devices = data;
                                //var temp = '[{"eventType":"ACCELEROMETER","events":[],"id":1,"mac":"BC:DS:37:SD:E3:7E","status":false},{"eventType":"FLASHLIGHT","events":[],"id":2,"mac":"B1:DS:37:AD:G3:7E","status":true},{"eventType":"PLAYSOUND","events":[],"id":89,"mac":"B1:DS:33:AD:E3:7E","status":true}]';//OUT FOR PRODUCTION
                                //devices = $.parseJSON(temp);
                                for (var i in devices) {
                                    var status = devices[i].status;
                                    var eventTy = devices[i].eventType;

                                    if (status) {
                                        if (eventTy == "STARTVIDEORECORDING'") {
                                            $('#camAct').html("Camera is available");
                                        }
                                        if (eventTy == "PLAYSOUND") {
                                            $('#soundAct').html("Sound is available");
                                        }
                                        if (eventTy == "FLASHLIGHT") {
                                            $('#lightAct').html("Light is available");
                                        }
                                    }
                                }
                                ;
                            }
                        });
                    }

                    function setMacField(mac) {
                        $('#DeviceMac').html(mac)
                    }

                </script>


                <button type="button" id="submit" class="button" data-toggle="modal" data-target="#eventInfoModal"
                        onclick="setMacField(\''$('#macDevice').text()'\');">Configure
                </button>

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

        var lightAppStatus = false;
        var camAppStatus = false;
        var soundAppStatus = false;
        //var app = appData;
        var devMac = document.getElementById('mac').value;
        if (document.getElementById('light').value == "1") {
            lightAppStatus = true;
        }
        if (document.getElementById('camera').value == "1") {
            camAppStatus = true;
        }
        if (document.getElementById('sound').value == "1") {
            soundAppStatus = true;
        }

        $.ajax({
            type: "PUT",
            url: URL = webServerPath + "/apps/update?mac=" + devMac + "&eventType='PLAYSOUND'&status=" + soundAppStatus + "",

        });

        $.ajax({
            type: "PUT",
            url: webServerPath + "/apps/update?mac=" + devMac + "&eventType='STARTVIDEORECORDING''&status=" + camAppStatus + "",

        });

        $.ajax({
            type: "PUT",
            url: webServerPath + "/apps/update?mac=" + devMac + "&eventType='FLASHLIGHT'&status=" + lightAppStatus + "",

        });

    }

    /** @author s124255
         device tab
         */
        function reloadDevs() {
            var devData;
            var URL = webServerPath + "/apps/getDevices";
            $.ajax({
                type: "GET",
                url: URL,
                data: devData,
                success: function (devData) {

                    //var temp = '[{"eventType":"ACCELEROMETER","events":[],"id":1,"mac":"BC:DS:37:SD:E3:7E","status":true},{"eventType":"FLASHLIGHT","events":[],"id":2,"mac":"B1:DS:37:AD:G3:7E","status":true},{"eventType":"FLASHLIGHT","events":[],"id":3,"mac":"B1:DS:33:AD:E3:7E","status":true}]';//OUT FOR PRODUCTION
                    //var devItems = $.parseJSON(temp);//OUT FOR PRODUCTION
                    var devItems = devData;//IN FOR PRODUCTION

                    $('#devs').empty();
                    for (var i in devItems) {
                        var printDevName = devItems[i].mac;


                        var devElement = '<a href="#" data-toggle="modal" data-target="#deviceInfoModal" class="list-group-item" onclick="deviceInfo(\'' + devItems[i].mac + '\')"><i class="fa fa-mobile fa-fw"></i> '
                                + printDevName
                                + '</a>';
                        $('#devs').append(devElement);
                    }
                    ;
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
