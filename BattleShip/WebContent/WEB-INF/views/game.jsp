<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<jsp:include page="nav_bar.jsp"></jsp:include>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="nav_bar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/assets/css/modal.css" />
<style>
body{
padding-top: 15%;
	padding-left: 2%;

}


</style>
<script type="text/javascript"
	src="resources/assetsGame/javascripts/gameScript.js"></script>
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
	type="text/css" media="screen" charset="utf-8">

</head>

<body>

			
	<div class="row">	
	
		<div class="col-md-6">
			<a 
			href="<c:url value="/leaveGame">
				 				<c:param name="lobby_id" value="${lobby.id}" />
				 		  </c:url>">
			<input type="image"  src="resources/assets/css/images/leaveGame.png" style="width:10%; height:5%;"></input>
		</a>
		</div>
		
		
							<div class="col-md-6">	
										<c:choose>
											<c:when test="${user.username == lobby.owner}">
												<h3><span id="turnMessage" class="label label-success">It's your turn</span></h3>
											</c:when>
											<c:otherwise>
												<h3><span id="turnMessage" class="label label-danger">Wait for your turn...</span></h3>
											</c:otherwise>
										</c:choose>
						</div>	
	</div>
	
	<div class="row">

		<div class="col-6">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-6 text-center">
				<div id="container">
					<table class="board table table-responsive animated fadeInUp"
						id="challengerGrid">
						<tbody>
							<tr>
								<td class="cell cell-title-left cell-title-top" id="cellCG-0-0"></td>
								<td class="cell cell-title-top" id="cellCG-0-1">A</td>
								<td class="cell cell-title-top" id="cellCG-0-2">B</td>
								<td class="cell cell-title-top" id="cellCG-0-3">C</td>
								<td class="cell cell-title-top" id="cellCG-0-4">D</td>
								<td class="cell cell-title-top" id="cellCG-0-5">E</td>
								<td class="cell cell-title-top" id="cellCG-0-6">F</td>
								<td class="cell cell-title-top" id="cellCG-0-7">G</td>
								<td class="cell cell-title-top" id="cellCG-0-8">H</td>
								<td class="cell cell-title-top" id="cellCG-0-9">I</td>
								<td class="cell cell-title-top" id="cellCG-0-10">J</td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-1-0">1</td>
								<td class="cell" id="cellCG-1-1"></td>
								<td class="cell" id="cellCG-1-2"></td>
								<td class="cell" id="cellCG-1-3"></td>
								<td class="cell" id="cellCG-1-4"></td>
								<td class="cell" id="cellCG-1-5"></td>
								<td class="cell" id="cellCG-1-6"></td>
								<td class="cell" id="cellCG-1-7"></td>
								<td class="cell" id="cellCG-1-8"></td>
								<td class="cell" id="cellCG-1-9"></td>
								<td class="cell" id="cellCG-1-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-2-0">2</td>
								<td class="cell" id="cellCG-2-1"></td>
								<td class="cell" id="cellCG-2-2"></td>
								<td class="cell" id="cellCG-2-3"></td>
								<td class="cell" id="cellCG-2-4"></td>
								<td class="cell" id="cellCG-2-5"></td>
								<td class="cell" id="cellCG-2-6"></td>
								<td class="cell" id="cellCG-2-7"></td>
								<td class="cell" id="cellCG-2-8"></td>
								<td class="cell" id="cellCG-2-9"></td>
								<td class="cell" id="cellCG-2-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-3-0">3</td>
								<td class="cell" id="cellCG-3-1"></td>
								<td class="cell" id="cellCG-3-2"></td>
								<td class="cell" id="cellCG-3-3"></td>
								<td class="cell" id="cellCG-3-4"></td>
								<td class="cell" id="cellCG-3-5"></td>
								<td class="cell" id="cellCG-3-6"></td>
								<td class="cell" id="cellCG-3-7"></td>
								<td class="cell" id="cellCG-3-8"></td>
								<td class="cell" id="cellCG-3-9"></td>
								<td class="cell" id="cellCG-3-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-4-0">4</td>
								<td class="cell" id="cellCG-4-1"></td>
								<td class="cell" id="cellCG-4-2"></td>
								<td class="cell" id="cellCG-4-3"></td>
								<td class="cell" id="cellCG-4-4"></td>
								<td class="cell" id="cellCG-4-5"></td>
								<td class="cell" id="cellCG-4-6"></td>
								<td class="cell" id="cellCG-4-7"></td>
								<td class="cell" id="cellCG-4-8"></td>
								<td class="cell" id="cellCG-4-9"></td>
								<td class="cell" id="cellCG-4-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-5-0">5</td>
								<td class="cell" id="cellCG-5-1"></td>
								<td class="cell" id="cellCG-5-2"></td>
								<td class="cell" id="cellCG-5-3"></td>
								<td class="cell" id="cellCG-5-4"></td>
								<td class="cell" id="cellCG-5-5"></td>
								<td class="cell" id="cellCG-5-6"></td>
								<td class="cell" id="cellCG-5-7"></td>
								<td class="cell" id="cellCG-5-8"></td>
								<td class="cell" id="cellCG-5-9"></td>
								<td class="cell" id="cellCG-5-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-6-0">6</td>
								<td class="cell" id="cellCG-6-1"></td>
								<td class="cell" id="cellCG-6-2"></td>
								<td class="cell" id="cellCG-6-3"></td>
								<td class="cell" id="cellCG-6-4"></td>
								<td class="cell" id="cellCG-6-5"></td>
								<td class="cell" id="cellCG-6-6"></td>
								<td class="cell" id="cellCG-6-7"></td>
								<td class="cell" id="cellCG-6-8"></td>
								<td class="cell" id="cellCG-6-9"></td>
								<td class="cell" id="cellCG-6-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-7-0">7</td>
								<td class="cell" id="cellCG-7-1"></td>
								<td class="cell" id="cellCG-7-2"></td>
								<td class="cell" id="cellCG-7-3"></td>
								<td class="cell" id="cellCG-7-4"></td>
								<td class="cell" id="cellCG-7-5"></td>
								<td class="cell" id="cellCG-7-6"></td>
								<td class="cell" id="cellCG-7-7"></td>
								<td class="cell" id="cellCG-7-8"></td>
								<td class="cell" id="cellCG-7-9"></td>
								<td class="cell" id="cellCG-7-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-8-0">8</td>
								<td class="cell" id="cellCG-8-1"></td>
								<td class="cell" id="cellCG-8-2"></td>
								<td class="cell" id="cellCG-8-3"></td>
								<td class="cell" id="cellCG-8-4"></td>
								<td class="cell" id="cellCG-8-5"></td>
								<td class="cell" id="cellCG-8-6"></td>
								<td class="cell" id="cellCG-8-7"></td>
								<td class="cell" id="cellCG-8-8"></td>
								<td class="cell" id="cellCG-8-9"></td>
								<td class="cell" id="cellCG-8-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-9-0">9</td>
								<td class="cell" id="cellCG-9-1"></td>
								<td class="cell" id="cellCG-9-2"></td>
								<td class="cell" id="cellCG-9-3"></td>
								<td class="cell" id="cellCG-9-4"></td>
								<td class="cell" id="cellCG-9-5"></td>
								<td class="cell" id="cellCG-9-6"></td>
								<td class="cell" id="cellCG-9-7"></td>
								<td class="cell" id="cellCG-9-8"></td>
								<td class="cell" id="cellCG-9-9"></td>
								<td class="cell" id="cellCG-9-10"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellCG-10-0">10</td>
								<td class="cell" id="cellCG-10-1"></td>
								<td class="cell" id="cellCG-10-2"></td>
								<td class="cell" id="cellCG-10-3"></td>
								<td class="cell" id="cellCG-10-4"></td>
								<td class="cell" id="cellCG-10-5"></td>
								<td class="cell" id="cellCG-10-6"></td>
								<td class="cell" id="cellCG-10-7"></td>
								<td class="cell" id="cellCG-10-8"></td>
								<td class="cell" id="cellCG-10-9"></td>
								<td class="cell" id="cellCG-10-10"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>



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
								<td class="cell" id="cellOG-1-1"></td>
								<td class="cell" id="cellOG-1-2"></td>
								<td class="cell" id="cellOG-1-3"  
									 ></td>
								<td class="cell" id="cellOG-1-4"  
									 ></td>
								<td class="cell" id="cellOG-1-5"  
									 ></td>
								<td class="cell" id="cellOG-1-6"  
									 ></td>
								<td class="cell" id="cellOG-1-7"  
									 ></td>
								<td class="cell" id="cellOG-1-8"  
									 ></td>
								<td class="cell" id="cellOG-1-9"  
									 ></td>
								<td class="cell" id="cellOG-1-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-2-0">2</td>
								<td class="cell" id="cellOG-2-1"  
									 ></td>
								<td class="cell" id="cellOG-2-2"  
									 ></td>
								<td class="cell" id="cellOG-2-3"  
									 ></td>
								<td class="cell" id="cellOG-2-4"  
									 ></td>
								<td class="cell" id="cellOG-2-5"  
									 ></td>
								<td class="cell" id="cellOG-2-6"  
									 ></td>
								<td class="cell" id="cellOG-2-7"  
									 ></td>
								<td class="cell" id="cellOG-2-8"  
									 ></td>
								<td class="cell" id="cellOG-2-9"  
									 ></td>
								<td class="cell" id="cellOG-2-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-3-0">3</td>
								<td class="cell" id="cellOG-3-1"  
									 ></td>
								<td class="cell" id="cellOG-3-2"  
									 ></td>
								<td class="cell" id="cellOG-3-3"  
									 ></td>
								<td class="cell" id="cellOG-3-4"  
									 ></td>
								<td class="cell" id="cellOG-3-5"  
									 ></td>
								<td class="cell" id="cellOG-3-6"  
									 ></td>
								<td class="cell" id="cellOG-3-7"  
									 ></td>
								<td class="cell" id="cellOG-3-8"  
									 ></td>
								<td class="cell" id="cellOG-3-9"  
									 ></td>
								<td class="cell" id="cellOG-3-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-4-0">4</td>
								<td class="cell" id="cellOG-4-1"  
									 ></td>
								<td class="cell" id="cellOG-4-2"  
									 ></td>
								<td class="cell" id="cellOG-4-3"  
									 ></td>
								<td class="cell" id="cellOG-4-4"  
									 ></td>
								<td class="cell" id="cellOG-4-5"  
									 ></td>
								<td class="cell" id="cellOG-4-6"  
									 ></td>
								<td class="cell" id="cellOG-4-7"  
									 ></td>
								<td class="cell" id="cellOG-4-8"  
									 ></td>
								<td class="cell" id="cellOG-4-9"  
									 ></td>
								<td class="cell" id="cellOG-4-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-5-0">5</td>
								<td class="cell" id="cellOG-5-1"  
									 ></td>
								<td class="cell" id="cellOG-5-2"  
									 ></td>
								<td class="cell" id="cellOG-5-3"  
									 ></td>
								<td class="cell" id="cellOG-5-4"  
									 ></td>
								<td class="cell" id="cellOG-5-5"  
									 ></td>
								<td class="cell" id="cellOG-5-6"  
									 ></td>
								<td class="cell" id="cellOG-5-7"  
									 ></td>
								<td class="cell" id="cellOG-5-8"  
									 ></td>
								<td class="cell" id="cellOG-5-9"  
									 ></td>
								<td class="cell" id="cellOG-5-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-6-0">6</td>
								<td class="cell" id="cellOG-6-1"  
									 ></td>
								<td class="cell" id="cellOG-6-2"  
									 ></td>
								<td class="cell" id="cellOG-6-3"  
									 ></td>
								<td class="cell" id="cellOG-6-4"  
									 ></td>
								<td class="cell" id="cellOG-6-5"  
									 ></td>
								<td class="cell" id="cellOG-6-6"  
									 ></td>
								<td class="cell" id="cellOG-6-7"  
									 ></td>
								<td class="cell" id="cellOG-6-8"  
									 ></td>
								<td class="cell" id="cellOG-6-9"  
									 ></td>
								<td class="cell" id="cellOG-6-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-7-0">7</td>
								<td class="cell" id="cellOG-7-1"  
									 ></td>
								<td class="cell" id="cellOG-7-2"  
									 ></td>
								<td class="cell" id="cellOG-7-3"  
									 ></td>
								<td class="cell" id="cellOG-7-4"  
									 ></td>
								<td class="cell" id="cellOG-7-5"  
									 ></td>
								<td class="cell" id="cellOG-7-6"  
									 ></td>
								<td class="cell" id="cellOG-7-7"  
									 ></td>
								<td class="cell" id="cellOG-7-8"  
									 ></td>
								<td class="cell" id="cellOG-7-9"  
									 ></td>
								<td class="cell" id="cellOG-7-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-8-0">8</td>
								<td class="cell" id="cellOG-8-1"  
									 ></td>
								<td class="cell" id="cellOG-8-2"  
									 ></td>
								<td class="cell" id="cellOG-8-3"  
									 ></td>
								<td class="cell" id="cellOG-8-4"  
									 ></td>
								<td class="cell" id="cellOG-8-5"  
									 ></td>
								<td class="cell" id="cellOG-8-6"  
									 ></td>
								<td class="cell" id="cellOG-8-7"  
									 ></td>
								<td class="cell" id="cellOG-8-8"  
									 ></td>
								<td class="cell" id="cellOG-8-9"  
									 ></td>
								<td class="cell" id="cellOG-8-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-9-0">9</td>
								<td class="cell" id="cellOG-9-1"  
									 ></td>
								<td class="cell" id="cellOG-9-2"  
									 ></td>
								<td class="cell" id="cellOG-9-3"  
									 ></td>
								<td class="cell" id="cellOG-9-4"  
									 ></td>
								<td class="cell" id="cellOG-9-5"  
									 ></td>
								<td class="cell" id="cellOG-9-6"  
									 ></td>
								<td class="cell" id="cellOG-9-7"  
									 ></td>
								<td class="cell" id="cellOG-9-8"  
									 ></td>
								<td class="cell" id="cellOG-9-9"  
									 ></td>
								<td class="cell" id="cellOG-9-10"  
									 ></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cellOG-10-0">10</td>
								<td class="cell" id="cellOG-10-1"  
									 ></td>
								<td class="cell" id="cellOG-10-2"  
									 ></td>
								<td class="cell" id="cellOG-10-3"  
									 ></td>
								<td class="cell" id="cellOG-10-4"  
									 ></td>
								<td class="cell" id="cellOG-10-5"  
									 ></td>
								<td class="cell" id="cellOG-10-6"  
									 ></td>
								<td class="cell" id="cellOG-10-7"></td>
								<td class="cell" id="cellOG-10-8"></td>
								<td class="cell" id="cellOG-10-9"></td>
								<td class="cell" id="cellOG-10-10"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>


	</div>

	<h2 id="message"></h2>
	

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



	<!-- The modal -->
	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel" aria-hidden="true">
		
		<div class="modal-dialog" role="document" style="background-color:#232b2b; background-size:cover; z-inedx:4;">
			<div class="modal-content" style="background">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabel">BattleShip MVC</h4>
				</div>
				<div class="modal-body" >
				<h2 id="labelWin" style="color:#429ef4;">You are the winner!!!!</h2>
				<h2 id="labelLoose" style="color:#429ef4;">You loose!!!</h2></div>
				<h2 id="arbitraryWin" style="color:#429ef4;">Match interrupted, your challenger left the game !!</h2>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>  <!--  end modal -->
	
	
	
	

	<hidden hidden id="lobbyId"
		value="${lobby.id}"></hidden>
		
		
		
		
</body>

</html>
