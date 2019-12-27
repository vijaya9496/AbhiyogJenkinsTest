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
@Table(name = "courttype")
public class CourtType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "court_type_id")
	private int courtTypeId;

	@Column(name = "court_type")
	private String courtType;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="court_type_id")
	private List<Litigation> litigation;

	public int getCourtTypeId() {
		return courtTypeId;
	}

	public void setCourtTypeId(int courtTypeId) {
		this.courtTypeId = courtTypeId;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}

	
}
