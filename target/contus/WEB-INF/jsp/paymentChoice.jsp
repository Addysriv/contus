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
	          
	          <div class="row" ><br><br></div>
	          
          
	         <div class="row" >
		         <div class="col-sm-12" style="text-align: center;"> 
          			  <label class="radio-inline" style="font-family: AvenirNext;font-size: 20px;color: #494949;">Very Inaccurate&nbsp;&nbsp;&nbsp;&nbsp;
          			  <input type="radio" class="radioCss" name="radioQues1" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					  <label class="radio-inline"> <input type="radio" class="radioCss" name="radioQues1" >&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</label>
					  <label class="radio-inline"> <input type="radio" class="radioCss" name="radioQues1" >&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</label>
					  <label class="radio-inline"> <input type="radio" class="radioCss" name="radioQues1" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					  <label class="radio-inline" style="font-family: AvenirNext;font-size: 20px;color: #494949;"> <input type="radio"class="radioCss"  name="radioQues1" >&nbsp;&nbsp;&nbsp;&nbsp;Very Accurate</label>
          		
          		
   <!--        		<div class="custom-control custom-radio custom-control-inline">
  <input type="radio" id="rd_2" name="rd" class="custom-control-input" value="No">
  <label class="custom-control-label red" for="rd_2">No</label>
</div> -->
          		</div></div>
          		
          		<div class="row" ><br><br></div>
          
        </div>
        <div class="modal-footer">
          <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
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