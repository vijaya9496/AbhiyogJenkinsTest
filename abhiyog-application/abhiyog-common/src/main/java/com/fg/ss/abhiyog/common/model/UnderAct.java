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
@Table(name = "underact")
public class UnderAct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "underactoid")
	private int underActId;

	@Column(name = "underact")
	private String underAct;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="underactoid")
	private List<Litigation> litigation;

	public int getUnderActId() {
		return underActId;
	}

	public void setUnderActId(int underActId) {
		this.underActId = underActId;
	}

	public String getUnderAct() {
		return underAct;
	}

	public void setUnderAct(String underAct) {
		this.underAct = underAct;
	}

}
