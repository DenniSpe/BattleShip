<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">




<jsp:include page="include.jsp"></jsp:include>
</head>

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
        
        <c:if test="${user!=null}">
     	<li class="active"><a href="#">${user.username }'s Profile</a></li>
     	</c:if>
     	
     	<c:if test="${user==null}">
        <li><a href="#" data-toggle="modal" data-target=".log-sign">Log in</a></li> 
           </c:if>
            
            <c:if test="${user!=null}">
            <li><a href="logout">Logout</a></li>
         </c:if>
           
           <li><a href="#">About Us</a></li>
      
      </ul>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade bs-modal-sm log-sign" id="mymodalOnNav" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        
        <div class="bs-example bs-example-tabs">
        
            <ul id="myTab" class="nav nav-tabs">
              <li id="tab1" class=" active tab-style login-shadow "><a href="#signin" data-toggle="tab">Log In</a></li>
              <li id="tab2" class=" tab-style "><a href="#signup" data-toggle="tab">Sign Up</a></li>
              
            </ul>
            
            
        </div>
        
      <div class="modal-body" >
        <div id="myTabContent" class="tab-content">
       
        <div class="tab-pane fade active in" id="signin">
            <form class="form-horizontal" method="POST" action="login" id="formLogin">
            <fieldset>
            <!-- Sign In Form -->
            <!-- Text input-->
              
      <div class="group">
	<input required class="input" type="text" name="uname" id="usernameLogin">
	<span class="highlight"></span><span class="bar"></span>
    <label class="label">Username</label>
    </div>
              
              
            <!-- Password input-->
            <div class="group">
		<input required class="input" type="password" name="pwd" id="pwdLogin"><span class="highlight"></span><span class="bar"></span>
    <label class="label">Password</label>
    </div>
    
<em>minimum 6 characters</em>

           <div class="forgot-link">
            <a href="#forgot" data-toggle="modal" data-target="#forgot-password"> I forgot my password</a>
            </div>
            

            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="signin"></label>
              <div class="controls">
              
              
                <button id="signin" name="signin" class="btn btn-primary btn-block" type="submit">Log In</button>

              </div>
            </div>
    

				<div class="alert alert-danger" role="alert" id="loginError">
				 Error Credentials
				</div>
				
				<div class="alert alert-success" role="alert" id="signInCorrect">
  				Correct Login
				</div>

            </fieldset>
 </form>
            
            
        </div>
          
          
        <div class="tab-pane fade" id="signup">
            <form class="form-horizontal" method="POST" action="registration" id="registrationForm" role="form"
										autocomplete="off">
            <fieldset>
            <!-- Sign Up Form -->
            
            <!-- Text input-->
            <div class="group">
<input required class="input" type="text" name="uname" id="username_register"><span class="highlight"></span><span class="bar"></span>
    <label class="label" >Username</label></div>

            
            <!-- Text input-->
            <div class="group">
<input required class="input" type="password" name="pwd" id="password_register"><span class="highlight"></span><span class="bar"></span>
    <label class="label">Password</label></div>
              <em>1-8 Characters</em>
            
              <div class="group">
<input required class="input" type="password" name="rpt_pwd" id="rpt_password_register"><span class="highlight"></span><span class="bar"></span>
    <label class="label" >Repeat Password</label></div>
            
            
            
            
         
            <div class="control-group">
              <label class="control-label" for="confirmsignup"></label>
              <div class="controls">
              
              
                 <input id="confirmsignup" name="confirmsignup" type="submit" class="btn btn-primary btn-block"></input>

              </div>
            </div>
            
                 

            </fieldset>
            </form>
      </div>
    </div>
      </div>
      <div class="alert alert-success" role="alert" id="signUpCorrect">
  User created
</div>
<div class="alert alert-danger" role="alert" id="usernameError">
  Error username exists
</div>
<div class="alert alert-danger" role="alert" id="passwordMatch">
  Password not match
</div>
      <div class="modal-footer">
      <center>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </center>
      </div>
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
       
	

	
</body>
</html>