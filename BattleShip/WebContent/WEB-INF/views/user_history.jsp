<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<head>
<title>BattleShip ${user.username }'s History </title>
<jsp:include page="nav_bar.jsp"></jsp:include>
<script src="resources/assets/js/main.js"></script>
<script type="text/javascript">
	function selectChange() {

	}

	$(document).ready(function() {
		var amount = $("#amount").attr("value");
		$("select").val(amount)
		$("select").change(function() {
			var value = this.value;
			var url = $("#serverUrl").attr("value");
			var page = $("#page").attr("value");
			window.location = url + "?amount=" + value
		});

	});
</script>
<style type="text/css">
body {
	min-height: 75rem;
	padding-top: 4.5rem;
}

.table .thead-dark th {
	color: #fff;
	background-color: #212529;
	border-color: #32383e;
}

.history-title {
	margin-bottom: 10px;
}

.btn-toolbar .btn-group {
	float: none;
}

select#amount{
	width: 20%;
	display: inline;
}


</style>
<body>
	<hidden hidden id="serverUrl"
		value="<c:url value="/userHistory"></c:url>"></hidden>
	<hidden hidden id="page" value="${page }"></hidden>
	<hidden hidden id="amount" value="${amount }"></hidden>
	
	<div class="container" style="padding-top:5%;">
		<div class="row">
			<div class="col-md-8">
				<h2 class="history-title">Your Match History</h2>
			</div>
			
			<div class="col-md-4 text-right">
				<b>Number of rows:</b> <select class="form-control" id="amount">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
			</div>
		</div>

		<div class="table-responsive mt-1">
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Match Name</th>
						<th scope="col">Creator</th>
						<th scope="col">Challenger</th>
						<th scope="col">Start Time</th>
						<th scope="col">End Time</th>
						<th scope="col">Won</th>
					</tr>
				</thead>
				<tbody class="table-striped">
					<c:forEach items="${ matches }" var="match">
						<tr>
							<!--  <th scope="row">1</th>-->
							<td><c:out value="${ match.matchName }"></c:out></td>
							<td><c:out value="${ match.creator.username }"></c:out></td>
							<td><c:out value="${match.challenger.username }"></c:out></td>
							<td><fmt:formatDate type="both" dateStyle="short"
									timeStyle="short" value="${match.startTime}" /></td>
							<td><fmt:formatDate type="both" dateStyle="short"
									timeStyle="short" value="${match.endTime}" /></td>
							<td><c:choose>
									<c:when
										test="${match.wonCreator && match.creator.username == user.username}">
									Yes
								</c:when>
									<c:otherwise>
									No
								</c:otherwise>
								</c:choose></td>
						</tr>

					</c:forEach>

				</tbody>
			</table>
		</div>


		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4 text-center">
				<div class="btn-toolbar" role="toolbar"
					aria-label="Toolbar with button groups">
					<div class="btn-group mr-2" role="group" aria-label="First group">
						<a href="userHistory?page=1&amount=${amount}">
							<button type="button" class="btn">
								<span class="glyphicon glyphicon-chevron-left"
									aria-hidden="true"></span> <span
									class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>

							</button>
						</a> <a href="userHistory?page=${ page-1}&amount=${amount}">
							<button type="button" class="btn" aria-label="Left Align">
								<span class="glyphicon glyphicon-chevron-left"
									aria-hidden="true"></span>
							</button>
						</a>
					</div>
					<c:out value="${page}"></c:out>
					of
					<c:out value="${totalPages}"></c:out>
					<div class="btn-group mr-2" role="group" aria-label="Second group">
						<a href="userHistory?page=${page+1 }&amount=${amount}">
							<button type="button" class="btn" aria-label="Left Align">
								<span class="glyphicon glyphicon-chevron-right"
									aria-hidden="true"></span>
							</button>
						</a> <a href="userHistory?page=${ totalPages}&amount=${amount}">
							<button type="button" class="btn">
								<span class="glyphicon glyphicon-chevron-right"
									aria-hidden="true"></span> <span
									class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							</button>
						</a>
					</div>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
		<!-- <hidden hidden id="serverUrl"
				value="<c:url value="/join_lobby"></c:url>"></hidden>
			<!-- <hidden hidden id="callenger" value="${ sessionScope.username }"></hidden> 
			<div id="lobbyList">
				<c:forEach items="${ lobbies }" var="lobby">
					<a class="list-group-item"
						href="<c:url value="/join_lobby"> 
  					<c:param name="lobby_owner" value="${lobby.owner}" />
  					<c:param name="lobby_id" value="${lobby.id}" />
  					<c:param name="lobby_challenger" value="${ sessionScope.username }" />
  				  </c:url>">
						${lobby.name} </a>
				</c:forEach>
			</div>

 -->
	</div>

</body>
</html>


