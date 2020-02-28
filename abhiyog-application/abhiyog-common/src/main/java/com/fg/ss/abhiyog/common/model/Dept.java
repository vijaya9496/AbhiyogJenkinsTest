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
@Table(name="dept")

public class Dept {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="deptoid")
	private int id;
	
	@Column(name="deptname")
	private String deptName;
	
	@Column(name="deptheadoid")
	private int deptHeadId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="deptoid")
	private List<CounterPartyDtls> counterPartyDtls;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="deptoid")
	private List<LawFirm> lawfirm;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="deptoid")
	private List<ShowCauseNotice> showCauseNotice;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="deptoid")
	private List<Litigation> litigation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptHeadId() {
		return deptHeadId;
	}

	public void setDeptHeadId(int deptHeadId) {
		this.deptHeadId = deptHeadId;
	}

	public List<CounterPartyDtls> getCounterPartyDtls() {
		return counterPartyDtls;
	}

	public void setCounterPartyDtls(List<CounterPartyDtls> counterPartyDtls) {
		this.counterPartyDtls = counterPartyDtls;
	}

	public List<LawFirm> getLawfirm() {
		return lawfirm;
	}

	public void setLawfirm(List<LawFirm> lawfirm) {
		this.lawfirm = lawfirm;
	}

	public List<ShowCauseNotice> getShowCauseNotice() {
		return showCauseNotice;
	}

	public void setShowCauseNotice(List<ShowCauseNotice> showCauseNotice) {
		this.showCauseNotice = showCauseNotice;
	}

	public List<Litigation> getLitigation() {
		return litigation;
	}

	public void setLitigation(List<Litigation> litigation) {
		this.litigation = litigation;
	}
	
	
}
