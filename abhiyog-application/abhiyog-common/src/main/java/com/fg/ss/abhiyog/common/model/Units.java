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
@Table(name = "units")

public class Units {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "unit_id")
	private int unitId;

	@Column(name = "unit_name")
	private String unitName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entity_id")
	private EntitySummary entitySummary;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regions_id")
	private Zone regions;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private List<UnitHeads> unitHeads;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private List<ShowCauseNotice> showCauseNotice;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private List<Litigation> litigation;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private List<LitigationUnits> litigationUnits;

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public EntitySummary getEntitySummary() {
		return entitySummary;
	}

	public void setEntitySummary(EntitySummary entitySummary) {
		this.entitySummary = entitySummary;
	}

	public Zone getRegions() {
		return regions;
	}

	public void setRegions(Zone regions) {
		this.regions = regions;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}

	public List<LitigationUnits> getLitigationUnits() {
		return litigationUnits;
	}

	public void setLitigationUnits(List<LitigationUnits> litigationUnits) {
		this.litigationUnits = litigationUnits;
	}

}
