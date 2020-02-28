package com.fg.ss.abhiyog.common.vo;

public class OutsideCounselVO {

	private String function;
	private String lawfirm;
	private String lawfirmHead; // partner
	private String emailId;
	private String address;
	private String mobile;
	private String telephone;
	private String faxNo;
	private String website;
	private int lawfirmId; // id

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

	public String getLawfirmHead() {
		return lawfirmHead;
	}

	public void setLawfirmHead(String lawfirmHead) {
		this.lawfirmHead = lawfirmHead;
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

	public int getLawfirmId() {
		return lawfirmId;
	}

	public void setLawfirmId(int lawfirmId) {
		this.lawfirmId = lawfirmId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutsideCounselVO [function=");
		builder.append(function);
		builder.append(", lawfirm=");
		builder.append(lawfirm);
		builder.append(", lawfirmHead=");
		builder.append(lawfirmHead);
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
