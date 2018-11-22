<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Lobbies</title>
</head>
<body>
Welcome <%= session.getAttribute("username") %>

<div class="container">
  <h2>Join or create a lobby !</h2>
  <div class="list-group">
  
  <div id="lobbies_div"> ${ lobbies }</div>
<!--  	     $("#lobbies_div").load(location.href+" #lobbies_div>*",""); 
		  $("#lobbies_div").html(result); -->
  <script>
  
  function getEventsFromServer() { 
  $.ajax({
	  url: "refreshLobbyList",
	      type: "POST",
	  
	  success: function(result){
		  
		  $("#lobbies_div").html(result);
	     
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
  $(document).ready(getEventsFromServer());
</script>
  
  
  
  
  
  
 <div id="lobbyList"> 
  	<c:forEach items="${ lobbies }" var="lobby">
  		<a class="list-group-item" 
  			href="<c:url value="/join_lobby"> 
  					<c:param name="lobby_owner" value="${lobby.owner}" />
  					<c:param name="lobby_id" value="${lobby.id}" />
  					<c:param name="lobby_challenger" value="${ sessionScope.username }" />
  				  </c:url>"> 
		${lobby.name}
  		</a>
  	</c:forEach>
</div>


  </div>
</div>




<div class="btn-group">
 <a href="<c:url value="/logout"/>"><button type="button" class="btn btn-primary"> Logout </button></a>
 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"> Create Lobby </button>
 
 
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Create the Lobby</h4>
        </div>
        <div class="modal-body">
          <form action="new_lobby" method="POST">
		          	<div class="form-group">
				      <label for="username">Name:</label>
				      <input type="text" class="form-control" id="lobby_name" placeholder="Enter a name" name="lobby_name" required>
				      <input type="hidden" name="lobby_owner" value="${ sessionScope.username }">
	    			</div>
					    		
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
          </form>
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
 
 
 
 
 
 
 
 
 
 
 
</div>
</body>
</html>


