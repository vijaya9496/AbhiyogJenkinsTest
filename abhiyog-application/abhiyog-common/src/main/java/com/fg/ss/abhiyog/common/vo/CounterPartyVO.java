package com.fg.ss.abhiyog.common.vo;

public class CounterPartyVO {

	private String function;
	private String counterPartyName;
	private String contactPerson1;
	private String contactPerson2;
	private String designation1;
	private String designation2;
	private String mobile1;
	private String mobile2;
	private String emailId1;
	private String emailId2;
	private String telephone1;
	private String telephone2;
	private String faxNo1;
	private String faxNo2;
	private String address;
	private String website;
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getCounterPartyName() {
		return counterPartyName;
	}
	public void setCounterPartyName(String counterPartyName) {
		this.counterPartyName = counterPartyName;
	}
	public String getContactPerson1() {
		return contactPerson1;
	}
	public void setContactPerson1(String contactPerson1) {
		this.contactPerson1 = contactPerson1;
	}
	public String getContactPerson2() {
		return contactPerson2;
	}
	public void setContactPerson2(String contactPerson2) {
		this.contactPerson2 = contactPerson2;
	}
	public String getDesignation1() {
		return designation1;
	}
	public void setDesignation1(String designation1) {
		this.designation1 = designation1;
	}
	public String getDesignation2() {
		return designation2;
	}
	public void setDesignation2(String designation2) {
		this.designation2 = designation2;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getEmailId1() {
		return emailId1;
	}
	public void setEmailId1(String emailId1) {
		this.emailId1 = emailId1;
	}
	public String getEmailId2() {
		return emailId2;
	}
	public void setEmailId2(String emailId2) {
		this.emailId2 = emailId2;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getFaxNo1() {
		return faxNo1;
	}
	public void setFaxNo1(String faxNo1) {
		this.faxNo1 = faxNo1;
	}
	public String getFaxNo2() {
		return faxNo2;
	}
	public void setFaxNo2(String faxNo2) {
		this.faxNo2 = faxNo2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CounterPartyVO [function=");
		builder.append(function);
		builder.append(", counterPartyName=");
		builder.append(counterPartyName);
		builder.append(", contactPerson1=");
		builder.append(contactPerson1);
		builder.append(", contactPerson2=");
		builder.append(contactPerson2);
		builder.append(", designation1=");
		builder.append(designation1);
		builder.append(", designation2=");
		builder.append(designation2);
		builder.append(", mobile1=");
		builder.append(mobile1);
		builder.append(", mobile2=");
		builder.append(mobile2);
		builder.append(", emailId1=");
		builder.append(emailId1);
		builder.append(", emailId2=");
		builder.append(emailId2);
		builder.append(", telephone1=");
		builder.append(telephone1);
		builder.append(", telephone2=");
		builder.append(telephone2);
		builder.append(", faxNo1=");
		builder.append(faxNo1);
		builder.append(", faxNo2=");
		builder.append(faxNo2);
		builder.append(", address=");
		builder.append(address);
		builder.append(", website=");
		builder.append(website);
		builder.append("]");
		return builder.toString();
	}
	
	
}
