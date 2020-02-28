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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "litigationdocs")
public class LitigationDocs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "litigationdocoid")
	private int litigationDocId;

	@Column(name = "doctitle")
	private String docTitle;

	@Column(name = "doccomment")
	private String docComment;

	@Column(name = "docname")
	private String docName;

	@Column(name = "docpath")
	private String docPath;

	@Column(name = "docsize")
	private long docSize;

	@Column(name = "uploadeddate")
	private LocalDateTime uploadedDate;

	@Column(name = "litigation_log_oid")
	private int litigationLogOid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "litigationoid")
	private Litigation litigation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uploadedbyoid")
	private User user;

	public int getLitigationDocId() {
		return litigationDocId;
	}

	public void setLitigationDocId(int litigationDocId) {
		this.litigationDocId = litigationDocId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocComment() {
		return docComment;
	}

	public void setDocComment(String docComment) {
		this.docComment = docComment;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public long getDocSize() {
		return docSize;
	}

	public void setDocSize(long l) {
		this.docSize = l;
	}

	public LocalDateTime getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(LocalDateTime uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public int getLitigationLogOid() {
		return litigationLogOid;
	}

	public void setLitigationLogOid(int litigationLogOid) {
		this.litigationLogOid = litigationLogOid;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
