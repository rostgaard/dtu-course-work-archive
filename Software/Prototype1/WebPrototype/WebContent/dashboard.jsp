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

    <title>Smart Home Security - Dashboard</title>

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

    <!-- Add IntroJs styles -->
    <link href="css/introjs.css" rel="stylesheet">

    <link href="css/bootstrap.min.css" rel="stylesheet">

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

<ul class="nav nav-tabs" role="tablist" data-step="1" data-intro="Browse the different pages using the top tab">
    <li class="active"><a href="#">Dashboard</a></li>
    <li><a href="/Prototype245/Forward?ref=devices">Devices</a></li>
    <li><a href="/Prototype245/Forward?ref=rules">Rules</a></li>
    <li><a href="/Prototype245/Forward?ref=users">Users</a></li>
    <li><a href="/Prototype245/Forward?ref=log">Event Log</a></li>
    <li style="float: right;"><a href="/Prototype245/LogoutServlet">Log Out</a></li>
    <li style="float: right;"><a href="javascript:void(0);" onclick="javascript:introJs().start();" data-step="6" data-intro="On every page you can get a detailed tour">Demo</a></li>

</ul>


<div class="tab-content">
    <div class="tab-pane active" id="dashboard">

        <div id="wrapper">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header" data-step="7" data-intro="Alright, you're good to go!">Dashboard</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row" data-step="2" data-intro="Get a quick overview of your home">
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
                                         onclick="location.href='/Prototype245/Forward?ref=devices'">View
                                    Details                            
                                </button>
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
                                        <div id="eventCountSinceLastWeek" class="huge">0</div>
                                        <div>Security Events Last 7 Days</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <button type="button" class="btn btn-link btn-xs btn-block"
                                        onclick="location.href='/Prototype245/Forward?ref=log'">
                                    View Details
                                </button>
                            </div>
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
                                        <div id="lastLogin" style="font-size: 26px;"></div>
                                        <div>Last Login</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <button type="button" class="btn btn-link btn-xs btn-block"
                                        data-toggle="modal" data-target="#lastLoginModal">View
                                    Details
                                </button>
                            </div>
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
                                    System
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-8">
                        <div class="panel panel-default">
                            <!-- /.panel-heading -->
                            <div class="panel-body" data-step="3"
                                 data-intro="Drag and drop your camera devices to their exact position in the floor plan">
                                <div id="cameraMap">
                                    <div id="floorplan" class="floorplan">
                                        <img src="images/floor_plan_example.png" width="40%" height="40%"/>
                                        <img src="images/floor_plan_example.png" width="40%" height="40%"/>
                                    </div>
                                </div>
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-8 -->
                    <div class="col-lg-4" data-step="4" data-intro="View the recent security events in your home">
                        <div class="panel panel-default" id="notificationsPanel">
                            <div class="panel-heading">
                                <i class="fa fa-bell fa-fw"></i> Notifications Panel
                            </div>
                            <!-- /.panel-heading -->

                            <div class="panel-body">
                                <div class="list-group" id="box"></div>
                                <button class="btn btn-primary btn-block" id="more" onclick="reloadEvents();">Show
                                    more
                                </button>
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

                        <div id="liveplayer">
                            <h1> Video Player </h1>
                            <hr>
                            <video width="800" id="playerlive">
                                Your browser does not support HTML5 video.
                            </video>
                        </div>
                        <!-- liveplayer -->
                        <div id="vodplayer">
                            <video width="800" id="playervod" controls>
                                Your browser does not support HTML5 video.
                            </video>
                        </div>
                        <!-- vodplayer -->

                        <button class="button" onClick="startLive(videoID);">Show live video feed</button>
                        <div id="voddiv">
                        </div>
                        <script>
                             //@author Stefan Mertens s113429
                            var videoID;
                            var url = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/video/";
                            function preparePlayer(id) {
//Init
                                var id = videoID;
                                $("#liveplayer").hide();
                                $("#vodplayer").hide();
                                $("#playerlive")[0].pause();
                                $('#voddiv').empty();
//Generate vod buttons
                                $.getJSON(url + "getVODS?id=" + id, function (data) {
                                    vods = data;
                                    $.each(data.reverse(), function (key, vod) {
                                        var date = new Date(vod.startTime);
                                        var element = '<a><br>' + date.toUTCString() + '&nbsp;&nbsp;&nbsp;Length: ' + vod.length * 4 + 's&nbsp;&nbsp;&nbsp;<button class="button" onClick="startVod(' + vod.startID + ',' + vod.length + ');">  Show video</button>';
                                        $('#voddiv').append(element);
                                    });
                                });
                            }
                            function startLive(id) {
                                $('#liveplayer').show();
                                var latest;
                                $("#playerlive").bind("ended", function () {

                                    $.get(url + "getLatest?id=" + id, function (data) {

                                        $("#playerlive")[0].src = url + "getVideo?id=" + id + "&count=" + data;

                                        $("#playerlive")[0].play();
                                    });
                                });

                                $.get(url + "getLatest?id=" + id, function (data) {

                                    $("#playerlive")[0].src = url + "getVideo?id=" + id + "&count=" + data;

                                    $("#playerlive")[0].play();
                                });

                            }// startlive

                              
                              function startVod(i, end) {
                                $('#vodplayer').show();
                                  $("#playervod").unbind("ended");//There might be previous bindings

                                $("#playervod")[0].src = url + "getVideo?id=" + videoID + "&count=" + i;

                                $("#playervod").bind("ended", function () {
                                    console.log(i + "/" + end);
                                    if (end > 1) {
										  i++;
                                          end--;
                                          $("#playervod")[0].src = url + "getVideo?id=" + videoID + "&count=" + i
                                          $("#playervod")[0].play();
                                    }
                                    else {
                                        $('#playervod')[0].pause();
 										$("#playervod").unbind("ended");
 										$('#vodplayer').hide();
                                    }
                                });
                                $("#playervod")[0].play();
                            }


                        </script>
                        <!-- PLAYER SCRIPT END-->

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
                    <div class="modal-body" id="loginModalBody">

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
                                    1
                                </button>
                                <button type="button" id="2" class="btn btn-default">Level
                                    2
                                </button>
                                <button type="button" id="3" class="btn btn-default">Level
                                    3
                                </button>
                                <button type="button" id="0" class="btn btn-default">Deactivate
                                    System
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>
    
  </div>

    <div class="modal fade" id="eventInfoModal" tabindex="-1" role="dialog"
         aria-labelledby="deviceInfoModal" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                </div>
                <div class="modal-body" align=center>

                    <p class="text" id="macEvent"></p>
                    
                    <p class="text" id="time"></p>

                    <script>

                        function eventInfo(id , time) {
                        var d= new Date(time);
                        $('#time').html("Event time: "+d);
                         $.ajax({
           					type: "GET",
            				url: "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/apps/getAppByID?id="+id,
           				 	data: data,
            				success: function (data) {
                				var mac = data.mac       
                            	$('#macEvent').html("Event from mac: " + mac);                       
                            }	
        				});
        			}
                    </script>

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
    <!-- /#wrapper -->

    <!--
    @author s124259(Jesper)
    -->
    <div class="panel-footer" data-step="5" data-intro="These useful links will help you even further">
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
    <!-- ##################################################################
    #######################################################################
    #######################################################################

    #########################	JavaScript	###############################

    #######################################################################
    #######################################################################
    ################################################################### -->
    <script>
        /*
         @author s124259(Jesper)
         Dynamically adding sensor buttons to cameraMap in dashboard
         based on devices from webrservice request
         */

        var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest";

        var URL = webServerPath + "/apps/getDevicesWithCamera";
        $.ajax({
            type: "GET",
            url: URL,
            success: function (data) {
                var devices = data;
                for (var i in devices) {
                   
                    var mac = devices[i].mac;
                    $('			<button id="' + mac + '"' + ' type="button" class="draggable" data-toggle="modal" onClick="videoID = '+(devices[i].id)+';preparePlayer('+devices[i].id+');" data-target="#jesperModal" />').text(+i+1).appendTo('#cameraMap');
                }
            }
        });
    </script>

    <script>
        /*
         @author s124259(Jesper)
         Getting device count with webservice call
         on page load and update #devicesRunning class with data.
         */
        var URL = webServerPath + "/apps/getDeviceCount";
        var data;
        $.ajax({
            type: "GET",
            url: URL,
            data: data,
            success: function (data) {
                $('#devicesRunning').html(data);
            }
        });
    </script>
    
    <script>
        /*
         @author s124259(Jesper)
         Getting lastLogin based on username with webservice call
         on page load and update #lastLogin class with data and details modal aswell.
         Using JQuery timeago plugin for formatting
         */
         $( document ).ready(function() {
	        var URL = webServerPath + "/users/getLastLoginByUserName?userName=" + "<%=user.getUserName()%>"
	        var data;
	        $.ajax({
	            type: "GET",
	            url: URL,
	            data: data,
	            success: function (data) {
	                var d = new Date(data);
	                $('#lastLogin').html(jQuery.timeago(d));
	                $('#loginModalBody').html("<h3>Last login: " + d + "</h3>");
	            }
	        });
         });
    </script>

    <script>
        /*
         @author s124259(Jesper)
         Getting security level with webservice call
         on page load and update #securityLevel class with data
         */

        var URL = webServerPath + "/rules/getSecurityLevel";
        $.ajax({
            type: "GET",
            url: URL,
            data: data,
            success: function (data) {
                if (data > 0) {
                    $('#securityLevel').html("Level " + data);
                }
                else {
                    $('#securityLevel').html("Deactivated");
                }
            }
        });
    </script>
    
    <script>
        /*
         @author s124259(Jesper)
         Getting total number of new events within last x miliseconds with webservice call
         on page load and update #eventCountSinceLastWeek class with data
         */

        var timespan = "604800000"; // 7 days in ms
        var URL = webServerPath + "/events/getTotalEventCountInTimeSpan?time=" + timespan;
        $.ajax({
            type: "GET",
            url: URL,
            success: function (data) {
                $('#eventCountSinceLastWeek').html(data);
            }
        });
    </script>

    <script>
    /* 
    	@author s124259(Jesper)
    	OnClick Event on whole button group (Security Level Buttons)
 		source: http://stackoverflow.com/questions/9262827/twitter-bootstrap-onclick-event-on-buttons-radio
    */
        $('#securityLevelGroup button').click(function () {
            $(this).addClass('active').siblings().removeClass('active');
            var level = $(this).attr('id');
            var URL = webServerPath + "/rules/setSecurityLevel?level=" + level;

            if (level > 0) {
                $("div.securityStatus").replaceWith("<h2>Security Level is now: " + level + "</h2>");
            }
            else {
                $("div.securityStatus").replaceWith("<h2>Home Security System is now Deactivated </h2>");
            }

            $.ajax({
                type: "POST",
                url: URL
            });

            document.location.reload();

        });
    </script>
    
    <script type="text/javascript">

        /** @author s124255
         dashboard tab
         */
  
	     var eventListSize = 5;
	        function reloadEvents() {
	            var data;
	            var URL = webServerPath + "/events/getAllEvents";
	            $.ajax({
	                type: "GET",
	                url: URL,
	                data: data,
	                success: function (data) {
	                    var items = data;
	                    $('#box').empty();
	                    for (var i in items.reverse()) {
	                        var type = items[i].eventType;
	                        var time = jQuery.timeago(new Date(items[i].time));
	                        var element = '<a href="#" data-toggle="modal" data-target="#eventInfoModal" class="list-group-item" onclick="eventInfo('+items[i].appID + ',' + items[i].time+')"><i class="fa fa-shield fa-fw"></i> ' + type.replace("PLAYSOUND", "Sound played").replace("FLASHLIGHT", "Flash light activated").replace("USERALERT", "User Alerted").replace("ACCELEROMETER", "Movement detected") + '<span class="pull-right text-muted small"><em>' + time + '</em></span></a>';
	                        $('#box').append(element);
	                        if(i >= eventListSize){
	                        	break;
	                        }
	                    }
	                }
	            });
	        }
     $( document ).ready(function() {      
	        reloadEvents();
     });
      
     $('#more').click(function () {
        eventListSize=eventListSize+4;
     })

    </script>

    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/jquery.timeago.js"></script>
    <script src="js/interact.js"></script>
    <script src="js/interact dragging.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>

    <!-- "Guided Tour" Plugin -->
    <script src="js/intro.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>
</body>

</html>
