package com.addy.contus.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.addy.contus.entity.TestResult;

public class ContusThreadUtility extends Thread  {

	private static final Logger logger = Logger.getLogger(ContusThreadUtility.class);
	
	
	public TestResult result;
	public String orderId;
	public String language;
	public String userEmail;
	public String userName;
	
	public ContusThreadUtility(TestResult result,String orderId,String language,String userEmail,String userName) {
		this.result=result;
		this.orderId=orderId;
		this.language=language;
		this.userEmail=userEmail;
		this.userName=userName;
		
	}
	
	@Override
	public void run() {
		
		logger.info("########### Thread started ###########");
		logger.info(result.toString());
		
		CreateGraphAsImage createGraphAsImage=new CreateGraphAsImage();
		
		boolean isImageCreated=createGraphAsImage.createGraphImage(orderId,result,language);
		
		if(isImageCreated) {
			logger.info("### Image created with name Order Id - "+orderId+" .jpeg");
			
			CreatePdfReport pdfReport=new CreatePdfReport();
			if(pdfReport.createPdf(orderId, language,result,userName,userEmail)) {
				logger.info("### PDF created with name resultReport"+orderId+".pdf");
				EmailSenderUtility emailUtility=new EmailSenderUtility(); 
				String email="";
				String pass="";
				FileReader reader;
				try {
					reader = new FileReader("src/main/webapp/WEB-INF/property/configData.properties");
					  Properties p=new Properties();  
					    p.load(reader);
					    email=p.getProperty("contusDomainEmail");
					    pass=p.getProperty("contusDomainEmailPass");
					    
				} catch (FileNotFoundException e) {
					logger.log(Level.ERROR, "Message", e);
					e.printStackTrace();
				} catch (IOException e) {
					logger.log(Level.ERROR, "Message", e);
					e.printStackTrace();
				}  
			      
			  
				emailUtility.sendReportToCustomerMail(orderId, language,userEmail,userName,email,pass);
				
			}
			else
				logger.info("### Error in creating PDF with name resultReport"+orderId+".pdf");
			
			
		}
		else
			logger.info("### Error in creatin image with name BarChart"+orderId+".jpeg");
		
		logger.info("########### Thread Ended ###########");
	}

}
