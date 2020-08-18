package com.addy.contus.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class ContusDao {
	
	
	
	public String addData() {
		
		//Session session=sessionFactory.getCurrentSession();
		System.out.println("---------%%%%%%%%%%");
		System.out.println("Done inside ****************************");
		 String JdbcURL = "jdbc:mysql://localhost:3306/contus?" + "autoReconnect=true&useSSL=false";
	      String Username = "root";
	      String password = "root123";
	      Connection con = null;
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");
	         con = DriverManager.getConnection(JdbcURL, Username, password);
	         System.out.println("Your JDBC URL is as follows:" + JdbcURL);
	         Statement stmt=con.createStatement();  
				
				System.out.println(stmt);
				System.out.println("*********");
				ResultSet rs=stmt.executeQuery("select * from emp");  
				while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2));
	      } catch (Exception exec) {
	         exec.printStackTrace();
	      }
		return "success";
	}

}
