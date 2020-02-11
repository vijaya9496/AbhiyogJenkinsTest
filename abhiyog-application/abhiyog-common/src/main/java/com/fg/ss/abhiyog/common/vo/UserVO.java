package com.fg.ss.abhiyog.common.vo;

public class UserVO {

	private int id; //userID(primarykey)
	private String firstName;
	private String middleName;
	private String lastName;
	private String loginId;
	private String role;
	private String password;
	private String address;
	private String city;
	private String phone;
	private String mobile;
	private String emailId;
	private String personalEmailId;
	private String gender;
	private String status;
	private String roleDesc;
	private String fullName;
	private int roleId;
	
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	
	private String zoneName;
	private String format;
	private String entityName;
	private String deptName;
	private String counterPartyName;
	private String categoryName;
	private String claim;
	private String stateName;
	private String lawfirm;
	private String courtTypeName;
	private String underActName;
	private String risk;
	private String matterBy;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPersonalEmailId() {
		return personalEmailId;
	}
	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCounterPartyName() {
		return counterPartyName;
	}
	public void setCounterPartyName(String counterPartyName) {
		this.counterPartyName = counterPartyName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getLawfirm() {
		return lawfirm;
	}
	public void setLawfirm(String lawfirm) {
		this.lawfirm = lawfirm;
	}
	public String getCourtTypeName() {
		return courtTypeName;
	}
	public void setCourtTypeName(String courtTypeName) {
		this.courtTypeName = courtTypeName;
	}
	public String getUnderActName() {
		return underActName;
	}
	public void setUnderActName(String underActName) {
		this.underActName = underActName;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	
	public String getMatterBy() {
		return matterBy;
	}
	public void setMatterBy(String matterBy) {
		this.matterBy = matterBy;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVO [firstName=");
		builder.append(firstName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", loginId=");
		builder.append(loginId);
		builder.append(", role=");
		builder.append(role);
		builder.append(", password=");
		builder.append(password);
		builder.append(", address=");
		builder.append(address);
		builder.append(", city=");
		builder.append(city);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", personalEmailId=");
		builder.append(personalEmailId);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", status=");
		builder.append(status);
		builder.append(", roleDesc=");
		builder.append(roleDesc);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", oldPassword=");
		builder.append(oldPassword);
		builder.append(", newPassword=");
		builder.append(newPassword);
		builder.append(", confirmNewPassword=");
		builder.append(confirmNewPassword);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	

}
