<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<html>
	<head>
	<title>Battleship MVC</title>
	<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
	<jsp:include page="nav_bar.jsp"></jsp:include>
	 <link rel="stylesheet" href="resources/assets/css/main.css" />
		  
		
		
		
	<script src="resources/assets/js/main.js"></script>
	<script src="resources/assets/js/navbar.js"></script>
	 <link rel="stylesheet" href="resources/assets/css/modal.css" />
<body>



	<!-- Header -->
		
			<header id="header" class="alt">

				<div class="inner">
					<h1>Battleship MVC</h1>
					<p>A final project developed for the Agile Software Development for Enterprise</p>
				</div>			
			</header>


		
		
		
		<!--  COUNTER -->
		<div class="row">
		<div class="col-12 col-sm-12 ">
		<div class="wrapper">
		<div class="col-4 col-sm-4">
    <div class="counter col_fourth">
      <i class="fa fa-code fa-2x"></i>
      <h2 class="timer count-title count-number" data-to="${ userRegistered }" data-speed="1500"></h2>
       <p class="count-text ">Users registered</p>
    </div>
    </div>
<div class="col-4 col-sm-4 ">
    <div class="counter col_fourth">
      <i class="fa fa-coffee fa-2x"></i>
      <h2 class="timer count-title count-number" data-to="${ userOnline }" data-speed="1500"></h2>
      <p class="count-text ">Users connected</p>
    </div>
</div>
<div class="col-4 col-sm-4">
    <div class="counter col_fourth">
      <i class="fa fa-lightbulb-o fa-2x"></i>
      <h2 class="timer count-title count-number" data-to="${ availableLobby }" data-speed="1500"></h2>
      <p class="count-text ">Lobby available</p>
    </div>
    </div>

    
</div>
</div>
</div>

			
		
	
		

				<!-- Footer -->
					<footer id="footer">
						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
							
						</ul>
						
					</footer>

			</div>

  

	</body>
</html>