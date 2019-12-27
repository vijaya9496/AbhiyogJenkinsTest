package com.fg.ss.abhiyog.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="unitheads")

public class UnitHeads {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="unit_head_id")
	private int unitHeadID;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unit_id")
	private Units units;

	public int getUnitHeadID() {
		return unitHeadID;
	}

	public void setUnitHeadID(int unitHeadID) {
		this.unitHeadID = unitHeadID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}
	
	
	
}
