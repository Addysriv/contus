<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>

<title>Contus</title>

<style>
.modalText{
	text-align: center;
	color: #2d2d2d;
	font-weight: bold;
}

.radioCss{
    transform: scale(1.5);
}
.custom-control-input:checked~.custom-control-label.red::before {
  background-color: #ff7d00;
}

.previousButton{
	width: 100%;
    
    border-radius: 29px;
    border: solid 3px #515151;
    
}


.nextButton{
	width: 100%;
    
    border-radius: 29px;
    border: solid 3px #2d2d2d;
    background-color: #2d2d2d;
}

.previousText{
    font-family: AvenirNext;
    font-size: larger;
    font-weight: bold;
    line-height: normal;
    text-align: center;
    color: #494949;
}

.nextText{
	font-family: AvenirNext;
    font-size: large;
    font-weight: bold;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    text-align: center;
    color: #ffffff;
	
}

</style>

</head>
<body>

	<nav class="navbar navbar-expand-md navbar-light bg-light ">

		<div class="container-fluid">
			<a class="navbar-brand" href="#"><img
				src="<c:url value="/resources/logo2.png" ></c:url>"
				alt="Contus Logo" style="width: 20%;"> </a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive">
				<span class="navbar-toggler-icon"></span>

			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" onclick="startTest();" href="#">Do The	Test</a></li>&nbsp;&nbsp;
					 <li class="nav-item"><a class="nav-link"  href="#">For Recruiters</a></li>&nbsp;&nbsp;
					<li class="nav-item"><a class="nav-link" href="#faqDiv" style="">FAQ</a></li>&nbsp;&nbsp;
					<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>&nbsp;&nbsp;
					<li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Language</a>
					      <div class="dropdown-menu">
					        <a class="dropdown-item" href="#">English</a>
					        <a class="dropdown-item" href="#">Svenska</a></div>
    				</li>
					
				</ul>
				

			</div>

		</div>

	</nav>

		<br>
		<br>
		<br>
	<img id="contusButtonImage" 
		src="<c:url value="/resources/s1.png" ></c:url>" alt="Test Brief" usemap="#workmap" 
		style="width: 100%; background-position: center center; background-repeat: no-repeat;">

		


<div class="container">
  <h2></h2>
  <!-- Trigger the modal with a button -->
  <button type="button" id="modalButton" class="btn btn-info btn-lg" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#myModal"></button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content" style="margin-top: 14%;">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <div class="modal-title"><img
				src="<c:url value="/resources/Logo.png" ></c:url>"
				alt="Contus Logo" style="width: 6%;"></div>
        </div>
        <div class="modal-body">
        
        
         <div id="page0">
	         <div class="row" >
		         <div class="col-sm-12">
			          <p class="modalText"><br>
			          The following test contains 120 questions and takes about 10 minutes to complete.
			          </p>
		          </div>
	          </div>
	          
	          <div class="row" ><br><br><br></div>
	          
	          <div class="row" >
		         <div class="col-sm-12">
		         	
		         	<button id="testStartButton" class="vertical-center" style="margin-left:35%;border-radius: 30px;width: 172px;
  				height: 47px;background-color: #ff7d00;border-color: #ff7d00;border-bottom-style: hidden;border-right: #ff7d00;">
  				<span id="testButtonModal" onclick="nextPage(1);" style="font-family: AvenirNext; font-size: 20px; font-weight: bold;
  					font-stretch: normal; font-style: normal; line-height: normal; letter-spacing: 1.2px; text-align: center; color: #ffffff;">
  					Start Test</span></button>
		         
		         </div></div>
		         
	          <div class="row" ><br></div>
	          
          </div>
          
          <div id="page1" style="display: none;">
          
           <div class="row" >
		         <div class="col-sm-12">
			          <p class="modalText"><br>
			          Question 1 comes here
			          </p>
		          </div>
	          </div>
	          
	         <div class="row" ><br>></div>
	      
	         <div class="row" >
		         <div class="col-sm-12" style="text-align: center;"> 
	            	  Very Inaccurate&nbsp;&nbsp;&nbsp;<label class="radio-inline" ">
	          		  <input type="radio" class="radioCss" name="radioQues1" value="1" > &nbsp;&nbsp;&nbsp;&nbsp;</label>
					  <label class="radio-inline"> <input type="radio" class="radioCss" name="radioQues1" value="2" > &nbsp;&nbsp;&nbsp;&nbsp;</label>
					  <label class="radio-inline"> <input type="radio" class="radioCss" name="radioQues1" value="3" >&nbsp;&nbsp; &nbsp;&nbsp;</label>
					  <label class="radio-inline"> <input type="radio" class="radioCss" name="radioQues1" value="4" > &nbsp;&nbsp;&nbsp;&nbsp;</label>
					  <label class="radio-inline" style="font-family: AvenirNext;font-size: 20px;color: #494949;"> 
					  <input type="radio"class="radioCss"  name="radioQues1" value="5" ></label>
					  &nbsp;&nbsp;&nbsp;&nbsp;Very Accurate
	     		</div>
          	</div>
          	
           <div class="row" ><br><br></div>
          
           <div class="row" >
           	<div class="col-sm-3"></div>
           	<div class="col-sm-3">
           		<button  class="vertical-center previousButton"><span class="previousText">Go Back</span></button>
           	</div>
           	 <div class="col-sm-3">
           		<button  class="vertical-center nextButton"><span class="nextText">Next</span></button>
           	</div>
          
        </div>
         <div class="row" ><br><br></div>
    
    	 <div class="container">  <!-- 5 % on 6 questions --> 
		 	<div class="progress" style="background-color: rgba(255, 125, 0, 0.2);border-radius: 12px;width: 70%;margin-left: 16%;">
		   <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0"
		    	aria-valuemax="100" style="width:100%;background-color: #ff7d00;margin: 3px; border-radius: 12px;">
		   </div>
		 </div>
		</div>
		<div class="row" ><br><br><br><br></div>
		
         </div>
        <div class="modal-footer">
        
         
         Question 2 of 10
        </div>
      </div>
      
    </div>
  </div>
  
</div>


</div>





</body>

<script>
$( document ).ready(function() {
	
	$('#modalButton').trigger('click');
	
	history.pushState(null, null, location.href);
 	window.onpopstate = function () {
 	    history.go(1);
 	};
	
});
	   
 function nextPage(index){
	 
	if(index==1)
		{
			$('#page0').hide();
			$('#page1').show();
		}
	else
		{
			
		
		
		
		
		
		}

 
 }
 
 
</script>

</html>