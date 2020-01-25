package com.fg.ss.abhiyog.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stagemaster")
public class StageMaster {

	@Id
	@Column(name="stage_master_id")
	private int stageMasterId;
	
	@Column(name="stage")
	private String stage;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stage_master_id")
	private LtgnLitigationLog  ltgnLitigationLog;

	public int getStageMasterId() {
		return stageMasterId;
	}

	public void setStageMasterId(int stageMasterId) {
		this.stageMasterId = stageMasterId;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public LtgnLitigationLog getLtgnLitigationLog() {
		return ltgnLitigationLog;
	}

	public void setLtgnLitigationLog(LtgnLitigationLog ltgnLitigationLog) {
		this.ltgnLitigationLog = ltgnLitigationLog;
	}
	
	
	
}
