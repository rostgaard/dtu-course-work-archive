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
    <li><a href="/Prototype245/Forward?ref=devices">Devices</a></li>
    <li><a href="/Prototype245/Forward?ref=rules">Rules</a></li>
    <li class="active"><a href="/Prototype245/Forward?ref=users">Users</a></li>


    <li style="float: right;"><a href="/Prototype245/LogoutServlet">Log
        Out</a></li>
    <li style="float: right;"><a href="javascript:void(0);" onclick="javascript:introJs().start();">Demo</a></li>
</ul>


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
                    <div class="col-lg-6" data-step="1" data-intro="View all the current users of the system">
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
                    <div class="col-lg-6" data-step="2" data-intro="Add a new user to the system">
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
                                                       placeholder="Lastname"/>
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
                                                       placeholder="Password"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <button type="button" id="submit"
                                                        class="btn btn-lg btn-success btn-block"
                                                        data-toggle="modal" data-target="#addUserModal"
                                                        onclick="addUser()">Add User
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
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
     aria-labelledby="addUserModal" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <p>New User</p>
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

                    function addUser() {
                        var username, firstname, lastname, email, role, password;

                        username = document.getElementById('username').value;
                        email = document.getElementById('email').value;
                        firstname = document.getElementById('firstname').value;
                        lastname = document.getElementById('lastname').value;
                        role = document.getElementById('role').value;
                        if (role == 1) {
                            role = "VIEWER"
                        }
                        else {
                            role = "MANAGER"
                        }
                        password = document.getElementById('password').value;

                        var URL = webServerPath + "/users/addUser?userName=" + username + "&email=" + email + "&firsName=" + firstname + "&lastName=" + lastname + "&role=" + role + "&password=" + password;
                        $.ajax({
                            type: "GET",
                            url: URL,
                            data: data,
                            error: function (data) {

                                $('#uname').html("Username: " + username);
                                $('#mail').html("Email: " + email);
                                $('#fname').html("Firstname: " + firstname);
                                $('#lname').html("Lastname: " + lastname);
                                $('#role').html("Role: " + role);
                                $('#pass').html("Password: " + password);
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

                    function getUser(user) {
                        //$('#uName').html("Username: "+user);

                        var userData = [];
                        var URL = webServerPath + "/users/getUserByUserName?userName=" + user;
                        $.ajax({
                            type: "GET",
                            url: URL,
                            success: function (user) {

                                //var usertemp = '[{"userName":"Perminator","email":"pr@mail.com","firstName":"Per","lastName":"Kristansen","role":"VIEWER","password":"pertheman"},{"userName":"TomCat","email":"tom@mail.com","firstName":"Tom","lastName":"Catgat","role":"VIEWER","password":"awesomeo"},{"userName":"Charleton","email":"chr@mail.com","firstName":"Charles","lastName":"Tonnisen","role":"VIEWER","password":"tonnibonde"}]';//OUT FOR PRODUCTION
                                //var userItems = $.parseJSON(usertemp);//OUT FOR PRODUCTION
                                //var user = $.parseJSON(userData);

                                $('#eMail').text("Email: " + user.email);
                                $('#fName').text("Firstname: " + user.userName);
                                $('#lName').text("Lastname: " + user.lastName);
                                $('#role').text("Role: " + user.role);
                                $('#pass').text("Password: " + user.password);

                            }
                        });

                    }
                </script>
            </div>
        </div>
    </div>
</div>

<!-- GetUsers -->
 <script>var webServerPath = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest";</script>
<script>

    function reloadUsers() {
        var userData;
        var URL = webServerPath + "/users/getUsers";
        $.ajax({
            type: "GET",
            url: URL,
            data: userData,
            success: function (userData) {

                //var usertemp = '[{"userName":"Perminator","email":"pr@mail.com","firstName":"Per","lastName":"Kristansen","role":"VIEWER","password":"pertheman"},{"userName":"TomCat","email":"tom@mail.com","firstName":"Tom","lastName":"Catgat","role":"VIEWER","password":"awesomeo"},{"userName":"Charleton","email":"chr@mail.com","firstName":"Charles","lastName":"Tonnisen","role":"VIEWER","password":"tonnibonde"}]';//OUT FOR PRODUCTION
                //var userItems = $.parseJSON(usertemp);//OUT FOR PRODUCTION
                var userItems = userData;

                $('#user').empty();
                for (var i in userItems) {
                    var userElement = '<a href="#" data-toggle="modal" data-target="#userInfoModal" class="list-group-item" onclick="getUser(\'' + userItems[i].userName + '\')"><i class="fa fa-user fa-fw"></i> '
                            + userItems[i].userName
                            + '</a>';
                    $('#user').append(userElement);
                }
                ;
            }
        });
    }

    reloadUsers();
</script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

    <!-- "Guided Tour" Plugin -->
    <script src="js/intro.js"></script>
</body>

</html>
