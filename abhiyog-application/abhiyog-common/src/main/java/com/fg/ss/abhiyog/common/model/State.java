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
@Table(name = "state")
public class State {

	@Id
	@Column(name = "state_id")
	private int stateId;

	@Column(name = "state_name")
	private String stateName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="state_id")
	private List<City> city;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="state_id")
	private List<Litigation> litigation;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}

	
}
