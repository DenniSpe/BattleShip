var nBoatsPositioned = 0;

$(document).ready(function() {
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

	$("button#IR").attr("disabled", "disabled");

});

function rotateBoat(id) {
	console.log("MY ID IS = " + id);
	var img = document.getElementById(id);
	if (img.getAttribute("style") == "transform:rotate(90deg); height: 45%; width: 75%;") {
		img.setAttribute("style",
				"transform:rotate(0deg); height: 45%; width: 75%;")
	} else {
		img.setAttribute("style",
				"transform:rotate(90deg); height: 45%; width: 75%;")
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
	var boatName = document.getElementById(data).getAttribute("id"); // Attenzione,
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

function checkUserIsReady(nBoatsPositioned) {
	if (nBoatsPositioned >= 5) { // The user put all his boats in the grid
		$("button#IR").removeAttr("disabled");
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

