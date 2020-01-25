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
@Table(name = "billing_type")
public class BillingType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "billing_type_id")
	private int billingTypeId;

	@Column(name = "billing_type")
	private String billingType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_type_id")
	private List<LawfirmBilling> lawfirmBilling;

	public int getBillingTypeId() {
		return billingTypeId;
	}

	public void setBillingTypeId(int billingTypeId) {
		this.billingTypeId = billingTypeId;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public List<LawfirmBilling> getLawfirmBilling() {
		return lawfirmBilling;
	}

	public void setLawfirmBilling(List<LawfirmBilling> lawfirmBilling) {
		this.lawfirmBilling = lawfirmBilling;
	}

}
