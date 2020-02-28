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
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cityoid")
	private int cityId;

	@Column(name = "cityname")
	private String cityName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateoid")
	private State state;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cityoid")
	private List<CourtCity> courtCity;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cityoid")
	private List<PoliceStation> policeStation;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<CourtCity> getCourtCity() {
		return courtCity;
	}

	public void setCourtCity(List<CourtCity> courtCity) {
		this.courtCity = courtCity;
	}

	public List<PoliceStation> getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(List<PoliceStation> policeStation) {
		this.policeStation = policeStation;
	}

	
}
