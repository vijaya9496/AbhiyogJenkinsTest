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
@Table(name = "litigation_matterbyagainst")
public class LitigationMatterByAgainst {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "litigation_matter_by_against_id")
	private int litigationMatterByAgainstId;

	@ManyToOne(cascade=CascadeType.PERSIST)
//	fetch = FetchType.LAZY
	@JoinColumn(name = "litigation_oid")//litigation_oid
	private Litigation litigation;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "representative_id")//matter_by_against_id
	private LtgnRepresentativeMaster ltgnRepresentativeMaster;

	public int getLitigationMatterByAgainstId() {
		return litigationMatterByAgainstId;
	}

	public void setLitigationMatterByAgainstId(int litigationMatterByAgainstId) {
		this.litigationMatterByAgainstId = litigationMatterByAgainstId;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

	public LtgnRepresentativeMaster getLtgnRepresentativeMaster() {
		return ltgnRepresentativeMaster;
	}

	public void setLtgnRepresentativeMaster(LtgnRepresentativeMaster ltgnRepresentativeMaster) {
		this.ltgnRepresentativeMaster = ltgnRepresentativeMaster;
	}

}
