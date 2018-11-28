<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Battleship</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">


<script type="text/javascript"
	src="resources/assetsGame/javascripts/ext/underscore.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/vendor/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/javascripts/ext/jshashtable-2.1.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/javascripts/ext/jquery.numeric.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/javascripts/ext/jquery.numberformatter.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/javascripts/ext/backbone.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/javascripts/ext/icanhaz.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/javascripts/boatPositioning.js"></script>
<script type="text/javascript"
	src="resources/assetsGame/vendor/bootstrap/js/bootstrap.min.js"></script>


<link href='https://fonts.googleapis.com/css?family=Russo+One'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="resources/assetsGame/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/assetsGame/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/assetsGame/vendor/animate.css">
<link rel="stylesheet" href="resources/assetsGame/stylesheets/main.css"
	type="text/css" media="screen">

</head>

<body>
	<div class="row">


		<div class="col-6">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-6 text-center">
				<div id="container">
					<table class="board table table-responsive animated fadeInUp"
						id="ownerGrid">
						<tbody>
							<tr>
								<td class="cell cell-title-left cell-title-top" id="cellOG-0-0"></td>
								<td class="cell cell-title-top" id="cellOG-0-1">A</td>
								<td class="cell cell-title-top" id="cellOG-0-2">B</td>
								<td class="cell cell-title-top" id="cellOG-0-3">C</td>
								<td class="cell cell-title-top" id="cellOG-0-4">D</td>
								<td class="cell cell-title-top" id="cellOG-0-5">E</td>
								<td class="cell cell-title-top" id="cellOG-0-6">F</td>
								<td class="cell cell-title-top" id="cellOG-0-7">G</td>
								<td class="cell cell-title-top" id="cellOG-0-8">H</td>
								<td class="cell cell-title-top" id="cellOG-0-9">I</td>
								<td class="cell cell-title-top" id="cellOG-0-10">J</td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-1-0">1</td>
								<td class="cell" id="cellOG-1-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-1-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-2-0">2</td>
								<td class="cell" id="cellOG-2-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-2-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-3-0">3</td>
								<td class="cell" id="cellOG-3-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-3-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-4-0">4</td>
								<td class="cell" id="cellOG-4-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-4-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-5-0">5</td>
								<td class="cell" id="cellOG-5-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-5-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-6-0">6</td>
								<td class="cell" id="cellOG-6-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-6-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-7-0">7</td>
								<td class="cell" id="cellOG-7-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-7-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-8-0">8</td>
								<td class="cell" id="cellOG-8-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-8-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-9-0">9</td>
								<td class="cell" id="cellOG-9-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-9-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-10-0">10</td>
								<td class="cell" id="cellOG-10-1" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-2" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-3" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-4" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-5" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-6" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-7" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-8" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-9" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
								<td class="cell" id="cellOG-10-10" ondrop="drop(event)"
									ondragover="allowDrop(event)"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>


	</div>

	<h2 id="message"></h2>



	<div>
		<button id="button1">Boat 1</button>
		<button id="button2">Boat 2</button>
		<button id="button3">Boat 3</button>
		<button id="button4">Boat 4</button>
		<button id="button5">Boat 5</button>

		<img id="boat-1" src="resources/assetsGame/images/boat.png"
			draggable="true" ondragstart="drag(event)"
			style="transform: rotate(0deg); height: 50px; width: 50%;"> <img
			id="boat-2" src="resources/assetsGame/images/boat.png"
			draggable="true" ondragstart="drag(event)"
			style="transform: rotate(0deg); height: 50px; width: 50%;"> <img
			id="boat-3" src="resources/assetsGame/images/boat.png"
			draggable="true" ondragstart="drag(event)"
			style="transform: rotate(0deg); height: 50px; width: 50%;"> <img
			id="boat-4" src="resources/assetsGame/images/boat.png"
			draggable="true" ondragstart="drag(event)"
			style="transform: rotate(0deg); height: 50px; width: 50%;"> <img
			id="boat-5" src="resources/assetsGame/images/boat.png"
			draggable="true" ondragstart="drag(event)"
			style="transform: rotate(0deg); height: 50px; width: 50%;">

	</div>


	<!--  End choose ships -->



	<button id="IR" type="button" onclick="waitingStart()">I'm
		Ready !</button>

	<hidden hidden id="lobbyId" value="${lobby.id}"></hidden>


</body>

</html>
