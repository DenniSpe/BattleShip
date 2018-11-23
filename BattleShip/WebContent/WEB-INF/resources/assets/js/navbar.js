$(document).ready(function() {
	
	$("#usernameError").hide();
	$("#passwordMatch").hide();
	$("#signUpCorrect").hide();
	$("#loginError").hide();
	$("#signInCorrect").hide();
	
	$("#registrationForm").on("submit", function(){
		
		 
			
		  $.ajax({
				type: "POST",
				  url: "registration",
				      
				      data: {
				    	  uname: $("#username_register").val(),
				    		  pwd: $("#password_register").val(),
				    			  rpt_pwd: $("#rpt_password_register").val()
				      },
				      
				      success: function(data){
				    	  if (data === "CORRECT"){

				    			  $("#signup").show();
				    		  $("#signUpCorrect").show();
				    		
				    		  
				    		  setTimeout(function() {
				    			  $("#mymodalOnNav").hide();
				    			 
				    			  window.location.href = "./"
							    }, 1500);
				    			  
				    		 
				    	  }
				    	  else if (data === "PSWD_EQUAL")
				    		  {
				    		  $("#signup").show();
				    		  $("#passwordMatch").show();
				    		  
				    		  $("#username_register").val("")
				    		  $("#password_register").val("")
				    		  $("#rpt_password_register").val("")
				    		  
				    		  setTimeout(function() {
				    			  $("#passwordMatch").hide();
							    }, 1000);
				    		  
				    		  
				    		  }
				    	  else if (data === "USN_EXT")
			    		  {
			    		  $("#signup").show();
			    		  $("#usernameError").show();
			    		  
			    		  $("#username_register").val("")
			    		  $("#password_register").val("")
			    		  $("#rpt_password_register").val("")
			    		  
			    		  setTimeout(function() {
			    			  $("#usernameError").hide();
						    }, 1000);
			    		  
			    		  
			    		  }
				   
				      }
			});
		  return false;
		  
		});
	
	
	$("#formLogin").on("submit", function(){
		
		  $.ajax({
				type: "POST",
				  url: "login",
				      
				      data: {
				    	  uname: $("#usernameLogin").val(),
				    	pwd: $("#pwdLogin").val(),
				    			  
				      },
				      
				      success: 
				    	  function(data){
				    	  if(data === "ERROR")
				    		  {
					    		  $("#signin").show();
					    		  $("#loginError").show();
					    		  
					    		  setTimeout(function() {
					    			  $("#loginError").hide();
								    }, 1000);
				    		  }
				    	  else if(data === "CORRECT")
				    		  {
				    		  $("#signin").show();
				    		  $("#signInCorrect").show();
				    		  
				    		  setTimeout(function() {
				    			  $("#signInCorrect").hide();
				    			  window.location.href = "./"
							    }, 1500);
				    		  }
				      }
		  });
		  
		return false;
		
	})
	  
	
});