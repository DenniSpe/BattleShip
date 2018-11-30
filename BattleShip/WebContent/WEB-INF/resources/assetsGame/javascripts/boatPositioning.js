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
	
	$("#button-destroyer-delete").click(function() {
		var ID = $("#lobbyId").attr("value");
		nBoatsPositioned--;
		checkUserIsReady();
		
		$.ajax({
			url : "deleteBoatFromGrid",
			type : "POST",
			data : {"boatID" : "destroyer", "ID" : ID, "row": rowDestroyer, "col" : colDestroyer, "dir" : dirDestroyer},
			success : function(result){
				
				alert("Deleting boat destroyer");
				
			},
			error : function (){
				
			}
				
		});
		
		
		
		//$("#destroyer").show();
	});
$("#button-submarine-delete").click(function() {
	var ID = $("#lobbyId").attr("value");
	nBoatsPositioned--;
	checkUserIsReady();
	
	$.ajax({
		url : "deleteBoatFromGrid",
		type : "POST",
		data : {"boatID" : "submarine", "ID" : ID, "row" : rowSubmarine, "col" : colSubmarine, "dir" : dirSubmarine},
		success : function(result){
			
			alert("Deleting boat submarine");
			
		},
		error : function (){
			
		}
	});
		//$("#submarine").show();
});

$("#button-cruiser-delete").click(function() {
	var ID = $("#lobbyId").attr("value");
	nBoatsPositioned--;
	checkUserIsReady();
	
	$.ajax({
		url : "deleteBoatFromGrid",
		type : "POST",
		data : {"boatID" : "cruiser", "ID" : ID, "row" : rowCruiser, "col" : colCruiser, "dir" : dirCruiser},
		success : function(result){
			
			alert("Deleting boat cruiser");
			
		},
		error : function (){
			
		}
	});
	//$("#cruiser").show();
});
$("#button-battleship-delete").click(function() {
	var ID = $("#lobbyId").attr("value");
	nBoatsPositioned--;
	checkUserIsReady();
	
	$.ajax({
		url : "deleteBoatFromGrid",
		type : "POST",
		data : {"boatID" : "battleship", "ID" : ID, "row" : rowBattleShip, "col" : colBattleShip, "dir" : dirBattleShip},
		success : function(result){
			
			alert("Deleting boat battleship");

		},
		error : function (){
			
		}
	});
	
	//$("#battleship").show();
});

$("#button-aircraft-delete").click(function() {
	var ID = $("#lobbyId").attr("value");
	nBoatsPositioned--;
	checkUserIsReady();
	
	$.ajax({
		url : "deleteBoatFromGrid",
		type : "POST",
		data : {"boatID" : "aircraft", "ID" : ID, "row": rowAircraft, "col" : colAircraft, "dir" : dirAircraft},
		success : function(result){
			
			alert("Deleting boat aircraft");
			
		},
		error : function (){
			
		}
	});
	//$("#aircraft").show();
});
	

	

});

/*
 * TO DO
 */
function deleteShip(id)
{
	
}

function rotateBoat(id) {
	console.log("MY ID IS = " + id);
	
	var pos = 0;
	
	var img = document.getElementById(id);
	if (img.getAttribute("style") == "transform:rotate(90deg); height: 45%; width: 75%;") {
		img.setAttribute("style","transform:rotate(0deg); height: 45%; width: 75%;");
		
		pos = 0;
	} else {
		img.setAttribute("style","transform:rotate(90deg); height: 45%; width: 75%;");
		
		pos = 1;
	}
	
	switch(id) {
    case "destroyer" :
        dirDestroyer = pos;
        break;
    case "submarine" :
    	dirSubmarine = pos;
        break;
    case "cruiser" :
    	disCruiser = pos;
    	break;
    case "battleship" :
    	dirBattleShip = pos;
    	break;
    case "aircraft" :
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
	
	//alert("LE COORDINATE DELLA BARCA SONO "+tmpRow+" e "+tmpCol);
	
	switch(boatName) {
    case "destroyer" :
        rowDestroyer = tmpRow;
        colDestroyer = tmpCol;
        break;
    case "submarine" :
    	rowSubmarine = tmpRow;
    	colSubmarine = tmpCol;
        break;
    case "cruiser" :
    	rowCruiser = tmpRow;
    	colCruiser = tmpCol;
    	break;
    case "battleship" :
    	rowBattleShip = tmpRow;
    	colBattleShip = tmpCol;
    	break;
    case "aircraft" :
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
										+ row + " e col = " + i);
								
								
								if(boatName =="submarine")	
									{
									$("#button-submarine").hide();
									$("#submarine").hide();
									
								ev.target.appendChild(document
										.getElementById(data));
								
								document.getElementById("cellOG-" + row + "-"
										+ i).style.background = 'green';
									}
								else if(boatName =="cruiser")
									{
									$("#button-cruiser").hide();
									$("#cruiser").hide();
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + row + "-"
											+ i).style.background = 'red';
									}
								else if(boatName =="destroyer")
								{
									$("#button-destroyer").hide();
									$("#destroyer").hide();
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + row + "-"
											+ i).style.background = 'yellow';
								}
								else if(boatName =="battleship")
								{
									$("#button-battleship").hide();
									$("#battleship").hide();
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + row + "-"
											+ i).style.background = 'blue';
								}
								else if(boatName =="aircraft")
								{
									$("#button-aircraft").hide();
									$("#aircraft").hide();
									
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + row + "-"
											+ i).style.background = 'orange';
								}
							}

						} else if (direction == 1) { // Vertical
							for (var j = row; j < row + size; j++) {
								console.log("Appendi l'immagine su riga = " + j
										+ " e col = " + col);
								if(boatName =="submarine")	
								{
									$("#button-submarine").hide();
									$("#submarine").hide();
									
								ev.target.appendChild(document
										.getElementById(data));
								
								document.getElementById("cellOG-" + j + "-"
										+ col).style.background = 'green';
								}
								else if(boatName =="cruiser")
								{
									$("#button-cruiser").hide();
									$("#cruiser").hide();
									
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'red';
								}
								else if(boatName =="destroyer")
								{

									$("#button-destroyer").hide();
									$("#destroyer").hide();
									
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'yellow';
								}
								else if(boatName =="battleship")
								{
									$("#button-battleship").hide();
									$("#battleship").hide();
									
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'blue';
								}
								else if(boatName =="aircraft")
								{
									$("#button-aircraft").hide();
									$("#aircraft").hide();
									
									ev.target.appendChild(document
											.getElementById(data));
									
									document.getElementById("cellOG-" + j + "-"
											+ col).style.background = 'orange';
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
	}
	else {
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

