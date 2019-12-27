package com.fg.ss.abhiyog.common.vo;

public class OutsideCounselVO {

	private String function;
	private String lawfirm;
	private String partner;
	private String emailId;
	private String address;
	private String mobile;
	private String telephone;
	private String faxNo;
	private String website;

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getLawfirm() {
		return lawfirm;
	}

	public void setLawfirm(String lawfirm) {
		this.lawfirm = lawfirm;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
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
		builder.append("OutsideCounselVO [function=");
		builder.append(function);
		builder.append(", lawfirm=");
		builder.append(lawfirm);
		builder.append(", partner=");
		builder.append(partner);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", address=");
		builder.append(address);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", faxNo=");
		builder.append(faxNo);
		builder.append(", website=");
		builder.append(website);
		builder.append("]");
		return builder.toString();
	}

}
