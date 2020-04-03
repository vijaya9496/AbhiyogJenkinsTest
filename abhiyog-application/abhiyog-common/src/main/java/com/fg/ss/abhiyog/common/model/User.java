package com.fg.ss.abhiyog.common.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "ltgn_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "login_password")
	private String password;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "status")
	private String status;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	@ColumnDefault("male")
	private String gender;

	@Email(message = "*Please provide a valid Email")
	@Column(name = "email_id")
	private String emailId;

	@Email(message = "*Please provide a valid Email")
	@Column(name = "personal_email_id")
	private String personalEmailId;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "city")
	private String city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role roles;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "unitheadoid")
	private List<UnitHeads> unitHeads;

	@OneToMany(mappedBy = "user")
//	@JoinColumn(name="user_id")
	private List<ShowCauseNotice> showCauseNotice;

	@OneToMany(mappedBy = "userDtls")
	private List<ShowCauseNoticeForms> showCauseNoticeForms;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Litigation litigation;

	@OneToMany(mappedBy = "user")
//	@JoinColumn(name = "user_id")
	private List<LitigationDocs> litigationDocs;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private LtgnLitigationLog ltgnLitigationLog;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private LitigationHistoryTLog litigationHistoryTLog;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public List<UnitHeads> getUnitHeads() {
		return unitHeads;
	}

	public void setUnitHeads(List<UnitHeads> unitHeads) {
		this.unitHeads = unitHeads;
	}

	public List<ShowCauseNotice> getShowCauseNotice() {
		return showCauseNotice;
	}

	public void setShowCauseNotice(List<ShowCauseNotice> showCauseNotice) {
		this.showCauseNotice = showCauseNotice;
	}

	public List<ShowCauseNoticeForms> getShowCauseNoticeForms() {
		return showCauseNoticeForms;
	}

	public void setShowCauseNoticeForms(List<ShowCauseNoticeForms> showCauseNoticeForms) {
		this.showCauseNoticeForms = showCauseNoticeForms;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

	public List<LitigationDocs> getLitigationDocs() {
		return litigationDocs;
	}

	public void setLitigationDocs(List<LitigationDocs> litigationDocs) {
		this.litigationDocs = litigationDocs;
	}

	public LtgnLitigationLog getLtgnLitigationLog() {
		return ltgnLitigationLog;
	}

	public void setLtgnLitigationLog(LtgnLitigationLog ltgnLitigationLog) {
		this.ltgnLitigationLog = ltgnLitigationLog;
	}

	public LitigationHistoryTLog getLitigationHistoryTLog() {
		return litigationHistoryTLog;
	}

	public void setLitigationHistoryTLog(LitigationHistoryTLog litigationHistoryTLog) {
		this.litigationHistoryTLog = litigationHistoryTLog;
	}

}
