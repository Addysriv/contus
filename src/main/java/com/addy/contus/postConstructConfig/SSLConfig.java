package com.addy.contus.postConstructConfig;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SSLConfig {
  

  @Value("${testKeyStore}")
  private String keystoreFileLocation;

  @Value("${testKeyStorePassword}")
  private String keyStorePassword;

  @Value("${keyStoreType}")
  private String keystoreType;

  @Value("${trustStore}")
  private String trustStoreFileLocation;

  @Value("${trustStorePassword}")
  private String trustStorePassword;


  @Value("${testSwishPaymentRequesturl}")
  private String testSwishPaymentRequesturl;

  @Value("${productionKeyStore}")
  private String productionKeyStore;

  @Value("${productionKeyStorePassword}")
  private String productionKeyStorePassword;

  @Value("${prodSwishPaymentRequestUrl}")
  private String prodSwishPaymentRequestUrl;
  
  @Value("${swishClientCountryCode}")
  private String swishClientCountryCode;
  
  @Value("${swishTrustStoreFileLocation}")
  private String swishTrustStoreFileLocation;
    
    
   private static final Logger logger = Logger.getLogger(SSLConfig.class);
    
    @PostConstruct
    private void configureSSL() {
    	logger.info("$$$$$$$$$$$$$$$  Inside configuration of SSL  $$$$$$$$$$$$$");
      System.setProperty("javax.net.ssl.keyStore", productionKeyStore);
      System.setProperty("javax.net.ssl.keyStorePassword", productionKeyStorePassword);
      System.setProperty("javax.net.ssl.keyStoreType", keystoreType);
      System.setProperty("javax.net.ssl.trustStore", swishTrustStoreFileLocation);
      System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
      

      System.setProperty("contus.system.swishpaymenturl", prodSwishPaymentRequestUrl);
    }
}