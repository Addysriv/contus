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
$( document ).ready(function() {
	$('#paymentDiv').hide();
	
	$('#payButton').on("click",function(){
		debugger;
		/* stripe.redirectToCheckout({
			  lineItems: [{
			    price: 'price_1HEAUIGjv13unDRN0ROam4KV', // Replace with the ID of your price
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
			}); */
		
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


<div id="userInfo">
		<div class="row"><br><br></div>
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
					
					
		
		<template>
  <div class="klarna-checkout" id="klarna-checkout">
    <div id="klarna-render-checkout" />
    <div v-if="checkout.loading">
      <loading-spinner />
    </div>
    <div v-if="checkout.error">
      Loading Klarna failed
    </div>
    <div v-if="checkout.snippet" v-html="checkout.snippet" /> <!-- eslint-disable-line vue/no-v-html -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { callApi } from '../helpers'
import { currentStoreView } from '@vue-storefront/core/lib/multistore'
import LoadingSpinner from 'theme/components/theme/blocks/AsyncSidebar/LoadingSpinner.vue'
import { isServer } from '@vue-storefront/core/helpers'
import { KlarnaEvents } from '../types'
import { plugins } from '../plugins'
export default {
  name: 'KlarnaCheckout',
  components: {
    LoadingSpinner
  },
  async mounted () {
    if (!isServer) {
      this.upsertOrder()
    }
  },
  beforeMount () {
    this.$bus.$on('klarna-update-order', this.configureUpdateOrder)
  },
  beforeDestroy () {
    this.$bus.$off('klarna-update-order')
  },
  computed: {
    ...mapGetters({
      order: 'kco/order',
      checkout: 'kco/checkout',
      totals: 'kco/platformTotals',
      hasTotals: 'kco/hasTotals',
      coupon: 'kco/coupon'
    })
  },
  watch: {
    coupon (newValue, oldValue) {
      if (!oldValue || newValue.code !== oldValue.code) {
        this.$bus.$emit('klarna-update-order')
      }
    },
    totals (newValue, oldValue) {
      if (oldValue) {
        if (newValue.qty !== oldValue.qty || newValue.base_grand_total !== oldValue.base_grand_total) {
          const storeView = currentStoreView()
          const countryId = this.$store.state.checkout.shippingDetails.country ? this.$store.state.checkout.shippingDetails.country : storeView.tax.defaultCountry
          this.$store.dispatch('cart/syncShippingMethods', {
            country_id: countryId
          })
          this.$bus.$emit('klarna-update-order')
        }
      }
    }
  },
  methods: {
    setupKlarnaListeners () {
      const events = {}
      Object.values(KlarnaEvents).forEach(event => {
        events[event] = data => {
          plugins.filter(plugin => plugin.on && plugin.on[event]).forEach(plugin => {
            plugin.on[event](data)
          })
          this.$bus.$emit('klarna-event-' + event, data)
        }
      })
      callApi(api => api.on(events))
      // Todo: refactor
      this.$bus.$on('klarna-order-loaded', () => {
        setTimeout(async () => {
          const order = await this.$store.dispatch('kco/fetchOrder', this.checkout.orderId)
          this.onKcoAddressChange({
            totalSegments: this.totals.total_segments,
            shippingAddress: order.shipping_address
          })
        }, 2000)
      })
    },
    async upsertOrder () {
      await this.$store.dispatch('kco/createOrder')
      const { default: postscribe } = await import('postscribe')
      postscribe('#klarna-render-checkout', this.checkout.snippet)
      await Promise.resolve()
      this.setupKlarnaListeners()
    },
    async configureUpdateOrder () {
      if (!this.checkout.order || !this.checkout.order.order_id) {
        return
      }
      await this.suspendCheckout()
      await this.upsertOrder()
      await this.resumeCheckout()
    },
    suspendCheckout () {
      return callApi(api => api.suspend())
    },
    resumeCheckout () {
      return callApi(api => api.resume())
    },
    onKcoAddressChange (orderData) {
      if (orderData.shippingAddress.postal_code) {
        this.$bus.$emit('kcoAddressChange', orderData)
      }
      return callApi(api => api.on({
        billing_address_change: async () => {
          this.$bus.$emit('klarna-order-loaded')
        }
      }))
    }
  }
}
</script>

<style lang="scss">
div.wrapper.wrapper {
  height: 30vh;
  max-width: 100%;
  padding-left: 25px;
}
#klarna-unsupported-page {
  display: none;
}
</style>			
</body>
</html>