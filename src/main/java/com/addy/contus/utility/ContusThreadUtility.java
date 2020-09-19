package com.addy.contus.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.addy.contus.dao.ContusDao;
import com.addy.contus.entity.TestResult;


public class ContusThreadUtility extends Thread  {

	private static final Logger logger = Logger.getLogger(ContusThreadUtility.class);
	
	public TestResult result;
	public String orderId;
	public String language;
	public String senderEmail;
	
	
	public ContusThreadUtility(TestResult result,String orderId,String language) {
		this.result=result;
		this.orderId=orderId;
		this.language=language;
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
			if(pdfReport.createPdf(orderId, language,result)) {
				logger.info("### PDF created with name resultReport"+orderId+".pdf");
				EmailSenderUtility emailUtility=new EmailSenderUtility(); 
				
				emailUtility.sendReportToCustomerMail(orderId, language);
				
			}
			else
				logger.info("### Error in creating PDF with name resultReport"+orderId+".pdf");
			
			
		}
		else
			logger.info("### Error in creatin image with name BarChart"+orderId+".jpeg");
		
		logger.info("########### Thread Ended ###########");
	}

}
