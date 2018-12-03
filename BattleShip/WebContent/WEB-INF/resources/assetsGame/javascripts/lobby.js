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