package com.addy.contus.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.addy.contus.dao.ContusDao;



@Controller
public class PaymentController {
	
	
	@Autowired
	public ContusDao contusDao;
	
	//static  Logger logger = Logger.getLogger(PaymentController.class.getName());
	
	@RequestMapping("/choice")
	public void paymentChoiceMethod(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		
		System.out.println("Here inside choice ****************************");
		
	
		try {
			//System.out.println("*******"+request.getSession().getId());
			request.getSession().setAttribute("validRequest", "true");
			response.sendRedirect("http://localhost:8080/Contus/test");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	} 
	
	
	@RequestMapping("/saveUser")
	public @ResponseBody String saveUserData(@RequestParam("userName") String name,@RequestParam("userEmail") String email,
											@RequestParam("userEmail") String couponCode,
											HttpServletRequest request,HttpServletResponse response) {
		System.out.println("Data saved start");
		System.out.println(name);
		System.out.println(email);
		System.out.println(couponCode);
		
		return "success";
	}
	
	@RequestMapping("/test")
	public String startTest(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("*******"+request.getSession().getAttribute("validRequest")+"********");
		if(request.getSession().getAttribute("validRequest")!=null && request.getSession().getAttribute("validRequest").equals("true"))
			return "paymentChoice";
		else
		{
			return "paymentChoice";
		}
	}
	
	@RequestMapping("/")
	public String mainPage(HttpServletRequest request,HttpServletResponse response) {
		
			return "mainPage";

	}

	@RequestMapping("/paymentModal")
	public String openPaymentModal(HttpServletRequest request,HttpServletResponse response) {
		
		
		
		
		return "paymentModal";

	}


}
