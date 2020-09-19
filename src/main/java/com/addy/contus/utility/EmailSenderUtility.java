package com.addy.contus.utility;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.addy.contus.dao.ContusDao;

@Component
public class EmailSenderUtility {
	
	public static final Logger logger=Logger.getLogger(EmailSenderUtility.class);
	
	@Value("${contactSenderEmail}")
	public String contactSenderEmailId;
	
	@Value("${contactEmail}")
	public String contactEmailId;
	
	@Value("${contusDomainEmail}")
	public String contusDomainEmailId;
	
	@Value("${emailSenderPass}")
	public String emailSenderPassword;
	
	@Value("${contusDomainEmailPass}")
	public String contusDomainEmailPassword;
	
	public static final String HOST="Mailcluster.loopia.se";
	public static final String SOCKET_PORT="465";
	public static final String SOCKET_FACTORY="javax.net.ssl.SSLSocketFactory";
	public static final String AUTH="true";
	public static final String SMTP_PORT="587";
	
	@Autowired
	ContusDao contusDao;
	
	
	public static void main(String[] args) {
		EmailSenderUtility emailSenderUtility=new EmailSenderUtility();
		
		emailSenderUtility.sendReportToCustomerMail("Aditya Srivastavaddysriv25@gmail.comDaniel100","english");
		
	}
	
	public boolean sendReportToCustomerMail(String orderId,String language)
	{
		boolean isEmailSend=true;
		
		 String toEmail=contusDao.getEmail(orderId);
		 if(toEmail==null || toEmail.equals(""))
			 return false;
		 
		  String to = toEmail;

	      String from = contusDomainEmailId;

	      String password = "X2020kalle";

	      Properties props = new Properties();
	        props.put("mail.smtp.host", HOST);
	        props.put("mail.smtp.socketFactory.port", SOCKET_PORT);
	        props.put("mail.smtp.socketFactory.class",SOCKET_FACTORY);
	        props.put("mail.smtp.auth", AUTH);
	        props.put("mail.smtp.port", SMTP_PORT);

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(from, password);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("This is message body");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "F:/temp.pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName("Test Report.pdf");
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	    	  System.out.println("Error occured");
	    	  logger.error("!!!!!!!!!!! Error occured in sending the email - MessagingException !!!!!");
	         logger.error("Error : ",e);
	      }
	      catch(Exception e) {
	    	  e.printStackTrace();
	    	  logger.error("!!!!!!!!!!! Error occured in sending the email - Exception !!!!!");
		         logger.error("Error : ",e);
	      }
		
		return isEmailSend;
	}
	
	public String sendContactFormThroughMail(String name,String company,String email,String msg) {
		
		String isMailSent="success";
		
		System.out.println(contactSenderEmailId);
		System.out.println(contactEmailId);
		System.out.println(contusDomainEmailId);
		
		 String to = contusDomainEmailId;

	      String from = contactSenderEmailId;

	      String password = emailSenderPassword;

	      Properties props = new Properties();
	        props.put("mail.smtp.host", HOST);
	        props.put("mail.smtp.socketFactory.port", SOCKET_PORT);
	        props.put("mail.smtp.socketFactory.class",SOCKET_FACTORY);
	        props.put("mail.smtp.auth", AUTH);
	        props.put("mail.smtp.port", SMTP_PORT);

	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(from, password);
	            }
	         });

	      try {
	    	  
	    	  MimeMessage message = new MimeMessage(session);

	    	  message.setFrom(new InternetAddress(from));

	    	  message.setRecipients(Message.RecipientType.TO,
	    			  InternetAddress.parse(to));

	    	  message.setSubject("Customer Support by "+name);

	    	  message.setText(" Hii "+name+" from company "+company+" has contacted . \n \n Message - "+msg);

	    	  Transport.send(message);

	    	  System.out.println("Sent message successfully....");
	    	  logger.info("Sent message successfully....");

	      } catch (MessagingException e) {
	    	  System.out.println("Error occured");
	    	  logger.error("!!!!!!!!!!! Error occured in sending the contact email - MessagingException !!!!!");
	         logger.error("Error : ",e);
	         
	      }
	      catch(Exception e) {
	    	  e.printStackTrace();
	    	  logger.error("!!!!!!!!!!! Error occured in sending the contact email - Exception !!!!!");
		      logger.error("Error : ",e);
		      
	      }
		
		return isMailSent;
	}

}
