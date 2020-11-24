package com.addy.contus.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.addy.contus.entity.Coupon;
import com.addy.contus.entity.TestResult;

public interface PaymentService {
	
	
	public String createOrderForKlarna(HttpServletRequest request,String language);
	
	public String confirmOrder(String orderId,String couponCode,String couponAmount,String couponNumb,HttpServletRequest request);
	
	public Coupon checkValidCoupon(String code);
	
	public TestResult calculateResult(Integer[] answers,String orderId);
	
	public List<Coupon> couponPresent();
	
	public String createCoupon(String couponCode,String amount,String numb_allowed);
	
	public String deleteCoupon(String code);
	
	public void updateCoupon(String couponCode, String couponNumb);
	
	public boolean saveCustomerData(String custName,String custEmail,String couponCode,String couponRedeemedNumb,String amount,String paymentMethod,HttpServletRequest request,String language);
	
	public boolean sendContactForm(String name,String comppany,String email,String msg);
}
