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

    <title>Smart Home Security - Rules</title>

    <!-- jQuery Version 1.11.0 -->
    <script src="js/jquery-1.11.0.js"></script>

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
    
        <!-- Add IntroJs styles -->
    <link href="css/syntaxIndent.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<%
		/**
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

<ul class="nav nav-tabs" role="tablist">
    <li><a href="/SmartHomeSecurity/Forward?ref=dashboard" >Dashboard</a></li>
    <li><a href="/SmartHomeSecurity/Forward?ref=devices">Devices</a></li>
    <li class="active"><a href="/SmartHomeSecurity/Forward?ref=rules">Rules</a></li>
    <li><a href="/SmartHomeSecurity/Forward?ref=users">Users</a></li>
    <li><a href="/SmartHomeSecurity/Forward?ref=log">Event Log</a></li>


    <li style="float: right;"><a href="/SmartHomeSecurity/LogoutServlet">Log
        Out</a></li>
    <li style="float: right;"><a href="javascript:void(0);" onclick="javascript:introJs().start();">Demo</a></li>

</ul>


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
                        <div class="panel panel-default" data-step="1" data-intro="View all policies in the system.">
                            <div class="panel-heading">
                                <i class="fa fa-filter fa-fw"></i> Polices
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="list-group" id="ruleList"></div>
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
	                                    <div data-step="3" data-intro="Define your own rule and associate it with a policy.">    
	                                        <div class = "form-group">
	                                        	<div class = "col-sm-6">
	                                        		<input type ="number" class="form-control" id="policyNr"
	                                        				placeholder ="Policy number" min=1>
	                                        	</div>
	                                        </div>
	                                        <div class = "form-group">
	                                        	<div class = "col-sm-12">
	                                        		<input type ="text" class="form-control" id="ruleString"
	                                        				placeholder ="Rule string">
	                                        	</div>
	                                        </div>
	                                        <div class="form-group">
	                                            <div class="col-sm-4">
	                                                <button type="button" id="submit"
	                                                        class="btn btn-lg btn-success btn-block"
	                                                        onclick="addRule()">Add new rule
	                                                </button>
	                                            </div>
	                                            <p id=ruleError></p>
	                                        </div>
                                        </div>
                                        
                                        <br>
                                        	Example syntax:
                                        <div data-step="2" data-intro="This is an example of a rule. Use this syntax when defining your own rules.">
                                  	  	 <div class="ConsolasItalic">
                                  	  	 <br>
	                        				test1: 
												<p class="indent_1">when ACCELEROMETER</p> 
												  <p class="indent_2">if event.source == 37 && system.securitylevel == 1</p>
												<p class="indent_2">then</p> 
												    <p class="indent_3">playSound.play("id"), flashlight.play("id"), UserAlert.raise("id");</p>
											</div>                  				   
                     				   </div>
                     				   <p id=status></p>
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

<div class="modal fade" id="ruleInfoModal" tabindex="-1" role="dialog"
     aria-labelledby="userInfoModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="ruleInfoModalLabel">Policy Information</h4>
            </div>
            <div class="modal-body" align=center>

                <p class="text" id="Policyid"></p>

                <div class="list-group" id="RuleString"></div>

                <script>

                    function getRule(policyId) {

                        var URL = webServerPath + "/rules/getPolicy?id=" + policyId;
                        $.ajax({
                            type: "GET",
                            url: URL,
                            success: function (policy) {
								var listOfStrings = policy.ruleStrings;
                                $('#Policyid').text("Policy Id: " + policy.id);
                                
                                
            					$('#RuleString').empty();
            					for (var i in listOfStrings) {				
                				var rulestring = "<li>"+ i + " : " +listOfStrings[i].rule+"</li>";	
                				$('#RuleString').append(rulestring);
            					}
                            }
                        });

                    }
                </script>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap Core JavaScript -->


    <!--
    @author s124259
    -->
    <div class="panel-footer" data-step="5" data-intro="These useful links will help you even further">
        <div style="width: 2%; margin: 0 auto;">
            v1.0.0
        </div>
        <div style="width: 22%; margin: 0 auto;">
            <a href="documentation/QuickStartGuide.pdf"
               target="_blank">Quick Start Guide</a>
            |
            <a href="documentation/Handbook.pdf"
               target="_blank">Handbook</a>
            |
            <a href="https://docs.google.com/spreadsheets/d/1-Mja1E3XRwdse36ivBhWv3xAWuNfAvNiuyAH2vrtuww/pubhtml"
               target="_blank">Known Bugs</a>
        </div>
        <div style="width: 22%; margin: 0 auto;">
            <a href="android/MonitorApp.apk"
               target="_blank">Monitor App</a>
            |
            <a href="android/SensorActuatorApp.apk"
               target="_blank">Sensor/Actuar App</a>
            |
            <a href="android/CameraApp.apk"
               target="_blank">Camera App</a>
        </div>
    </div>



<script>
    var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/SmartHomeSecurity/rest";
	
    function reloadRules(){
    var URL = webServerPath + "/rules/getAllPolicies";
    $.ajax({
        type: "GET",
        url: URL,
        success: function (data) {
        	$('#ruleList').empty();
            var policies = data;
            for (var i in policies) {
                var policy = '<a href="#" data-toggle="modal" data-target="#ruleInfoModal" class="list-group-item" onclick="getRule(\'' + policies[i].id + '\')"><i class="fa fa-user fa-fw"></i> ' + "Policy " + policies[i].id + '</a>';                
                $('#ruleList').append(policy);

            }
        }
    });
    }
    reloadRules();
</script>
<script>

	function addRule(){
	
	var newRulestring = document.getElementById('ruleString').value;
	var id = document.getElementById('policyNr').value;
	var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/SmartHomeSecurity/rest";
	var data;	
	
	if(!isNaN(id)){
	
	var ruleURL = webServerPath + "/rules/addRuleString?policyId="+ id;
			$.ajax({
				type: "POST",
				contentType : "text/plain", 
				url: ruleURL,
				data: newRulestring,
				success: function(data){
					alert("Rule successfully added");
				},
				error: function(data){
					alert("Input Error: \n Rule String was not parsed correctly");		
				}
			});
	reloadRules();
	}
	else{
	alert("Policy Id must be a number");
	}
}
	
</script>
<script src="js/bootstrap.min.js"></script>

    <!-- "Guided Tour" Plugin -->
    <script src="js/intro.js"></script>
</html>
