<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
	
<script src="resources/js/questionsScript.js"></script>

<title>Contus</title>

<style>
.modalText{
	text-align: center;
	color: #2d2d2d;
	font-weight: bold;
	font-size: x-large;
}

.modalTextStarting{
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


 #testButton{
  width: 219px;
  height: 27px;
  font-family: AvenirNext;
  font-size: 20px;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: 1.2px;
  text-align: center;
  color: #ffffff;
  }

.nextButton{
	width: 100%;
    
    border-radius: 29px;
    border: solid 3px #2d2d2d;
    background-color: #2d2d2d;
}

.submitButton{
    width: 50%;
    border-radius: 29px;
    border: solid 3px #2d2d2d;
    background-color: #2d2d2d;
    height: 50px;
    margin-left: 10%;

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

.custom-control-input:checked~.custom-control-label::before {
  color: #fff;
  border-color: #7B1FA2;
}

.custom-control-input:checked~.custom-control-label::before {
  background-color: red;
}

.custom-control-input:checked~.custom-control-label.green::before {
  background-color: green;
}

button.active.focus, button.active:focus,
button.focus, button:active.focus, 
button:active:focus, button:focus {
  outline: none;
  box-shadow: none;
  
}


#resultText{
  width: 940px;
  height: 33px;
  font-family: AvenirNext;
  font-size: 24px;
  font-weight: normal;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: 1.44px;
  text-align: center;
  color: #626262;
}


</style>


<script>

var defaultLang='';
var userLang = navigator.language || navigator.userLanguage;
defaultLang=userLang;

var counter=0;
var answers=[0];
var orderId='${orderId}';

var ques1="<spring:message code="label.ques1" />";
var ques2="<spring:message code="label.ques2" />";
var ques3="<spring:message code="label.ques3" />";
var ques4="<spring:message code="label.ques4" />";
var ques5="<spring:message code="label.ques5" />";
var ques6="<spring:message code="label.ques6" />";
var ques7="<spring:message code="label.ques7" />";
var ques8="<spring:message code="label.ques8" />";
var ques9="<spring:message code="label.ques9" />";
var ques10="<spring:message code="label.ques10" />";
var ques11="<spring:message code="label.ques11" />";
var ques12="<spring:message code="label.ques12" />";
var ques13="<spring:message code="label.ques13" />";
var ques14="<spring:message code="label.ques14" />";
var ques15="<spring:message code="label.ques15" />";
var ques16="<spring:message code="label.ques16" />";
var ques17="<spring:message code="label.ques17" />";
var ques18="<spring:message code="label.ques18" />";
var ques19="<spring:message code="label.ques19" />";
var ques20="<spring:message code="label.ques20" />";
var ques21="<spring:message code="label.ques21" />";
var ques22="<spring:message code="label.ques22" />";
var ques23="<spring:message code="label.ques23" />";
var ques24="<spring:message code="label.ques24" />";
var ques25="<spring:message code="label.ques25" />";
var ques26="<spring:message code="label.ques26" />";
var ques27="<spring:message code="label.ques27" />";
var ques28="<spring:message code="label.ques28" />";
var ques29="<spring:message code="label.ques29" />";
var ques30="<spring:message code="label.ques30" />";
var ques31="<spring:message code="label.ques31" />";
var ques32="<spring:message code="label.ques32" />";
var ques33="<spring:message code="label.ques33" />";
var ques34="<spring:message code="label.ques34" />";
var ques35="<spring:message code="label.ques35" />";
var ques36="<spring:message code="label.ques36" />";
var ques37="<spring:message code="label.ques37" />";
var ques38="<spring:message code="label.ques38" />";
var ques39="<spring:message code="label.ques39" />";
var ques40="<spring:message code="label.ques40" />";
var ques41="<spring:message code="label.ques41" />";
var ques42="<spring:message code="label.ques42" />";
var ques43="<spring:message code="label.ques43" />";
var ques44="<spring:message code="label.ques44" />";
var ques45="<spring:message code="label.ques45" />";
var ques46="<spring:message code="label.ques46" />";
var ques47="<spring:message code="label.ques47" />";
var ques48="<spring:message code="label.ques48" />";
var ques49="<spring:message code="label.ques49" />";
var ques50="<spring:message code="label.ques50" />";
var ques51="<spring:message code="label.ques51" />";
var ques52="<spring:message code="label.ques52" />";
var ques53="<spring:message code="label.ques53" />";
var ques54="<spring:message code="label.ques54" />";
var ques55="<spring:message code="label.ques55" />";
var ques56="<spring:message code="label.ques56" />";
var ques57="<spring:message code="label.ques57" />";
var ques58="<spring:message code="label.ques58" />";
var ques59="<spring:message code="label.ques59" />";
var ques60="<spring:message code="label.ques60" />";
var ques61="<spring:message code="label.ques61" />";
var ques62="<spring:message code="label.ques62" />";
var ques63="<spring:message code="label.ques63" />";
var ques64="<spring:message code="label.ques64" />";
var ques65="<spring:message code="label.ques65" />";
var ques66="<spring:message code="label.ques66" />";
var ques67="<spring:message code="label.ques67" />";
var ques68="<spring:message code="label.ques68" />";
var ques69="<spring:message code="label.ques69" />";
var ques70="<spring:message code="label.ques70" />";
var ques71="<spring:message code="label.ques71" />";
var ques72="<spring:message code="label.ques72" />";
var ques73="<spring:message code="label.ques73" />";
var ques74="<spring:message code="label.ques74" />";
var ques75="<spring:message code="label.ques75" />";
var ques76="<spring:message code="label.ques76" />";
var ques77="<spring:message code="label.ques77" />";
var ques78="<spring:message code="label.ques78" />";
var ques79="<spring:message code="label.ques79" />";
var ques80="<spring:message code="label.ques80" />";
var ques81="<spring:message code="label.ques81" />";
var ques82="<spring:message code="label.ques82" />";
var ques83="<spring:message code="label.ques83" />";
var ques84="<spring:message code="label.ques84" />";
var ques85="<spring:message code="label.ques85" />";
var ques86="<spring:message code="label.ques86" />";
var ques87="<spring:message code="label.ques87" />";
var ques88="<spring:message code="label.ques88" />";
var ques89="<spring:message code="label.ques89" />";
var ques90="<spring:message code="label.ques90" />";
var ques91="<spring:message code="label.ques91" />";
var ques92="<spring:message code="label.ques92" />";
var ques93="<spring:message code="label.ques93" />";
var ques94="<spring:message code="label.ques94" />";
var ques95="<spring:message code="label.ques95" />";
var ques96="<spring:message code="label.ques96" />";
var ques97="<spring:message code="label.ques97" />";
var ques98="<spring:message code="label.ques98" />";
var ques99="<spring:message code="label.ques99" />";
var ques100="<spring:message code="label.ques100" />";
var ques101="<spring:message code="label.ques101" />";
var ques102="<spring:message code="label.ques102" />";
var ques103="<spring:message code="label.ques103" />";
var ques104="<spring:message code="label.ques104" />";
var ques105="<spring:message code="label.ques105" />";
var ques106="<spring:message code="label.ques106" />";
var ques107="<spring:message code="label.ques107" />";
var ques108="<spring:message code="label.ques108" />";
var ques109="<spring:message code="label.ques109" />";
var ques110="<spring:message code="label.ques110" />";
var ques111="<spring:message code="label.ques111" />";
var ques112="<spring:message code="label.ques112" />";
var ques113="<spring:message code="label.ques113" />";
var ques114="<spring:message code="label.ques114" />";
var ques115="<spring:message code="label.ques115" />";
var ques116="<spring:message code="label.ques116" />";
var ques117="<spring:message code="label.ques117" />";
var ques118="<spring:message code="label.ques118" />";
var ques119="<spring:message code="label.ques119" />";
var ques120="<spring:message code="label.ques120" />";

var questionsArray=["none",ques1,ques2,ques3,ques4,ques5,ques6,ques7,ques8,ques9,ques10,
	ques11,ques12,ques13,ques14,ques15,ques16,ques17,ques18,ques19,
	ques20,ques21,ques22,ques23,ques24,ques25,ques26,ques27,ques28,
	ques29,ques30,ques31,ques32,ques33,ques34,ques35,ques36,ques37,
	ques38,ques39,ques40,ques41,ques42,ques43,ques44,ques45,ques46,
	ques47,ques48,ques49,ques50,ques51,ques52,ques53,ques54,ques55,
	ques56,ques57,ques58,ques59,ques60,ques61,ques62,ques63,ques64,
	ques65,ques66,ques67,ques68,ques69,ques70,ques71,ques72,ques73,
	ques74,ques75,ques76,ques77,ques78,ques79,ques80,ques81,ques82,
	ques83,ques84,ques85,ques86,ques87,ques88,ques89,ques90,ques91,
	ques92,ques93,ques94,ques95,ques96,ques97,ques98,ques99,ques100,
	ques101,ques102,ques103,ques104,ques105,ques106,ques107,ques108,
	ques109,ques110,ques111,ques112,ques113,ques114,ques115,ques116,
	ques117,ques118,ques119,ques120];
</script>

</head>
<body>

	
	<nav class="navbar navbar-expand-md navbar-light " style="background-color: rgba(255, 142, 34, 0.31);">

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
					<li class="nav-item active"><a class="nav-link" onclick="startTest();" href="#"><spring:message code="label.contus.doTest" /></a></li>&nbsp;&nbsp;
					 <%-- <li class="nav-item"><a class="nav-link"  href="#"><spring:message code="label.contus.recruiters" /></a></li>&nbsp;&nbsp; --%>
					<li class="nav-item"><a class="nav-link" href="#faqDiv" style=""><spring:message code="label.contus.faq" /></a></li>&nbsp;&nbsp;
					<li class="nav-item"><a class="nav-link" href="#"><spring:message code="label.contus.contact" /></a></li>&nbsp;&nbsp;
					<!-- <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Language</a>
					      <div class="dropdown-menu">
					        <a class="dropdown-item" href="#">English</a>
					        <a class="dropdown-item" href="#">Svenska</a></div>
    				</li> -->
					
				</ul>
				

			</div>

		</div>

	</nav>

		<br>
		

	<div class="jumbotron" style="background-color: white;padding:1%;">

		<div class="row" style="margin-left: 10%;">
			<div class="col-sm-6">
			<br><br>
			<h3 style="font-family: AvenirNext;font-size: 50px;font-weight: 600;font-stretch: normal;font-style: normal; line-height: 1.4;
    			letter-spacing: 9.52px;color: #4a4a4a;"><spring:message code="label.contus" /></h3>
			<span id="lowerContusData"><spring:message code="label.contus.lower.heading" /></span>
			<br><br>
			<p style="font-family: AvenirNext;font-size: 19px;font-weight: normal;font-stretch: normal;font-style: normal;margin-right: 29%;
  				line-height: 1.3;letter-spacing: normal;color: #4a4a4a;"><spring:message code="label.contus.context" /></p>
  					<br><br>
  				<button id="testStartButton" onclick="startTest();" style="border-radius: 30px;width: 242px; 
  				height: 60px;background-color: #ff7d00;border-color: #ff7d00;border-bottom-style: hidden;border-right: #ff7d00;">
  				<span id="testButton"  >
  				<spring:message code="label.contus.doTest" />
  				</span></button>
  				
			</div>
			<div class="col-sm-6">
			<img  src="resources/orange.jpg" id="orangeImage" alt="Test Brief" style="width: 84%;margin-top:8%;height:500px;">
			<img  src="resources/phone.png" alt="Test Brief" style="width: 100%;position: absolute;right: 0;margin-top: -41%;">
			</div>


		</div>



	</div>

	

<div class="container">
  <h2></h2>
  <!-- Trigger the modal with a button -->
  <button type="button" id="modalButton" class="btn btn-info btn-lg" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#myModal"></button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content" style="margin-top: 10%;width: 110%;margin-left: -6%;;">
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
		         <p class="modalTextStarting"><br>
			          <spring:message code="label.contus.time" />
			         <br><spring:message code="label.contus.beTruthfull" />
			          
			          </p>
			          <p class="modalText">
			          
			          </p>
		          </div>
	          </div>
	          
	          <div class="row" ><br><br><br></div>
	          
	          <div class="row" >
		         <div class="col-sm-12">
		         	
		         	<button id="testStartButton" class="vertical-center" style="margin-left:38%;border-radius: 30px;width: 172px;
  				height: 47px;background-color: #ff7d00;border-color: #ff7d00;border-bottom-style: hidden;border-right: #ff7d00;">
  				<span id="testButtonModal" onclick="startQues();" style="font-family: AvenirNext; font-size: 20px; font-weight: bold;
  					font-stretch: normal; font-style: normal; line-height: normal; letter-spacing: 1.2px; text-align: center; color: #ffffff;">
  					<spring:message code="label.contus.startTest" /></span></button>
		         
		         </div></div>
		         
	          <div class="row" ><br></div>
	          
          </div>

          	<div id="questionsDiv">
          		
	           <div class="row" >
			         <div class="col-sm-12">
				          <p id="questionColumn" class="modalText"><br>
				          <spring:message code="label.ques1" />
				          </p>
			          </div>
		          </div>
		          
		         <div class="row" ><br></div>
		      
	          	 <div class="row" >
	          	    <div class="col-sm-2"></div>
	          	   <div class="" >
	          	   <span id="veryInaccurate" style="color: grey;">&nbsp;&nbsp;
	          	   <spring:message code="label.inaccurate" /> &nbsp;&nbsp;&nbsp;</span></div>
	          	   <div class="col-sm-1">
	          	   
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="q1_rd_1" name="radioQues"
											class="custom-control-input" value="1"> <label
											class="custom-control-label red" for="q1_rd_1"></label>
									</div>
					</div>
					<div class="col-sm-1">
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="q1_rd_2" name="radioQues"
											class="custom-control-input" value="2"> <label
											class="custom-control-label red" for="q1_rd_2"></label>
									</div>
									
					</div>	
					
					<div class="col-sm-1">
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="q1_rd_3" name="radioQues"
											class="custom-control-input" value="3"> <label
											class="custom-control-label red" for="q1_rd_3"></label>
									</div>
									
					</div>
					
					<div class="col-sm-1">
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="q1_rd_4" name="radioQues"
											class="custom-control-input" value="4"> <label
											class="custom-control-label red" for="q1_rd_4"></label>
									</div>
									
					</div>
					
					<div class="col-sm-1">
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" id="q1_rd_5" name="radioQues"
											class="custom-control-input" value="5"> <label
											class="custom-control-label red" for="q1_rd_5"></label>
									</div>
					</div>
					<div class="col-sm-3">
					<span id="veryAccurate" style="color: grey;">
					&nbsp;&nbsp;<spring:message code="label.accurate" /></span></div>
									
					</div>			
												          	
	           <div class="row" ><br><br></div>
	          
	           <div class="row" id="switchButtons">
	           	<div class="col-sm-3"></div>
	           	<div class="col-sm-3">
	           		<button  class="vertical-center previousButton"><span class="previousText" onclick="previousPage();"><spring:message code="label.goBack" /></span></button>
	           	</div>
	           	<div class="col-sm-1"><br><br></div>
	           	 <div class="col-sm-3">
	           		<button  class="vertical-center nextButton" onclick="nextPage();"><span class="nextText"><spring:message code="label.next" /></span></button>
	           	</div>
	          
	        </div>
	        
	        <div class="row" id="submitButton">
	           	<div class="col-sm-4"></div>
	           	<div class="col-sm-6">
	           		<button  class="vertical-center submitButton" onclick="submitAnswers();"><span class="nextText"><spring:message code="label.submit" /></span></button>
	           	</div>
	        </div>
	        
	         <div class="row" ><br><br></div>
	    
	    	 <div class="container" >  <!-- 5 % on 6 questions --> 
			 	<div class="progress" style="background-color: rgba(255, 125, 0, 0.2);border-radius: 12px;width: 76%;margin-left: 16%;">
			   <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0"
			    	aria-valuemax="100" style="width:0%;background-color: #ff7d00;margin: 3px; border-radius: 12px;">
			   </div>
			 </div>
			</div>
			<div class="row" ><br><br></div>
		
          	
          	</div>
          
          <div class="container"  id="resultDiv" style="position: relative; height: 50vh;margin-left: -7%;">
          	<canvas id="resultChart"></canvas>
          	
          </div>
          	<br><br>
          	
          	<span id="resultText" style="margin-left:10%;">
          	<spring:message code="label.resultText" /> </span><br>
        <div class="modal-footer" >
        
         <div id="questionNumber"><spring:message code="label.question" /> <span id="queseNumb"></span> of 120</div>
         
        </div>
      </div>
      
    </div>
  </div>
  
</div>


</div>





</body>

<script>
var progressWidth=0.17;



$( document ).ready(function() {
	$('#questionNumber').hide();
	$('#questionsDiv').hide();
	$('#resultDiv').hide();
	$('#resultText').hide();
	$('#modalButton').trigger('click');
	
	history.pushState(null, null, location.href);
 	window.onpopstate = function () {
 	    history.go(1);
 	};
 	
/* $('#questionForm').submit(function (event) {

 	    event.preventDefault();
 	    
 	});
	 */
});






function submitAnswers(){
	
	 answers[counter]=$("input[name='radioQues']:checked").val();
	 
	var dataToPost = JSON.stringify({ 'answersList':answers });
	 $.ajax({ 
       url : "${pageContext.request.contextPath}/calculateResult",
       type: 'POST',
		data :{
			answersList :answers,
			orderIdValue:orderId,
			lang:defaultLang
		},
		success : function(result) {
				alert('success'+result);
				$('#questionsDiv').hide();
				$('#questionNumber').hide();
				$('#page1').hide();
				$('#submitButton').hide();
				$('#switchButtons').hide();
				$('#resultDiv').show();
				$('#resultText').show();
				var resultStr=result.split('-');
				showResultInChart(resultStr[0],resultStr[1],resultStr[2],resultStr[3],resultStr[4]);
				
			}
	}); 
	
}


function previousPage(){
	if(counter==1)
	{
		return;
	}
	counter=counter-1;
	
	var widthNum = parseFloat(counter);
	var width = widthNum - (widthNum * 0.17);
	$('.progress-bar').css("width", width + "%");
	
	$('#queseNumb').text(counter);
	$('#questionColumn').text(questionsArray[counter]);
	
}

 function nextPage(){
	 
	 if($("input[name='radioQues']:checked").val()==undefined)
	 {
		 return;
	 }
	 
	 answers[counter]=$("input[name='radioQues']:checked").val();
	 $('[id^="q1_rd"]').prop("checked",false);
	 
	 var widthNum = parseFloat(counter);
	var width = widthNum - (widthNum * 0.17);
	$('.progress-bar').css("width", width + "%");
	counter=counter+1;
	$('#queseNumb').text(counter);
	$('#questionColumn').text(questionsArray[counter]);
	
	if(counter==120)
	{
		$('#submitButton').show();
		$('#switchButtons').hide();
	}
	
 }
 
 function startQues(){
	 
	 	$('#submitButton').hide();
	    $('.modalTextStarting').hide();
		$('#page0').hide();
		$('#questionsDiv').show();
		$('#questionNumber').show();
		$('#page1').show();
		$('#queseNumb').text(1);
		$('[id^="q1_rd"]').prop("checked",false);
		$('#questionColumn').text(questionsArray[1]);
		counter=1;
 }
 
 function showResultInChart(neuro,extra,open,agree,consc){
	 
	 let myChart = document.getElementById('resultChart').getContext('2d');

	 // Global Options
	 Chart.defaults.global.defaultFontFamily = 'AvenirNext';
	 let massPopChart = new Chart(myChart, {
	   type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
	   data:{
	     labels:['Neuroticism', 'Extraversion', 'Openness', 'Agreeableness', 'Conscientiousness'],
	     datasets:[{
	       label:'Facet',
	       data:[
	    	   neuro,
	    	   extra,
	    	   open,
	    	   agree,
	    	   consc,
	         0,
	         100
	       ],
	       backgroundColor:'#ff882a',
	      /*  backgroundColor:[
	         'rgba(255, 99, 132, 0.6)',
	         'rgba(54, 162, 235, 0.6)',
	         'rgba(255, 206, 86, 0.6)',
	         'rgba(75, 192, 192, 0.6)',
	         'rgba(153, 102, 255, 0.6)',
	         'rgba(255, 159, 64, 0.6)',
	         'rgba(255, 99, 132, 0.6)'
	       ], */
	       borderWidth:1,
	       borderColor:'#FFDCBA',
	       hoverBorderWidth:3,
	       hoverBorderColor:' #FFDCBA'
	     }]
	   },
	   options:{
		   
		   "hover": {
	              "animationDuration": 0
	            },
	             "animation": {
	                "duration": 1,
	              "onComplete": function() {
	                var chartInstance = this.chart,
	                  ctx = chartInstance.ctx;
	 
	                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
	                ctx.textAlign = 'center';
	                ctx.textBaseline = 'bottom';
	 
	                this.data.datasets.forEach(function(dataset, i) {
	                  var meta = chartInstance.controller.getDatasetMeta(i);
	                  meta.data.forEach(function(bar, index) {
	                    var data = dataset.data[index];
	                    ctx.fillText(data, bar._model.x, bar._model.y - 5);
	                  });
	                });
	              }
	            },
	            
	     title:{
	       display:true,
	       text:'The Results',
	       fontSize:30
	     },
	     legend:{
	       display:false,
	       
	     },
	     
	     tooltips:{
	       enabled:true
	     },
	     maintainAspectRatio:false,
	     responsive: true,
         scales: {
           xAxes: [{
             gridLines: {
               show: false,
               color: "rgba(0, 0, 0, 0)",
             }
           }],
           yAxes: [{
        	     
             gridLines: {
               color: "rgba(0, 0, 0, 0)",
             },
             ticks: {
                 display: false
             },
             afterFit: function(scaleInstance) {
                 scaleInstance.width = 100; // sets the width to 100px
               }
           }]
         }
	   }
	 });

	 
 }
 
</script>

</html>