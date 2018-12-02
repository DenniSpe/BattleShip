$(document).ready(function() {
	$("#arbitraryWin").hide();
	
	 for (var i = 1; i <= 10; i++) {
		for (var j = 1; j <= 10; j++) {
			console.log("#cellCG-" + i + "-" + j);
			$("#cellCG-" + i + "-" + j).click(function() {
				shoot(this.id);
			});
		}
	}

	checkTurn();
	checkAlive();
	

});



function shoot(cellacliccata) {
	var lobbyID = $("#lobbyId").attr("value");
	$.ajax({
		url : "shoot",
		type : "POST",
		dataType: "json",
		data : {
			'cella' : cellacliccata,
			'id' : lobbyID
		},
		success : function(result) {
//			alert("IL RESULT DI SHOOT E' "+result);
			
			if(result.youWin){
				appendTag(result);		
				$("#modal").on("click", function() {
				     window.location= "/BattleShip/";
				});	
				$("#labelWin").removeClass("hidden");
				$("#labelLoose").addClass("hidden");
				$('#modal').modal('show');
//				if(whoWin == "OWNERWIN"){
//					alert("HA VINTO L'OWNER");
//				}
//				else if(whoWin == "CHALLENGERWIN"){
//					alert("HA VINTO IL CHALLENGER");
//				}
			}
			else if(result.waitTurn){
//				$('#modal').modal('show');
			}else{
				var whoWin = appendTag(result);				
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

//	var str = result.split('-');
//	var i = str[1];
//	var j = str[2];
//	var winner = str[3];
	
	if (result.hit === false) {
		$("#cellCG-" + result.row + "-" + result.col)
				.append(
						'<i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i>');
	}
	if (result.hit === true) {
		$("#cellCG-" + result.row + "-" + result.col).append(
				'<img src="resources/assetsGame/images/1331900690_fire.png">');
	}
	
}


function cleanLobbyAfterFinish(lobbyid){
	$.ajax({
		url : "destroy_lobby",
		type : "POST",
		data : { "lobby_id" : lobbyid },
		success : function(result) {
			
		},
			
		error : function(res) {
			console.log("errorrrrrrrrrrrrrrrrrrr"+res);
			
		}
	});
}

function checkAlive() {
	var lobbyID = $("#lobbyId").attr("value");
	
	
	$.ajax({
		url : "checkAlive",
		type : "POST",
		data : {
			"lobby_id" : lobbyID
		},
		success : function(result) {
			
			console.log(result);
			
					if(result == false)
						{
						$("#labelWin").addClass("hidden");
						$("#labelLoose").addClass("hidden");
						$("#arbitraryWin").show();
						
						$('#modal').modal('show');
						setTimeout(function() {
							window.location = "/BattleShip/lobbies";
						}, 3000);
						
						}
				
			setTimeout(function() {
				checkAlive();
			}, 1000);
		},
		error : function(result) {
			console.log("ERRORE CHECK OWNER" + result);

			//call events again after some time
			setTimeout(function() {
				checkAlive();
			}, 1000);
		}
	});
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

			if(result.hasOwnProperty("youWin")){
				$("#modal").on("click", function() {
				    window.location= "/BattleShip/";
				    cleanLobbyAfterFinish(lobbyID);
				});
				if(result.youWin===true){
//					alert("ganaste");
					$("#labelWin").removeClass("hidden");
					$("#labelLoose").addClass("hidden");
					$('#modal').modal('show');
				}else{
					$("#labelWin").addClass("hidden");
					$("#labelLoose").removeClass("hidden");
					$('#modal').modal('show');
				}
				return;
			}
			if (result.turn == true) {
				$("#turnMessage").html("It's your turn");
				$("#turnMessage").removeClass("label-danger");
				$("#turnMessage").addClass("label-success")
			} else {
				$("#turnMessage").html("Wait for your turn...");
				$("#turnMessage").removeClass("label-success");
				$("#turnMessage").addClass("label-danger")
			}
			
			for (var i = 0; i < result.refreshGrid.length; i++) {
				if (result.refreshGrid[i].value === -1) {
					$("#cellOG-" + result.refreshGrid[i].row + "-" +result.refreshGrid[i].col)
							.html(
									'<i class="miss marker animated  fa fa-times fa-2x text-muted"></i>');
				}
				if (result.refreshGrid[i].value === 1) {
					$("#cellOG-" + result.refreshGrid[i].row + "-" + result.refreshGrid[i].col).html(
							'<img src="resources/assetsGame/images/1331900690_fire.png">');
				}
			}
			
			for (var i = 0; i < result.refreshGridI.length; i++) {
				if (result.refreshGridI[i].value === -1) {
					$("#cellCG-" + result.refreshGridI[i].row + "-" +result.refreshGridI[i].col)
							.html(
									'<i class="miss marker animated  fa fa-times fa-2x text-muted"></i>');
				}
				if (result.refreshGridI[i].value === 1) {
					$("#cellCG-" + result.refreshGridI[i].row + "-" + result.refreshGridI[i].col).html(
							'<img src="resources/assetsGame/images/1331900690_fire.png">');
				}
			}
			
			
			setTimeout(function() {
				checkTurn();
			}, 1000);
		},

		error : function() {
			// call events again after some time
		}
	});
}
