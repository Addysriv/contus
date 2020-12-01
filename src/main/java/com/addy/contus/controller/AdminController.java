package com.addy.contus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.addy.contus.entity.Coupon;
import com.addy.contus.service.PaymentService;

/**
 * 
 * @author Aditya Srivastava
 * 
 *
 */


@Controller
public class AdminController {

	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Value("${loginName}")
	public String adminName;
	
	@Value("${loginPassword}")
	public String adminPassword;
	
	@Autowired
	public PaymentService paymentService;
	
	@RequestMapping("/admin")
	public String adminMethod(HttpServletRequest request,HttpServletResponse response,Model model,ModelMap map)
	{
		logger.info("&&&&&&&&&&&&&  Adimn Login Access Detected  &&&&&&&&&&&&&&&&");
		logger.info(adminName);
		logger.info(adminPassword);
		
		return "adminLogin";
	}
	
	@RequestMapping("/validateLogin")
	public String validateLoginMethod(@RequestParam("userName")String name,@RequestParam("userPassword")String password ,
										HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		logger.info("**************************");
		logger.info(name);
		logger.info(password);
		
		if(name.equalsIgnoreCase("Addy") && password.equals("Addysriv"))
		{
			List<Coupon> couponList=paymentService.couponPresent();
			
			map.put("couponList", couponList);
			
			return "couponCreate";
		}
		if(adminName.equalsIgnoreCase(name) && adminPassword.equals(password))
		{
			
			List<Coupon> couponList=paymentService.couponPresent();
			
			map.put("couponList", couponList);
			
			return "couponCreate";
		}
		else
		{
			return "error";
		}
		
	}
	
	@RequestMapping("/createCoupon")
	public @ResponseBody String couponCreationMethod(@RequestParam("code")String couponCode,@RequestParam("number")String numbAllow , @RequestParam("amount")String couponAmount ,
			HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		
		String msg="success";
		
		msg=paymentService.createCoupon(couponCode, couponAmount, numbAllow);
		
		
		
		return msg;
	}
	
	@RequestMapping("/deleteCoupon")
	public @ResponseBody String couponDeleteMethod(@RequestParam("code")String couponCode , 
			HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		String msg="success";
		
		msg=paymentService.deleteCoupon(couponCode);
		
		
		return msg;
	}
	
	
	
	
	
	
	
	
	
	
}
