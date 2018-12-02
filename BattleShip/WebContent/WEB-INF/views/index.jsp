<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<head>
<jsp:include page="nav_bar.jsp"></jsp:include>
<script src="resources/assets/js/main.js"></script>
<style type="text/css">
body {
	
	padding-top: 14.5rem;
	background-image: url("http://localhost:8080/BattleShip/resources/assets/css/images/nave.png");
	background-repeat: no-repeat;
	background-size: cover; 
}
.list-group-item-secondary{
	background-color: #f5f5f5;
}
</style>
<script>
	function getEventsFromServer() {
		$.ajax({
			url : "refreshLobbyList",
			type : "POST",
			dataType : "json",
			success : function(result) {

				// $("#lobbies_div").html(result);

				// var ul = $("<ul></ul>").addClass("list-group");
				var url = $("#serverUrl").attr("value");
				//var chall = $("#callenger").attr("value");
				$("#lobbyList").html("")
				$.each(result, function(index, value) {
					var link = $("<a></a>").addClass("list-group-item text-left").text(
							value.name).attr("href",
							url + "?lobby_id=" + value.id);
					var badge =  $("<a></a>").addClass("badge").text(value.owner);
					link.append(badge);
					if (value.full == true) {
						link.addClass("list-group-item-danger");
					}else if(index%2>0){
						link.addClass("list-group-item-secondary");
					}
					$("#lobbyList").append(link)
				});

				//  $("#lobbyList").html(ul);

				setTimeout(function() {
					getEventsFromServer();
				}, 5000);

			},
			error : function(e) {
				alert(e.responseText);
				console.log("JOIN ERROR: ", e);

				setTimeout(function() {
					getEventsFromServer();
				}, 15000);
			}
		});
	}
	function showModalInst() {
		var first = $("#firstTime").attr('value');
		if (first == "true") {
			$('#modal').modal('show');
		}
	}
	$(document).ready(function() {
		getEventsFromServer();
		showModalInst();

	});
</script>
<body>

	<title>Join or create a lobby !</title>

	<div class="row">


		<div class="col-md-6" style="padding-left: 8%;">

			<h1 style="font-size: 250%; padding-bottom: 3%; color: white">Lobby
				available</h1>
			<div class="list-group">

				<div id="lobbies_div"></div>


				<hidden hidden id="serverUrl"
					value="<c:url value="/join_lobby"></c:url>"></hidden>

				<!--	 <div class="table-responsive mt-1">
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Creator</th>
					</tr>
				</thead>
				<tbody class="table-striped">
					<c:forEach items="${ lobbies }" var="lobby">
						<tr>
							
							<td><c:out value="${ lobby.name}"></c:out></td>
							<td><c:out value="${lobby.owner }"></c:out></td>							
						</tr>					
					</c:forEach>
				</tbody>
			</table>
		</div>
			 -->
				<div id="lobbyList" style="width: 100%; text-align: center;">
					<c:forEach items="${ lobbies }" var="lobby" varStatus="loop">
						<c:choose>
							<c:when
								test="${loop.index%2 > 0}">
								<a class="list-group-item text-left list-group-item-secondary"
									href="<c:url value="/join_lobby"> 
			  					<c:param name="lobby_owner" value="${lobby.owner}" />
			  					<c:param name="lobby_id" value="${lobby.id}" />
			  					<c:param name="lobby_challenger" value="${ sessionScope.username }" />
			  				  </c:url>">
									<span>${lobby.name}</span> <span
									class="badge">${lobby.owner}</span>
								</a>
							</c:when>
							<c:otherwise>
								<a class="list-group-item text-left"
									href="<c:url value="/join_lobby"> 
			  					<c:param name="lobby_owner" value="${lobby.owner}" />
			  					<c:param name="lobby_id" value="${lobby.id}" />
			  					<c:param name="lobby_challenger" value="${ sessionScope.username }" />
			  				  </c:url>">
									<span>${lobby.name}</span> <span
									class="badge">${lobby.owner}</span>
								</a>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</div>


			</div>
		</div>
		<!--  end col-md-6 -->

		<div class="col-md-6" style="padding-left: 8%;">
			<h1 style="font-size: 250%; padding-bottom: 3%; color: white">Create
				a new Lobby</h1>
			<form action="new_lobby" method="POST" style="width: 70%;">
				<div class="form-group">

					<input type="text" class="form-control" id="lobby_name"
						placeholder="Enter a name" name="lobby_name" required> <input
						type="hidden" name="lobby_owner"
						value="${ sessionScope.username }">
				</div>

				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="Create Lobby"></input>
				</div>
			</form>
		</div>


	</div>
	<!--  end row -->
	<hidden hidden id="firstTime" value="${fTime}"></hidden>
	<!-- The modal -->
	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabel">BattleShip
						Instructions</h4>
				</div>
				<div class="modal-body">
					<div>
						<h2>Create or join to a lobby</h2>
						<br> To start playing you have two options: - Create your own
						lobby in which you wait for a challenger to start playing<br>
						- Join a game already created by selecting it from the list of
						lobbies<br>
					</div>
					<br>
					<div>
						<h2>Ships positioning</h2>
						<br> When the creator starts it is necessary to position the
						boats first. You must decide the orientation (Vertical,
						horizontal) with the button rotate, then just drag and drop the
						ship in the desired position, it is possible to eliminate it
						later.
					</div>
					<br>
					<div>
						<h2>Playing</h2>
						<br> Once on the field of play you must wait your turn to
						make the shot. If you hit, you keep your turn otherwise the turn
						pass to the opponent. The game ends when one of the players
						completely destroys the rival ships.
					</div>
					<br>
					<div class="text-center">GOOD LUCK</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


