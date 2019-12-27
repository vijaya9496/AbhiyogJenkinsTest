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
@Table(name = "policestation")
public class PoliceStation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "police_station_id")
	private int policeStationId;

	@Column(name = "police_station")
	private String policeStation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "police_station_id")
	private List<Litigation> litigation;

	public int getPoliceStationId() {
		return policeStationId;
	}

	public void setPoliceStationId(int policeStationId) {
		this.policeStationId = policeStationId;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}

}
