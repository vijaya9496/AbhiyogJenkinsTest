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
@Table(name = "format")

public class Format {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "formatoid")
	private int formatId;
	@Column(name = "format")
	private String format;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "formatoid")
	private List<ShowCauseNotice> showCauseNotice;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="formatoid")
	private List<Litigation> litigation;

	public int getFormatId() {
		return formatId;
	}

	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public List<ShowCauseNotice> getShowCauseNotice() {
		return showCauseNotice;
	}

	public void setShowCauseNotice(List<ShowCauseNotice> showCauseNotice) {
		this.showCauseNotice = showCauseNotice;
	}

}
