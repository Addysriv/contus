package com.addy.contus.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.addy.contus.dao.ContusDao;
import com.addy.contus.entity.BigFiveAgreeableness;
import com.addy.contus.entity.BigFiveConscientiousness;
import com.addy.contus.entity.BigFiveExtraversion;
import com.addy.contus.entity.BigFiveNeuroticism;
import com.addy.contus.entity.BigFiveOpenness;
import com.addy.contus.entity.Coupon;
import com.addy.contus.entity.Customer;
import com.addy.contus.entity.TestResult;
import com.addy.contus.utility.EmailSenderUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * 
 *   @author Aditya Srivastava
 * 
 * 	Scoring Key: 
		N1: Anxiety:1, 31, 61, 91
		N2: Anger: 6, 36, 66, 96R
		N3: Depression: 11, 41, 71, 101
		N4: Self-consciousness: 16, 46, 76R, 106R
		N5: Immoderation: 21, 51, 81R, 111R
		N6: Vulnerability: 26, 56R, 86R, 116R
		E1: Friendliness: 2, 32, 62, 92
		E2: Gregariousness: 7, 37, 67R, 97R
		E3: Assertiveness: 12, 42, 72, 102R
		E4: Activity Level: 17, 47, 77, 107
		E5: Excitement Seeking: 22, 52, 82, 112
		E6: Cheerfulness: 27, 57, 87, 117
		O1: Imagination: 3, 33, 63, 93
		O2: Artistic Interests: 8, 38R, 68R, 98R
		O3: Emotionality: 13, 43R, 73R, 103R
		O4: Adventurousness: 18R, 48R, 78R, 108R
		O5: Intellect: 23R, 53R, 83R, 113R
		O6: Liberalism: 28, 58R, 88R, 118R
		A1: Trust: 4, 34, 64, 94R
		A2: Morality: 9R, 39R, 69R, 99R
		A3: Altruism: 14, 44, 74, 104R
		A4: Cooperation: 19R, 49R, 79R, 109R
		A5: Modesty: 24R, 54R, 84R, 114R
		A6: Sympathy: 29, 59, 89, 119R
		C1: Self Efficacy: 5, 35, 65, 95
		C2: Orderliness: 10, 40, 70R, 100R
		C3: Dutifulness: 15, 45, 75R, 105R
		C4: Achievement Striving: 20, 50, 80, 110R
		C5: Self Discipline: 25, 55R, 85R, 115R
		C6: Cautiousness: 30R, 60R, 90R, 120R
*/
	

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);
	
	/*@Autowired
	*/
	
	@Autowired
	ContusDao contusDao; 
	
	@Autowired
	EmailSenderUtility emailUtility;
	
	@Value("${klarnaPostUrl}")
	String klarnaUrl;
	
	@Value("${klarnaCurrency}")
	String klarnaCurrentCurrency;
	
	@Value("${klarnaCountry}")
	String klarnaCurrentCountry;
	
	@Value("${klarnaUser}")
	String klarnaUserName;
	
	@Value("${klarnaPassword}")
	String klarnaUserPassword;
	
	@Value("${klarnaTermsUrl}")
	String termsUrl;
	
	@Value("${klarnaCheckOutUrl}")
	String checkOutUrl;
	
	@Value("${klarnaConfirmationUrl}")
	String confirmationUrl;
	
	@Value("${klarnaPushUrl}")
	String pushUrl;
	
	@Value("${klarnaOrderMngmntUrl}")
	String klarnaOrderUrl;
	
	public String createOrderForKlarna(HttpServletRequest request,String language) {
		
		logger.info("!!!!!!!!!!!!!!!! Inside klarna create method !!!!!!!! \n");
		
		String html="";
		//String price="9900";
		String country=klarnaCurrentCountry;
		String currency=klarnaCurrentCurrency;
		String locale="en-GB";
		String url_address=klarnaUrl;
		
		if(language.equals("sv"))
		{
			country="SE";
			locale="sv-se";
		}

		String amnt=(String) request.getSession().getAttribute("couponAmount");
		if(amnt!=null ) {
			logger.info("amount  - "+amnt);
			if(amnt.length()==2)
				amnt=amnt+"00";
			
		}
		else
			amnt="9900";
		
/*		String couponCode=(String) request.getSession().getAttribute("couponCode");
		if(couponCode!=null && !couponCode.equalsIgnoreCase("none"))
			 updateCoupon(couponCode,(String) request.getSession().getAttribute("couponNumb"));
*/
		
		String user=klarnaUserName;
		String password=klarnaUserPassword;
		
		String jsonRequest="{\"purchase_country\":\""+country+"\",\"purchase_currency\":\""+currency+"\",\"locale\":\""+locale+"\",\"order_amount\":"+amnt+",\"order_tax_amount\""
							+ ":0,\"order_lines\":[{\"type\":\"digital\",\"reference\":\"contus-test\",\"name\":\"Personality Test\",\"quantity\":1,\"quantity_unit\""
							+ ":\"pcs\",\"unit_price\":"+amnt+",\"tax_rate\":0,\"total_amount\":"+amnt+",\"total_discount_amount\":0,\"total_tax_amount\":0}],\"merchant_urls\""
							+ ":{\"terms\":\""+termsUrl+"\""
							+ ",\"checkout\":\""+checkOutUrl+"\",\""
							+ "confirmation\":\""+confirmationUrl+"\",\""
							+ "push\":\""+pushUrl+"\"}}";
		
		logger.info(jsonRequest);
		logger.info(" \n \n ");
		
		try {
				URL url = new URL(url_address);
			
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setConnectTimeout(5000);
		        conn.setRequestProperty("Content-Type", "application/json;");
		        conn.setDoOutput(true);
		        conn.setDoInput(true);
		        conn.setRequestMethod("POST");
		        String auth = user + ":" + password;
		        logger.info("auth ----- "+auth);
		        byte[] encodedAuth = Base64.getUrlEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
		        String authHeaderValue = "Basic " + new String(encodedAuth);
		        logger.info("authHeaderValue ----- "+authHeaderValue);
		        conn.setRequestProperty("Authorization", authHeaderValue);
		       
		        OutputStream os = conn.getOutputStream();
		        os.write(jsonRequest.getBytes("UTF-8"));
		        os.close(); 
		        logger.info(conn.getResponseMessage());
		        logger.info(conn.getResponseCode());
		        // read the response
		        BufferedReader br;
		        boolean valid=false;
		        if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
		            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            valid=true;
		        } else {
		            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            valid=false;

		            logger.error("%%%%%%%%%%%  Error occured in creating order %%%%%%%%%%%");
		            logger.error(conn.getResponseCode() +" "+ conn.getErrorStream());
		            String error="";
		            String erroMsg="";
		            while ((error = br.readLine()) != null) {
			        	logger.info("------------------------------");
			        	
			        	erroMsg=error+erroMsg;
			        	logger.info(error);
			          
			        }
		            logger.error(erroMsg);
		            
		            return "error";
		        }
		        
		        String output;
		        String responseStr="";
		        
		        while (valid && (output = br.readLine()) != null) {
		        	
		        	responseStr=responseStr+output;
		        	
		        }
		      
		        logger.info("***************** responseStr for order start ********************");
		        logger.info(responseStr);
		        logger.info("***************** responseStr for order end ********************  \n");
		        
		        if(valid) {
		        JsonElement root = new JsonParser().parse(responseStr);
		        
		        String orderId = root.getAsJsonObject().get("order_id").getAsString();
		         html= root.getAsJsonObject().get("html_snippet").getAsString();
		        
		         request.getSession().setAttribute("orderId", orderId);
		         request.getSession().setAttribute("amnt", amnt);
		         
		         logger.info("$$$$$$ oder id = "+orderId);
		         
		        logger.info(orderId);
		       
		        
		        }
		        logger.info("\n \n!!!!!!!!!!!!!!!! Executed klarna create method !!!!!!!! \n");
		        
		        return html;
			
		} catch (Exception e) {

			e.printStackTrace();
			logger.info(e.getCause());
			logger.error("%%%%%%%%%%%% error occured %%%%%%%");
			logger.error("\n");
			logger.error("Error : ",e);
			logger.error("%%%%%%%%%%%% error end %%%%%%%");
			return "error";
		}
		
	}
	
	public String confirmOrder(String orderId,String couponCode,String couponAmount,String couponNumb,HttpServletRequest request) {
		
		String url_address=klarnaUrl+"/"+orderId;
		String user=klarnaUserName;
		String password=klarnaUserPassword;
		logger.info(url_address);
		try {
			URL url = new URL(url_address);
			
			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setConnectTimeout(5000);
		        conn.setRequestProperty("Content-Type", "application/json;");
		        conn.setDoOutput(true);
		        conn.setDoInput(true);
		        conn.setRequestMethod("GET");
		        String auth = user + ":" + password;
		        logger.info("auth ----- "+auth);
		        byte[] encodedAuth = Base64.getUrlEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
		        String authHeaderValue = "Basic " + new String(encodedAuth);
		        logger.info("authHeaderValue ----- "+authHeaderValue);
		        conn.setRequestProperty("Authorization", authHeaderValue);
		       
		        logger.info(conn.getResponseMessage());
		        logger.info(conn.getResponseCode());
		        // read the response
		        BufferedReader br;
		        
		        if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
		            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            
		        } else {
		            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            
		            logger.error("%%%%%%%%%%%  Error occured in creating order %%%%%%%%%%%");
		            logger.error(conn.getResponseCode() +" "+ conn.getErrorStream());
		            String error="";
		            String erroMsg="";
		            while ((error = br.readLine()) != null) {
			        	logger.info("------------------------------");
			        	
			        	erroMsg=error+erroMsg;
			        	logger.info(error);
			          
			        }
		            logger.error(erroMsg);
		            
		            return "error";
		        }
		        
		        String output;
		        String responseStr="";
		        //logger.info();
		        logger.info("***************************");
		        while ((output = br.readLine()) != null) {
		        	logger.info("------------------------------");
		        	
		        	responseStr=responseStr+output;
		        	logger.info(output);
		          
		        }
		        
		        JsonElement root = new JsonParser().parse(responseStr);
		        
		        String status = root.getAsJsonObject().get("status").getAsString();
		       
		        
		        
		        Customer cust=new Customer();
		        cust.setCustName(root.getAsJsonObject().get("billing_address").getAsJsonObject().get("given_name").getAsString()+" "
		        				+root.getAsJsonObject().get("billing_address").getAsJsonObject().get("family_name").getAsString());
		        cust.setEmail(root.getAsJsonObject().get("billing_address").getAsJsonObject().get("email").getAsString());
		       /* cust.setAddress(root.getAsJsonObject().get("billing_address").getAsJsonObject().get("street_address").getAsString() +" addrerss 2 - "
		        				+root.getAsJsonObject().get("billing_address").getAsJsonObject().get("street_address2").getAsString()+ " postal code : "+
		        				 root.getAsJsonObject().get("billing_address").getAsJsonObject().get("postal_code").getAsString());*/
		        cust.setMobile(root.getAsJsonObject().get("billing_address").getAsJsonObject().get("phone").getAsString());
		        cust.setPaymentMethod("klarna");
		        cust.setOrderId(root.getAsJsonObject().get("order_id").getAsString());
		        cust.setAmountPaid(root.getAsJsonObject().get("order_amount").getAsString());
		        cust.setOrderStatus(root.getAsJsonObject().get("status").getAsString());
		        cust.setCouponUsed(couponCode);
		        
		        request.getSession().setAttribute("emailId",cust.getEmail());
				request.getSession().setAttribute("userName",cust.getCustName());
				
		        logger.info(status);
		        //logger.info();
		        
		        logger.info("No errors");
		        if(status.equalsIgnoreCase("checkout_complete"))
		        {
		        	saveCustomer(cust);
		        	if(!(couponCode==null || couponCode.equals("none")))
		        		updateCoupon(couponCode,couponNumb);
		        	captureOrder(cust.getOrderId(),cust.getAmountPaid());
		        	return "success";
		        }
		        else
		        {
		        	return "error";
		        }
		    
			
		} catch (Exception e) {

			e.printStackTrace();
			logger.info(e.getCause());
			logger.error("%%%%%%%%%%%% error occured %%%%%%%");
			logger.error("\n");
			logger.error("Error : ",e);
			logger.error("%%%%%%%%%%%% error end %%%%%%%");
			return "error";
		}
		
		
	}
	
	public void updateCoupon(String couponCode, String couponNumb) {
		
		Connection con=contusDao.getConnection();
		logger.info("In coupon Update method");
		logger.error("/n");
		 try(Statement stmt=con.createStatement())
        {
			 Integer num=Integer.parseInt(couponNumb);
			 num++;
			 stmt.executeUpdate("update coupon  set redeemed_numb='"+num+"' where coupon_code='"+couponCode+"'");
			 
			 logger.info(" %%%%%%%%%% Coupon deleted successfully "+couponCode);
        }
		 catch(SQLException sqlExc)
         {
			 
        	 logger.error("%%%%%%%%%%%% SQL error occured in updating coupon %%%%%%%%%%%%%%");
        	 logger.error("/n");
        	 logger.error(sqlExc);
        	
         }
		 catch(Exception ex) {
			 
			 logger.error("%%%%%%%%%%%% SQL error occured in updating coupon %%%%%%%%%%%%%%");
        	 logger.error("/n");
        	 logger.error("Error : ",ex);
		 }
		
		
	}

	private String captureOrder(String orderId,String amount)
	{
		logger.info("!!!!!!!!!!!! Ready to capture order id "+orderId);
		String msg="success";
		String baseUrl=klarnaOrderUrl+"/"+orderId+"/captures";
		String user=klarnaUserName;
		String password=klarnaUserPassword;
		
		String jsonRequest="{\"captured_amount\":" +amount+"}";
		
		logger.info(jsonRequest);
		logger.info("\n \n");
		

		try {
			URL url = new URL(baseUrl);
			
			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setConnectTimeout(5000);
		        conn.setRequestProperty("Content-Type", "application/json;");
		        conn.setDoOutput(true);
		        conn.setDoInput(true);
		        conn.setRequestMethod("POST");
		        String auth = user + ":" + password;
		        logger.info("auth ----- "+auth);
		        byte[] encodedAuth = Base64.getUrlEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
		        String authHeaderValue = "Basic " + new String(encodedAuth);
		        logger.info("authHeaderValue ----- "+authHeaderValue);
		        conn.setRequestProperty("Authorization", authHeaderValue);
		       
		        OutputStream os = conn.getOutputStream();
		        os.write(jsonRequest.getBytes("UTF-8"));
		        BufferedReader br;
		        os.close(); 
		        if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
		            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            
		        } else {
		            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            
		            logger.error("%%%%%%%%%%%  Error occured in capturing order %%%%%%%%%%%");
		            logger.error("\n");
		            logger.error(conn.getResponseCode() +" "+ conn.getErrorStream());
		            String error="";
		            String erroMsg="";
		            while ((error = br.readLine()) != null) {
			        	logger.info("------------------------------");
			        	
			        	erroMsg=error+erroMsg;
			        	logger.info(error);
			        	msg=erroMsg;
			          
			        }
		            logger.error(erroMsg);
		            logger.error("\n");
		            logger.error("\n");
		            return "error";
		        }
		        
		}
		catch(Exception ex) {
			logger.error("%%%%%%%%%%%% error occured method captureOrder %%%%%%%");
			logger.error("\n");
			logger.error("Error : ",ex);
			logger.error("%%%%%%%%%%%% error end %%%%%%%");
		}
		
		logger.info(" Captured msg - "+msg);
		return msg;
		
		
	}
	
	private String saveCustomer(Customer cust)
	{
		String msg="success";
		try {
			msg=contusDao.saveCustomer(cust);
		}
		
		catch(Exception ex) {
			msg="error";
			logger.error("\n");
			logger.error("%%%%%%%%%%%% error occured in saving customer  %%%%%%%");
			logger.error("\n");
			logger.error(cust.getOrderId());
			logger.error("Error : ",ex);
			logger.error("%%%%%%%%%%%% error end %%%%%%%");
		}
		
		
		return msg;
	}
	
	public Coupon checkValidCoupon(String code)
	{
		
		Coupon coupon=contusDao.checkValidCoupon(code);
		
		
		return coupon;
		
	}
	
	/** 
	 * This method calculates the np120 ipip test result with answers in array
	*/
	public TestResult calculateResult(Integer[] answers,String orderId)
	{
		logger.info("%%%%%%%%%%%%%%%%%%% Inside Calculate ressult service method %%%%%%%%%%%%%%%%%");
		logger.info(Arrays.toString(answers));	
		logger.info("\n");
		logger.info("****** orderId - "+orderId);
		TestResult result=new TestResult(); 
		
		BigFiveNeuroticism neuroticism=new BigFiveNeuroticism();
		
		neuroticism.n1Anxiety=answers[1]+answers[31]+answers[61]+answers[91];
		
		neuroticism.n2Anger=answers[6]+answers[36]+answers[66]+(6-answers[96]);
		
		neuroticism.n3Depression=answers[11]+answers[41]+answers[71]+(6-answers[101]);
		
		neuroticism.n4SelfConsciousness=answers[16]+answers[46]+answers[76]+(6-answers[106]);
		
		neuroticism.n5Immoderation=answers[21]+(6-answers[51])+(6-answers[81])+(6-answers[111]);
		
		neuroticism.n6Vulnerability=answers[26]+answers[56]+answers[86]+(6-answers[116]);
		
		Double totalScoreNeur=Double.valueOf(neuroticism.n1Anxiety+neuroticism.n2Anger+neuroticism.n3Depression+neuroticism.n4SelfConsciousness+neuroticism.n5Immoderation+neuroticism.n6Vulnerability);
		
		Double neuroPercent=(totalScoreNeur*100)/120.0;
		
		neuroticism.totalPercentage=neuroPercent;
		
		result.neuroticism=neuroticism;
		
		logger.info("  ***  Neuroticism  ***  ");
		logger.info(neuroticism.toString());
		
		BigFiveExtraversion extraversion=new BigFiveExtraversion();
		
		extraversion.e1Friendliness=answers[2]+answers[32]+(6-answers[62])+(6-answers[92]);
		
		extraversion.e2Gregariousness=answers[7]+answers[37]+(6-answers[67])+(6-answers[97]);
		
		extraversion.e3Assertiveness=answers[12]+answers[42]+answers[72]+(6-answers[102]);
		
		extraversion.e4ActivityLevel=answers[17]+answers[47]+answers[77]+(6-answers[107]);
		
		extraversion.e5ExcitementSeeking=answers[22]+answers[52]+answers[82]+answers[112];
		
		extraversion.e6Cheerfulness=answers[27]+answers[57]+answers[87]+answers[117];
		
		Double totalScoreExtra=Double.valueOf(extraversion.e1Friendliness+extraversion.e2Gregariousness+extraversion.e3Assertiveness+extraversion.e4ActivityLevel+
							extraversion.e5ExcitementSeeking+extraversion.e6Cheerfulness);
		
		Double extraPercent=(totalScoreExtra*100)/120.0;
		
		extraversion.totalPercentage=extraPercent;
		
		result.extraversion=extraversion;
		
		logger.info("  ***  extraversion  ***  ");
		logger.info(extraversion.toString());
		
		BigFiveOpenness openness=new BigFiveOpenness();
		
		openness.o1Imagination=answers[3]+answers[33]+answers[63]+answers[93];

		openness.o2ArtisticInterests=answers[8]+answers[38]+(6-answers[68])+(6-answers[98]);
		
		openness.o3Emotionality=answers[13]+answers[43]+(6-answers[73])+(6-answers[103]);
		
		openness.o4Adventurousness=answers[18]+(6-answers[48])+(6-answers[78])+(6-answers[108]);
		
		openness.o5Intellect=answers[23]+(6-answers[53])+(6-answers[83])+(6-answers[113]);
		
		openness.o6Liberalism=answers[28]+answers[58]+(6-answers[88])+(6-answers[118]);
		
		Double totalScoreOpenes=Double.valueOf(openness.o1Imagination+openness.o2ArtisticInterests+openness.o3Emotionality+openness.o4Adventurousness+
				openness.o5Intellect+openness.o6Liberalism);

		Double openesPercent=(totalScoreOpenes*100)/120.0;

		openness.totalPercentage=openesPercent;
		
		result.openness=openness;
		
		logger.info("  ***  openness  ***  ");
		logger.info(openness.toString());
		
		BigFiveAgreeableness agreeableness=new BigFiveAgreeableness();
		
		agreeableness.a1Trust=answers[4]+answers[34]+answers[64]+(6-answers[94]);
		
		agreeableness.a2Morality=(6-answers[9])+(6-answers[39])+(6-answers[69])+(6-answers[99]);
		
		agreeableness.a3Altruism=answers[14]+answers[44]+(6-answers[74])+(6-answers[104]);
		
		agreeableness.a4Cooperation=(6-answers[19])+(6-answers[49])+(6-answers[79])+(6-answers[109]);
		
		agreeableness.a5Modesty=(6-answers[24])+(6-answers[54])+(6-answers[84])+(6-answers[114]);
		
		agreeableness.a6Sympathy=answers[29]+answers[59]+(6-answers[89])+(6-answers[119]);
		
		Double totalScoreAgree=Double.valueOf(agreeableness.a1Trust+agreeableness.a2Morality+agreeableness.a3Altruism+agreeableness.a4Cooperation+
					agreeableness.a5Modesty+agreeableness.a6Sympathy);

		Double agreePercent=(totalScoreAgree*100)/120.0;

		agreeableness.totalPercentage=agreePercent;
		
		result.agreeableness=agreeableness;
		
		logger.info("  ***  agreeableness  ***  ");
		logger.info(agreeableness.toString());
		
		BigFiveConscientiousness conscientiousness=new BigFiveConscientiousness();
		
		conscientiousness.c1SelfEfficacy=answers[5]+answers[35]+answers[65]+answers[95];
		
		conscientiousness.c2Orderliness=answers[10]+(6-answers[40])+(6-answers[70])+(6-answers[100]);
		
		conscientiousness.c3Dutifulness=answers[15]+answers[45]+(6-answers[75])+(6-answers[105]);
		
		conscientiousness.c4AchievementStriving=answers[20]+answers[50]+(6-answers[80])+(6-answers[110]);
		
		conscientiousness.c5SelfDiscipline=answers[25]+answers[55]+(6-answers[85])+(6-answers[115]);
		
		conscientiousness.c6Cautiousness=(6-answers[30])+(6-answers[60])+(6-answers[90])+(6-answers[120]); 

		Double totalScoreConsc=Double.valueOf(conscientiousness.c1SelfEfficacy+conscientiousness.c2Orderliness+conscientiousness.c3Dutifulness+conscientiousness.c4AchievementStriving+
				conscientiousness.c5SelfDiscipline+conscientiousness.c6Cautiousness);

		Double conscPercent=(totalScoreConsc*100)/120.0;

		conscientiousness.totalPercentage=conscPercent;
		
		result.conscientiousness=conscientiousness;
		
		logger.info("  ***  conscientiousness  ***  ");
		logger.info(conscientiousness.toString());
		
		logger.info("Service method end \n\n");
		
		return result;
	}
	
	public List<Coupon> couponPresent()
	{
		List<Coupon> couponList=null;
		
		Connection con=contusDao.getConnection();
		
		 try(Statement stmt=con.createStatement())
         {
        	 logger.info(stmt);
				logger.info(stmt);
				
				
				java.sql.ResultSet rs=stmt.executeQuery("select * from coupon ");
				
				couponList=new ArrayList<Coupon>();
				while(rs.next())
				{
					Coupon coupon=new Coupon();
					coupon.setId((long) rs.getInt(1));
					coupon.setCouponCode(rs.getString(2));
					coupon.setNumbAllowed(rs.getInt(3));
					coupon.setRedeemedNumb(rs.getInt(5));
					coupon.setAmount(rs.getString(7));
					coupon.setDateTime(rs.getString(9));
					logger.info("Coupon code "+coupon.getCouponCode()+coupon.getId());
					couponList.add(coupon);
				}
				
				logger.info("*********");
				contusDao.closeConnection();
				logger.info("getting coupon list");
         }
         catch(SQLException sqlExc)
         {
        	 logger.error("%%%%%%%%%%%% SQL error occured in getting coupon %%%%%%%%%%%%%%");
        	 logger.error("/n");
        	 logger.error("Error : ",sqlExc);
        	
         }
		 catch (Exception ex) {
			 logger.error("%%%%%%%%%%%% Error error occured in getting coupon %%%%%%%%%%%%%%");
        	 logger.error("/n");
        	 logger.error("Error : ",ex);
		 }
		
		return couponList;
		
	}
	
	public String createCoupon(String couponCode,String amount,String numb_allowed) {
		String msg="success";
		
		Connection con=contusDao.getConnection();
		logger.info("In create coupon method");
		logger.error("/n");
		 try(Statement stmt=con.createStatement())
        {
			 
			 stmt.executeUpdate("insert into coupon set coupon_code='"+couponCode+"' , amount="+amount+" , numb_allowed="+numb_allowed+", redeemed_numb=0");
			 
			 logger.info(" %%%%%%%%%% Coupon created successfully "+couponCode +" with amount "+amount);
        }
		 catch(SQLException sqlExc)
         {
			 msg="error";
        	 logger.error("%%%%%%%%%%%% SQL error occured in creating coupon %%%%%%%%%%%%%%");
        	 logger.error("/n");
        	 logger.error("Error : ",sqlExc);
        	
         }
		 
		 return msg;
	}

		
	public String deleteCoupon(String code)
	{
		String msg="success";
		
		Connection con=contusDao.getConnection();
		logger.info("In coupon delete method");
		logger.error("/n");
		 try(Statement stmt=con.createStatement())
        {
			 
			 stmt.executeUpdate("delete from coupon where coupon_code='"+code+"'");
			 
			 logger.info(" %%%%%%%%%% Coupon deleted successfully "+code);
        }
		 catch(SQLException sqlExc)
         {
			 msg="error";
        	 logger.error("%%%%%%%%%%%% SQL error occured in deleting coupon %%%%%%%%%%%%%%");
        	 logger.error("/n");
        	 logger.error("Error : ",sqlExc);
         }
		
		 return msg;
		
	}
	
	public boolean saveCustomerData(String custName,String custEmail,String mobile,String couponCode,String couponRedeemedNumb,String amount,
												String paymentMethod,HttpServletRequest request,String language) {
		
		logger.info("******** In save Customer Data *******");
		boolean dataSaved=true;
		String orderId=custName+custEmail+couponCode;
		logger.info("!!!!!!!!!!! Manual Unique Order Id - "+orderId);
		request.getSession().setAttribute("orderId", orderId);
		request.getSession().setAttribute("payment", paymentMethod);
		request.getSession().setAttribute("couponCode", couponCode);
		request.getSession().setAttribute("couponAmount", amount);
		request.getSession().setAttribute("couponNumb", couponRedeemedNumb);
		request.getSession().setAttribute("language", language);
		
		if(couponCode!=null)
			updateCoupon(couponCode,couponRedeemedNumb);
		
		Customer cust = new Customer();
		cust.setCustName(custName);
		cust.setCouponUsed(couponCode);
		cust.setEmail(custEmail);
		cust.setMobile(mobile);
		cust.setAmountPaid(amount);
		cust.setPaymentMethod(paymentMethod);
		cust.setOrderId(orderId);
		cust.setOrderStatus("complete");
		
		String response=saveCustomer(cust);
		if(response=="error")
		{
			dataSaved=false;
		}
		
		return dataSaved;
	}
	
	public boolean saveSwishCustomerData(String custName,String custEmail,String mobile,String couponCode,String couponRedeemedNumb,String amount,
			String paymentMethod,HttpServletRequest request,String language) {

		logger.info("******** In save Customer Data *******");
		boolean dataSaved=true;
		String orderId=custName+custEmail+couponCode;
		logger.info("!!!!!!!!!!! Manual Unique Order Id - "+orderId);
		request.getSession().setAttribute("orderId", orderId);
		request.getSession().setAttribute("payment", paymentMethod);
		request.getSession().setAttribute("couponCode", couponCode);
		request.getSession().setAttribute("couponAmount", amount);
		request.getSession().setAttribute("couponNumb", couponRedeemedNumb);
		request.getSession().setAttribute("language", language);
/*		
		if(couponCode!=null)
		 updateCoupon(couponCode,couponRedeemedNumb);
*/
		Customer cust = new Customer();
		cust.setCustName(custName);
		cust.setCouponUsed(couponCode);
		cust.setEmail(custEmail);
		cust.setMobile(mobile);
		cust.setAmountPaid(amount);
		cust.setPaymentMethod(paymentMethod);
		cust.setOrderId(orderId);
		cust.setOrderStatus("incomplete");

		String response=saveCustomer(cust);
		if(response=="error")
		{
			dataSaved=false;
		}

		return dataSaved;
}
	
	
	public boolean sendContactForm(String name,String company,String email,String msg)
	{
		boolean mailSent=true;
		
		String result=emailUtility.sendContactFormThroughMail(name, company, email, msg);
		
		if(result.equals("error"))
			mailSent=false;
		
		logger.info("!!!!!!!!!!! Contact data sent "+name+" by email - "+email);
		
		return mailSent;
		
	}
}
