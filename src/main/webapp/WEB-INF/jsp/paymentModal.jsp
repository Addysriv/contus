<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/customFrontPage.css">
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
	
	<script src="resources/js/frontPage.js"></script>
	
	<script src="https://js.stripe.com/v3/"></script>
	
<script>

/* var stripe = Stripe('pk_test_51HCljJGjv13unDRNVjZNRdMZA3NUrTAwQUyDAZix6GyjqyGz0NB30EbtW0HahKdSbN8SujIc45ojwpUtFHraAaDA00JOUq9XoZ');
 */
var defaultLang='';
 var klarnaClicked=true;
 var swishClicked=false;
 
$( document ).ready(function() {
	$('#paymentDiv').hide();
	
	$('#payButton').on("click",function(){
		continuePayment();
		
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

function selectPaymentMethod(num){
	if(num==1)
	{
		if(swishClicked==true){
			$('#myCheckbox2').trigger('click')
			swishClicked=false;
		}
			klarnaClicked=true;
			$('#klarnaLabel').css("background-color","rgba(255, 142, 34, 0.31)");
			$('#swishLabel').css("background-color","white");
	}
	else
		{
		if(klarnaClicked==true){
			$('#myCheckbox1').trigger('click')
			klarnaClicked=false;
		}
		swishClicked=true;
		$('#swishLabel').css("background-color","rgba(255, 142, 34, 0.31)");
		$('#klarnaLabel').css("background-color","white");
		}
}
function continueCheckout(){
$('#userInfo').hide();
$('#paymentDiv').show();
}

function continuePayment(){
	
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
	var payment="";
	if(swishClicked==true)
	{
		payment="swish";
	}
	else 
		{
		payment="klarna";
		}
	
	$.ajax({ 
        url : "${pageContext.request.contextPath}/saveUser",
		async : false,
		data : ({ 
			userName : name,
			userEmail : email,
			userCoupon : coupon,
			paymentMode : payment
			}), 
			success : function(jqXHR) {
				alert('success');
				
				
			}
	});

	
}

</script>
</head>
<body>


<div id="userInfo">

		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" id="paymentModalHeading">Tell us about yourself</div>
		</div>
			<div class="row"><br><br></div>
						<div class="row">
								<div class="col-sm-3"></div>
								
								<div class="col-sm-8" >
									<input type="text" id="userName" placeholder="   Name" style="width:80%;border-radius: 14px;background-color: rgba(255, 142, 34, 0.31);">
								</div>		
						</div>
						<div class="row"><br></div>
							<div class="row">
								<div class="col-sm-3"></div>
								
								<div class="col-sm-8">
									<input type="text"  id="userEmail" placeholder="   Email" style="width:80%;border-radius: 14px;background-color: rgba(255, 142, 34, 0.31);"><br>
									<span id="incorrectEmail" style="color:red;margin-left:16%;"></span>
								</div>		
						   </div>
						   <!-- <div class="row"><br></div>
						   <div class="row"><div class="col-sm-4"></div>
						   <div class="col-sm-6" style="font-size:10px;">*
						   Please provide correct information as this will be used for report.</div></div>
						    -->
						   <div class="row"><br></div>
						   
						   	
							<div class="row">
								<div class="col-sm-3"></div>
								
								<!-- <div class="col-sm-2" style="font-size: smaller;"><b>Coupon Code :</b> (If any provided by company)</div> -->
								<div class="col-sm-8">
									<input type="text" placeholder="   Coupon Code" id="userCoupon" style="width:80%;border-radius: 
									14px;background-color: rgba(255, 142, 34, 0.31);">
								</div>		
							</div>
			<div class="row"><br></div>
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-6" style="text-align:center;">
							<h4>Choose Payment Method</h4>
			
						</div>
						</div>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<input type="checkbox" id="myCheckbox1" name="paymentType" /> <label id="klarnaLabel" for="myCheckbox1"><img
						src="resources/klarna.jpg" onclick="selectPaymentMethod(1);"/></label> 
				</div>
				<div class="col-sm-3">
				<input type="checkbox" id="myCheckbox2" name="paymentType" />
					<label for="myCheckbox2" id="swishLabel"><img src="resources/swish.png" onclick="selectPaymentMethod(2);"/></label>
				</div>
		</div>

		<div class="row"><br><br></div>
						<div class="row">
							<div class="col-sm-4"></div>
							<div class="col-sm-5">
									<button type="button" id="continueButton"
										class="btn btn-lg vertical-center">
										<span id="testButtonModal" onclick="continueCheckout();"
											style="font-family: AvenirNext; font-size: 20px; font-weight: bold; font-stretch: normal; font-style: normal; 
											line-height: normal; letter-spacing: 1.2px; text-align: center; color: #ffffff;">
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
					




</body>

<style>


input[type="checkbox"][id^="myCheckbox"] {
  display: none;
}

label {
  border: 1px solid #fff;

  position: relative;
  margin: 15px;
  cursor: pointer;
}

label:before {
  background-color: white;
  color: white;
  content: " ";
  display: block;
  border-radius: 50%;
  border: 1px solid grey;
  position: absolute;
  top: -5px;
  left: -5px;
  width: 25px;
  height: 25px;
  text-align: center;
  line-height: 28px;
  transition-duration: 0.4s;
  transform: scale(0);
}

label img {
  height: 100px;
  width: 100px;
  transition-duration: 0.2s;
  transform-origin: 50% 50%;
}

:checked + label {
  border-color: #ddd;
}

:checked + label:before {
  content: "";
  background-color: rgba(255, 142, 34, 0.31);
  
}

:checked + label img {
  transform: scale(0.9);
  /* box-shadow: 0 0 5px #333; */
  z-index: -1;
}

#paymentModalHeading {
font-family: AvenirNext;
    font-size: xx-large;
    font-weight: bold;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    /* letter-spacing: 2.4px; */
    text-align: center;
    color: #2d2d2d;
}


</style>


</html>