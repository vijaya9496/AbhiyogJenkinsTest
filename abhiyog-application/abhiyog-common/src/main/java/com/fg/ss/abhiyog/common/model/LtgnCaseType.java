package com.fg.ss.abhiyog.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ltgn_case_type")
public class LtgnCaseType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "case_type_oid")
	private int caseTypeId;

	@Column(name = "case_type")
	private String caseType;

	@Column(name = "status")
	private String status;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="case_type_oid")
	private List<Litigation> litigation;

	public int getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(int caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
