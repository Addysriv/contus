<html>
<head>
<!-- <meta charset="ISO-8859-1"> -->
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
#navbarResponsive li{
font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  color: #000000;
  font-family: AvenirNext;
  
  font-size: 18px;
    font-weight: 600;
}

#gridRowDiv p{

 
  font-family: AvenirNext;
  font-size: 20px;
  
  text-align: center;
  color: #313131;

}

#gridRowDiv img{
display: block;
  margin-left: auto;
  margin-right: auto;
}

#testInfo row p{


}
.faqText{

  font-family: AvenirNext;

  font-weight: normal;
  font-stretch: normal;
  font-style: normal;
  line-height: 1.28;
  letter-spacing: normal;
  color: #ffffff;
}

.faqHeader{

  font-family: AvenirNext;

  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  color: #ffffff;}
  
  
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
  
input[type=text] {
  width: 60%;
  padding: 8px 3px;
  
  box-sizing: border-box;
  border: 2px solid #ccc;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  outline: none;
}

input[type=text]:focus {
  border: 3px solid #ff7d00;
}
  #payButton , #continueButton{
	border-radius: 30px;
	width: 172px;
	height: 47px;
	background-color: #ff7d00;
	border-color: #ff7d00;
	border-bottom-style: hidden;
	border-right: #ff7d00;
	  
  }
  
  @media only screen and (max-width: 600px){
 #jumbotronFaqDiv{
  height: 500% !important;
 }
 }
 
 @media only screen and (max-width: 400px){
 #jumbotronFaqDiv{
  height: 600% !important;
 }
 }
</style>

<script src="https://js.stripe.com/v3/"></script>

<script>

var stripe = Stripe('pk_test_51HCljJGjv13unDRNVjZNRdMZA3NUrTAwQUyDAZix6GyjqyGz0NB30EbtW0HahKdSbN8SujIc45ojwpUtFHraAaDA00JOUq9XoZ');

var defaultLang='';
$( document ).ready(function() {
	$('#modalButton').hide();
	$('#paymentDiv').hide();
	
	var userLang = navigator.language || navigator.userLanguage; 
	defaultLang=userLang;
	if(userLang=="sv")
	{
		alert ("The language is: " + defaultLang);
	
	}
	else
		{
		console.log ("The language is: " + "English");
	
		}
	
	$('#payButton').on("click",function(){
		
		stripe.redirectToCheckout({
			  lineItems: [{
			    price: 'price_1HEAUIGjv13unDRNW98vfKAv', // Replace with the ID of your price
			    quantity: 1,
			  }],
			  mode: 'payment',
			  successUrl: 'http://localhost:8080/Contus/choice',
			  cancelUrl: 'http://localhost:8080/Contus/paymentCancel',
			  customerEmail: 'customer@example.com',
			}).then(function (result) {
			  // If `redirectToCheckout` fails due to a browser or network
			  // error, display the localized error message to your customer
			  // using `result.error.message`.
			});
		
	});
	
	$('#userEmail').on("change",function(){
		
		
		var email=$('#userEmail').val();
		
		
		
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if(reg.test(email))
			{
			$('#userEmail').css("border-color","#ccc"); 
			$('#incorrectEmail').text('');
			}
		else{
			$('#userEmail').css("border-color","red"); 
			$('#incorrectEmail').text('Incorrect Email');
			
		}
	});
		
	$('#userName').on("change",function(){
		var name=$('#userName').val();
		if(name=="")
		{
			$('#userEmail').css("border-color","red");
		}
		else{
			$('#userEmail').css("border-color","#ccc");
			
		}
		
		
	});
	
	
	

});
function startTest(){
	
	$('#modalButton').click();
}

function continueCheckout(){
	
	var name=$('#userName').val();
	var email=$('#userEmail').val();
	var coupon=$('#userCoupon').val();
	if(name=="")
	{
		$('#userName').css("border-color","red");
	}
	
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if(reg.test(email))
		{
		$('#userEmail').css("border-color","#ccc"); 
		$('#incorrectEmail').text('');
		}
	else{
		$('#userEmail').css("border-color","red"); 
		$('#incorrectEmail').text('Incorrect Email');
		return;
	}
	
	$.ajax({ 
        url : "${pageContext.request.contextPath}/saveUser",
		async : false,
		data : ({ 
			userName : name,
			userEmail : email,
			userCoupon : coupon,
			}), 
			success : function(jqXHR) {
				alert('success');
				$('#userInfo').hide();
				$('#paymentDiv').show();
				
			}
	});

	
}

</script>

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
		src="<c:url value="/resources/s1.png" ></c:url>" alt="Test Brief"  onclick="startTest();"
		style="width: 100%; background-position: center center; background-repeat: no-repeat;">
		
<%-- 	<img id="contusButtonImage" 
		src="<c:url value="/resources/s1.png" ></c:url>" alt="Test Brief" usemap="#workmap" 
		style="width: 100%; background-position: center center; background-repeat: no-repeat;">
 --%>
		
<!-- 	<map name="workmap">
	  <area shape="rect" coords="254,475,513,540" onclick="startTest();" alt="Computer" href="#">
	  
	</map> -->
		<br>
		<br>
		<br>
		<br>


	<div class="container">
		<h2></h2>
		<!-- Trigger the modal with a button -->
		<button type="button" id="modalButton" class="btn btn-info btn-lg "
			data-backdrop="static" data-keyboard="false" data-toggle="modal"
			data-target="#myModal" style="background-color: white;border-color: white;"></button>

		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content" style="margin-top: 14%;">
					<div class="modal-header">
						
						<div class="modal-title">
							<img src="<c:url value="/resources/Logo.png" ></c:url>"
								alt="Contus Logo" style="width: 6%;">
						</div>
						<button type="button" class="close" data-dismiss="modal" style="float:right;">&times;</button>
					</div>
					<div class="modal-body">
					
					<div id="userInfo">
						<div class="row">
								<div class="col-sm-2"></div>
								
								<div class="col-sm-2"><b>Full Name : </b></div>
								<div class="col-sm-8">
									<input type="text" id="userName" >
								</div>		
						</div>
						<div class="row"><br><br></div>
							<div class="row">
								<div class="col-sm-2"></div>
								<div class="col-sm-2"><b>Email : </b></div>
								<div class="col-sm-8">
									<input type="text"  id="userEmail" ><br>
									<span id="incorrectEmail" style="color:red;margin-left:16%;"></span>
								</div>		
						   </div>
						   <div class="row"><br></div>
						   <div class="row"><div class="col-sm-4"></div>
						   <div class="col-sm-6" style="font-size:10px;">*
						   Please provide correct information as this will be used for report.</div></div>
						   
						   <div class="row"><br></div>
						   
						   	
							<div class="row">
								<div class="col-sm-2"></div>
								
								<div class="col-sm-2" style="font-size: smaller;"><b>Coupon Code :</b> (If any provided by company)</div>
								<div class="col-sm-8">
									<input type="text" id="userCoupon" >
								</div>		
							</div>
							<div class="row"><br><br></div>
						<div class="row">
							<div class="col-sm-4"></div>
							<div class="col-sm-5">
									<button type="button" id="continueButton"
										class="btn btn-lg vertical-center">
										<span id="testButtonModal" onclick="continueCheckout();"
											style="font-family: AvenirNext; font-size: 20px; font-weight: bold; font-stretch: normal; font-style: normal; line-height: normal; letter-spacing: 1.2px; text-align: center; color: #ffffff;">
											Continue</span>
									</button>
								</div>
							</div>
						
							<div class="row"><br></div>
						  <div class="row"><br></div>
						</div>
						
					<div id="paymentDiv">
						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">
								<h3>Please make sure you have 20 minutes after the payment
									, once test starts it cannot be canceled.<br><br></h3>
							</div>
						</div>
						<div class="row"><br></div>
						<div class="row">
							<div class="col-sm-4"></div>
							<div class="col-sm-5">
							<button type="button" id="payButton" class="btn btn-lg vertical-center"><span id="testButtonModal" onclick="checkout();" style="font-family: AvenirNext; font-size: 20px; font-weight: bold;
	  							font-stretch: normal; font-style: normal; line-height: normal; letter-spacing: 1.2px; text-align: center; color: #ffffff;">
	  								Pay 99 SEK</span></button>
							</div>
						</div>
						<div class="row"><br></div>
					</div>
					
					</div>

				</div>
			</div>
		</div>
	</div>



	<div id="gridRowDiv" class="container">
				<div class="row" style="width:115%;">
					<div class="col-sm-1"></div>
					
					<div class="col-sm-3">
					 <img src="resources/u1.png"><br>
					<p>120 questions in 10 mintues.</p>
					</div>
					
					
					<div class="col-sm-3">
					
					 <img src="resources/u2.png"><br>
					<p>The result will be sent to your email.</p>
					</div>
					
					<div class="col-sm-2">
					
					 <img src="resources/u3.png"><br>
					<p>A good compliment to your applications.</p>
					</div>
				
				</div>
				<div class="row"><br>
				<br></div>
				<div class="row" style="width:115%;">
					<div class="col-sm-1"></div>
					
					<div class="col-sm-3">
					 <img src="resources/u4.png"><br>
					<p>Highlight your soft skills.</p>
					</div>
					
					
					<div class="col-sm-3">
					
					 <img src="resources/u5.png"><br>
					<p>Increase your chances of getting an <br>
					interview by standing out.</p>
					</div>
					
					<div class="col-sm-2">
					
					 <img src="resources/u6.png"><br>
					<p>Discussion material during the interview.</p>
					</div>
					
				</div>
			</div>
		<br><br><br>

		<div id="testInfo" class="jumbotron" style="background: url(resources/Pic1New.jpg) cyan; background-size: 100%;
				background-position: center center; background-repeat: no-repeat; background-size: cover;margin-bottom: 0 !important;" >
				
			<div class="row">
		
				<div class="col-sm-2"></div>
				<div class="col-sm-3" style="margin-top: 40%;color:white;">
					<h4>How does it work?</h4>
		
					<p id="personalityTestWork"><br>
						The test is based on the Five Factor Model, which is the most influential personality theory,
						 developed by psychologists Paul Costa and Robert McCrae. The model is based on hypothetical questions 
						 and answers that place the test person on a scale with two extremes in five different personality dimensions. 
						 Together, the answers form the basis of a personality.
						
						</p>
		
				</div>
			<div class="col-sm-1"></div>
				<div class="col-sm-3" style="margin-top: 40%;color:white;">
					<h4>What is a personality?</h4>
		
					<p id="personalityTestWorkSec"><br>
						There is some disagreement among researchers about how much influence a personality has,
						 but one assumption is that a person exhibits a characteristic pattern of thoughts, feelings and behaviors. 
						 When we work with people, it is therefore interesting to know how the person reasons in different situations and
						  whether their philosophy rhymes well with the corporate culture. 
						A personality test can be an important piece of the puzzle in an overall picture of the candidate - 
						and a more honest picture of yourself as an applicant.</p>
		
				</div>
		
			</div>
		</div>
		
		
		
		<div class="jumbotron" id="jumbotronFaqDiv" style="width:100%; margin-top:-3%;margin-bottom: 0 !important;transform: skewY(3.5deg);
    		transform-origin: 56%; z-index: -1;height: 330%; border: solid 1px #979797; background-color: #454545;">
					
					<div class="row"><br><br><br><br><br><br></div>
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
					<div class="col-sm-2"></div>
					
					
					<div class="col-sm-6"><h3>About the Five Factor Model </h3><br></div>
					
					</div>
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
					<div class="col-sm-2"></div>
					<div class="col-sm-6">
						<p>
						The model assesses a personality according to the following five dimensions and opposites:<br><br>
						</p></div>
					</div>
					
					
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
						<div class="col-sm-2"></div>
							<div class="col-sm-6">
							<span>
							<span style="color:#ff7d00">Neurotic trait - Emotionally stable: </span>
							How emotionally stable the person in question is and propensity for anxiety and stress sensitivity.
							<br><br>
							</span></div>
					</div>
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
						<div class="col-sm-2"></div>
							<div class="col-sm-6">
							<span>
							<span style="color:#ff7d00">
							Extrovert trait - Introvert trait:</span> How outward-looking or inward-looking the person is, any closedness with an interest 
							in the theoretical and abstract.<br><br>
							</span></div>
					</div>
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
						<div class="col-sm-2"></div>
							<div class="col-sm-6">
							<span>
							<span style="color:#ff7d00">
							Openness to new experiences - Disinterest:</span>
							 How interested the person is in new ideas, activities and values versus conservative, conventional thinking.<br><br>
							</span></div>
					</div>
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
						<div class="col-sm-2"></div>
							<div class="col-sm-6">
							<span>
							<span style="color:#ff7d00">
							Kindness / warmth - Rejection / unpleasantness:</span>
							 Shows a person's helpfulness, willingness to cooperate and compassion for others.<br><br>
							</span></div>
					</div>
					
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
						<div class="col-sm-2"></div>
							<div class="col-sm-6">
							<span>
							<span style="color:#ff7d00">
							Conscientiousness - Easyness: </span>How thorough, goal-oriented, disciplined and orderly the person is and prone to negligence.<br><br>
							</span></div>
					</div>
				
					<div class="row"><br><br><br><br><br><br></div>
					
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
					<div class="col-sm-2"></div>
					
					<div class="col-sm-6" style="color: #ffffff; width: 84px; height: 55px;font-family: AvenirNext;font-size: 40px;
					text-align: center;" id="faqDiv">FAQ</div>
					
					</div>
					<div class="row"><br><br></div>
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
					<div class="col-sm-2"></div>
					
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">Why should I do a personality test? <br></h5>
						
						<span class="faqText" >An ad can generate hundreds of applications. To stand out, an appealing presentation is required.
						 A good CV with qualifications and letters can show competence, but personality is just as important in the assessment. 
						 By applying with a test early in the process, an overall picture of the applicant can increase 
						 the chances of a more successful recruitment.</span>
						
					</div>
						
					<div class="col-sm-1"></div>
						
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">How realiabe is the test? <br></h5>
						
						<span class="faqText" >Validity and reliability are important factors in assessing the reliability of a test. 
							Validity is about measuring the right things and reliability if the test is measured correctly. 
							The selection is often about future job performance and which candidate has the best conditions for handling a certain situation. 
							Here, the Five Factor model can predict results with some precision,
						 	but if you look at personality and human behavior in the organization, it can give a much better predictor.</span>
						
					</div>
						
						
					</div>
					
					<div class="row"><br><br></div>
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
					<div class="col-sm-2"></div>
					
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">What will the test show? <br></h5>
						
						<span class="faqText" >The results show various personality traits such as social skills, 
							ability to work together, orderliness, resilience to stress, openness to new ideas and
							 how the person ranks on those scales.</span>
						
					</div>
						
					<div class="col-sm-1"></div>
						
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">What can the test be used for? <br></h5>
						
						<span class="faqText" >It can be used as a complement to the job application and be a basis for 
						further discussion in the interview and decision-making process.</span>
						
					</div>
						
						
					</div>
					
					<div class="row"><br><br></div>
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
					<div class="col-sm-2"></div>
					
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">What if I am on the wrong side of the scale? <br></h5>
						
						<span class="faqText" ><br>There are no right or wrong answers, rather the test shows in which environment the
						 person feels and performs best. The professional role or how qualities can strengthen a team 
						 should determine which personality traits are most appropriate.</span>
						
					</div>
						
					<div class="col-sm-1"></div>
						
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">How is the test assessed?<br></h5>
						
						<span class="faqText" >The test is a scientifically based measuring instrument that assesses 
							the answers on a scale with two extremes in five different personality dimensions.
							 Together, the answers form the basis of a personality.</span>
						
					</div>
						
						
					</div>
					
					<div class="row"><br><br></div>
					<div class="row" style="color:white;transform: skewY(-3.5deg);">
					<div class="col-sm-2"></div>
					
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">What happens after the test is done? <br></h5>
						
						<span class="faqText" >The result is displayed immediately and is also sent as a report to the registered 
						email address. The report can then be shared with employers or recruiters.<br><br></span>
						<br><br>
						<h5 class="faqHeader">What is soft skills? <br></h5>
						
						<span class="faqText" >Soft skills, so-called soft skills, refer to non-technical qualities such as social competence, ability to work 
							together and conflict management. To compare with hard skills that are based more on competence
							 and experience<br><br></span>
						
						
					</div>
						
					<div class="col-sm-1"></div>
						
					<div class="col-sm-3" style="color: #ffffff;">
						<h5 class="faqHeader">How does the test differ from other tests on the market? <br></h5>
						
						<span class="faqText" ><br>There are many market participants and different types of tests on the market. 
						Type tests such as DISC/DISA that divide personality traits into different groups (eg red, green, yellow, blue)
						 do not take into account the scale between two extremes. The risk is that you see the candidate as only "blue" and 
						 miss other qualities that are highly relevant to the position. Attribute tests such as the Five Factor Model identify 
						 characteristics on a scale and provide a personality profile rather than a personality type.</span>
						
					</div>
						
						
					</div>
					
	</div>
	
	<div class="jumbotron" style="background-color: #d8d8d8;position:sticky;height:40%;">
	
	
	</div>
	<br><br><br>
	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6" style="font-family: AvenirNext;"><h1>Are you looking for a job? </h1></div>
			
		</div>
		
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6" style="font-family: AvenirNext;color: #4a4a4a;"><h6>Impress and stand out with
				 a personality test attached to your application.</h6></div>
			
		</div>
		<div class="row"><br><br></div>
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6 vertical-center btn" ><button id="testStartButton" style="margin-left:-15%;border-radius: 30px;width: 242px;
  				height: 60px;background-color: #ff7d00;border-color: #ff7d00;border-bottom-style: hidden;border-right: #ff7d00;">
  				<span id="testButton" onclick="startTest();" >Do The Test</span></button></div>
			
			
			
		</div>
		<div class="row"><br><br><br><br><br><br><br><br></div>
	</div>
	
	
		
		<div class="footer" style="background-color: #6a6a6a; height:35%;"></div>
		
</body>

</html>
