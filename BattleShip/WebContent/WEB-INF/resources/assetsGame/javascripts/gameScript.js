var nBoatsPositioned = 0;

$(document).ready(function() {
	 for (var i = 1; i <= 10; i++) {
		for (var j = 1; j <= 10; j++) {
			$("#cellCG-" + i + "-" + j).click(function() {
				shoot(this.id);
			});
		}
	}

	$("#button1").click(function() {
		rotateBoat("boat-1");
	});
	$("#button2").click(function() {
		rotateBoat("boat-2");
	});
	$("#button3").click(function() {
		rotateBoat("boat-3");
	});
	$("#button4").click(function() {
		rotateBoat("boat-4");
	});
	$("#button5").click(function() {
		rotateBoat("boat-5");
	});
	
	$("button#IR").attr("disabled", "disabled");
	checkTurn();
	
});

function shoot(cellacliccata) {
	var lobbyID= $("#lobbyId").attr("value");
	$.ajax({
		url : "shoot",
		type: "POST",
		data : {
			'cella' : cellacliccata,
			'id': lobbyID
		},
		success : function(result) {
			if(result=="wait-turn"){
				$('#modal').modal('show');
			}else{
				appendTag(result);
				$("#message").html(result);
			}
		},
		error : function() {
			// call events again after some time
//			setTimeout(function() {
//				shoot();
//			}, 5000);
		}
	});
}

function rotateBoat(id) {
	console.log("MY ID IS = " + id);
	var img = document.getElementById(id);
	if (img.getAttribute("style") == "transform:rotate(90deg); height: 50px; width: 50%;") {
		img.setAttribute("style",
				"transform:rotate(0deg); height: 50px; width: 50%;")
	} else {
		img.setAttribute("style",
				"transform:rotate(90deg); height: 50px; width: 50%;")
	}
}

function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");

	console.log("Stai droppando sulla cella " + ev.target.id);

	

	var dir = document.getElementById(data).getAttribute("style");
	var size = document.getElementById(data).getAttribute("id").split("-")[1]; // Attenzione,
																				// da
																				// questo
																				// id
																				// dell'img
																				// mi
																				// prendo
																				// la
																				// size
																				// della
																				// boat

	var url = new URL(window.location.href);
	var lobbyID = $("#lobbyId").attr("value");

	alert("LA SIZE e' " + size);

	$
			.ajax({
				url : "putBoat",
				data : {
					'ID' : lobbyID,
					'cella' : ev.target.id,
					"dir" : dir,
					"size" : size
				},
				success : function(result) {
					if (!result.localeCompare("ERROR")) {
						alert("You can't put your boat here !");
					} else {
						alert(result);
						
						nBoatsPositioned += 1;
						alert("I am putting my "+nBoatsPositioned);
						checkUserIsReady(nBoatsPositioned);
						// row, col, dir, size

						var param = result.split(",");

						var row = parseInt(param[0]);
						var col = parseInt(param[1]);
						var direction = parseInt(param[2]);
						var size = parseInt(param[3]);

						alert("row = " + row + " ,col = " + col + " ,dir = "
								+ direction + " ,size = " + size);

						ev.target.appendChild(document.getElementById(data));

						if (direction == 0) {// Horizontal
							for (var i = col; i < col + size; i++) {
								console.log("Appendi l'immagine su riga = "
										+ row + " e col = " + i);

								ev.target.appendChild(document
										.getElementById(data));
								document.getElementById("cellOG-" + row + "-"
										+ i).style.background = 'red';
							}

						} else if (direction == 1) { // Vertical
							for (var j = row; j < row + size; j++) {
								console.log("Appendi l'immagine su riga = " + j
										+ " e col = " + col);

								ev.target.appendChild(document
										.getElementById(data));
								document.getElementById("cellOG-" + j + "-"
										+ col).style.background = 'blue';

							}
						}
					}
					$("#prova").html(result);
				},
				error : function() {
					// call events again after some time
					setTimeout(function() {
						drop(ev);
					}, 5000);
				}
			});

}

function checkUserIsReady(nBoatsPositioned){
	if(nBoatsPositioned >= 5){ // The user put all his boats in the grid
		 $("button#IR").removeAttr("disabled");
	}
	
}

function waitingStart() {
	var url = new URL(window.location.href);
	var lobbyID = $("#lobbyId").attr("value");

	alert("WAITING START CON ID " + lobbyID);

	$.ajax({
		url : "waitingStart",
		data : {
			"ID" : lobbyID
		},
		success : function(result) {
			if (!result.localeCompare("game")) { // If it's game
				window.location = "/BattleShip/game?id="+lobbyID;
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
	if (str[0] == "miss") {
		$("#cellCG-" + i + "-" + j)
				.append(
						'<i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i>');
	}
	if (str[0] == "hit") {
		$("#cellCG-" + i + "-" + j).append(
				'<img src="resources/assetsGame/images/1331900690_fire.png">');
	}
}

function checkTurn() {
	var lobbyID = $("#lobbyId").attr("value");
	$.ajax({
		url : "checkTurn",
		type:'POST',
		dataType: "json",
		data : {
			"lobbyid" : lobbyID
		},
		success : function(result) {
			if(result.turn == true){
				$("#turnMessage").html("It's your turn");
				$("#turnMessage").removeClass( "label-danger" );
				$("#turnMessage").addClass("label-success")
			}else{
				$("#turnMessage").html("Wait for your turn...");
				$("#turnMessage").removeClass( "label-success" );
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
