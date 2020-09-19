package com.addy.contus.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import com.addy.contus.dao.ContusDao;
import com.addy.contus.entity.Coupon;
import com.addy.contus.entity.QuestionSet;
import com.addy.contus.entity.TestResult;
import com.addy.contus.service.PaymentService;
import com.addy.contus.utility.ContusThreadUtility;


@Controller
public class BaseFunctionController {


	@Autowired
	public PaymentService paymentService;
	
	@Autowired
	ContusDao contusDao;
	
	private static final Logger logger = Logger.getLogger(BaseFunctionController.class);

	@RequestMapping("/choice")
	public void paymentChoiceMethod(HttpServletRequest request,HttpServletResponse response,Model model,ModelMap map)
	{
		System.out.println(request.getHeader("accept-language"));
		System.out.println("Here inside choice ****************************");

		String orderId=(String) request.getSession().getAttribute("orderId");
		String payment=(String) request.getSession().getAttribute("payment");
		String couponCode=(String) request.getSession().getAttribute("couponCode");
		String couponAmount=(String) request.getSession().getAttribute("couponAmount");
		String couponNumb=(String) request.getSession().getAttribute("couponNumb");
		String language=(String) request.getSession().getAttribute("language");
		
		System.out.println(orderId);
		System.out.println(payment);
		System.out.println(couponCode);
		System.out.println(couponAmount);
		System.out.println(couponNumb);
		System.out.println(language);
		
		if(payment!=null &&  payment.equalsIgnoreCase("klarna")) {
			String confirmOrderResponse=paymentService.confirmOrder(orderId,couponCode,couponAmount,String.valueOf(couponNumb));

			if(confirmOrderResponse.equals("error"))
			{
				try {
					response.sendRedirect("http://localhost:8080/Contus/error");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(payment!=null && payment.equalsIgnoreCase("swish")) {
			
		}

		try {
			//System.out.println("*******"+request.getSession().getId());
			request.getSession().setAttribute("validRequest", "true");
			response.sendRedirect("http://localhost:8080/Contus/test");

		} catch (IOException e) {

			e.printStackTrace();
		}


	} 


	@RequestMapping("/saveUser")
	public @ResponseBody String saveUserData(@RequestParam(value="userName",required=false) String name,
			@RequestParam(value="userEmail",required=false) String email,
			@RequestParam("userCoupon") String couponCode,
			@RequestParam("paymentMode")String payment,@RequestParam("lang")String language,
			HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		System.out.println("Data saved start");
		System.out.println(language);
		System.out.println(email);
		System.out.println(couponCode);
		System.out.println(payment);
		System.out.println(request.getHeader("accept-language"));
		request.getSession().setAttribute("language",language);
		Coupon coupon=paymentService.checkValidCoupon(couponCode);
		if(coupon==null)
		{
			request.getSession().setAttribute("couponCode","none");
			request.getSession().setAttribute("couponAmount","9900");
		}
		else {
			request.getSession().setAttribute("couponCode",coupon.getCouponCode());
			request.getSession().setAttribute("couponAmount",coupon.getAmount());
			request.getSession().setAttribute("couponNumb",String.valueOf(coupon.getRedeemedNumb()));
		}
		if(coupon!=null && (coupon.getAmount().equals("0") || coupon.getAmount().equals("0.0") || coupon.getAmount().equals("0.00")))
		{
			paymentService.updateCoupon(couponCode, String.valueOf(coupon.getRedeemedNumb()));
			
			//save customer as customer is not yet being saved
			
			boolean dataSaved=paymentService.saveCustomerData(name, email, couponCode, String.valueOf(coupon.getRedeemedNumb()), "0.00", "Coupon",request,language);
			logger.info(" !!!!!!!! Free coupon customer data saved status - "+dataSaved);
			
			return "free-http://localhost:8080/Contus/choice";
			
		}
		
		request.getSession().setAttribute("payment", payment);

		if(payment!=null && payment.equalsIgnoreCase("klarna"))
		{

			String paymentResponse=paymentService.createOrderForKlarna(request,language);
			if(paymentResponse.equals("error"))
			{
				return "error";
			}

			return paymentResponse;
		}

		else {
			
			
			return "swish";
		}

	}

	@RequestMapping("/test")
	public String startTest(HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		System.out.println(request.getHeader("accept-language"));
		 logger.info("just a test info log");
		map.put("orderId", request.getSession().getAttribute("orderId"));
		
		if(request.getSession().getAttribute("validRequest")!=null && request.getSession().getAttribute("validRequest").equals("true"))
			return "paymentChoice";
		else
		{
			System.out.println("session time out********************");
			return "paymentChoice";
		}
	}

	@RequestMapping(value= {"/","/index","/home","default"})
	public String mainPage(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%");
		return "mainPage";

	}

	@RequestMapping("/paymentModal")
	public String openPaymentModal(HttpServletRequest request,HttpServletResponse response) {




		return "paymentModal";

	}

	@RequestMapping("/freeCouponApp")
	public String pushMethod(HttpServletRequest request,HttpServletResponse response) {


		System.out.println("********************freeCouponApp");

		return "paymentChoice";

	}

	@RequestMapping("/terms")
	public String termsMethod(HttpServletRequest request,HttpServletResponse response) {

		System.out.println("********************terms");


		return "paymentModal";

	}

	@RequestMapping("/checkout")
	public String checkoutMethod(HttpServletRequest request,HttpServletResponse response) {


		System.out.println("********************checkout");

		return "paymentModal";

	}
	
	@RequestMapping("/error")
	public String errorMethod(HttpServletRequest request,HttpServletResponse response) {


		System.out.println("********************paymentFailiure");

		return "paymentFailiure";

	}
	
	@RequestMapping("/calculateResult")
	public @ResponseBody String calculteResultMethod(HttpServletRequest request,HttpServletResponse response,@RequestParam("answersList[]")Integer[] answers,
										@RequestParam("orderIdValue")String orderId,@RequestParam("lang")String language) {
		
		logger.info("Inside Calculate result in controller");
		
		logger.info(answers.length);
		System.out.println(language);
		TestResult result=paymentService.calculateResult(answers, orderId);
		logger.info(result.toString());
		
		ContusThreadUtility utility=new ContusThreadUtility(result,orderId,language);
		
		utility.start();  // Thread started
		
		String resultTextForJs=result.neuroticism.totalPercentage.intValue()+"-"+result.extraversion.totalPercentage.intValue()+"-"+result.openness.totalPercentage.intValue()
					+"-"+result.agreeableness.totalPercentage.intValue()+"-"+ result.conscientiousness.totalPercentage.intValue();
		logger.info("%%%%%%%%% result js "+resultTextForJs);
		
		return resultTextForJs;

	}
	
	@RequestMapping("/submitQuesions")
	public String submitQuesionsMethod(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("questionSet") QuestionSet questionSetObject) {

		//System.out.println(ques1);
		System.out.println("********************submitQuesions"+questionSetObject);
		
		
		System.out.println("********************submitQuesions"+questionSetObject.questionsList.get(0));
		
		
		return "success";

	}
	
	@RequestMapping("/checkCoupon")
	public @ResponseBody String checkCoupon(@RequestParam("userCoupon")String couponCode,HttpServletRequest request,HttpServletResponse response)
	{
		String validCoupon="false";
		Coupon coupon=paymentService.checkValidCoupon(couponCode);
		if(coupon!=null)
		{
			validCoupon=coupon.getAmount();
			Double amnt=Double.parseDouble(coupon.getAmount());
	
			Integer numAllow=coupon.getNumbAllowed();
			Integer numbRedeem=coupon.getRedeemedNumb();
			if(numAllow<=numbRedeem)
				return "false";
			if(amnt==0)
				return "free";
			logger.info("Valid coupon "+validCoupon);
		}
		return validCoupon;
	}
	

	@RequestMapping("/submitContactForm")
	public @ResponseBody String submitContactFormMethod(@RequestParam(value="contactName") String name,	@RequestParam(value="contactEmail") String email,
								@RequestParam("contactCompany") String company,	@RequestParam("contactComment")String message,
								HttpServletRequest request,HttpServletResponse response) {
		
			System.out.println(name);
			System.out.println(company);
			System.out.println(email);
			System.out.println(message);
			
			boolean msg=paymentService.sendContactForm(name, company, email, message);
			
			if(msg)
				return "success";
			else
				return "error";
			
	}

	
}
