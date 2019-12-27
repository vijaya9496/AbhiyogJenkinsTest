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
@Table(name = "litigationrepresentativemaster")
public class LtgnRepresentativeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "representative_id")
	private int representativeId;

	@Column(name = "representative_name")
	private String representativeName;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "representative_id")
	private List<Litigation> litigation;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="representative_id")
	private List<LitigationMatterByAgainst> litigationMatterByAgainst;
	
	public int getRepresentativeId() {
		return representativeId;
	}

	public void setRepresentativeId(int representativeId) {
		this.representativeId = representativeId;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}

}
