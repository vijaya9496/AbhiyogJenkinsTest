package com.fg.ss.abhiyog.common.model;

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
import javax.persistence.Table;

@Entity
@Table(name = "lawfirm")

public class LawFirm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lawfirm_id")
	private int lawfirmId;

	@Column(name = "lawfirm")
	private String lawfirm;

	@Column(name = "lawfirm_head")
	private String lawfirmHead;

	@Column(name = "lawfirm_head_email_id")
	private String lawfirmHeadEmailId;

	@Column(name = "address")
	private String address;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "fax_no")
	private String faxNo;

	@Column(name = "website")
	private String website;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	private Dept dept;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="lawfirm_id")
	private List<Litigation> litigation;

	public int getLawfirmId() {
		return lawfirmId;
	}

	public void setLawfirmId(int lawfirmId) {
		this.lawfirmId = lawfirmId;
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

	public String getLawfirmHeadEmailId() {
		return lawfirmHeadEmailId;
	}

	public void setLawfirmHeadEmailId(String lawfirmHeadEmailId) {
		this.lawfirmHeadEmailId = lawfirmHeadEmailId;
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

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}
	
	

}
