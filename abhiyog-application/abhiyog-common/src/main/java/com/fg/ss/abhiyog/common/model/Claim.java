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
@Table(name = "claim")
public class Claim {

	@Id
	@Column(name = "claimoid")
	private int claimId;

	@Column(name = "claim")
	private String claim;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="claimoid")
	private List<Litigation> litigation;

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public String getClaim() {
		return claim;
	}

	public void setClaim(String claim) {
		this.claim = claim;
	}

}
