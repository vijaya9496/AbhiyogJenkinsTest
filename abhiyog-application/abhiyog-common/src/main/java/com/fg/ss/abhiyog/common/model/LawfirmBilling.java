package com.fg.ss.abhiyog.common.model;

import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "lawfirm_billing")
public class LawfirmBilling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lawfirm_billing_id")
	private int lawfirmBillingId;

	@Column(name = "litigation_log_id")
	private int litigationLogId;

	@Column(name = "billing_amount")
	private float billingAmount;

	@Column(name = "billing_date")
	private Date billingDate;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "doc_name")
	private String docName;

	@Column(name = "doc_path")
	private String docPath;

	@Column(name = "doc_size")
	private long docSize;

	@Column(name = "file_data")
	private byte[] fileData;

	@Column(name = "file_extension")
	private String fileExtension;

	@Column(name = "remark")
	private String remark;

	@Column(name = "paid_unpaid")
	private String paidUnpaid;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "litigation_oid")
	private Litigation litigation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_type_id")
	private BillingType billingType;

	public int getLawfirmBillingId() {
		return lawfirmBillingId;
	}

	public void setLawfirmBillingId(int lawfirmBillingId) {
		this.lawfirmBillingId = lawfirmBillingId;
	}

	public int getLitigationLogId() {
		return litigationLogId;
	}

	public void setLitigationLogId(int litigationLogId) {
		this.litigationLogId = litigationLogId;
	}

	public float getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(float billingAmount) {
		this.billingAmount = billingAmount;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPaidUnpaid() {
		return paidUnpaid;
	}

	public void setPaidUnpaid(String paidUnpaid) {
		this.paidUnpaid = paidUnpaid;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

	public BillingType getBillingType() {
		return billingType;
	}

	public void setBillingType(BillingType billingType) {
		this.billingType = billingType;
	}

}
