<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Battleship</title>
<jsp:include page="nav_bar.jsp"></jsp:include>
<style type="text/css">
body {
	
	padding-top: 5%;
	padding-left: 2%;
}
</style>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">


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
<div class="row" style="padding-left:60%; padding-bottom: 3%; padding-top: 5%;">
			<div class="col-md-12">
				<input type="button" id="IR" class="btn btn-success"  onclick="waitingStart()" value="Ready!">
					</input>
			
				<hidden hidden id="lobbyId" value="${lobby.id}"></hidden>
			</div>

</div>

	<div class="row">
	
<div class="col-md-4">
	
	<div class="row containerShip" id="destroyerContainer">
		
		
								<div class="row-center">
								<h3 class="nameShip">Destoyer</h3>
								</div>
						
							<div class="row containerButton">
							
							<div class="col-md-8">
													<img id="destroyer" src="resources/assetsGame/images/boat.png"
													draggable="true" ondragstart="drag(event)"
													class="shipImage" style="transform: rotate(0deg); 
	height: 45%; 
	width: 75%;
	padding-left: 10%;"
													> 
											</div>
											<div class="col-md-4" style="padding-bottom:3%">
												<button id="button-destroyer">Rotate</button>
											</div>
											
											
											<div class="col-md-2">
												<button id="button-destroyer-delete">Delete</button>
											</div>
											
											
								</div>
							
								
							
								
						
	</div>	
	
	<!--  sub marine -->
	<div class="row containerShip" id="submarineContainer">
		
		
								<div class="row-center">
								<h3 class="nameShip">Submarine</h3>
								</div>
						
							<div class="row containerButton">
							
							<div class="col-md-8">
													<img id="submarine" src="resources/assetsGame/images/boat.png"
													draggable="true" ondragstart="drag(event)"
													style="transform: rotate(0deg); 
	height: 45%; 
	width: 75%;
	padding-left: 10%;"
													> 
											</div>
											<div class="col-md-4" style="padding-bottom:3%">
												<button id="button-submarine">Rotate</button>
											</div>
											
											
											<div class="col-md-2">
												<button id="button-submarine-delete">Delete</button>
											</div>
											
											
								</div>
							
								
							
								
						
	</div>	
	
	<!--  end sub marine -->
	
	
	
	
	
	<!--  sub cruiser -->
	<div class="row containerShip" id="cruiserContainer">
		
		
								<div class="row-center">
								<h3 class="nameShip">Cruiser</h3>
								</div>
						
							<div class="row containerButton">
							
							<div class="col-md-8">
													<img id="cruiser" src="resources/assetsGame/images/boat.png"
													draggable="true" ondragstart="drag(event)"
													style="transform: rotate(0deg); 
	height: 45%; 
	width: 75%;
	padding-left: 10%;"
													> 
											</div>
											<div class="col-md-4" style="padding-bottom:3%">
												<button id="button-cruiser">Rotate</button>
											</div>
											
											
											<div class="col-md-2">
												<button id="button-cruiser-delete">Delete</button>
											</div>
											
											
								</div>
							
								
							
								
						
	</div>	
	
	<!--  end cruiser -->
	
	
	<!--  battleship -->
	<div class="row containerShip" id="battleshipContainer">
		
		
								<div class="row-center">
								<h3 class="nameShip">Battleship</h3>
								</div>
						
							<div class="row containerButton">
							
							<div class="col-md-8">
													<img id="battleship" src="resources/assetsGame/images/boat.png"
													draggable="true" ondragstart="drag(event)"
													style="transform: rotate(0deg); 
	height: 45%; 
	width: 75%;
	padding-left: 10%;"
													> 
											</div>
											<div class="col-md-4" style="padding-bottom:3%">
												<button id="button-battleship">Rotate</button>
											</div>
											
											
											<div class="col-md-2">
												<button id="button-battleship-delete">Delete</button>
											</div>
											
											
								</div>
							
								
							
								
						
	</div>	
	
	<!--  battleship -->
	
	<!--  sub marine -->
	<div class="row containerShip" id="aircraftContainer">
		
		
								<div class="row-center">
								<h3 class="nameShip">Aircraft</h3>
								</div>
						
							<div class="row containerButton">
							
							<div class="col-md-8">
													<img id="aircraft" src="resources/assetsGame/images/boat.png"
													draggable="true" ondragstart="drag(event)"
													style="transform: rotate(0deg); 
	height: 45%; 
	width: 75%;
	padding-left: 10%;"
													> 
											</div>
											<div class="col-md-4" style="padding-bottom:3%">
												<button id="button-aircraft">Rotate</button>
											</div>
											
											
											<div class="col-md-2">
												<button id="button-aircraft-delete">Delete</button>
											</div>
											
											
								</div>
							
								
							
								
						
	</div>	
	
	<!--  end sub marine -->
		

</div>

		<div class="col-md-8">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
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

	<h2>${grid.hasShip(1,1)}</h2>



	


	<!--  End choose ships -->

	<c:forEach var="i" begin="1" end="10">
		<c:forEach var="j" begin="1" end="10">
			<c:if test="${grid.hasShip(i,j)}">
				<c:choose>

					<c:when test="${grid.tipeShip(i,j) == 2}">
						<script>
					$("#cellOG-"+${i}+"-"+${j}).attr("class","cell showBoat destroyer");
			</script>
					</c:when>
					<c:when test="${grid.tipeShip(i,j) == 3}">
						<script>
					$("#cellOG-"+${i}+"-"+${j}).attr("class","class showBoat cruiser");
			</script>
					</c:when>
					<c:when test="${grid.tipeShip(i,j) == 4}">
						<script>
					$("#cellOG-"+${i}+"-"+${j}).attr("class","cell showBoat battleship");
			</script>
					</c:when>
					<c:when test="${grid.tipeShip(i,j) == 5}">
						<script>
					$("#cellOG-"+${i}+"-"+${j}).attr("class","cell showBoat aircraft-carrier");
			</script>
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>

			</c:if>


		</c:forEach>
	</c:forEach>


</body>

</html>
