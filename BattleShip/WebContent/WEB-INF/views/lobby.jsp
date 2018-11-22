<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${ lobby.name }</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<p>name = ${ lobby.name }</p>

<p>owner = ${ lobby.owner }</p>

<p> challenger = <h5 id="challenger"></h5> </p>

<div id="lobby_id">${ currentLobbyID }</div>


<div class="btn-group">
 <a href="<c:url value="/quit_lobby">
 				<c:param name="lobby_id" value="${lobby.id}" />
 		  </c:url>">
 			<button type="button" class="btn btn-primary"> Quit </button>
 			</a>
</div>

<script>
	function getEventsFromServer() {
		var lobbyID = document.getElementById("lobby_id").innerHTML;
		console.log("JAVASCRIPT IDLOBBY ======== "+lobbyID);
		$.ajax({
			url : "waiting",
			type: "POST",
			data: {"lobbyID": lobbyID},
			success : function(result) {
				console.log(result);
				$("#challenger").html(result);
				setTimeout(function() {
					getEventsFromServer();
				}, 3000);
			},
			error : function() {
				//call events again after some time
				setTimeout(function() {
					getEventsFromServer();
				}, 3000);
			}
		});
	}

	
	
	
	function checkOwner() {
		var lobbyID = document.getElementById("lobby_id").innerHTML;
	console.log("CHECK OWNER");
		$.ajax({
			url : "checkOwner",
			type: "POST",
			data: {"lobby_id": lobbyID},
			success : function(result) {
				console.log("CHECK OWNER = "+result);
				console.log("IL RESULT E' = "+result.localeCompare("notOwner"));
					if(result.localeCompare("notOwner") == 0) {
						window.location="/BattleShip/lobbies";
					}
				setTimeout(function() {
					checkOwner();
				}, 3000);
			},
			error : function(result) {
				console.log("ERRORE CHECK OWNER"+result);
				
				//call events again after some time
				setTimeout(function() {
					checkOwner();
				}, 3000);
			}
		});
	} 
	
	$(document).ready(
			function(){
					getEventsFromServer();
					checkOwner();
				}			
			);
	
	
	
	
	
	
	
	
	
	
	
</script>


<!--	
	

-->





<div>
<h2> Server </h2>
<h2 id=message></h2>
</div>


</body>
</html>