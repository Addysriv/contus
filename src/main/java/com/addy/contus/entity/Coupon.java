package com.addy.contus.entity;

public class Coupon {
	
	private Long id;
	private String couponCode;
	private Integer numbAllowed;
	private String validTill;
	private Integer redeemedNumb;
	private String confirmUse;
	private String amount;
	private Double percentage;
	private String dateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Integer getNumbAllowed() {
		return numbAllowed;
	}
	public void setNumbAllowed(Integer numbAllowed) {
		this.numbAllowed = numbAllowed;
	}
	public String getValidTill() {
		return validTill;
	}
	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}
	public Integer getRedeemedNumb() {
		return redeemedNumb;
	}
	public void setRedeemedNumb(Integer redeemedNumb) {
		this.redeemedNumb = redeemedNumb;
	}
	public String getConfirmUse() {
		return confirmUse;
	}
	public void setConfirmUse(String confirmUse) {
		this.confirmUse = confirmUse;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
