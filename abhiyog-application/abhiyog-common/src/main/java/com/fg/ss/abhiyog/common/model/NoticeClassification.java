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
@Table(name="noticeclassification")

public class NoticeClassification {

	@Id
	@Column(name="noticeclassificationoid")
	private int noticeClassificationId;
	
	@Column(name="noticeclassification")
	private String noticeClassification;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="noticeclassificationoid")
	private List<ShowCauseNotice> showCauseNotice;

	public int getNoticeClassificationId() {
		return noticeClassificationId;
	}

	public void setNoticeClassificationId(int noticeClassificationId) {
		this.noticeClassificationId = noticeClassificationId;
	}

	public String getNoticeClassification() {
		return noticeClassification;
	}

	public void setNoticeClassification(String noticeClassification) {
		this.noticeClassification = noticeClassification;
	}

	public List<ShowCauseNotice> getShowCauseNotice() {
		return showCauseNotice;
	}

	public void setShowCauseNotice(List<ShowCauseNotice> showCauseNotice) {
		this.showCauseNotice = showCauseNotice;
	}
	
	
}
