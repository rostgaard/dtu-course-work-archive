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
//allow access only if session attribute "user" is set beforehand
User user = (User) session.getAttribute("user");
if (user == null) {
user = new User();
response.sendRedirect("login.jsp");
}

%>

<ul class="nav nav-tabs" role="tablist">
    <li><a href="/Prototype245/Forward?ref=dashboard" >Dashboard</a></li>
    <li><a href="/Prototype245/Forward?ref=devices">Devices</a></li>
    <li class="active"><a href="/Prototype245/Forward?ref=rules">Rules</a></li>
    <li><a href="/Prototype245/Forward?ref=users">Users</a></li>


    <li style="float: right;"><a href="/Prototype245/LogoutServlet">Log
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
                        <div class="panel panel-default">
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
                                        
                                        <div class = "form-group">
                                        	<div class = "col-sm-6">
                                        		<input type ="text" class="form-control" id="policyNr"
                                        				placeholder ="Policy nr.">
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
                                        </div>
                                        <br>
                                        	Example syntax:
                                        <div>
                                  	  	 <div class="ConsolasItalic">
                                  	  	 <br>
	                        				test2:
												<p class="indent_1">  when ACCELEROMETER
												  <br>if system.securitylevel >= 2</p>
												 <p class="indent_2">    ||
												     event.source == 1 
												     &&
												     event.value >= 50  
												     </p>
												<p class="indent_1">  then actorSound1.play(1,30),</p> 
												    <p class="indent_2">   UserAlert.raise(event.event);</p> 
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

<div class="modal fade" id="ruleInfoModal" tabindex="-1" role="dialog"
     aria-labelledby="userInfoModal" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
            </div>
            <div class="modal-body" align=center>

                <p class="text" id="Policyid"></p>

                <p class="text" id="Name"></p>

                <div class="list-group" id="rules"></div>

                <p class="text" id="SecurityLevel"></p>

                <script>

                    function getRule(policyId) {

                        var URL = webServerPath + "/rules/getPolicy?id=" + policyId;
                        $.ajax({
                            type: "GET",
                            url: URL,
                            success: function (policy) {

                                $('#Policyid').text("Policy Id: " + policy.id);
                                $('#Name').text("Policy Name: " + policy.name);
                                
            					$('#RuleString').empty();
            					for (var i in plicy.Rule) {				
                				var rule = plicy.ruleStrings[i];	
                				$('#RuleString').append(rule);
            					};
            			
                                $('#SecurityLevel').text("Role: " + policy.securityLevel);

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
	
    var URL = webServerPath + "/rules/getAllPolicies";
    $.ajax({
        type: "GET",
        url: URL,
        success: function (data) {
            var policies = data;
            for (var i in policies) {
<<<<<<< .mine
                var policy = '<a href="#" data-toggle="modal" data-target="#ruleInfoModal" class="list-group-item" onclick="getRule(\'' + policies[i].id + '\')"><i class="fa fa-user fa-fw"></i> ' + policies[i].name + '</a>';                
                $('#ruleList').append(policy);
=======
                var policy ='<a href="#" data-toggle="modal" data-target="#ruleInfoModal" class="list-group-item" onclick="getRule(' + policies[i].id + ')"><i class="fa fa-user fa-fw"></i> '
                + policies[i].name
                + '</a>';
                $('#rules').append(policy);
>>>>>>> .r317
            }
        }
    });
</script>
<script>

	function addRule(){
	
	var rulestring = document.getElementById('ruleString');
	var id = document.getElementById('policyNr');
	var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest";
	var data;
	var polURL = webServerPath + "/rules/addPolicy?name=Policy "+id;
	
	$.ajax({
		type: "GET",
		url: polURL,
		data: data,
		succes: function(policy){
			var ruleURL = webServerPath + "/rules/addRuleString?ruleString="+rulestring + "&policyId="+id;
			$.ajax({
				type: "GET",
				url: ruleURL,
				data: data,
				success: function(data){
				}
			});
		},
		error: function(policy){
			var ruleURL = webServerPath + "/rules/addRuleString?ruleString="+rulestring + "&policyId=0";
			$.ajax({
				type: "GET",
				url: ruleURL,
				data: data,
				success: function(data){
				}
			});
		}
			
	});
	
}
	
	

</script>
<script src="js/bootstrap.min.js"></script>

    <!-- "Guided Tour" Plugin -->
    <script src="js/intro.js"></script>
</html>
