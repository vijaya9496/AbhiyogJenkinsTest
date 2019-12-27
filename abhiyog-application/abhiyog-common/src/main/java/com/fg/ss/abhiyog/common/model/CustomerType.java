package com.fg.ss.abhiyog.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customertype")
public class CustomerType {

	@Id
	@Column(name = "customer_type_id")
	private int customerTypeId;

	@Column(name = "customer_type")
	private String customerType;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_type_id")
	private List<Litigation> litigation;

	public int getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(int customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}

	
}
