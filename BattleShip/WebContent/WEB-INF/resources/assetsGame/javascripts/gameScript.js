$(document).ready(function() {
	 for (var i = 1; i <= 10; i++) {
		for (var j = 1; j <= 10; j++) {
			console.log("#cellCG-" + i + "-" + j);
			$("#cellCG-" + i + "-" + j).click(function() {
				shoot(this.id);
			});
		}
	}

	checkTurn();

});

function shoot(cellacliccata) {
	var lobbyID = $("#lobbyId").attr("value");
	$.ajax({
		url : "shoot",
		type : "POST",
		data : {
			'cella' : cellacliccata,
			'id' : lobbyID
		},
		success : function(result) {
			alert("IL RESULT DI SHOOT E' "+result);
			if(result=="wait-turn"){
				$('#modal').modal('show');
			}else{
				var whoWin = appendTag(result);
				alert("WHO WIN E' "+whoWin);
				if(whoWin == "OWNERWIN"){
					alert("HA VINTO L'OWNER");
				}
				else if(whoWin == "CHALLENGERWIN"){
					alert("HA VINTO IL CHALLENGER");
				}

			}
		},
		error : function() {
			// call events again after some time
			// setTimeout(function() {
			// shoot();
			// }, 5000);
		}
	});
}

function refreshGrid() {

	$.ajax({
		url : "refreshGrid",
		data : {
			"ID" : lobbyID
		},
		success : function(result) {
			if (!result.localeCompare("game")) { // If it's game
				window.location = "/BattleShip/game";
			} else if (!result.localeCompare("boatPositioning")) {
				// do nothing
			}

			setTimeout(function() {
				waitingStart();
			}, 5000);
		},

		error : function() {
			// call events again after some time
			setTimeout(function() {
				drop(ev);
			}, 5000);
		}
	});

}

function appendTag(result) {

	var str = result.split('-');
	var i = str[1];
	var j = str[2];
	var winner = str[3];
	
	if (str[0] == "miss") {
		$("#cellCG-" + i + "-" + j)
				.append(
						'<i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i>');
	}
	if (str[0] == "hit") {
		$("#cellCG-" + i + "-" + j).append(
				'<img src="resources/assetsGame/images/1331900690_fire.png">');
	
		if(str[3] == "OWNERWIN"){
			return "OWNERWIN";
		}
		else if(str[3] == "CHALLENGERWIN"){
			return "CHALLENGERWIN";
		}
	}
	
}

function checkTurn() {
	var lobbyID = $("#lobbyId").attr("value");
	$.ajax({
		url : "checkTurn",
		type : 'POST',
		dataType : "json",
		data : {
			"lobbyid" : lobbyID
		},
		success : function(result) {
			if (result.turn == true) {
				$("#turnMessage").html("It's your turn");
				$("#turnMessage").removeClass("label-danger");
				$("#turnMessage").addClass("label-success")
			} else {
				$("#turnMessage").html("Wait for your turn...");
				$("#turnMessage").removeClass("label-success");
				$("#turnMessage").addClass("label-danger")
			}
			setTimeout(function() {
				checkTurn();
			}, 3000);
		},

		error : function() {
			// call events again after some time
		}
	});
}
