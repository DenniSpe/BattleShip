<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<html>
	<head>
		<title>Battleship MVC</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		
		 <link rel="stylesheet" href="resources/assets/css/main.css" />
		  <link rel="stylesheet" href="resources/assets/css/carousel3d.css" />

		<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  <link rel='stylesheet' href='https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css'>
  
	


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    
	</head>
	
	<body>
<body>
<div class="navbar navbar-inverse navbar-fixed-top opaque-navbar">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navMain">
  <span class="glyphicon glyphicon-chevron-right" style="color:white;"></span>
    
  </button>
      <a class="pull-left" href="#"><img class="img-responsive2" src="resources/assets/css/images/logo.png"  width= 170px></a>
    </div>
    <div class="collapse navbar-collapse" id="navMain">
      <ul class="nav navbar-nav pull-right">
        <li class="active"><a href="#">Home</a></li>
     
        <li><a href="#" data-toggle="modal" data-target=".log-sign">Log in</a></li> 
           <li><a href="#">About Us</a></li>
      
      </ul>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade bs-modal-sm log-sign" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        
        <div class="bs-example bs-example-tabs">
            <ul id="myTab" class="nav nav-tabs">
              <li id="tab1" class=" active tab-style login-shadow "><a href="#signin" data-toggle="tab">Log In</a></li>
              <li id="tab2" class=" tab-style "><a href="#signup" data-toggle="tab">Sign Up</a></li>
              
            </ul>
        </div>
      <div class="modal-body">
        <div id="myTabContent" class="tab-content">
       
        <div class="tab-pane fade active in" id="signin">
            <form class="form-horizontal">
            <fieldset>
            <!-- Sign In Form -->
            <!-- Text input-->
              
               <div class="group">
<input required="" class="input" type="text"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">Email address</label></div>
              
              
            <!-- Password input-->
            <div class="group">
<input required="" class="input" type="password"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">Password</label>
    </div>
<em>minimum 6 characters</em>

           <div class="forgot-link">
            <a href="#forgot" data-toggle="modal" data-target="#forgot-password"> I forgot my password</a>
            </div>
            

            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="signin"></label>
              <div class="controls">
              <form method="POST" action="login">
                <button id="signin" name="signin" class="btn btn-primary btn-block" type="submit">Log In</button>
             	</form>
              </div>
            </div>
    
<c:if test="${not empty error}">
<div class="alert alert-danger" role="alert">
 
</div>
</c:if>
            </fieldset>
            </form>
        </div>
          
          
        <div class="tab-pane fade" id="signup">
            <form class="form-horizontal">
            <fieldset>
            <!-- Sign Up Form -->
            <!-- Text input-->
            <div class="group">
<input required="" class="input" type="text"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">First Name</label></div>
            
            <!-- Text input-->
            <div class="group">
<input required="" class="input" type="text"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">Last Name</label></div>
            
            <!-- Password input-->
            <div class="group">
<input required="" class="input" type="text"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">Email</label></div>
            
            <!-- Text input-->
            <div class="group">
<input required="" class="input" type="password"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">Password</label></div>
              <em>1-8 Characters</em>
            
              <div class="group2">
<input required="" class="input" type="text"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">Country</label></div>
            
            
            
            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="confirmsignup"></label>
              <div class="controls">
                <button id="confirmsignup" name="confirmsignup" class="btn btn-primary btn-block">Sign Up</button>
              </div>
            </div>
                 <div class="alert alert-success" role="alert">
  Correct login
</div>
<div class="alert alert-danger" role="alert">
  Error username
</div>
            </fieldset>
            </form>
      </div>
    </div>
      </div>
      <!--<div class="modal-footer">
      <center>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </center>
      </div>-->
    </div>
  </div>
</div>
  
   

<!--modal2-->

<div class="modal fade bs-modal-sm" id="forgot-password" tabindex="0" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Password will be sent to your email</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
        <fieldset>
        <div class="group">
<input required="" class="input" type="text"><span class="highlight"></span><span class="bar"></span>
    <label class="label" for="date">Email address</label></div>
        
        
        <div class="control-group">
              <label class="control-label" for="forpassword"></label>
              <div class="controls">
                <button id="forpasswodr" name="forpassword" class="btn btn-primary btn-block">Send</button>
              </div>
            </div>
          </fieldset>
            </form>
          
      </div>
    </div>
    
</div>
</div>
<!-- end modal -->
		<!-- Header -->
			<header id="header" class="alt">

				<div class="inner">
					<h1>Battleship MVC</h1>
					<p>A final project developed for the Agile Software Development for Enterprise</a></p>
				</div>

				
			</header>




		
		
		
		<!--  COUNTER -->
		<div class="row">
		<div class="col-12 col-sm-12 ">
		<div class="wrapper">
		<div class="col-4 col-sm-4">
    <div class="counter col_fourth">
      <i class="fa fa-code fa-2x"></i>
      <h2 class="timer count-title count-number" data-to="300" data-speed="1500"></h2>
       <p class="count-text ">Users registered</p>
    </div>
    </div>
<div class="col-4 col-sm-4 ">
    <div class="counter col_fourth">
      <i class="fa fa-coffee fa-2x"></i>
      <h2 class="timer count-title count-number" data-to="1700" data-speed="1500"></h2>
      <p class="count-text ">Users connected</p>
    </div>
</div>
<div class="col-4 col-sm-4">
    <div class="counter col_fourth">
      <i class="fa fa-lightbulb-o fa-2x"></i>
      <h2 class="timer count-title count-number" data-to="11900" data-speed="1500"></h2>
      <p class="count-text ">Lobby available</p>
    </div>
    </div>

    
</div>
</div>
</div>

		<!-- END COUNTER -->
		
		<div class="row">
		<div class="col-12">
		<!--  carousel -->
	<div class="slider3d first">
  <div class="slider3d__wrapper">
    <div class="slider3d__inner">
      <div class="slider3d__rotater">
        <div class="slider3d__item">
          <a href="www.google.com"><h2 class="slider3d__heading" data-text="SO HEADING">SO HEADING</h2></a>
          
        </div>
        <div class="slider3d__item">
          <h2 class="slider3d__heading" data-text="MUCH ROTATION">MUCH ROTATION</h2>
        </div>
        <div class="slider3d__item">
          <h2 class="slider3d__heading" data-text="VERY 3D">VERY 3D</h2>
        </div>
        <div class="slider3d__item">
          <h2 class="slider3d__heading" data-text="SUCH JAVASCRIPT">SUCH JAVASCRIPT</h2>
        </div>
        <div class="slider3d__item">
          <h2 class="slider3d__heading" data-text="WOW WOW!">WOW WOW!</h2>
        </div>
      </div>
    </div>
  </div>
  <div class="slider3d__controls">
    <div class="slider3d__handle">
      <div class="slider3d__handle__inner">
        <div class="slider3d__handle__rotater">
          <div class="slider3d__handle__item active">Page 1</div>
          <div class="slider3d__handle__item">Page 2</div>
          <div class="slider3d__handle__item">Page 3</div>
          <div class="slider3d__handle__item">Page 4</div>
          <div class="slider3d__handle__item">Page 5</div>
        </div>
      </div>
    </div>
    <div class="slider3d__control m--up"></div>
    <div class="slider3d__control m--down"></div>
  </div>
</div>
</div>
</div>

	<!--  end carousel -->		
		
	
		

				<!-- Footer -->
					<footer id="footer">
						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
							
						</ul>
						
					</footer>

			</div>

		<!-- Scripts -->
		
			<script src="resources/assets/js/jquery.min.js"></script>
			<script src="resources/assets/js/skel.min.js"></script>
			<script src="resources/assets/js/util.js"></script>
			<script src="resources/assets/js/main.js"></script>
			<script src="resources/assets/js/counter.js"></script>
			<script  src="resources/assets/js/carousel.js"></script>

			<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
  
  

	</body>
</html>