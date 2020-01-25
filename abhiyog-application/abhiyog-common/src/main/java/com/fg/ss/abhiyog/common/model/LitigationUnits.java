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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "litigationunits")
public class LitigationUnits {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "litigation_units_id")
	private int litigationUnitsId;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private Units units;

	@OneToOne(cascade = CascadeType.MERGE)
//	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private Litigation litigation;

	public int getLitigationUnitsId() {
		return litigationUnitsId;
	}

	public void setLitigationUnitsId(int litigationUnitsId) {
		this.litigationUnitsId = litigationUnitsId;
	}

	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

}
