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
@Table(name="noticecategory")

public class NoticeCategory {

	@Id
	@Column(name="notice_category_id")
	private int noticeCategoryId;
	
	@Column(name="notice_category")
	private String noticeCategory;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="notice_category_id")
	private List<ShowCauseNotice> showCauseNotice;

	public int getNoticeCategoryId() {
		return noticeCategoryId;
	}

	public void setNoticeCategoryId(int noticeCategoryId) {
		this.noticeCategoryId = noticeCategoryId;
	}

	public String getNoticeCategory() {
		return noticeCategory;
	}

	public void setNoticeCategory(String noticeCategory) {
		this.noticeCategory = noticeCategory;
	}

	public List<ShowCauseNotice> getShowCauseNotice() {
		return showCauseNotice;
	}

	public void setShowCauseNotice(List<ShowCauseNotice> showCauseNotice) {
		this.showCauseNotice = showCauseNotice;
	}
	
	
}
