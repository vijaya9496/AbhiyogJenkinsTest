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
@Table(name="courtcity")
public class CourtCity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "court_city_id")
	private int courtCityId;

	@Column(name = "court_city")
	private String courtCity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="court_city_id")
	private List<Litigation> litigation;

	public int getCourtCityId() {
		return courtCityId;
	}

	public void setCourtCityId(int courtCityId) {
		this.courtCityId = courtCityId;
	}

	public String getCourtCity() {
		return courtCity;
	}

	public void setCourtCity(String courtCity) {
		this.courtCity = courtCity;
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
