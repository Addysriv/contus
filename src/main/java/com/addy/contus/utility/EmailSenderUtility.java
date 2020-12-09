package com.addy.contus.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EmailSenderUtility {
	
	public static final Logger logger=Logger.getLogger(EmailSenderUtility.class);
	
	@Value("${contactSenderEmail}")
	public String contactSenderEmailId;
	
	@Value("${contusSupportEmail}")
	public String contusSupportEmailId;
	
	@Value("${contusDomainEmail}")
	public String contusDomainEmailId;
	
	@Value("${emailSenderPass}")
	public String emailSenderPassword;
	
	@Value("${contusDomainEmailPass}")
	public String contusDomainEmailPassword;
	
	@Value("${developerEmail}")
	public String developerEmailId;
	
	public static final String HOST="mail.contus.se";
	public static final String SOCKET_PORT="143";
	public static final String SOCKET_FACTORY="javax.net.ssl.SSLSocketFactory";
	public static final String AUTH="true";
	public static final String SMTP_PORT="587";
	
	
	public static void main(String[] args) {
		EmailSenderUtility emailSenderUtility=new EmailSenderUtility();
		
		FileReader reader;
		try {
			reader = new FileReader("src/main/webapp/WEB-INF/property/configData.properties");
			  Properties p=new Properties();  
			    p.load(reader);
			    System.out.println(p.getProperty("contusDomainEmail"));
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
		
		System.out.println("****"+emailSenderUtility.contusDomainEmailId+" "+emailSenderUtility.contusDomainEmailPassword+emailSenderUtility.contactSenderEmailId);
		
		emailSenderUtility.sendReportToCustomerMail("addyPdf","sv","addysriv25@gmail.com","Addy","Contus_report@contus.se","R71tlCe46Vc3Bi9s");
		
	}
	
	public boolean sendReportToCustomerMail(String orderId,String language,String email,String name,String contusEmail,String contusPass)
	{
		logger.info("$$$$$$$$$$$$$ inside email send method  $$$$$$$$$$$$$");
		boolean isEmailSend=true;
		logger.info("Inside Email send method with id "+orderId+" language - "+language);
		 //String emailName=contusDao.getEmail(orderId);
		 logger.info("To email of customer "+email+" Name - "+name);
		 String toEmail=email;
		 String userName=name;
		 logger.info("TO email "+toEmail);
		 
		 if(toEmail==null || toEmail.equals(""))
			 return false;
		 
		  String to = toEmail;

	      String from = contusEmail;

	      String password = contusPass;

	      Properties props = new Properties();
	        props.put("mail.smtp.host", "mailcluster.loopia.se");
	        props.put("mail.smtp.socketFactory.port", "143");
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
	         
	         if(language.equals("english") || language.contains("en")) {
	        	 message.setSubject("Your Contus personality test report");
	         }
	         else
	         {
	        	 message.setSubject("Din Contus-personlighetstestrapport");
	         }
	        

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         String emailBody="Dear "+userName+",\n\nPlease find attached, the results of your Contus test. The test is based on the Big Five theory. You can" + 
	         		" read more about this theory on our site - www.contus.se. \n \n" + 
	         		"Keep in mind that there are no right or wrong answers. The test doesn't provide answers of who you" + 
	         		" are or what you can do in your life. Our personality is complex and a test gives you an indication of" + 
	         		" how you act/react in different situations both at work and in your social life. It also gives you a better" + 
	         		" insight and can help you develop yourself.\n \n" + 
	         		"Besides for your own development, you can use this test by sending it together with a job application " + 
	         		" and can furthermore be used as a basis for discussion in an interview. Interviews are used to decide" + 
	         		" which candidate is best suitable for the role by getting to know you personally. Most of us are less" + 
	         		" open to show who we really are and that might be a question mark for an employer. By using this" + 
	         		" report in a job application gives you an advantage by showing that you are not afraid to show" + 
	         		" yourself and who you are \" behind your CV \". \n \n Best Regards , \n Contus Team.";
	         
	         
	         String emailBodySwedish="Kära "+userName+",\n\nSe bifogade resultat från ditt Contus-test. Testet baseras på Big Five-teorin. " + 
		         		"Du kan läsa mer om denna teori på vår webbplats ä www.contus.se. \n \n" + 
		         		"Tänk på att det inte finns några rätta eller felaktiga svar. " + 
		         		"Testet ger inte svar på vem du är eller vad du kan göra i ditt liv. " + 
		         		"Vår personlighet är komplex och ett test ger dig en indikation på hur du agerar / reagerar i olika situationer både på jobbet och i ditt sociala liv. " + 
		         		"Det ger dig också en bättre insikt och kan hjälpa dig att utveckla dig själv.\n \n" + 
		         		"Förutom din egen utveckling kan du använda detta test genom att skicka det tillsammans med en arbetsansökan" + 
		         		" och kan dessutom användas som underlag för diskussion i en intervju. " + 
		         		"Intervjuer används för att avgöra vilken kandidat som passar bäst för rollen genom att lära känna dig personligen. " + 
		         		"De flesta av oss är mindre öppna för att visa vem vi verkligen är och det kan vara ett frågetecken för en arbetsgivare. " + 
		         		"Genom att använda denna rapport tillsammans med en ansökan får du en fördel i en rekryteringsprocess då arbetsgivaren ser att"
		         		+ " du som kandidat har en bättre insikt genom att visa vem som är \" bakom \" CV:t." + 
		         		" \n \n Vänliga hälsningar , \n Contus Team";
	         
	         if(language.equals("english") || language.contains("en")) {
	        	 messageBodyPart.setText(emailBody);
	         }
	         else
	         {
	        	 messageBodyPart.setText(emailBodySwedish);
	         }
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "src/main/resources/pdfReport/resultReport"+orderId+".pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName("Contus Report.pdf");
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);
	         
	         // Send message
	         Transport.send(message);
	         
	         
	         
	         
	         logger.info("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	    	  logger.info("Error occured");
	    	  logger.error("!!!!!!!!!!! Error occured in sending the email - MessagingException !!!!!");
	         logger.error("Error",e);
	      }
	      catch(NullPointerException e) {
	    	  e.printStackTrace();
	    	  logger.error("!!!!!!!!!!! Error occured in sending the email - Exception !!!!!");
	    	  logger.error("Error",e);
	      }
	      catch(Exception e) {
	    	  e.printStackTrace();
	    	  logger.error("!!!!!!!!!!! Error occured in sending the email - Exception !!!!!");
	    	  logger.error("Error",e);
	      }
		
		return isEmailSend;
	}
	
	public String sendContactFormThroughMail(String name,String company,String email,String msg) {
		
		String isMailSent="success";
		
		logger.info("In send contact method Email ");
		 String to = contusSupportEmailId;
		 logger.info("Customer email  "+to +"With message "+msg);
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
	    	  if(developerEmailId!=null)
	    	  {
	    		  logger.info("developerEmailId -"+developerEmailId);
	    		  message.setRecipients(Message.RecipientType.BCC,
		    			  InternetAddress.parse(developerEmailId));
	    	  }
	    	  
	    	  message.setSubject("Customer Support by "+name);

	    	  message.setText(" Hii Daniel , \n \n  \t "+name+" from company "+company+" has contacted with email "+email+". \n \n  \t Message - "+msg+" \n \n \n Regards, \n Contus Team. ");

	    	  Transport.send(message);

	    	  logger.info("Sent message successfully....");

	      } catch (MessagingException e) {
	    	  logger.info("Error occured");
	    	  logger.error("!!!!!!!!!!! Error occured in sending the contact email - MessagingException !!!!!");
	         logger.error("Error : ",e);
	         isMailSent="error";
	         
	      }
	      catch(Exception e) {
	    	  
	    	  logger.error("!!!!!!!!!!! Error occured in sending the contact email - Exception !!!!!");
		      logger.error("Error : ",e);
		      isMailSent="error";
	      }
		
		return isMailSent;
	}
	
	
	public boolean deleteImages(String orderId)
	{
		boolean deleteSuccessfull=true;
		logger.info(" \n !!!!!!!!!!!!!! Inside image delee message for order id - "+orderId+"  !!!!!!!!!");
		
		File img1 = new File("src/main/resources/chartImages/BarChart"+orderId+".jpeg"); 
		  
		if(img1.delete()) 
		{ 
			logger.info("Image "+"BarChart"+orderId+".jpeg"+"deleted successfully"); 
		} 
		else
		{ 
			logger.error("Failed to delete the Image - BarChart"+orderId); 
		}
		
		File img2 = new File("src/main/resources/chartImages/extraGraph"+orderId+".jpeg"); 
		  
		if(img2.delete()) 
		{ 
			logger.info("Image"+"extraGraph"+orderId+".jpeg"+"deleted successfully"); 
		} 
		else
		{ 
			logger.error("Failed to delete the Image - extraGraph"+orderId); 
		}
		
		File img3 = new File("src/main/resources/chartImages/openGraph"+orderId+".jpeg"); 
		  
		if(img3.delete()) 
		{ 
			logger.info("Image "+"openGraph"+orderId+".jpeg"+"deleted successfully"); 
		} 
		else
		{ 
			logger.error("Failed to delete the Image - openGraph"+orderId); 
		}
		
		
		File img4 = new File("src/main/resources/chartImages/agreeGraph"+orderId+".jpeg"); 
		  
		if(img4.delete()) 
		{ 
			logger.info("Image "+"agreeGraph"+orderId+".jpeg"+"deleted successfully"); 
		} 
		else
		{ 
			logger.error("Failed to delete the Image - agreeGraph"+orderId); 
		}
		
		File img5 = new File("src/main/resources/chartImages/conscGraph"+orderId+".jpeg"); 
		  
		if(img5.delete()) 
		{ 
			logger.info("Image "+"conscGraph"+orderId+".jpeg"+"deleted successfully"); 
		} 
		else
		{ 
			logger.error("Failed to delete the Image - conscGraph"+orderId); 
		}
		
		logger.info(" \n %%%%%%%%%%%   Image delete method successfull %%%%%%%%%%%%");
		
		return deleteSuccessfull;
	}

}
