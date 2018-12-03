var nBoatsPositioned = 0;

var rowDestroyer = 0;
var colDestroyer = 0;
var dirDestroyer = 0;

var rowSubmarine = 0;
var colSubmarine = 0;
var dirSubmarine = 0;

var rowCruiser = 0;
var colCruiser = 0;
var dirCruiser = 0;

var rowBattleShip = 0;
var colBattleShip = 0;
var dirBattleShip = 0;

var rowAircraft = 0;
var colAircraft = 0;
var dirAircraft = 0;

$(document).ready(function() {
	checkAlive();
	$("#IR").hide();
	$("#loading").hide();

	$("#button-destroyer").click(function() {
		rotateBoat("destroyer");
	});
	$("#button-submarine").click(function() {
		rotateBoat("submarine");
	});
	$("#button-cruiser").click(function() {
		rotateBoat("cruiser");
	});
	$("#button-battleship").click(function() {
		rotateBoat("battleship");
	});
	$("#button-aircraft").click(function() {
		rotateBoat("aircraft");
	});

	/*
	 * 
	 * Delete button
	 */

	$("#button-destroyer-delete").hide();
	$("#button-destroyer-delete").click(function() {
		var ID = $("#lobbyId").attr("value");
		nBoatsPositioned--;
		checkUserIsReady();

		$.ajax({
			url : "deleteBoatFromGrid",
			type : "POST",
			data : {
				"boatID" : "destroyer",
				"ID" : ID,
				"row" : rowDestroyer,
				"col" : colDestroyer,
				"dir" : dirDestroyer
			},
			success : function(result) {

				deleteShip("destroyer");

			},
			error : function() {

			}

		});
		$("#button-destroyer-delete").hide();

	});

	$("#button-submarine-delete").hide();

	$("#button-submarine-delete").click(function() {
		var ID = $("#lobbyId").attr("value");
		nBoatsPositioned--;
		checkUserIsReady();

		$.ajax({
			url : "deleteBoatFromGrid",
			type : "POST",
			data : {
				"boatID" : "submarine",
				"ID" : ID,
				"row" : rowSubmarine,
				"col" : colSubmarine,
				"dir" : dirSubmarine
			},
			success : function(result) {

				deleteShip("submarine");

			},
			error : function() {

			}
		});
		$("#button-submarine-delete").hide();

	});

	$("#button-cruiser-delete").hide();

	$("#button-cruiser-delete").click(function() {
		var ID = $("#lobbyId").attr("value");
		nBoatsPositioned--;
		checkUserIsReady();

		$.ajax({
			url : "deleteBoatFromGrid",
			type : "POST",
			data : {
				"boatID" : "cruiser",
				"ID" : ID,
				"row" : rowCruiser,
				"col" : colCruiser,
				"dir" : dirCruiser
			},
			success : function(result) {

				deleteShip("cruiser");
			},
			error : function() {

			}
		});
		$("#button-cruiser-delete").hide();

	});
	$("#button-battleship-delete").hide();

	$("#button-battleship-delete").click(function() {
		var ID = $("#lobbyId").attr("value");
		nBoatsPositioned--;
		checkUserIsReady();
		$.ajax({
			url : "deleteBoatFromGrid",
			type : "POST",
			data : {
				"boatID" : "battleship",
				"ID" : ID,
				"row" : rowBattleShip,
				"col" : colBattleShip,
				"dir" : dirBattleShip
			},
			success : function(result) {

				deleteShip("battleship");

			},
			error : function() {

			}
		});
		$("#button-battleship-delete").hide();

	});
	$("#button-aircraft-delete").hide();
	$("#button-aircraft-delete").click(function() {
		var ID = $("#lobbyId").attr("value");
		nBoatsPositioned--;
		checkUserIsReady();

		$.ajax({
			url : "deleteBoatFromGrid",
			type : "POST",
			data : {
				"boatID" : "aircraft",
				"ID" : ID,
				"row" : rowAircraft,
				"col" : colAircraft,
				"dir" : dirAircraft
			},
			success : function(result) {

				deleteShip("aircraft");

			},
			error : function() {

			}
		});
		$("#button-aircraft-delete").hide();

	});

	$("#IR").click(function() {
		$("#button-destroyer-delete").hide();
		$("#button-submarine-delete").hide();
		$("#button-cruiser-delete").hide();
		$("#button-battleship-delete").hide();
		$("#button-aircraft-delete").hide();

		$(".nameShip").hide();
	});

});

function deleteShip(id) {
	if (id == "destroyer") {
		var row = rowDestroyer;
		var col = colDestroyer;
		var direction = dirDestroyer;
		var size = 3;
		$("#destroyer").appendTo("#rowImageDestroyer");
		$("#button-destroyer").show();
	} else if (id == "submarine") {
		var row = rowSubmarine;
		var col = colSubmarine;
		var direction = dirSubmarine;
		var size = 3;
		$("#submarine").appendTo("#rowImageSubmarine");
		$("#button-submarine").show();
	} else if (id == "aircraft") {
		var row = rowAircraft;
		var col = colAircraft;
		var direction = dirAircraft;
		var size = 3;
		$("#aircraft").appendTo("#rowImageAircraft");
		$("#button-aircraft").show();
	} else if (id == "battleship") {
		var row = rowBattleShip;
		var col = colBattleShip;
		var direction = dirBattleShip;
		var size = 5;
		$("#battleship").appendTo("#rowImageBattleship");
		$("#button-battleship").show();
	} else if (id == "cruiser") {
		var row = rowCruiser;
		var col = colCruiser;
		var direction = dirCruiser;
		var size = 5;
		$("#cruiser").appendTo("#rowImageCruiser");
		$("#button-cruiser").show();
	}

	if (direction == 0) // Horizontal
	{
		for (var i = col; i < col + size; i++) {
			document.getElementById("cellOG-" + row + "-" + i).style.background = "";
		}

	} else if (direction == 1) {
		for (var j = row; j < row + size; j++) {
			document.getElementById("cellOG-" + j + "-" + col).style.background = "";
		}
	}

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

			if (result == false) {
				alert("Match interrupted, your challenger left the game !!");
				window.location = "/BattleShip/lobbies";
			}

			setTimeout(function() {
				checkAlive();
			}, 1000);
		},
		error : function(result) {
			console.log("ERRORE CHECK OWNER" + result);

			// call events again after some time
			setTimeout(function() {
				checkAlive();
			}, 1000);
		}
	});
}

function rotateBoat(id) {
	console.log("MY ID IS = " + id);

	var pos = 0;

	var img = document.getElementById(id);
	if (img.getAttribute("style") == "transform:rotate(90deg); height: 15%; width: 60%;padding-bottom:30%;") {
		img
				.setAttribute("style",
						"transform:rotate(0deg); height: 15%; width: 60%;padding-bottom:30%;");

		pos = 0;
	} else {
		img
				.setAttribute("style",
						"transform:rotate(90deg); height: 15%; width: 60%;padding-bottom:30%;");

		pos = 1;
	}

	switch (id) {
	case "destroyer":
		dirDestroyer = pos;
		break;
	case "submarine":
		dirSubmarine = pos;
		break;
	case "cruiser":
		disCruiser = pos;
		break;
	case "battleship":
		dirBattleShip = pos;
		break;
	case "aircraft":
		dirAircraft = pos;
		break;
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

	var boatName = document.getElementById(data).getAttribute("id");

	var tmpRow = ev.target.id.split("-")[1];
	var tmpCol = ev.target.id.split("-")[2];

	// alert("LE COORDINATE DELLA BARCA SONO "+tmpRow+" e "+tmpCol);

	switch (boatName) {
	case "destroyer":
		rowDestroyer = tmpRow;
		colDestroyer = tmpCol;
		break;
	case "submarine":
		rowSubmarine = tmpRow;
		colSubmarine = tmpCol;
		break;
	case "cruiser":
		rowCruiser = tmpRow;
		colCruiser = tmpCol;
		break;
	case "battleship":
		rowBattleShip = tmpRow;
		colBattleShip = tmpCol;
		break;
	case "aircraft":
		rowAircraft = tmpRow;
		colAircraft = tmpCol;
		break;

	}

	var lobbyID = $("#lobbyId").attr("value");

	$
			.ajax({
				url : "putBoat",
				data : {
					'ID' : lobbyID,
					'cella' : ev.target.id,
					"dir" : dir,
					"boatName" : boatName
				},
				success : function(result) {
					if (!result.localeCompare("ERROR")) {
						alert("You can't put your boat here !");
					} else {

						nBoatsPositioned += 1;
						checkUserIsReady(nBoatsPositioned);
						// row, col, dir, size

						var param = result.split(",");

						var row = parseInt(param[0]);
						var col = parseInt(param[1]);
						var direction = parseInt(param[2]);
						var size = parseInt(param[3]);

						ev.target.appendChild(document.getElementById(data));

						if (direction == 0) {// Horizontal
							for (var i = col; i < col + size; i++) {
								console.log("Appendi l'immagine su riga = "
										+ row + " e col = " + i + "data="
										+ data);

								if (boatName == "submarine") {
									$("#button-submarine").hide();

									ev.target.appendChild(document
											.getElementById(data));

									$("#button-submarine-delete").show();

									document.getElementById("cellOG-" + row
											+ "-" + i).style.background = 'green';

									$("#hint")
											.html(
													"<h2>The Submarine take 345 cells</h2>");
									document.getElementById("hint").style.color = 'green';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);

								} else if (boatName == "cruiser") {
									$("#button-cruiser").hide();
									ev.target.appendChild(document
											.getElementById(data));

									$("#button-cruiser-delete").show();

									document.getElementById("cellOG-" + row
											+ "-" + i).style.background = 'red';

									$("#hint")
											.html(
													"<h2>The Cruiser take 3 cells</h2>");
									document.getElementById("hint").style.color = 'red';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);
								} else if (boatName == "destroyer") {
									$("#button-destroyer").hide();
									ev.target.appendChild(document
											.getElementById(data));
									$("#button-destroyer-delete").show();
									document.getElementById("cellOG-" + row
											+ "-" + i).style.background = 'yellow';

									$("#hint")
											.html(
													"<h2>The Destroyer take 2 cells</h2>");
									document.getElementById("hint").style.color = 'yellow';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);
								} else if (boatName == "battleship") {
									$("#button-battleship").hide();
									ev.target.appendChild(document
											.getElementById(data));

									$("#button-battleship-delete").show();

									document.getElementById("cellOG-" + row
											+ "-" + i).style.background = 'blue';

									$("#hint")
											.html(
													"<h2>The Battleship take 4 cells</h2>");
									document.getElementById("hint").style.color = 'blue';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);
								} else if (boatName == "aircraft") {
									$("#button-aircraft").hide();

									ev.target.appendChild(document
											.getElementById(data));

									$("#button-aircraft-delete").show();

									document.getElementById("cellOG-" + row
											+ "-" + i).style.background = 'orange';

									$("#hint")
											.html(
													"<h2>The Aircraft take 5 cells</h2>");
									document.getElementById("hint").style.color = 'orange';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);
								}
							}

						} else if (direction == 1) { // Vertical
							for (var j = row; j < row + size; j++) {
								console.log("Appendi l'immagine su riga = " + j
										+ " e col = " + col);
								if (boatName == "submarine") {
									$("#button-submarine").hide();

									ev.target.appendChild(document
											.getElementById(data));

									$("#button-submarine-delete").show();

									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'green';

									$("#hint")
											.html(
													"<h2>The Submarine take 3 cells</h2>");
									document.getElementById("hint").style.color = 'green';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);

								} else if (boatName == "cruiser") {
									$("#button-cruiser").hide();

									ev.target.appendChild(document
											.getElementById(data));

									$("#button-cruiser-delete").show();

									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'red';

									$("#hint")
											.html(
													"<h2>The Cruiser take 4 cells</h2>");
									document.getElementById("hint").style.color = 'red';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);

								} else if (boatName == "destroyer") {

									$("#button-destroyer").hide();

									ev.target.appendChild(document
											.getElementById(data));

									$("#button-destroyer-delete").show();

									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'yellow';

									$("#hint")
											.html(
													"<h2>The Destroyer take 2 cells</h2>");
									document.getElementById("hint").style.color = 'yellow';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);

								} else if (boatName == "battleship") {
									$("#button-battleship").hide();

									ev.target.appendChild(document
											.getElementById(data));

									$("#button-battleship-delete").show();

									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'blue';

									$("#hint")
											.html(
													"<h2>The Battleship take 4 cells</h2>");
									document.getElementById("hint").style.color = 'blue';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);
								} else if (boatName == "aircraft") {
									$("#button-aircraft").hide();

									ev.target.appendChild(document
											.getElementById(data));

									$("#button-aicraft-delete").show();

									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'orange';

									$("#hint")
											.html(
													"<h2>The Aircraft take 5 cells</h2>");
									document.getElementById("hint").style.color = 'orange';

									setTimeout(function() {
										$("#hint").html("");
									}, 2000);

								}

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

function checkUserIsReady(nBoatsPositioned) {
	if (nBoatsPositioned >= 5) { // The user put all his boats in the grid

		$("#IR").show();
	} else {
		$('#IR').hide();
	}

}

function waitingStart() {
	var lobbyID = $("#lobbyId").attr("value");
	$.ajax({
		url : "waitingStart",
		data : {
			"ID" : lobbyID
		},
		success : function(result) {
			if (!result.localeCompare("game")) { // If it's game
				window.location = "/BattleShip/game?id=" + lobbyID;
			} else if (!result.localeCompare("boatPositioning")) {

				$("#positioning").hide();

				$("#IR").hide();
				$("#loading").show();
			}

			setTimeout(function() {
				waitingStart();
			}, 1000);
		},

		error : function() {
			// call events again after some time
			setTimeout(function() {
				drop(ev);
			}, 1000);
		}
	});
}
