var attempt = 3; // Variable to count number of attempts.

function validate(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	/*var user = $.ajax({
		  type: "GET",
		  contentType: "application/json; charset=utf-8",
		  url: "UserWebService.Java/getUserByUsername",
		  data: JSON.stringify(DTO),
		  dataType: "json"
		});*/
	
	//service.useService("http://www.extentrix.com/webservices/2.0.0/ExtentrixWebServicesForCPS");
	
//	var user;
//	user = $.getJSON( "http://localhost:8080/Prototype1/rest/users/getUserByUserName?userName="+username)

//	var baseURL = "http://localhost:8080/Prototype1/rest/users/getUserByUserName?userName=";
//	var buildURL = baseURL.concat(username);
//	alert(data);
//	
//	user$.getJSON( buildURL, function(data){
//		alert(data);
//	});

	if ( username == "formget" && password == "formget#123"){
		window.location = "dashboard.html"; // Redirecting to other page.
		return false;
	}
	else{
		attempt --;// Decrementing by one.
		alert("You have left "+attempt+" attempt;");
//		Disabling fields after 3 attempts.
		if( attempt == 0){
			document.getElementById("username").disabled = true;
			document.getElementById("password").disabled = true;
			document.getElementById("submit").disabled = true;
			return false;
		}
	}
}