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
 		 min-height: 75rem;
 		 padding-top: 14.5rem;
		}
	</style>
	<script>
  
  function getEventsFromServer() { 
  $.ajax({
	  url: "refreshLobbyList",
      type: "POST",
      dataType: "json",	  
	  success: function(result){
		  
		 // $("#lobbies_div").html(result);
		  
		  var ul = $("<ul></ul>").addClass("list-group");
		  var url = $("#serverUrl").attr("value");
		  //var chall = $("#callenger").attr("value");
		  
		  $.each(result, function(index, value){			  
			  var link = $("<a></a>").addClass("list-group-item").text(value.name)
			  .attr("href", url+"?lobby_id="+value.id);
			  if(value.full == true){
				  link.addClass("list-group-item-danger");
			  }
			  ul.append(link)
		  });
		  
		  $("#lobbyList").html(ul);
		 
	     
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
<body>

 <title>Join or create a lobby !</title>
 
<div class="row">
		
				
			<div class="col-md-6" style=" padding-left:8%; " >
			 
			  <h1 style="font-size: 250%; padding-bottom:3%;color:#429ef4">Lobby available</h1>
			  <div class="list-group">
			  
			  <div id="lobbies_div"></div>
			
			   
			 <hidden hidden id="serverUrl" value="<c:url value="/join_lobby"></c:url>"></hidden>
			
			 <div id="lobbyList" style="width:50%; text-align:center;"> 
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
			</div> <!--  end col-md-6 -->
			
			<div class="col-md-6" style="padding-left:8%;">
		 <h1 style="font-size: 250%; padding-bottom:3%;color:#429ef4">Create a new Lobby</h1>
				 <form action="new_lobby" method="POST" style="width:70%;">
		          	<div class="form-group">
				      
				      <input type="text" class="form-control" id="lobby_name" placeholder="Enter a name" name="lobby_name" required>
				      <input type="hidden" name="lobby_owner" value="${ sessionScope.username }">
	    			</div>
					    		
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value="Create Lobby"></input>
					</div>
          </form>
			</div>
			
				
</div> <!--  end row -->

</body>
</html>


