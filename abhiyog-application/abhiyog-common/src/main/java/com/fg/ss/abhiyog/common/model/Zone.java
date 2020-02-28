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
@Table(name="regions")

public class Zone {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="regionoid")
	private int zoneId;
	@Column(name="regionname")
	private String zoneName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="regionoid")
	private List<Units> units;

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public List<Units> getUnits() {
		return units;
	}

	public void setUnits(List<Units> units) {
		this.units = units;
	}
	
	
	
}
