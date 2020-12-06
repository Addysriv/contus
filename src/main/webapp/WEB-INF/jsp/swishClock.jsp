<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/customFrontPage.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
<title>Swish Payment Gateway</title>
<script type="text/javascript">



var swishTimeoutMsg="<spring:message code="label.contus.swishLimitReached" />";



	function startTimer(duration, display,swishCheckouturl,appCheckRequest) {
		var newseconds = 180;
		var timer = duration, minutes, seconds;
		var counter = setInterval(function() {
			if (newseconds <= 0) {
				clearInterval(counter);
				//$("#message-timeout").show();
				$('#openSwishAppText').text(swishTimeoutMsg);
				$('#time').text('00:00');
				$('#swishAnimeImg').removeClass('rotate');
				return;
			} else if ($("#swish-success").is(":visible")) {
				clearInterval(counter);
				return;
			}
			else if ($("#message-swish-launch-error").is(":visible")) {
				clearInterval(counter);
				return;
			}
			else {
				newseconds = newseconds - 1;
			}
			minutes = parseInt(timer / 60, 10);
			seconds = parseInt(timer % 60, 10);

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			display.textContent = minutes + ":" + seconds;

			if (--timer < 0) {
				timer = duration;
			}

		}, 1000);

		var secondCounter = 36;

		var timeoutCounter2 = setInterval(function() {
			if (secondCounter <= 0) {
				clearInterval(timeoutCounter2);
				clearInterval(counter);
				/* $("#swish-timeout").hide();
				$("#message-timeout").show(); */
				$('#openSwishAppText').text(swishTimeoutMsg);
				$('#time').text('00:00');
				$('#swishAnimeImg').removeClass('rotate');
				return;
			} else {
				secondCounter--;
				paymentCheckout(timeoutCounter2,swishCheckouturl);
			}

		}, 5000);
	}

/*  	window.onload = function() {
		$("#swish-success").hide();
		$("#message-timeout").hide();
		var threeminutes = 60 * 3, display = document.querySelector('#time');
		var paymentRequestInfo = "${paymentRequestInfoUrl}";
		var appCheckRequest = true;
		console.log(paymentRequestInfo);
		var swishCheckouturl;
		var paymenturlarray;
		if (paymentRequestInfo.indexOf(',') > -1) {
			debugger;
			paymenturlarray = paymentRequestInfo.split(',');
			var location = paymenturlarray[0];
			console.log(location);
			swishCheckouturl = paymenturlarray[1];
			setTimeout(function() {
	            window.open("https://www.swish.nu/private",'_target');
	            appCheckRequest = false;
	        }, 10);
	        window.location = "swish://paymentrequest?token=" + location
					+ "&callbackurl=https://google.com//";
			console.log(swishCheckouturl);
		} else {
			swishCheckouturl = paymenturlarray;
			console.log(swishCheckouturl);
		}
		startTimer(threeminutes, display,swishCheckouturl,appCheckRequest);
	}
  */
	//Our custom function.


	function paymentCheckout(timeoutCounter2, swishCheckouturl) {
		$
				.ajax({
					url : "${pageContext.request.contextPath}/swish-get-paymentStatus",
					data : ({
						swishPaymentCheckurl : swishCheckouturl
					}),
					async : false,
					success : function(data) {
						debugger;
						console.log(data);
						/* if(!appCheckRequest){
							$("#message-swish-launch-error").show();
							$("#swish-timeout").hide();
							clearInterval(timeoutCounter2);
						} */
						//console.log the response
						if (data == 'CREATED') {
							
							console
									.log("Do Nothing!!Continue till we get Success or Fail Response");
						} else if (data == 'ERROR') {
							$("#swish-timeout").hide();
							clearInterval(timeoutCounter2);
							console.log("Payment Failed From Swish End!!");
							window.location.href = '${pageContext.request.contextPath}/error';

						} else if (data.includes("PAID")) {
							console.log('Status paid');
							var ur = data.split(':');
							clearInterval(timeoutCounter2);
							//$("#swish-success").show();
							window.location.href = '${pageContext.request.contextPath}/'+ ur[1];
						}

					}
				});
	}
</script>
<style>

#swishTextTimer{

	font-family:  Avenir next, sans-serif;
    font-size: 20px;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    letter-spacing: 2.4px; 
    text-align: center;
    color: #2d2d2d;

}

#openSwishAppText{

	font-family:  Avenir next, sans-serif;
    font-size: 18px;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    letter-spacing: 2.4px; 
    text-align: center;
    color: #2d2d2d;

}




.rotate {
  animation: rotation 5s infinite linear;
}

@keyframes rotation {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(359deg);
  }
}
div#message-timeout {
	font-family:  Avenir next, sans-serif;
    font-size: 20px;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    letter-spacing: 2.4px; 
    text-align: center;
    color: #2d2d2d;
	
}

div#message-swish-launch-error {
	font-family:  Avenir next, sans-serif;
    font-size: 25px;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    letter-spacing: 5px; 
    text-align: center;
    color: red;
	
}

#swishText {
	margin-left: 7% !important;
}

@media only screen and (max-width: 600px) {
	#swishText {
		margin-left: 7% !important;
	}
	#swishImage {
		margin-left: 0% !important;
	}
	#extraSpaceDiv {
		margin-top: 11%;
	}
	#klarnaImage {
		margin-left: 0% !important;
		margin-top: -5% !important;
	}
	#payButton {
		margin-left: 5% !important;
	}
}

@media only screen and (max-width: 441px) {
	#klarnaImage {
		margin-left: -8% !important;
		margin-top: -5% !important;
	}
	#swishImage {
		margin-left: -10% !important;
		margin-top: -4% !important;
	}
	#swishText {
		margin-left: 7% !important;
	}
}

@media only screen and (max-width: 353px) {
	
	#swishText {
		font-size: 10px !important;
	}
}
</style>
</head>
<body>

<div class="container">
	<div id="swish-timeout">
		<br>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" style="text-align: center;">
				<span id="openSwishAppText"> <spring:message code="label.contus.launchSwish" /></span>
				
			</div>
		
		</div>
		<br><br><br>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" style="text-align: center;">
				<div class="center" >
					<img src="resources/swishImageCrop.png" id="swishAnimeImg"
						style="width: 21%;"	class="rotate" /> 
						<span id="swishText">
						</span>
				</div>
				<br>
				
				<!-- <img src="resources/swishTextImage.png" id="swishImage" style="width: 80px;height: 24px;"/> -->
					<!-- <div  id="swishTextTimer">
						Session expiring in <span id="time">03:00</span> minutes!
						
					</div> -->
					<span id="time" style="float:right;font-family:  Avenir next, sans-serif;">03:00</span>
			</div>
		</div>
		<br>
	</div>

		<div id="swish-success">
			<br>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6" style="text-align: center;">
					<div class="center;">
						<div class="row text-center">
							<div class="col-sm-6 col-sm-offset-3">
								<br> <br>
								<p>Detail Message</p>
								<div class="alert alert-success">
									<strong>Success!</strong>
								</div>
								<dl class="dl-horizontal">
									<dt>Payment Succesfull in Swish Gateway. Redirecting you
										to HomePage</dt>

								</dl>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
		<div id="message-swish-launch-error" style="display: none;">
			<br></br>
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8" style="text-align: center;">
					<span id="openSwishAppText"> <spring:message
							code="label.contus.launchSwishFailed" /></span>
				</div>
			</div>
		</div>
		<div id="message-timeout" style="display:none;">
		SWISH REACHED A TIME LIMIT BEFORE PAYMENT STARTED
	</div>
</div>
</body>
</html>