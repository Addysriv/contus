package com.addy.contus.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.addy.contus.entity.Coupon;
import com.addy.contus.entity.Customer;


@Component
public class ContusDao {

	private static final Logger logger = Logger.getLogger(ContusDao.class);


	//public String JdbcURL="jdbc:mysql://localhost:3306/contus?autoReconnect=true&useSSL=false";

	@Value("${jUrl}")
	public String JdbcURL;

	@Value("${dBusername}")
	public String Username;

	@Value("${dBpassword}")
	public String password;

	public Connection con = null;

	
	public Connection getConnection()
	{

		try {
			logger.info("%%%%%%%%%%  JdbcURL - " + JdbcURL +" user name "+Username);
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			logger.info("%%%%%%% connection created successfully %%%%%%%% "+con);
		}
		catch(Exception ex)
		{
			logger.error("Error : ",ex);
		}
		return con;
	}

	public Boolean closeConnection()
	{
		try {
			con.close();
			return true;
		} catch (SQLException e) {

			logger.error("Error : ",e);

			return false;
		}



	}

	public String addData() {

		//Session session=sessionFactory.getCurrentSession();
		logger.info("---------%%%%%%%%%%");
		logger.info("Done inside ****************************");

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			logger.info("Your JDBC URL is as follows:" + JdbcURL);
			Statement stmt=con.createStatement();  

			logger.info(stmt);
			logger.info("*********");
			ResultSet rs=stmt.executeQuery("select * from emp");  
			while(rs.next())  
				logger.info(rs.getInt(1)+"  "+rs.getString(2));
		} catch (Exception exec) {
			logger.error("Error : ",exec);
		}
		return "success";
	}

	public String saveCustomer(Customer cust)
	{
		String msg="success";

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			logger.info("Your JDBC URL is as follows:" + JdbcURL);
			try(Statement stmt=con.createStatement())
			{
				logger.info(stmt);
				logger.info(stmt);
				if(cust.getMobile()==null)
					cust.setMobile(" ");
				logger.info("*********");
				String query="insert into customers set cust_name='"+cust.getCustName()+"',email='"+cust.getEmail()+"',"
						+"mobile='"+cust.getMobile()+"',"
						+ "order_id='"+cust.getOrderId()+"',order_status='"+cust.getOrderStatus()+"',"
						+ "amount_paid='"+cust.getAmountPaid()+"',payment_method='"+cust.getPaymentMethod()+"',"
						+ "coupon_used='"+cust.getCouponUsed()+"'";
				logger.info("Query - "+query);
				stmt.executeUpdate(query);  

				con.close();
				logger.info("successfull customer saved");
			}
			catch(SQLException sqlExc)
			{
				logger.error("%%%%%%%%%%%% SQL error occured  %%%%%%%%%%%%%%");
				logger.error("/n");
				logger.error("Error : ",sqlExc);
				if(con!=null)
					con.close();
			}



		} catch (Exception exec) {
			logger.error("%%%%%%%%%%%% Exception occured in sasving cutomer  %%%%%%%%%%%%%%");
			logger.error("/n");
			logger.error("Error : ",exec);
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					logger.error("%%%%%%%%%%%% Exception occured in closing connection  %%%%%%%%%%%%%%");
					logger.error("/n");
					logger.error("Error : ",e);
				}
		}



		return msg;

	}

	public Coupon checkValidCoupon(String couponCode)
	{
		Coupon coupon=new Coupon();
		boolean valid=false;
		logger.info("Checking for valid coupon code "+couponCode);
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			logger.info("Your JDBC URL is as follows:" + JdbcURL);
			try(Statement stmt=con.createStatement())
			{
				logger.info(stmt);
				logger.info(stmt);

				ResultSet rs=stmt.executeQuery("select * from coupon where coupon_code='"+couponCode+"'");

				while(rs.next())
				{
					coupon.setId((long) rs.getInt(1));
					coupon.setCouponCode(rs.getString(2));
					coupon.setNumbAllowed(rs.getInt(3));
					coupon.setRedeemedNumb(rs.getInt(5));
					coupon.setAmount(rs.getString(7));
					coupon.setDateTime(rs.getString(9));
					valid=true;
				}

				logger.info("*********");
				con.close();
				logger.info("successfull coupon validated");
			}
			catch(SQLException sqlExc)
			{
				logger.error("%%%%%%%%%%%% SQL error occured  %%%%%%%%%%%%%%");
				logger.error("/n");
				logger.error("Error : ",sqlExc);
				if(con!=null)
					con.close();
			}



		} catch (Exception exec) {
			logger.error("%%%%%%%%%%%% Exception occured in coupon check  %%%%%%%%%%%%%%");
			logger.error("/n");
			logger.error("Error : ",exec);
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					logger.error("%%%%%%%%%%%% Exception occured in closing connection  %%%%%%%%%%%%%%");
					logger.error("/n");
					logger.error("Error : ",e);

				}
		}

		if(!valid)
			return null;

		return coupon;

	}
	
	public String getEmail(String orderId)
	{
		String emailId="";
		Connection conn=getConnection();
		logger.info("$$$$$$$$$$$$$$$$$$$$$");
		try(Statement stmt=conn.createStatement())
		{
			logger.info("##########################");
			ResultSet rs=stmt.executeQuery("select email from customers where order_id='"+orderId+"'");
			if(rs!=null)
			{
				while(rs.next()) {
				emailId=rs.getString(1);
				logger.info("***"+emailId);
				}
				
			}
			else {
				closeConnection();
				return null;
					
			}
			logger.info("*********");

			logger.info("successfull mobile number retrieved");
			closeConnection();
		}
		catch(SQLException sqlExc)
		{
			logger.error("%%%%%%%%%%%% SQL error occured  %%%%%%%%%%%%%%");
			logger.error("/n");
			logger.error("Error : ",sqlExc);
			closeConnection();
			return null;
		}
		catch(Exception ex)
		{
			logger.error("%%%%%%%%%%%% SQL error occured  %%%%%%%%%%%%%%");
			logger.error("/n");
			logger.error("Error : ",ex);
			closeConnection();
			return null;
		}
		
		return emailId;
		
	}



}
