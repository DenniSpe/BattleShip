
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${ lobby.name }</title>

<jsp:include page="nav_bar.jsp"></jsp:include>
<script src="resources/assets/js/main.js"></script>
<link rel="stylesheet" href="resources/assets/css/loading.css" />

<style type="text/css">
body {
	min-height: 75rem;
	padding-top: 9rem;
	padding-left: 8.5rem;
}
</style>

<script>
	function getEventsFromServer() {
		var lobbyID = document.getElementById("lobby_id").innerHTML;
		console.log("JAVASCRIPT IDLOBBY ======== " + lobbyID);

		var challengerArrived = false;
		var username = $("#username").attr("value");
		

		var lobbyOwner = $("#owner").attr("value");
		console.log("lobby onwnrer"+ lobbyOwner);

		$.ajax({
			url : "waiting",
			type : "POST",
			data : {
				"lobbyID" : lobbyID
			},
			success : function(result) {
				console.log("RESULT____"+result);
				challengerArrived = true;

				if (result) {

					if (lobbyOwner!=username) //it means I am the challenger
					{
						$("#challengerWaiting").hide();
						$("#ownerAccept").show();

						var position = result.split(":")[1];

						if (position == "true") {

							window.location = "/BattleShip/startPositioning?ID="+lobbyID;
						}

					} else {
						$("#challengerWaiting").hide();
						$(".challengerArrived").show();
					}

				}

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
			type : "POST",
			data : {
				"lobby_id" : lobbyID
			},
			success : function(result) {
				console.log("CHECK OWNER = " + result);
				console.log("IL RESULT E' = "
						+ result.localeCompare("notOwner"));
				if (result.localeCompare("notOwner") == 0) {
					window.location = "/BattleShip/lobbies";
				}
				setTimeout(function() {
					checkOwner();
				}, 3000);
			},
			error : function(result) {
				console.log("ERRORE CHECK OWNER" + result);

				//call events again after some time
				setTimeout(function() {
					checkOwner();
				}, 3000);
			}
		});
	}

	$(document).ready(function() {
		//$("#challengerWaiting").hide();
		$("#ownerAccept").hide();
		$(".challengerArrived").hide();

		getEventsFromServer();
		checkOwner();
	});
</script>


</head>
<body>
<hidden hidden id="username"
		value="${user.username}"></hidden>
<hidden hidden id="owner"
		value="${lobby.owner}"></hidden>
	<!-- 
<div class="btn-group">
 <a href="<c:url value="/quit_lobby">
 				<c:param name="lobby_id" value="${lobby.id}" />
 		  </c:url>">
 			<button type="button" class="btn btn-primary"> Quit </button>
 			</a>
</div>



<form action="startGame">
<button >click me</button>
<input type="hidden" value=${ currentLobbyID } name="id"/>
</form>



<div>
 
<h2 id=message></h2>
</div>

<div class="row">

<h1>name = ${ lobby.name }</h1>

<h1>owner = ${ lobby.owner }</h1>

<h1> challenger = <h5 id="challenger"> </h5></h1> 


<h1><div id="lobby_id">${ currentLobbyID }</div></h1>

</div>

 -->

	<hidden>
	<div hidden  id="lobby_id">${ lobby.id }</div>
	</hidden>

	<div class='container'>
		<div class='row'>
		
		<div class="col-md-12 col-sm-12">
			<div class='loaderPixel'>
				<h1 id="lobbyName" style="padding-bottom:5%">${ lobby.name }</h1>

				<div id="challengerWaiting" style="font-size:180%">
					<h5>Hi ${user.username }, please wait for you challenger</h5>
				</div>

				<div id="ownerAccept" style="font-size:180%">
					<h5>Hi ${user.username }, please wait the owner accept you</h5>
				</div>



				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>
				<div class='pixels'></div>

				

			</div>
		</div>
		</div>


	</div>

<div class="row">
<div class="col-md-12" style="padding-left:30%; padding-bottom:3%;">
<h5 style="font-size:180%" class="challengerArrived">Hi ${user.username },  ${lobby.challenger } want to play with you, please start
								the game</h5>
								</div>
</div>


<div class="row">
<div class="col-md-6 col-sm-12" >
		<a 
			href="<c:url value="/quit_lobby">
				 				<c:param name="lobby_id" value="${lobby.id}" />
				 		  </c:url>">
			<input type="image"  src="resources/assets/css/images/goBack.png" style="width:30%; height:15%;"></input>
		</a>
	</div>
<div class="col-md-6 col-sm-12">

		<div class="challengerArrived">
		
								
							<form method="GET" action="startPositioning">
								<input type="image"  src="resources/assets/css/images/start.png" style="width:30%; height:15%;">
									</input>
								<input type="hidden" value="${lobby.id}" name="ID" />
							</form>
		
		</div>
</div>

	

</div>


</body>
</html>