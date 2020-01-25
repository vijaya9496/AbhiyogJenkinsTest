package com.fg.ss.abhiyog.common.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "litigationhistorytlog")
public class LitigationHistoryTLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "litigation_historyt_log_id")
	private int litigationHistoryTLogId;

	@Column(name = "activity_type")
	private String activityType;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "activity_description")
	private String activityDescription;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_log_id")
	private LtgnLitigationLog ltgnLitigationLog;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "updated_by_id")
	private User user;

	public int getLitigationHistoryTLogId() {
		return litigationHistoryTLogId;
	}

	public void setLitigationHistoryTLogId(int litigationHistoryTLogId) {
		this.litigationHistoryTLogId = litigationHistoryTLogId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public LtgnLitigationLog getLtgnLitigationLog() {
		return ltgnLitigationLog;
	}

	public void setLtgnLitigationLog(LtgnLitigationLog ltgnLitigationLog) {
		this.ltgnLitigationLog = ltgnLitigationLog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
