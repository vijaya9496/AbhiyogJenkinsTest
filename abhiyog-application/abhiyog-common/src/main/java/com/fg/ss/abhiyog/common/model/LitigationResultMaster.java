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
@Table(name = "litigationresultmaster")
public class LitigationResultMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "resultoid")
	private int resultId;

	@Column(name = "result")
	private String result;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "resultoid")
	private List<Litigation> litigation;

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}

}
