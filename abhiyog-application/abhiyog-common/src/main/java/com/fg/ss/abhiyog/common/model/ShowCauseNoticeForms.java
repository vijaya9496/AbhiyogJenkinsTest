package com.fg.ss.abhiyog.common.model;

import java.time.LocalDateTime;

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
@Table(name = "showcausenoticeforms")

public class ShowCauseNoticeForms {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "showcause_notice_forms_id")
	private int showCauseNoticeFormsId;

	@Column(name = "input_doc_name")
	private String inputDocName;

	@Column(name = "input_doc_path")
	private String inputDocPath;

	@Column(name = "comments")
	private String comments;

	@Column(name = "uploaded_date")
	private LocalDateTime uploadedDate;

	@Column(name = "doc_size")
	private long docSize;

	@Column(name = "file_data")
	private byte[] fileData;

	@Column(name = "file_extension")
	private String fileExtension;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showcause_notice_id")
	private ShowCauseNotice showCauseNotice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uploaded_by_id")
	private User userDtls;

	public int getShowCauseNoticeFormsId() {
		return showCauseNoticeFormsId;
	}

	public void setShowCauseNoticeFormsId(int showCauseNoticeFormsId) {
		this.showCauseNoticeFormsId = showCauseNoticeFormsId;
	}

	public String getInputDocName() {
		return inputDocName;
	}

	public void setInputDocName(String inputDocName) {
		this.inputDocName = inputDocName;
	}

	public String getInputDocPath() {
		return inputDocPath;
	}

	public void setInputDocPath(String inputDocPath) {
		this.inputDocPath = inputDocPath;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDateTime getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(LocalDateTime uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public long getDocSize() {
		return docSize;
	}

	public void setDocSize(long docSize) {
		this.docSize = docSize;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public ShowCauseNotice getShowCauseNotice() {
		return showCauseNotice;
	}

	public void setShowCauseNotice(ShowCauseNotice showCauseNotice) {
		this.showCauseNotice = showCauseNotice;
	}

	public User getUserDtls() {
		return userDtls;
	}

	public void setUserDtls(User userDtls) {
		this.userDtls = userDtls;
	}

}
