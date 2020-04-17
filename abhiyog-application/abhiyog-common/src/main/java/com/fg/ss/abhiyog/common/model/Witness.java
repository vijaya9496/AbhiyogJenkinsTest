package com.fg.ss.abhiyog.common.model;

import javax.persistence.CascadeType;
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
@Table(name = "witness")
public class Witness {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "witnessoid")
	private int witnessId;

	@Column(name = "witness")
	private String witness;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "litigationoid")
	private Litigation litigation;

	public int getWitnessId() {
		return witnessId;
	}

	public void setWitnessId(int witnessId) {
		this.witnessId = witnessId;
	}

	public String getWitness() {
		return witness;
	}

	public void setWitness(String witness) {
		this.witness = witness;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

}
