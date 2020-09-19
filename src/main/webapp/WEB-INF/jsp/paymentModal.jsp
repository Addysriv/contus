<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/customFrontPage.css">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	
<!-- jQuery library -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
	
<script src="resources/js/frontPage.js"></script>
	
	
<script>

 var defaultLang='';
 var userLang = navigator.language || navigator.userLanguage;
 defaultLang=userLang;
 
 var klarnaClicked=false;
 var swishClicked=false;
 var checkIfCouponValid=true;
 var invalidErrorCode='<spring:message code="label.contus.invalidCoupon" />';
 var couponApplied='<spring:message code="label.contus.validCoupon" />';
 var currency= '<spring:message code="label.contus.sek" />';
$( document ).ready(function() {
	$('#paymentDiv').hide();
	$('#paymentOrderDiv').hide();
	$('#payButton').on("click",function(){
		$(".modalDiv").show();
		continuePayment();
		
	});
	
	$('#userCoupon').on("change",function(){
		var coupon=$('#userCoupon').val();
		if($('#userCoupon').val()=="")
			{
			checkIfCouponValid=true;
			$('#couponError').text('');
			$('#userCoupon').css("border-color","rgba(255, 142, 34, 0.31)");	
			$('#userCoupon').css("background-color","rgba(255, 142, 34, 0.31)");	
			}
		else{
		$.ajax({ 
	        url : "${pageContext.request.contextPath}/checkCoupon",
			async : false,
			data : ({ 
				userCoupon : coupon,
				}), 
				success : function(jqXHR) {
					//console.log(jqXHR);
					if(jqXHR!="false")
					{
						checkIfCouponValid=true;
						if(jqXHR=="free")
						{
							$('#amountToPay').text("0.00");
							$('#couponError').text(couponApplied+' '+'0.00'+' '+currency);
							$('#couponError').css("color","orange");
							$('#userCoupon').css("border-color","rgba(255, 142, 34, 0.31)");	
							$('#userCoupon').css("background-color","rgba(255, 142, 34, 0.31)");	
						}
						else{
							jqXHR=jqXHR.substring(0, 2)+"."+jqXHR.substring(2, 4);
						$('#amountToPay').text(jqXHR);
						$('#couponError').text(couponApplied+' '+jqXHR+' '+currency);
						$('#couponError').css("color","orange");
						$('#userCoupon').css("border-color","rgba(255, 142, 34, 0.31)");	
						$('#userCoupon').css("background-color","rgba(255, 142, 34, 0.31)");	
						}	
					}
					else
					{
						
						checkIfCouponValid=false;
					    $('#couponError').text(invalidErrorCode);
					    $('#couponError').css("color","red");
					    $('#userCoupon').css("border-color","red");
					}
				}
		});
		}
		
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
			$('#userName').css("border-color","red");
		}
		else{
			$('#userName').css("border-color","#ccc");
			
		}
		
		
	}); 
	
	
});


/* 
function selectPaymentMethod(num){
	$('#paymentChooseText').css("color","black");
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
} */

function continueCheckout(){
	var flag = true;
	debugger;
	var coupon=$('#userCoupon').val();
	/* var payment=$("input[name='paymentMethod']:checked"). val();
	if(payment==undefined)
	
	couponError
	 */
	if($('#amountToPay').text()=="0.00"){
		$("#userName").show();
		$("#userEmail").show();
	}
	else{
		$("#userName").hide();
		$("#userEmail").hide();
	}
	if(checkIfCouponValid==true){	
	$('#userInfo').hide();
	$('#paymentDiv').show();
	}
}

function continuePayment(){
	
	var name=$('#userName').val();
	var email=$('#userEmail').val();
	var coupon=$('#userCoupon').val();
	
	var payment=$("input[name='paymentMethod']:checked"). val();

	if($('#incorrectEmail').text()=="")
		{
		
		}
	else
		{
		return;
		}
	
	$.ajax({ 
        url : "${pageContext.request.contextPath}/saveUser",
		async : false,
		data : ({ 
			userName : name,
			userEmail : email,
			userCoupon : coupon,
			paymentMode : payment,
			lang:defaultLang
			}), 
			success : function(jqXHR) {
				console.log('success');
				
				$(".modalDiv").hide();
				if(jqXHR.includes('free-'))
				{
					console.log('Customer is not getting saved for free users.');
					var ur=jqXHR.split('-');
					
					window.location.href = ur[1];
				}
				else if(jqXHR=='error'){
					console.log("error occured");
					window.location.href = 'http://localhost:8080/Contus';
				}
				else{
				$('#KCO').val(jqXHR);
				startKlarnaMethod();
				$('#paymentDiv').hide();
				$('#paymentOrderDiv').show();
				}
			}
	});
	
	
}

</script>
</head>
<body>


<div id="userInfo">

						   <div class="row"><br></div>
						   
						   	
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-6" style="text-align:center;" id="paymentChooseText">
							<h4><spring:message code="label.contus.paymentMethod" />
							</h4>
			
						</div>
						</div>
						<div class="row"><br></div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<hr>
			</div>

		</div>
		<div class="row" style="margin-bottom: -17px;margin-top: -13px;">
				<div class="col-sm-2"></div>
				<div class="col-sm-2">
				<div class="radio">
					<label><input type="radio" name="paymentMethod" style="transform: scale(1.5);" value="klarna" checked></label>
				</div>
				
				</div>
				<div class="col-sm-2" style="margin-bottom: 8px;margin-top: 8px;">
					<img src="resources/klarna.jpg"  style="width: 80px;height: 27px;"/> 
					</div>
					<div class="col-sm-2" style="    font-family: AvenirNext;font-size: 16px;margin-top: 10px;
					font-stretch: normal;font-style: normal;line-height: normal;letter-spacing: 2.4px;"> 
					<spring:message code="label.contus.klarna" />
				</div>
			</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<hr>
			</div>
		</div>
		<div class="row" style="margin-bottom: -17px;margin-top: -13px;">
				<div class="col-sm-2"></div>
				<div class="col-sm-2">
				<div class="radio">
					<label><input type="radio" name="paymentMethod" value="swish" style="transform: scale(1.5);"></label>
				</div>
				
				</div>
				<div class="col-sm-2">
					<img src="resources/swish.png" style="width: 89px;height: 40px;"/> 
					</div>
					<div class="col-sm-2" style="    font-family: AvenirNext;font-size: 16px;margin-top: 10px;
					font-stretch: normal;font-style: normal;line-height: normal;letter-spacing: 2.4px;"> 
					<spring:message code="label.contus.swish" />
				</div>
		</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<hr>
			</div>
		</div>
		 <div class="row"><br><br></div>

							<div class="row">
								<div class="col-sm-2"></div>
								
								<!-- <div class="col-sm-2" style="font-size: smaller;"><b>Coupon Code :</b> (If any provided by company)</div> -->
								<div class="col-sm-8">
									<input type="text" placeholder="   Coupon Code" id="userCoupon" style="width:100%;border-radius: 
									14px;background-color: rgba(255, 142, 34, 0.31);">
									&nbsp;&nbsp;<span id="couponError" style="color:red"></span>
								</div>		
							</div>
			<div class="row"><br></div>
			
		
						<div class="row">
							<div class="col-sm-4"></div>
							<div class="col-sm-5">
									<button type="button" id="continueButton"  onclick="continueCheckout();" 
										class="btn btn-lg vertical-center">
										<span id="testButtonModal" 
											style="font-family: AvenirNext; font-size: 25px; font-weight: bold; font-stretch: normal; font-style: normal; 
											line-height: normal; letter-spacing: 1.2px; text-align: center; color: #ffffff;">
											<spring:message code="label.contus.continue" />
											</span>
									</button>
								</div>
							</div>
					
						  <div class="row"><br></div>
						</div>
						
					<div id="paymentDiv">
						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">
								<h3><spring:message code="label.contus.warning" />
								<br></h3>
							</div>
						</div>
						<div class="row" id="loaderDiv"><br></div>
						
							<div class="row" class="userDataDiv">
								<div class="col-sm-3"></div>
								<div class="col-sm-6">
									<input type="text" placeholder="&nbsp;&nbsp;<spring:message code="label.contus.contactName" />"	 id="userName">
								
								</div>
							</div>
							<div class="row" ><br></div>
							<div class="row" class="userDataDiv">
								<div class="col-sm-3"></div>
								<div class="col-sm-6">
									<input type="text" placeholder="&nbsp;&nbsp;<spring:message code="label.contus.contactEmail" />"  id="userEmail">
								<span id="incorrectEmail" style="color:red;"></span>
								</div>
							</div>
						
						<div class="row" ><br><br></div>
						<div class="row">
							<div class="col-sm-4"></div>
							<div class="col-sm-5">
							<button type="button" id="payButton" class="btn btn-lg vertical-center"><span id="testButtonModal" 
							 style="font-family: AvenirNext; font-size: 20px; font-weight: bold;
	  							font-stretch: normal; font-style: normal; line-height: normal; letter-spacing: 1.2px; text-align: center; color: #ffffff;">
	  								<spring:message code="label.contus.price" /> <span id="amountToPay">99 </span> <spring:message code="label.contus.sek" /> </span></button>
							</div>
						</div>
						<div class="row"><br></div>
					</div>
					
					<div id="paymentOrderDiv">
						
				<textarea style="display: none" id="KCO">
	
			    </textarea>
			
			
			
				<div id="my-checkout-container"></div>
			
			
			
				<!-- START - Dont edit -->
				<script type="text/javascript">
				//startKlarnaMethod();
				function startKlarnaMethod(){
				var checkoutContainer = document.getElementById('my-checkout-container')
					checkoutContainer.innerHTML = (document.getElementById("KCO").value).replace(/\\"/g, "\"").replace(/\\n/g, "");
					var scriptsTags = checkoutContainer.getElementsByTagName('script')
					for (var i = 0; i < scriptsTags.length; i++) {
						var parentNode = scriptsTags[i].parentNode
						var newScriptTag = document.createElement('script')
						newScriptTag.type = 'text/javascript'
						newScriptTag.text = scriptsTags[i].text
						parentNode.removeChild(scriptsTags[i])
						parentNode.appendChild(newScriptTag)
					}
				}
				</script>
				<!-- END -->
					</div>
					
					




</body>

<style>



/* Center the loader */
#loader {
  position: absolute;
  left: 55%;
  top: 71%;
  z-index: 1;
  width: 150px;
  height: 150px;
  margin: -75px 0 0 -75px;
  border: 16px solid white;
  border-radius: 50%;
  border-top: 16px solid  orange;
  border-bottom: 16px solid  orange;
  width: 35px;
  height: 35px;
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

/* Add animation to "page content" */
.animate-bottom {
  position: relative;
  -webkit-animation-name: animatebottom;
  -webkit-animation-duration: 1s;
  animation-name: animatebottom;
  animation-duration: 1s
}

@-webkit-keyframes animatebottom {
  from { bottom:-100px; opacity:0 } 
  to { bottom:0px; opacity:1 }
}

@keyframes animatebottom { 
  from{ bottom:-100px; opacity:0 } 
  to{ bottom:0; opacity:1 }
}


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

.modalDiv {
    display:    none;
    position:   fixed;
    z-index:    1000;
    top:        0;
    left:       0;
    height:     100%;
    width:      100%;
    background: rgba( 255, 255, 255, .8 ) 
                url('http://i.stack.imgur.com/FhHRx.gif') 
                50% 50% 
                no-repeat;
}

#userEmail , #userName{
    width: 100%;
    height: 40px;
    border-radius: 14px;
    background-color: rgba(255, 142, 34, 0.31);
}


</style>


</html>